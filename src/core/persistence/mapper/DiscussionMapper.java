package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.messages.Discussion;
import utils.Constants;
import utils.LogUtils;

/**
 * The mapper of the duscussions
 * @author Mathieu
 *
 */
public class DiscussionMapper {
	static DiscussionMapper instance;
	public String TAG = "DiscussionMapper";
	public Connection connection;
	
	public static final String sql_table = "discussion", 
			sql_discussionid = "discussion_id";
	
	private DiscussionMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static DiscussionMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new DiscussionMapper();
	}
	
	/**
	 * A method used to create an instance of discussion inside te database
	 * @return
	 */
	public int create() {
		//I don't insert the id because it's autoincreented
		String sqlRequest = "INSERT INTO " + sql_table + " values (NULL)";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest,  new String[] { sql_discussionid });			
			int result = ps.executeUpdate();
			if(result != 0) {
			 //I get the create id and send it back
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
			LogUtils.log(TAG, Constants.ERROR, "Error while inserting new user");
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * A function used toread a discussion by its id
	 * @param discussion_id the discussion id
	 * @return a discussion (an arraylist of mesages)
	 */
	public Discussion readById(int discussion_id) {
		return MessageMapper.getInstance().readByDiscussionId(discussion_id);
	}


}
