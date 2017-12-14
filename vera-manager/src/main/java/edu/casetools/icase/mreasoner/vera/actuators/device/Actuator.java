package edu.casetools.icase.mreasoner.vera.actuators.device;

import edu.casetools.icase.mreasoner.vera.actuators.data.Action;
import edu.casetools.icase.mreasoner.vera.actuators.data.ActuatorConfigs;

public interface Actuator {
	
	public void performAction(Action action);
	public ActuatorConfigs getConfigs();
}
