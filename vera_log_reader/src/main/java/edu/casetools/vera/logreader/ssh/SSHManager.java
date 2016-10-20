package edu.casetools.vera.logreader.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.NoRouteToHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sshtools.net.SocketTransport;
import com.sshtools.ssh.ChannelOpenException;
import com.sshtools.ssh.HostKeyVerification;
import com.sshtools.ssh.SshAuthentication;
import com.sshtools.ssh.SshClient;
import com.sshtools.ssh.SshConnector;
import com.sshtools.ssh.SshException;
import com.sshtools.ssh.SshSession;
import com.sshtools.ssh.components.SshPublicKey;
import com.sshtools.ssh2.Ssh2PasswordAuthentication;

import edu.casetools.vera.logreader.VeraLogDataManager;
import edu.casetools.vera.logreader.ssh.exceptions.AuthenticationException;
import edu.casetools.vera.logreader.ssh.exceptions.PartialAuthenticationException;
import edu.casetools.vera.logreader.ssh.exceptions.TerminalAllocationException;

public class SSHManager extends Thread {
	private static final Logger LOGGER = Logger.getLogger( SSHManager.class.getName() );
	private SSHConfigs sshConfigs;
	private SshClient ssh;
	private SshConnector con;
	private SshSession session;
	private BufferedReader br;
	private VeraLogDataManager dataManager;

	private String command;
	boolean running;
	boolean initFinished;
	boolean finalFinished;
	int connectionStatus;


	public SSHManager(VeraLogDataManager dataManager, SSHConfigs sshConfigs){
		this.sshConfigs = sshConfigs;
		this.dataManager = dataManager;
		initFinished = false;
		finalFinished = false;
		running = true;
		connectionStatus = 0;
		command = "";
	}

	public void setCommand(String cmd) {
		command = cmd;
	}

	public int initialize() throws IOException, SshException {
		con = SshConnector.createInstance();
		HostKeyVerification hkv = new HostKeyVerification() {
			public boolean verifyHost(String hostname, SshPublicKey key) {
				return true;
			}
		};
		con.getContext().setHostKeyVerification(hkv);

		try {			
			ssh = con.connect(new SocketTransport(sshConfigs.getHostname(), sshConfigs.getPort() ), sshConfigs.getHostname(), true);
		}
		catch(NoRouteToHostException nrhe) {
			LOGGER.log(Level.SEVERE, "No route to host " + sshConfigs.getHostname() + ":" + sshConfigs.getPort() );
			return SshAuthentication.FAILED;
		}

		int result = authenticate();
		if( result==SshAuthentication.COMPLETE )
			result = openAndReadLog(result);
		else {
			printSSHConfigsAuthenticationError();
		}
		return result;
	}

	private int openAndReadLog(int result) {
		int finalResult = result; 
		try{
			openSession();	
			finalResult = readLog(finalResult);	  
			checkAuthenticationProtocolState(result);
		} catch(IOException e){
			LOGGER.log( Level.SEVERE, "", e );
			finalResult = 0;
		} catch(AuthenticationException e){
			LOGGER.log( Level.SEVERE, "", e );
			finalResult = 0;
		} catch (TerminalAllocationException e) {
			LOGGER.log( Level.SEVERE, "", e );
			finalResult = 0;
		} catch (SshException e) {
			e.printStackTrace();
		} catch (ChannelOpenException e) {
			e.printStackTrace();
		}

		return finalResult;
	}

	private void checkAuthenticationProtocolState(int result) throws AuthenticationException {
		if (result == SshAuthentication.FURTHER_AUTHENTICATION_REQUIRED ){
			throw new PartialAuthenticationException();
		}
		if (result == SshAuthentication.FAILED){
			throw new AuthenticationException();
		}
	}

	private void openSession() throws TerminalAllocationException, SshException, ChannelOpenException {
		session = ssh.openSessionChannel();	
		if(!session.requestPseudoTerminal("vt100", 80, 24, 0, 0)){
			throw new TerminalAllocationException();
		}
	}

	private int readLog(int result) throws IOException, SshException {
		int finalResult = result;

		if(session.startShell()) {
			startReadingLog();
		}
		else{  
			LOGGER.log( Level.SEVERE, "Failed to start the users shell");
			finalResult = 0;     
		}
		return finalResult;
	}

	private int authenticate() throws SshException {
		Ssh2PasswordAuthentication pwd = new Ssh2PasswordAuthentication();

		pwd.setUsername( sshConfigs.getUsername() );
		pwd.setPassword( sshConfigs.getPassword() );

		return ssh.authenticate(pwd);
	}

	private void startReadingLog(String cmd) throws IOException {
		OutputStream out = session.getOutputStream();

		out.write(cmd.getBytes());

		br = new BufferedReader(new InputStreamReader(session.getInputStream(), "US-ASCII"));		
	}

	private void startReadingLog() throws IOException {
		if( command.equals("") )
			command = "tail -f  /var/log/cmh/LuaUPnP.log\n";
		startReadingLog(command);
	}

	private void printSSHConfigsAuthenticationError() {
		LOGGER.log( Level.SEVERE, 
				"AUTHENTICATION ERROR FOR: HOSTNAME"+sshConfigs.getHostname()+
				" USER: "+sshConfigs.getUsername());
	}

	@Override	
	public void run() {
		try {
			connectionStatus = initialize();

			if( connectionStatus == SshAuthentication.COMPLETE){
				LOGGER.log(Level.INFO, "Successfully connected");
				main();
				Thread.sleep(100);
			}
			initFinished = true;
			if ( connectionStatus == SshAuthentication.COMPLETE) {
				while ( running ) {
					if( Thread.currentThread().isInterrupted() ) {
						LOGGER.log(Level.WARNING, "interrupted");
						break;
					}
					if( !main() )
						break;
				}
			}

		}
		catch (Exception e) {
			LOGGER.log( Level.SEVERE, "", e);
		}
	}

	@Override
	public void interrupt(){
		try {
			LOGGER.log( Level.FINEST, "Interrupting SSH Connection");			
			ssh.disconnect();
			session.close();
			Thread.sleep(50);
			running = false;
		} catch (InterruptedException e) {
			LOGGER.log( Level.SEVERE, "SSH Interrupted Exception",e);
			Thread.currentThread().interrupt();
		}
		super.interrupt();
		finalFinished = true;
	}

	private boolean main(){

		try {
			String line = br.readLine();
			if(!sshConfigs.isSilence()){
				LOGGER.log( Level.SEVERE, line);
			}
			dataManager.readAndStoreLine(line);
			return true;

		} catch (IOException e) {
			LOGGER.log( Level.SEVERE, "SSH I/O Exception",e);
			return false;
		}

	}

	public boolean isInitializationFinished(){
		return initFinished;
	}

	public boolean isFinalizationFinished(){
		return finalFinished;
	}

	public void terminate(){
		running = false;
	}

	public boolean checkConnection() {
		return connectionStatus == SshAuthentication.COMPLETE;
	}

}
