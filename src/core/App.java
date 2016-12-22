package core;

import java.sql.Connection;
import java.util.ArrayList;

import core.domain.User;
import core.domain.proxy.ProxyUser;
import core.persistence.DBManager;
import core.service.ServicesProvider;
import ui.DevFrame;
import utils.Constants;
import utils.LogUtils;

/**
 * A class representing the current App state
 * @author Mathieu
 *
 */
public class App {
	public static String TAG = "App";
	private static ArrayList<String> logs = new ArrayList<String>();
	private ServicesProvider mServicesProvider = new ServicesProvider();
	private ProxyUser connectedUser;
	private DevFrame devframe;
	private static DBManager session;
	private App() {}
	
	//Create an instance
	private static App instance = new App();
	
	
	public static App getInstance() {
		return instance;
	}
	
	/**
	 * Creting the app's connection to database
	 * @return the connection
	 */
	public static Connection createSession() {
		LogUtils.log(TAG, Constants.INFO, "Creating app database session");
		session = new DBManager();
		session.connect();
		return session.getConnection();
	}
	
	/**
	 * Get the current connection
	 * @return the connection
	 */
	public static Connection getConnection() {
		if(session == null) {
			return createSession();
		} else {
			return session.getConnection();
		}
	}
	
	/**
	 * Used to debug things
	 * @param logType the type of log we want to implement
	 */
	public static void addLogType(String logType) {
		logs.add(logType);
	}
	
	public static ArrayList<String> getLogTypes() {
		return logs;
	}
	/**
	 * Get the app serices provider
	 * @return app serices provider
	 */
	public ServicesProvider getServicesProvider() {
		return this.mServicesProvider;
	}

	/**
	 * Set a connected user
	 * @param r the user we want to conect
	 */
	public void setConnectedUser(ProxyUser r) {
		this.connectedUser = r;
		LogUtils.log(TAG, Constants.INFO, r.getUsername() + "logged !");
	}
	
	public ProxyUser getLoggedUser() {
		return this.connectedUser;
	}

	/**
	 * Unused
	 * @param devFrame
	 */
	public void setDevFrame(DevFrame devFrame) {
		this.devframe = devFrame;		
	}
	
	public DevFrame getDevFrame() {
		return this.devframe;
	}

	public boolean isInDevMode() {
		return Constants.DEV_MODE;
	}
	
	
}
