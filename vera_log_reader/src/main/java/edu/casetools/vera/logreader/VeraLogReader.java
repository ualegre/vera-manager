package edu.casetools.vera.logreader;

import edu.casetools.vera.logreader.ssh.SSHConfigs;
import edu.casetools.vera.logreader.ssh.SSHConnection;

public abstract class VeraLogReader {

	SSHConnection          sshClient;
	SSHConfigs 			   configs;

	public VeraLogReader(){
		
	}
	
	public VeraLogReader(SSHConfigs configs){
		this.configs = configs;
	}
	
	public void start(){
		sshClient.start();
	}

	
	public void stop() {

		try {
			sshClient.interrupt();
			sshClient.terminate();
			sshClient.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public SSHConfigs getConfigs() {
		return configs;
	}

	public void setConfigs(SSHConfigs configs) {
		this.configs = configs;
	}
	
	public SSHConnection getSshClient() {
		return sshClient;
	}

	public void setSshClient(SSHConnection sshClient) {
		this.sshClient = sshClient;
	}

}
