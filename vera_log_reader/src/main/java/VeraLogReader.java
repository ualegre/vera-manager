import edu.casetools.vera.logreader.DataManager;
import edu.casetools.vera.logreader.ssh.SSHConfigs;
import edu.casetools.vera.logreader.ssh.SSHManager;

public class VeraLogReader {
	SSHConfigs configs;
	SSHManager ssh;
	DataManager dataManager;
	
	public VeraLogReader(DataManager dataManager,SSHConfigs configs){
		ssh = new SSHManager(dataManager, configs);
		this.configs = configs;
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
	
}
