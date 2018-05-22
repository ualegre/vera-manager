package edu.casetools.icase.mreasoner.vera.actuators;

import java.util.Vector;

import edu.casetools.icase.mreasoner.vera.actuators.data.Action;
import edu.casetools.icase.mreasoner.vera.actuators.device.Actuator;

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
				Action action = readAction(actuators.get(i).getStateName());				
				actuators.get(i).performAction(action);
			}
		}
	}
	
	private Action readAction(String stateName){
		Action action  = new Action();

		String device  = getDevice(stateName);
		boolean status = getStatus(stateName);
		
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
