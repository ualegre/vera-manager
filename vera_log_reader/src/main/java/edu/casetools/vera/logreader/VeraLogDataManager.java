package edu.casetools.vera.logreader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.casetools.vera.logreader.data.VeraData;
import edu.casetools.vera.logreader.data.VeraVariable;
import edu.casetools.vera.logreader.interpreters.VeraInterpreter;
import edu.casetools.vera.logreader.ssh.SSHManager;

public abstract class VeraLogDataManager extends Observable implements Observer {

	protected VeraInterpreter interpreter;


	public VeraLogDataManager(){
		interpreter = new VeraInterpreter();
	}

	public void update(Observable o, Object arg) {
		if( o instanceof SSHManager ) {
			readAndStoreLine( (String)arg );
		}
	}

	public void readAndStoreLine(String line){
			//System.out.println(line);
		VeraData  data = readLine(interpreter, line);
		if(data != null){
			storeData(data);
		}
	}

	private VeraData readLine(VeraInterpreter interpreter, String line) {
		VeraData data = new VeraData();
		if (line != null){
			data = interpreter.interpret(line);
		}
		return data;
	}

	private void storeData(VeraData data) {
		switch(data.getId()){
		case 6:
			storeVeraVariable(data);
			//System.out.println("VARIABLE"+data);
			break;
		case 7:
			storeVeraEvent(data);
			//System.out.println("EVENT"+data);
			break;
		default:
			break;
		}
	}

	protected long getVariableTimestamp(VeraVariable var) {
		SimpleDateFormat veraFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS");
		String veraEventTime = var.getDate() + " " + var.getTime();
		
		try {	
			Date date1 = veraFormat.parse(veraEventTime);
			return date1.getTime();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	protected abstract void storeVeraEvent(VeraData data);
	protected abstract void storeVeraVariable(VeraData data);
}
