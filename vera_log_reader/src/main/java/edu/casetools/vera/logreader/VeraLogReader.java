package edu.casetools.vera.logreader;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.casetools.vera.logreader.VeraLogDataManager;
import edu.casetools.vera.logreader.ssh.SSHConfigs;
import edu.casetools.vera.logreader.ssh.SSHManager;

public class VeraLogReader {
  
    private static final Logger LOGGER = Logger.getLogger( VeraLogReader.class.getName() );
	protected SSHConfigs sshConfigs;
	protected SSHManager sshManager;
	protected VeraLogDataManager dataManager;
	
	public VeraLogReader(){
		
	}
	
	public VeraLogReader(VeraLogDataManager dataManager,SSHConfigs configs){
		sshManager = new SSHManager(dataManager, configs);
		this.sshConfigs = configs;
		this.dataManager = dataManager;
	}
	
	public void initialize (VeraLogDataManager dataManager,SSHConfigs sshConfigs){
		sshManager = new SSHManager(dataManager, sshConfigs);
		this.sshConfigs = sshConfigs;
		this.dataManager = dataManager;
	}
	
	
	public void start(){
		sshManager.start();
	}

	
	public void stop() {

		try {
			sshManager.interrupt();
			sshManager.terminate();
			sshManager.join();
		} catch (InterruptedException e) {
            LOGGER.log( Level.SEVERE, "", e );
            Thread.currentThread().interrupt();
		}
	}
	
	public SSHConfigs getConfigs() {
		return sshConfigs;
	}

	public void setConfigs(SSHConfigs configs) {
		this.sshConfigs = configs;
	}
	
	public SSHManager getSshClient() {
		return sshManager;
	}

	public void setSshClient(SSHManager ssh) {
		this.sshManager = ssh;
	}
	
	public VeraLogDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(VeraLogDataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	public boolean checkConnection(){
		return sshManager.checkConnection();
	}
}
