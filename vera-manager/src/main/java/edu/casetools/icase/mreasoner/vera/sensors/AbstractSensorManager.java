package edu.casetools.icase.mreasoner.vera.sensors;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.casetools.icase.mreasoner.vera.sensors.core.VeraLogDataObserver;
import edu.casetools.icase.mreasoner.vera.sensors.ssh.SSHManager;

public abstract class AbstractSensorManager {
	private static final Logger LOGGER = Logger.getLogger( AbstractSensorManager.class.getName() );
	private SSHManager sshManager;
	private Thread readerThread;
	
	protected AbstractSensorManager(String configsFileName) {
		sshManager = SSHManager.getInstance(configsFileName);
		sshManager.setCommand("tail -f  /var/log/cmh/LuaUPnP.log\n");
	}

	public void registerObserver(VeraLogDataObserver dataManager) {
		sshManager.registerObserver(dataManager);
	}

	public void start(){
		if( readerThread!=null && (readerThread.isAlive() || readerThread.isInterrupted()) )
			return;
		
		readerThread = new Thread(sshManager);
		readerThread.start();
	}
	public void initialize (SSHManager dataManager){
		this.sshManager = dataManager;
	}

	public void stop() {
		try {
			if( readerThread.isInterrupted() )
				return;
			
			sshManager.interrupt();
			readerThread.interrupt();
			readerThread.join();
		} catch (InterruptedException e) {
			LOGGER.log( Level.SEVERE, "", e );
			Thread.currentThread().interrupt();
		}
	}

	public SSHManager getSshClient() {
		return sshManager;
	}

	public void setSshClient(SSHManager ssh) {
		this.sshManager = ssh;
	}
	public boolean checkConnection(){
		return sshManager.checkConnection();
	}
}
