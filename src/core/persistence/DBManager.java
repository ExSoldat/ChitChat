package core.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.Constants;
import utils.LogUtils;


/**
 * A class used to manage the connection to the mysql database
 * @author Mathieu
 *
 */
public class DBManager {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String TAG = "DBManager";

	static Connection connection;
	
	public DBManager() {
		
	}
	
	//Use on of those constants depending on the server you want to use
	public boolean connect() {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(Constants.LOCAL_URL, Constants.USERNAME, Constants.PASSWORD);

			LogUtils.log(this.TAG, Constants.SUCCESS, "Connection successful");
			return true;
		} catch (Exception e) {
			LogUtils.log(this.TAG, Constants.ERROR, "Connection to database failed");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * A function used to get the current connection
	 * @return the current connection
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	//A function used to disconnect from the database, it is not used for my to unfortunately
	public boolean disconnect(boolean commit) {
		if(commit) {
			try {
				connection.commit();
				connection.close();
				return true;
			} catch (SQLException e) {
				LogUtils.log(this.TAG, Constants.ERROR, "Unable to explicitly commit and close the connection");
				return false;
			}
		} else {
			try {
				connection.rollback();
				connection.close();
				return true;
			} catch (SQLException e) {
				LogUtils.log(this.TAG, Constants.ERROR, "Unable to explicitly rollback and close the connection");
				return false;
			}
		}
	}
}
