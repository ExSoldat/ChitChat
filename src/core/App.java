package core;

import java.sql.Connection;
import java.util.ArrayList;

import core.domain.User;
import core.service.ServicesProvider;
import utils.Constants;
import utils.LogUtils;

public class App {
	//Things i did not have time to implement : 
	//TODO : use a Visitor design pattern for PersonneMapper
	//TODO : manage rollbacks and commits
	public static String TAG = "App";
	private static ArrayList<String> logs = new ArrayList<String>();
	private ServicesProvider mServicesProvider = new ServicesProvider();
	private User connectedUser;
	private App() {}
	
	private static App instance = new App();
	
	public static App getInstance() {
		return instance;
	}
	
	//public static Connection createSession() {
		//LogUtils.log(TAG, Constants.INFO, "Creating app database session");
		//session = new DBManager();
		//session.connect();
		//return session.getConnection();
	//}
	/*
	public static Connection getConnection() {
		if(session == null) {
			return createSession();
		} else {
			return session.getConnection();
		}
	}
	*/
	public static void addLogType(String logType) {
		logs.add(logType);
	}
	
	public static ArrayList<String> getLogTypes() {
		return logs;
	}
	
	public ServicesProvider getServicesProvider() {
		return this.mServicesProvider;
	}

	public void setConnectedUser(User r) {
		this.connectedUser = r;
		LogUtils.log(TAG, Constants.INFO, r.getUsername() + "logged !");
	}
	
	public User getLoggedUser() {
		return this.connectedUser;
	}
}
