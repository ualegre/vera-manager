package edu.casetools.mreasoner.vera.actuators;

import java.util.Vector;

import edu.casetools.mreasoner.vera.actuators.data.Action;
import edu.casetools.mreasoner.vera.actuators.data.ActuatorConfigs;
import edu.casetools.mreasoner.vera.actuators.device.Actuator;



public abstract class AbstractActuatorManager extends Thread{

	private boolean running;
	protected Vector<Actuator> actuators;
	
	public AbstractActuatorManager(Vector<Actuator> actuators){	
		this.actuators = actuators;
		running = true;
		
	}

	public void run(){
		while (running)
		{
			for(int i=0;i<actuators.size();i++){
				Action action = readAction(actuators.get(i).getConfigs());				
				actuators.get(i).performAction(action);
			}
		}
	}
	
	private Action readAction(ActuatorConfigs actuatorConfigs){
		Action action  = new Action();
		String state   = actuatorConfigs.getState();

		String device  = getDevice(state);
		boolean status = getStatus(state);
		
		if(device != null){
			action.setDevice(device);
			action.setValue(status);
		}
		
		return action;
	}

	public void terminate(){
		running = false;
	}
	
	protected abstract String getDevice(String state);
	protected abstract boolean getStatus(String state); 
	
}
