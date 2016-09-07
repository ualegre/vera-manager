package edu.casetools.vera.logreader.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;

import edu.casetools.vera.logreader.data.VeraData;
import edu.casetools.vera.logreader.interpreters.VeraInterpreter;



public abstract class SSHConnection extends Thread{
	SSHConfigs sshConfigs;
	SshClient ssh;
	SessionChannelClient session;
	boolean running;
	boolean initFinished;
	boolean finalFinished;
	BufferedReader br;
	int connectionStatus;
	
   public SSHConnection(SSHConfigs sshConfigs){
		initFinished = false;
		finalFinished = false;
		this.sshConfigs = sshConfigs;
		running = true;
		connectionStatus = 0;
  }
	
  public int initialize() throws IOException{

	      ssh = new SshClient();

	      ssh.connect(sshConfigs.getHostname());
	      
	      PasswordAuthenticationClient pwAuth = new PasswordAuthenticationClient();
	      pwAuth.setUsername(sshConfigs.getUsername());
	      pwAuth.setPassword(sshConfigs.getPassword());

	      int result = ssh.authenticate(pwAuth);

	      
	      if ( result == AuthenticationProtocolState.COMPLETE) {
	    	System.out.println("\n***********CORRECT AUTHENTICATION*************");
	        session = ssh.openSessionChannel();
	        
	        if(!session.requestPseudoTerminal("vt100", 80, 24, 0, 0, ""))
	          System.out.println("Failed to allocate a pseudo terminal");
	        if(session.startShell()) {
	        	startReadingLog();
	       } else{
	            System.out.println("Failed to start the users shell");
	         	result = 0;
	       }
	       if (result == AuthenticationProtocolState.PARTIAL) {
		             System.out.println("Further authentication requried!");
		   }
           if (result == AuthenticationProtocolState.FAILED) {
             System.out.println("Authentication failed!");
           }
	       
  		}else {
  			printSSHConfigs();
  		}
	        return result;
  }

private void startReadingLog() throws IOException, UnsupportedEncodingException {
	System.out.println("\n**********SHELL STARTED*********************");
        OutputStream out = session.getOutputStream();

       String cmd = "tail -f  /var/log/cmh/LuaUPnP.log\n";
 
       out.write(cmd.getBytes()); 
       
       br = new BufferedReader(new InputStreamReader(session.getInputStream(),"US-ASCII"));
}

private void printSSHConfigs() {
	System.out.println("AUTHENTICATION ERROR FOR:");
	System.out.println("HOSTNAME: "+sshConfigs.getHostname());
	System.out.println("USER: "+sshConfigs.getUsername());
}
	
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
	  	VeraData data = null;
	  	VeraInterpreter interpreter = new VeraInterpreter();
	    String line;
	    
		try {
			  line = br.readLine();
	       
		      data = readLine(data, interpreter, line);
		      if(data != null) storeData(data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private VeraData readLine(VeraData data, VeraInterpreter interpreter, String line) {
		if (line != null){
		 	 data = interpreter.interpret(line);
		 	 if(!sshConfigs.isSilence())System.out.println(line);
		  }
		return data;
	}

	private void storeData(VeraData data) {
		switch(data.getId()){
		  case 6:
			  storeVeraVariable(data);
			  break;
		  case 7:
			  storeVeraEvent(data);
			  break;    	 
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
	
	protected abstract void storeVeraEvent(VeraData data);

	protected abstract void storeVeraVariable(VeraData data);
}