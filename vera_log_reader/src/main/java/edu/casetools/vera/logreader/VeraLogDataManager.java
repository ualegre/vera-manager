package edu.casetools.vera.logreader;

import edu.casetools.vera.logreader.data.VeraData;
import edu.casetools.vera.logreader.interpreters.VeraInterpreter;

public abstract class VeraLogDataManager {

  	protected VeraInterpreter interpreter;

	
  	public VeraLogDataManager(){
  		interpreter = new VeraInterpreter();
  	}
  	
	public void readAndStoreLine(String line){
	   VeraData  data = readLine(interpreter, line);
	   if(data != null) storeData(data);
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
			  break;
		  case 7:
			  storeVeraEvent(data);
			  break;    	 
		 }
	}
	
	protected abstract void storeVeraEvent(VeraData data);

	protected abstract void storeVeraVariable(VeraData data);
	
}
