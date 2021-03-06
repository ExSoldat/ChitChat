package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.Group;
import core.domain.proxy.ProxyUser;
import utils.Constants;
import utils.LogUtils;

/**
 * A class used to manipulate group administrator databaase
 * @author Mathieu
 *
 */
public class GroupAdministratorMapper {

	static GroupAdministratorMapper instance;
	public String TAG = "GroupAdministratorMapper";
	public Connection connection;
	public static final String sql_adminid = "administrator_id", 
			sql_groupid = "group_id",
			sql_table = "chat_group_administrator";
	
	private GroupAdministratorMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static GroupAdministratorMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new GroupAdministratorMapper();
	}
	
	/**
	 * Create an instance of groupamdinistrator in the database
	 * @param group the group corresponding
	 * @return true if everyting went fine, false otherise
	 */
	public boolean create(Group group) {

		//I don't insert the id because it's autoincreented
		String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_groupid 
				+ ", " + sql_adminid
				+ ") values (?, ?);";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, group.getId());
			ps.setInt(2, group.getAdministrator().getId());
			
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
			LogUtils.log(TAG, Constants.ERROR, "Error while inserting new group");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * A function used to cread the administrator by the group id
	 * @param groupid the group we want the admin of
	 * @return the user corresponding to the group's aministrator
	 */
	public ProxyUser readAdminById(int groupid) {

		String sqlRequest = "SELECT " + sql_adminid 
				+ " FROM " + sql_table 
				+ " WHERE " + sql_groupid + " = ?";
		ProxyUser u = null;
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, groupid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				u = UserMapper.getInstance().readById(rs.getInt(sql_adminid));
			}
			//If no results were returned then no one was found
			if(u == null) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return u;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "User found");
				return u;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding group administrator");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * A functionn used to delete a group administrator in the database
	 * @param group
	 * @return
	 */
	public boolean delete(Group group) {
		String sqlRequest = "DELETE FROM " + sql_table + " WHERE " + sql_adminid + " = ?";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, group.getAdministrator().getId());
			int result = ps.executeUpdate();
			LogUtils.log(TAG, Constants.SUCCESS, "Successfully deleted");
			//App.getConnection().commit();
			return true;
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while updating Users");
			e.printStackTrace();
			return false;
		}
	}

}
