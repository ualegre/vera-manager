package edu.casetools.mreasoner.vera.sensors.ssh.configs;

public class SSHConfigs {

	private String  hostname;
	private String	port;
	private String  username;
	private String  password;
	private boolean silence;

	public SSHConfigs() {
		port = "22";
	}
	
	public String getPortAsString() {
		return port;
	}
	
	public int getPort() {
		return Integer.parseInt(port);
	}
	
	public void setPort(String port ) {
		this.port = port;
	}
	
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isSilence(){
		return silence;
	}

	public void setSilence(boolean silence) {
		this.silence = silence;
		
	}
	
	public String parseConfigs(){
		String result = "";
		
		//SSH Info

		result = result+"<HOSTNAME> \n";
		result = result+getHostname()+"\n";
		result = result+"</HOSTNAME> \n";
		
		result = result+"<PORT> \n";
		result = result+getPort()+"\n";
		result = result+"</PORT> \n";
		
		result = result+"<USERNAME> \n";
		result = result+getUsername()+"\n";
		result = result+"</USERNAME> \n";
		
		result = result+"<PASSWORD> \n";
		result = result+getPassword()+"\n";
		result = result+"</PASSWORD> \n";
		
		result = result+"<SILENCE> \n";
		result = result+isSilence()+"\n";
		result = result+"</SILENCE> \n";
		
		return result;	
	}
	
}
