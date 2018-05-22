package edu.casetools.icase.mreasoner.vera.actuators.data;

public class VeraActuatorConfigs {
	
	 private String state;
	 private String ip;
	 private String port;
	 
	 public VeraActuatorConfigs(String state, String ip, String port){
		 this.state = state;
	 }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	 
	 
}
