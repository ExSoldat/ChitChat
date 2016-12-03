package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.Group;
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

}
