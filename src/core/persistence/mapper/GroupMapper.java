package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.Group;
import core.domain.User;
import core.domain.proxy.ProxyAdministrateur;
import core.domain.proxy.ProxyGroup;
import core.domain.proxy.ProxyUser;
import utils.Constants;
import utils.LogUtils;

/**
 * A class used to manipulate Groups in the database
 * @author Mathieu
 *
 */
public class GroupMapper {
	static GroupMapper instance;
	public String TAG = "GroupMapper";
	public Connection connection;
	public static final String sql_name = "name", 
			sql_id = "id", sql_description = "description", sql_discussionid = "discussion_id",
			sql_table = "chat_group";
	
	private GroupMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static GroupMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new GroupMapper();
	}
	
	/**
	 * Creates a group
	 * @param group
	 * @return
	 */
	public int create(Group group) {

		//I don't insert the id because it's autoincreented
		String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_name 
				+ ", " + sql_description
				+ ", " + sql_discussionid
				+ ") values (?, ?, ?);";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest,  new String[] { sql_id });
			ps.setString(1, group.getName());
			ps.setString(2, group.getDescription());
			ps.setInt(3, group.getDiscussionId());
			
			int result = ps.executeUpdate();
			if(result != 0) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successful inserted");
				//App.getConnection().commit();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if(null != generatedKeys && generatedKeys.next())
					return generatedKeys.getInt(1);
				return -1;
			}
			//If no return has been made, no rows could be inserted
			LogUtils.log(TAG, Constants.ERROR, "No rows inserted");
			return -1;
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while inserting new group");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Read a group knowing its id
	 * @param groupid
	 * @return
	 */
	public ProxyGroup readById(int groupid) {

		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_name
				+ ", " + sql_description
				+ ", " + sql_discussionid
				+ " FROM " + sql_table
				+ " WHERE " + sql_id + " = ? ";
		ArrayList<Group> result = new ArrayList<Group>();
		
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, groupid);
			ResultSet rs = ps.executeQuery();
			ProxyGroup g = null;
			//We wait only for one result to come. We can't have more normally, but in this case we would only send one
			if(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				g = new ProxyGroup(rs.getInt(sql_id), rs.getString(sql_name), rs.getString(sql_description));
				g.setDiscussionId(rs.getInt(sql_discussionid));
				//Adding the User to the list of results	
				result.add(g);
			}
			//If no results were returned then no one was found
			if(result.isEmpty()) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return null;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "Users found");
				return g;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding Users");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delets an existant group
	 * @param group
	 * @return
	 */

	public boolean delete(Group group) {
		if(GroupAdministratorMapper.getInstance().delete(group)) {
			String sqlRequest = "DELETE FROM " + sql_table + " WHERE " + sql_id + " = ?";
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setInt(1, group.getId());
				int result = ps.executeUpdate();
				LogUtils.log(TAG, Constants.SUCCESS, "Successfully deleted");
				//App.getConnection().commit();
				return true;
			} catch (SQLException e) {
				LogUtils.log(TAG, Constants.ERROR, "Error while updating Users");
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
}
