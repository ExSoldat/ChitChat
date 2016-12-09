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

public class GroupMapper implements Mapper<Group> {
	static GroupMapper instance;
	public String TAG = "GroupMapper";
	public Connection connection;
	public static final String sql_name = "name", 
			sql_id = "id", sql_description = "description",
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
	
	public boolean create(Group group) {

		//I don't insert the id because it's autoincreented
		String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_name 
				+ ", " + sql_description
				+ ") values (?, ?);";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setString(1, group.getName());
			ps.setString(2, group.getDescription());
			
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
	
	@Override
	public ArrayList<Group> read() {
		return null;
	}

	@Override
	public boolean update(Group object) {
		// TODO Auto-generated method stub
		return false;
	}

	public ProxyGroup readById(int groupid) {

		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_name
				+ ", " + sql_description
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

}
