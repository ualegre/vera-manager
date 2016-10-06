package edu.casetools.vera.logreader.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;

import edu.casetools.vera.logreader.VeraLogDataManager;
import edu.casetools.vera.logreader.ssh.exceptions.AuthenticationException;
import edu.casetools.vera.logreader.ssh.exceptions.PartialAuthenticationException;
import edu.casetools.vera.logreader.ssh.exceptions.TerminalAllocationException;



public class SSHManager extends Thread{
  
	private static final Logger LOGGER = Logger.getLogger( SSHManager.class.getName() );
	private SSHConfigs sshConfigs;
	private SshClient ssh;
	private SessionChannelClient session;
	private BufferedReader br;
	private VeraLogDataManager dataManager;
	
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

  }
	
  public int initialize() throws IOException {

	      ssh = new SshClient();
	      ssh.connect(sshConfigs.getHostname());
	      
	      int result = authenticate();
     
	      if ( result == AuthenticationProtocolState.COMPLETE) 
	    	result = openAndReadLog(result);
	       
	      else printSSHConfigsAuthenticationError();
  		
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
	  } 
         
	return finalResult;
}

private void checkAuthenticationProtocolState(int result) throws AuthenticationException {
	if (result == AuthenticationProtocolState.PARTIAL){
		throw new PartialAuthenticationException();
	}
    if (result == AuthenticationProtocolState.FAILED){
    	throw new AuthenticationException();
    }
}

private void openSession() throws IOException, TerminalAllocationException {
	session = ssh.openSessionChannel();	
	if(!session.requestPseudoTerminal("vt100", 80, 24, 0, 0, "")){
	  throw new TerminalAllocationException();
	}
}

private int readLog(int result) throws IOException {
      int finalResult = result;
    
      if(session.startShell()) {
		startReadingLog();
      } else{  
       LOGGER.log( Level.SEVERE, "Failed to start the users shell");
       finalResult = 0;     
      }
	return finalResult;
}

private int authenticate() throws IOException {
	PasswordAuthenticationClient pwAuth = new PasswordAuthenticationClient();
	
    pwAuth.setUsername(sshConfigs.getUsername());
    pwAuth.setPassword(sshConfigs.getPassword());
    return ssh.authenticate(pwAuth);

}

private void startReadingLog() throws IOException {
      OutputStream out = session.getOutputStream();

       String cmd = "tail -f  /var/log/cmh/LuaUPnP.log\n";
 
       out.write(cmd.getBytes()); 
       
       br = new BufferedReader(new InputStreamReader(session.getInputStream(),"US-ASCII"));
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

    	if( connectionStatus == AuthenticationProtocolState.COMPLETE){ 
    		main();
    		Thread.sleep(100);
    	}
    	initFinished = true;
	     if ( connectionStatus == AuthenticationProtocolState.COMPLETE) {
	         while (running)
	         {
	        	 	main();
	
	         }
	      }
	         session.close();
	      
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
		} catch (IOException e) {
	       LOGGER.log( Level.SEVERE, "SSH I/O Exception",e);
		} catch (InterruptedException e) {
	       LOGGER.log( Level.SEVERE, "SSH Interrupted Exception",e);
	       Thread.currentThread().interrupt();
		}
		super.interrupt();
		finalFinished = true;
	}
  
	private void main(){
	    
		try {
			  String line = br.readLine();
			  if(!sshConfigs.isSilence()){
			    LOGGER.log( Level.SEVERE, line);
			  }
			  dataManager.readAndStoreLine(line);
			  
		} catch (IOException e) {
          LOGGER.log( Level.SEVERE, "SSH I/O Exception",e);
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
		return connectionStatus == AuthenticationProtocolState.COMPLETE;
	}
	
}