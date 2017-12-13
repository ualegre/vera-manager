package edu.casetools.mreasoner.vera.sensors.ssh.exceptions;

public class PartialAuthenticationException extends AuthenticationException {
	
	private static final long serialVersionUID = -3518079639471857189L;

	public PartialAuthenticationException (){
		super("Further authentication requried.");
	}

}
