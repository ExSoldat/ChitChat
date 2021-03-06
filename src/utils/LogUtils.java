package utils;

import javax.swing.JLabel;

import core.App;
import ui.components.CCColor;

public class LogUtils {

	/**
	 * A function of a class only here to help me log what the function does, so the console messages are uniformized
	 * @param tag the tag of the class from where the log has been sent
	 * @param type the type of the message (all are defined in the Constants class)
	 * @param message the message we want to type
	 */
	public static void log(String tag, String type, String message) {
		JLabel graphicLog = new JLabel(tag + ">> " + type + " : " + message);
		if(App.getInstance().getLogTypes().contains(type)) {
			String tab = ">> ";
			if(type.equals(Constants.RESPONSE))
				tab = "----------------------------------------------\n>>---- ";
			else if (type.equals(Constants.USE_CASE))
				tab = "**********************************************\n>>>>--";
			System.out.println(tab + tag + " " + type.toUpperCase() + " : " + message);
			if(type.equals(Constants.RESPONSE))
				System.out.println("----------------------------------------------");
			else if (type.equals(Constants.USE_CASE)) 
				System.out.println("**********************************************");
			
			//I won't change what is above
			if(type.equals(Constants.RESPONSE))
				graphicLog.setForeground(CCColor.CCGREEN.getColor());
			else if (type.equals(Constants.ERROR))
				graphicLog.setForeground(CCColor.CCDANGER.getColor());
			else if (type.equals(Constants.USE_CASE))
				graphicLog.setForeground(CCColor.CCPRIMARY.getColor());
			
			if(App.getInstance().isInDevMode()) {
				//DevFrame frame = App.getInstance().getDevFrame();
				//frame.addLog(graphicLog);
			}
		}
	}
		
}
