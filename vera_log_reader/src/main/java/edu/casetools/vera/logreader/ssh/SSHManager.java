package edu.casetools.vera.logreader.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;

import edu.casetools.vera.logreader.DataManager;
import edu.casetools.vera.logreader.ssh.exceptions.AuthenticationException;
import edu.casetools.vera.logreader.ssh.exceptions.PartialAuthenticationException;
import edu.casetools.vera.logreader.ssh.exceptions.TerminalAllocationException;



public class SSHManager extends Thread{
	
	SSHConfigs sshConfigs;
	SshClient ssh;
	SessionChannelClient session;
	BufferedReader br;
	DataManager dataManager;
	
	boolean running;
	boolean initFinished;
	boolean finalFinished;
	int connectionStatus;
	
	
   public SSHManager(DataManager dataManager, SSHConfigs sshConfigs){
	   
		this.sshConfigs = sshConfigs;
		this.dataManager = dataManager;
	    initFinished = false;
		finalFinished = false;
		running = true;
		connectionStatus = 0;

  }
	
  public int initialize() throws IOException, TerminalAllocationException, AuthenticationException{

	      ssh = new SshClient();
	      ssh.connect(sshConfigs.getHostname());
	      
	      int result = authenticate();
     
	      if ( result == AuthenticationProtocolState.COMPLETE) 
	    	result = openAndReadLog(result);
	       
	      else printSSHConfigsAuthenticationError();
  		
	        return result;
  }

private int openAndReadLog(int result) {
	  try{
		  openSession();	
		  result = readLog(result);	  
	      checkAuthenticationProtocolState(result);
	  } catch(IOException e){
		  e.printStackTrace();
	  } catch(AuthenticationException e){
		  e.printStackTrace();
	  } catch (TerminalAllocationException e) {
		e.printStackTrace();
	  } 
         
	return result;
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
	if(!session.requestPseudoTerminal("vt100", 80, 24, 0, 0, "")) throw new TerminalAllocationException();
}

private int readLog(int result) throws IOException {
	if(session.startShell()) {
		startReadingLog();
    } else{
	    System.out.println("Failed to start the users shell");
	 	result = 0;
    }
	return result;
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
		System.out.println("AUTHENTICATION ERROR FOR:");
		System.out.println("HOSTNAME: "+sshConfigs.getHostname());
		System.out.println("USER: "+sshConfigs.getUsername());
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
      e.printStackTrace();
    }
  }
  
   @Override
   public void interrupt(){
		try {
			System.out.println("\n\n Disconnecting...");
			ssh.disconnect();
			session.close();
			Thread.sleep(50);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.interrupt();
		finalFinished = true;
	}
  
	private void main(){
	    
		try {
			  String line = br.readLine();
			  if(!sshConfigs.isSilence()) System.out.println(line);
			  dataManager.readAndStoreLine(line);
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if( connectionStatus == AuthenticationProtocolState.COMPLETE)
		return true;
		else return false;
	}
	
}