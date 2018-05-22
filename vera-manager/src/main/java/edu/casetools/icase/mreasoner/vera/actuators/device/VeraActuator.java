package edu.casetools.icase.mreasoner.vera.actuators.device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import edu.casetools.icase.mreasoner.vera.actuators.data.Action;
import edu.casetools.icase.mreasoner.vera.actuators.data.VeraActuatorConfigs;

public class VeraActuator extends Actuator{
	
	private URL 		  		service;
	private URLConnection 		connection;
	private String 		  		serviceId;
	private String 		  		actionCommand;
	private VeraActuatorConfigs configs;
	private Action 				lastAction;
	
	public VeraActuator(String serviceId, String actionCommand){
		this.serviceId 		  = serviceId;
		this.actionCommand    = actionCommand;
	}
	
	@Override
	public void performAction(Action action) {
		if(!lastAction.equals(action))
			connectAndUpdateValue(action);
		lastAction = action;
	}

	private void connectAndUpdateValue(Action action) {
		try {
		
			service = new URL(getUrl(action));
			connection = service.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    updateValue(action, in);
		    in.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getUrl(Action action) {
		String url = "http://"+configs.getIp()+":"+configs.getPort()+
				"/data_request?id=lu_action&output_format=xml&DeviceNum="+action.getDevice()+
				"&serviceId="+serviceId+"&action="+actionCommand+"&newTargetValue="+
				action.getValue();
		return url;
	}
	
	private void updateValue(Action action, BufferedReader in) throws IOException {
		String inputLine;
		System.out.println("ACTUATOR MANAGER: SWITCHING DEVICE"+action.getDevice()+" TO VALUE "+action.getValue());
		while ((inputLine = in.readLine()) != null) 
						System.out.println(inputLine);
	}
	
	public VeraActuatorConfigs getConfigs(){
		return this.configs;
	}
		
}
