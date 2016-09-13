package edu.casetools.vera.logreader;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.casetools.vera.logreader.VeraLogDataManager;
import edu.casetools.vera.logreader.ssh.SSHConfigs;
import edu.casetools.vera.logreader.ssh.SSHManager;

public class VeraLogReader {
  
    private static final Logger LOGGER = Logger.getLogger( VeraLogReader.class.getName() );
	protected SSHConfigs configs;
	protected SSHManager ssh;
	protected VeraLogDataManager dataManager;
	
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
            LOGGER.log( Level.SEVERE, "", e );
            Thread.currentThread().interrupt();
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
