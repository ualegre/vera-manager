package edu.casetools.mreasoner.vera.actuators.device;

import edu.casetools.mreasoner.vera.actuators.data.Action;
import edu.casetools.mreasoner.vera.actuators.data.ActuatorConfigs;

public interface Actuator {
	
	public void performAction(Action action);
	public ActuatorConfigs getConfigs();
}
