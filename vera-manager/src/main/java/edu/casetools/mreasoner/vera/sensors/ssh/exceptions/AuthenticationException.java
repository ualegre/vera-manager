package edu.casetools.mreasoner.vera.sensors.ssh.exceptions;

public class AuthenticationException extends Exception {
	
	private static final long serialVersionUID = -6750908083450498365L;

	public AuthenticationException (){
		super("Authentication failed!");
	}
	
	public AuthenticationException (String message){
		super("Authentication failed: "+message);
	}

}
