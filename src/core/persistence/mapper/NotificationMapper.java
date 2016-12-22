package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.notifications.MapperCreationVisitor;
import core.domain.notifications.Notification;
import core.domain.notifications.NotificationFactory;
import core.domain.proxy.ProxyAdministrateur;
import core.domain.proxy.ProxyUser;
import utils.Constants;
import utils.LogUtils;

public class NotificationMapper {

	static NotificationMapper instance;
	public NotificationFactory nf = new NotificationFactory();
	public String TAG = "NotificationMapper";
	public Connection connection;
	public static final String sql_id = "id", 
			sql_receiverid = "receiver_id", sql_seen = "seen", 
			sql_type = "type", sql_subjectid = "subject_id",
			sql_table = "notification";
	
	private NotificationMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static NotificationMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new NotificationMapper();
	}
	
	/**
	 * Creates a notification in the database using the MapperCreationVisitor to get the SQL query
	 * @param n
	 * @return
	 */
	public boolean create(Notification n) {
		String sqlRequest = n.accept(new MapperCreationVisitor());
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setBoolean(1, false);
			ps.setInt(2, n.getReceiverId());
			ps.setInt(3, n.getSubjectId());
			
			int result = ps.executeUpdate();
			if(result != 0) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successful inserted");
				//App.getConnection().commit();
				return true;
			}
			//If no return has been made, no rows could be inserted
			LogUtils.log(TAG, Constants.ERROR, "No rows inserted");
			return false;
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while inserting new user");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates a notification in te database
	 * @param notification
	 * @return
	 */
	public boolean update(Notification notification) {

		String sqlRequest = "UPDATE " + sql_table + " SET " + sql_seen 
				+ "= ? " + " WHERE " + sql_id + " = ?";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setBoolean(1, notification.isReceived());
			ps.setInt(2, notification.getId());
			int result = ps.executeUpdate();
			if(result == 1) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successfully updated");
				//App.getConnection().commit();
				return true;
			} else {
				LogUtils.log(TAG, Constants.ERROR, "No or too much rows have been inserted");
				//App.getConnection().rollback();
				return false;
			}
			
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while updating Users");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * A funtion used to get all the notifications of a receiver
	 * @param receiver
	 * @return
	 */
	public ArrayList<Notification> readByReceiver(ProxyUser receiver) {

		String sqlRequest = "SELECT " + sql_id
				+ ", " + sql_receiverid
				+ ", " + sql_type
				+ ", " + sql_subjectid
				+ " FROM " + sql_table
				+ " WHERE " + sql_receiverid + " = " + receiver.getId()
				+ " AND " + sql_seen +" = false";
		ArrayList<Notification> result = new ArrayList<Notification>();
		
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				Notification n = nf.createNewNotification(rs.getInt(sql_id), rs.getInt(sql_type), rs.getInt(sql_receiverid), rs.getInt(sql_subjectid));
				result.add(n);
			}
			//If no results were returned then no one was found
			if(result.isEmpty()) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return null;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "Users found");
				return result;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding Users");
			e.printStackTrace();
			return null;
		}
	}
}
