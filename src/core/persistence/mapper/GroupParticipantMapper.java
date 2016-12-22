package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.Group;
import core.domain.User;
import core.domain.proxy.ProxyGroup;
import core.domain.proxy.ProxyUser;
import utils.Constants;
import utils.LogUtils;

/**
 * A class used to maniupate the groupparticipants in the database
 * @author Mathieu
 *
 */

public class GroupParticipantMapper {

	static GroupParticipantMapper instance;
	public String TAG = "GroupParticipantMapper";
	public Connection connection;
	public static final String sql_participantid = "participant_id", 
			sql_groupid = "group_id",
			sql_table = "chat_group_participant";
	
	private GroupParticipantMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static GroupParticipantMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new GroupParticipantMapper();
	}
	
	/**
	 * A function used to add a participant to a group in the databaase
	 * @param group
	 * @param participant
	 * @return
	 */
	public boolean create(Group group, User participant) {
		//I don't insert the id because it's autoincreented
				String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_groupid 
						+ ", " + sql_participantid
						+ ") values (?, ?);";
				try {
					PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
					ps.setInt(1, group.getId());
					ps.setInt(2, participant.getId());
					
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
					LogUtils.log(TAG, Constants.ERROR, "Error while inserting new group participant");
					e.printStackTrace();
					return false;
				}
	}

	/**
	 * A function used to send back all the users participating to a group discussion
	 * @param groupid
	 * @return
	 */
	public ArrayList<ProxyUser> readUsersByGroupId(int groupid) {
		String sqlRequest = "SELECT " + sql_participantid
				+ ", " + sql_groupid
				+ " FROM " + sql_table 
				+ " WHERE " + sql_groupid + " = ?";
		ProxyUser u = null;
		ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, groupid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				u = UserMapper.getInstance().readById(rs.getInt(sql_participantid));
				result.add(u);
			}
			//If no results were returned then no one was found
			if(result.isEmpty()) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return null;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "User found");
				return result;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding group administrator");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * A function used to get all the groups of an user
	 * @param id
	 * @return
	 */
	public ArrayList<ProxyGroup> readGroupsByUserId(int id) {

		String sqlRequest = "SELECT " + sql_participantid
				+ ", " + sql_groupid
				+ " FROM " + sql_table 
				+ " WHERE " + sql_participantid + " = ?";
		ProxyGroup g = null;
		ArrayList<ProxyGroup> result = new ArrayList<ProxyGroup>();
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				g = GroupMapper.getInstance().readById(rs.getInt(sql_groupid));
				result.add(g);
			}
			//If no results were returned then no one was found
			if(result.isEmpty()) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return null;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "User found");
				return result;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding group administrator");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * A function used to delete an existing group
	 * @param user
	 * @param group
	 * @return
	 */

	public boolean delete(User user, Group group) {
		String sqlRequest = "DELETE FROM " + sql_table + " WHERE " + sql_participantid + " = ?";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, user.getId());
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
