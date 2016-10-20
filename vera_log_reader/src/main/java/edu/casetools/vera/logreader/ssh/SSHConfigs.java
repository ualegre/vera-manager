package edu.casetools.vera.logreader.ssh;

public class SSHConfigs {

	private String  hostname;
	private String  username;
	private String  password;
	private int		port;
	private boolean silence;

	public SSHConfigs(){
		this.port = 22;
	}
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
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
	
}
