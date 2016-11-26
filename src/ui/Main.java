package ui;

import core.App;
import utils.Constants;

public class Main {	
	public static void main(String[] args) {	
		
		if(App.getInstance().isInDevMode()) {
			App.getInstance().addLogType("DBG"); // See Errors that occurred in the code
			App.getInstance().addLogType(Constants.ERROR); // See Errors that occurred in the code
			App.getInstance().addLogType(Constants.INFO); // Some informations that can be used to debug
			App.getInstance().addLogType(Constants.RESPONSE); // What is shown after we get a response from the ServiceProvider
			App.getInstance().addLogType(Constants.SUCCESS); // When an SQL query is successful
			App.getInstance().addLogType(Constants.USE_CASE); // Which Use case is being checked
			App.getInstance().addLogType(Constants.NOTE); // Note about the result of the Service
			App.getInstance().addLogType(Constants.SQL); // Executed SQL REquests
			//DevFrame devFrame = new DevFrame();
			//devFrame.init();
			//devFrame.setVisible(true);
		}
		
		LoginFrame login = new LoginFrame();
		login.init();
		login.setVisible(true);
	}
	
}
