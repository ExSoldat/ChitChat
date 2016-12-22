package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.App;
import core.domain.User;
import core.domain.proxy.ProxyUser;
import utils.Constants;
import utils.LogUtils;

/**
 * A class used to manipulate an association between two friends to create a friends list in the database
 * @author Mathieu
 *
 */
public class UserFriendsMapper {
	
	static UserFriendsMapper instance;
	public String TAG = "UserFriendsMapper";
	public Connection connection;
	
	public static final String sql_table = "user_friendslist", sql_userid = "user_id", sql_friendid = "friend_id", sql_isvalid = "is_valid", sql_discussionid = "discussion_id";
	
	private UserFriendsMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static UserFriendsMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new UserFriendsMapper();
	}

	
		/**
		 * A function used to an an user to another's friendlist
		 * @param addingUser
		 * @param addedUser
		 * @return
		 */
		public boolean addUserToFriendsList(User addingUser, User addedUser) {
			//First, we create a new discussion
			int dId = DiscussionMapper.getInstance().create();
			String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_userid
					+ ", " + sql_friendid
					+ ", " + sql_discussionid
					+ ", " + sql_isvalid
					+ ") values (?,?,?,?)";
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setInt(1, addingUser.getId());
				ps.setInt(2, addedUser.getId());
				ps.setInt(3, dId);
				ps.setBoolean(4, false); //TODO : remove this
				
				int result = ps.executeUpdate();
				if(result == 1) {
					LogUtils.log(TAG, Constants.SUCCESS, "Friend successfully added");
					//App.getConnection().commit(); Got error "Can't call commit when autocommit = true"
					return true;
				} else {
					LogUtils.log(TAG, Constants.ERROR, "No or too much rows have been inserted");
					App.getConnection().rollback();
					return false;
				}
				
			} catch (SQLException e) {
				LogUtils.log(TAG, Constants.ERROR, "Error while updating Users");
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * A function used to get all the friends of an user
		 * @param user_id
		 * @return
		 */
		public ArrayList<ProxyUser> readByUserId(int user_id) {
			String sqlRequest = "SELECT " + sql_userid 
					+ ", " + sql_friendid  
					+ ", " + sql_isvalid 
					+ " FROM " + sql_table 
					+ " WHERE (" + sql_userid + " = ?"
					+ " OR " + sql_friendid + " = ? )"
					+ " AND " + sql_isvalid  + " = true";
			ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setInt(1, user_id);
				ps.setInt(2, user_id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {		
					LogUtils.log(TAG, Constants.INFO, "Row found");
					ProxyUser u = null;
					u = user_id == rs.getInt(sql_userid) ? UserMapper.getInstance().readById(rs.getInt(sql_friendid)) : UserMapper.getInstance().readById(rs.getInt(sql_userid));
					//Adding the User to the list of results	
					result.add(u);
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
		
		/**
		 * Remove a users friend
		 * @param removingUser the user removing the friendship
		 * @param removedUser the user we want to remove
		 * @return boolean if everything went fine. False otherwise
		 */
		public boolean delete(User removingUser, User removedUser) {

			String sqlRequest = "DELETE FROM " + sql_table + " WHERE (" 
				+ sql_friendid + " = ? AND " + sql_userid + " = ? ) OR (" 
				+ sql_userid + " = ? + AND " + sql_friendid + " = ? )";
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setInt(1, removedUser.getId());
				ps.setInt(2, removingUser.getId());
				ps.setInt(3, removedUser.getId());
				ps.setInt(4, removingUser.getId());
				int result = ps.executeUpdate();
				if(result == 1) {
					LogUtils.log(TAG, Constants.SUCCESS, "Successfully deleted");
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
		 * Removes frendship of an user in the case we delete him
		 * @param deleteduser the user we want to remove
		 * @return boolean if everything went fine. False otherwise
		 */
		public boolean delete(User deletedUser) {

			String sqlRequest = "DELETE FROM " + sql_table + " WHERE " + sql_friendid + " = ? OR " + sql_userid + " = ?";
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setInt(1, deletedUser.getId());
				ps.setInt(2, deletedUser.getId());
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

		public int readDiscussionIdByMeAndHim(ProxyUser me, ProxyUser him) {

			String sqlRequest = "SELECT " + sql_discussionid 
					+ " FROM " + sql_table 
					+ " WHERE (" + sql_userid + " = ?"
					+ " AND " + sql_friendid + " = ? "
					+ ") OR (" + sql_userid + " = ?"
					+ " AND " + sql_friendid + " = ? "
					+ ") AND " + sql_isvalid  + " = true";
			ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setInt(1, me.getId());
				ps.setInt(2, him.getId());
				ps.setInt(3, him.getId());
				ps.setInt(4, me.getId());
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {		
					LogUtils.log(TAG, Constants.INFO, "Row found");
					ProxyUser u = null;
					return rs.getInt(sql_discussionid);
				}
				//If no results were returned then no one was found
				if(result.isEmpty()) {
					LogUtils.log(TAG, Constants.INFO, "No rows found");
					return -1;
				} else {
					//If a result is here, then we send the result
					LogUtils.log(TAG, Constants.SUCCESS, "Users found");
					return -1;
				}
			} catch (SQLException e) {
				LogUtils.log(TAG, Constants.ERROR, "Error while finding Users");
				e.printStackTrace();
				return -1;
			}
		}

		/**
		 * Saves whether the friendhip is active or not
		 * @param me
		 * @param him
		 * @return
		 */
		public boolean saveIsActive(ProxyUser me, User him) {
			String sqlRequest = "UPDATE " + sql_table + " SET " + sql_isvalid 
					+ "= ? " + " WHERE (" + sql_friendid + " = ? AND " + sql_userid + " = ?) OR (" + sql_userid + " = ? AND " + sql_friendid + " = ?)";
			try {
				PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
				ps.setBoolean(1, true);
				ps.setInt(2, him.getId());
				ps.setInt(3, me.getId());
				ps.setInt(4, him.getId());
				ps.setInt(5, me.getId());
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

}
