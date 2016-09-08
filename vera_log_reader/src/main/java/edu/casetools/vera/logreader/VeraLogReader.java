package edu.casetools.vera.logreader;
import edu.casetools.vera.logreader.VeraLogDataManager;
import edu.casetools.vera.logreader.ssh.SSHConfigs;
import edu.casetools.vera.logreader.ssh.SSHManager;

public class VeraLogReader {
	private SSHConfigs configs;
	private SSHManager ssh;
	private VeraLogDataManager dataManager;
	
	public VeraLogReader(){
		
	}
	
	public VeraLogReader(VeraLogDataManager dataManager,SSHConfigs configs){
		ssh = new SSHManager(dataManager, configs);
		this.configs = configs;
		this.dataManager = dataManager;
	}
	
	public void initialize (VeraLogDataManager dataManager,SSHConfigs configs){
		ssh = new SSHManager(dataManager, configs);
		this.configs = configs;
		this.dataManager = dataManager;
	}
	
	
	public void start(){
		ssh.start();
	}

	
	public void stop() {

		try {
			ssh.interrupt();
			ssh.terminate();
			ssh.join();
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
	
	public SSHManager getSshClient() {
		return ssh;
	}

	public void setSshClient(SSHManager ssh) {
		this.ssh = ssh;
	}
	
	public VeraLogDataManager getDataManager() {
		return dataManager;
	}


	public void setDataManager(VeraLogDataManager dataManager) {
		this.dataManager = dataManager;
	}
}
