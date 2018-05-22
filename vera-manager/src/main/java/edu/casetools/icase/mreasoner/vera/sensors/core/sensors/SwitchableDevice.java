package edu.casetools.icase.mreasoner.vera.sensors.core.sensors;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import edu.casetools.icase.mreasoner.vera.sensors.core.VeraLogDataObserver;
import edu.casetools.icase.mreasoner.vera.sensors.core.data.VeraData;
import edu.casetools.icase.mreasoner.vera.sensors.core.data.VeraVariable;

public class SwitchableDevice extends VeraLogDataObserver {
	protected String devId;
	protected String deviceName;
	protected int NORMAL_USE_MS;
	protected ArrayList<String> possibleFails;
	protected TimeUseMonitor timeUseMonitor;
	private BlockingQueue<VeraVariable> queue = new LinkedBlockingQueue<VeraVariable>();

	public SwitchableDevice( String deviceId ) {
		this(deviceId, new ArrayList<>(), 2000);
	}

	public SwitchableDevice( String deviceId, ArrayList<String> fails, int normal_use_time ) {
		devId = deviceId;
		deviceName = devId;
		possibleFails = fails;
		
		NORMAL_USE_MS = normal_use_time;
	}

	@Override
	protected void handleVeraEvent(VeraData data) { }

	@Override
	protected void handleVeraVariable(VeraData data) {
		VeraVariable var;

		var = data.getVariable();
		System.out.println( var.toString() );
		
		if( var.getDeviceId()!=null) {
			
		}
		
		if( !var.getDeviceId().equals(devId) )
			return;


		checkError(var);
	}

	protected boolean checkError(VeraVariable var) {
		if( checkStatusError(var) )
			return true;
		
		checkTimeUse(var);
		return false;
	}

	private boolean checkStatusError(VeraVariable var) {
		if (var.getVariable().equals("Status") && // first case: no changed status
				var.getNewValue().equals("1") &&
				var.getOldValue().equals( var.getNewValue() ) ) {
			return true;
		}
		
		return false;
	}

	private synchronized void checkTimeUse(VeraVariable var) {
		if( timeUseMonitor==null ) {
			timeUseMonitor = new TimeUseMonitor();
			new Thread( timeUseMonitor ).start();
		}

		try {
			queue.put(var);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected class TimeUseMonitor implements Runnable {

		@Override
		public void run() {
			long startUsageTS=0;
			VeraVariable msg;

			while( true ) {
				while ((msg = queue.poll()) != null) {
					if( msg.getVariable().equals("Status") &&
							msg.getOldValue().equals("0") &&
							msg.getNewValue().equals("1") ) {
						startUsageTS=getVariableTimestamp(msg);

						// real switch on
//						System.out.println( deviceName + " variable: " + msg.toString() );
//						System.out.println("starting monitoring the energy");
					}
					else if( msg.getVariable().equals("Status") &&
							msg.getOldValue().equals("1") &&
							msg.getNewValue().equals("0") ) {

						// switch off
						if( startUsageTS>0 ) {
							if( (getVariableTimestamp(msg)-startUsageTS) > NORMAL_USE_MS ) {
								// it is a normal use of the device. I should send a message to the user if he is in another room
							}
							else {
							}
							startUsageTS = 0;
						}

					}
				}

				try {
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
