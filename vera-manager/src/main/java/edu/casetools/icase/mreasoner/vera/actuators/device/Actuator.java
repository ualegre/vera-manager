package edu.casetools.icase.mreasoner.vera.actuators.device;

import edu.casetools.icase.mreasoner.vera.actuators.data.Action;

public abstract class Actuator {

	private String stateName;
	
	public abstract void performAction(Action action);

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
}
