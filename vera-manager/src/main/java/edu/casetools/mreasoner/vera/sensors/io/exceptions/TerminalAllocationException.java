package edu.casetools.mreasoner.vera.sensors.io.exceptions;

public class TerminalAllocationException extends Exception{

	private static final long serialVersionUID = -8936320663481794542L;

	public TerminalAllocationException(){
		super("Failed to allocate a pseudo terminal");
	}

}
