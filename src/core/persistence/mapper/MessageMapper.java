package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;

import core.App;
import core.domain.Discussion;
import core.domain.Message;
import core.domain.User;
import core.domain.proxy.ProxyAdministrateur;
import core.domain.proxy.ProxyUser;
import core.domain.vrtualproxy.UserFactory;
import utils.Constants;
import utils.LogUtils;
import utils.MessageComparator;

public class MessageMapper implements Mapper<Discussion> {
	static MessageMapper instance;
	public String TAG = "MessageMapper";
	public Connection connection;
	
	public static final String sql_table = "message", 
			sql_messageid = "message_id", 
			sql_discussionid = "discussion_id", 
			sql_content = "content", 
			sql_senderid = "sender_id", 
			sql_datesent = "date_sent", 
			sql_encrypted = "encrypted", 
			sql_expiration = "expiration", 
			sql_urgent = "urgent", 
			sql_receipt = "receipt", 
			sql_isread = "is_read";
	
	private MessageMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static MessageMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new MessageMapper();
	}
	
	public boolean create(Message m) {
		if(m.getDiscussionId() == null)
			return false;
		/**
		 * sql_messageid = "message_id", 
			sql_discussionid = "discussion_id", 
			sql_content = "content", 
			sql_senderid = "sender_id", 
			sql_datesent = "date_sent", 
			sql_encrypted = "encrypted", 
			sql_expiration = "expiration", 
			sql_urgent = "urgent", 
			sql_receipt = "receipt", 
			sql_isread = "is_read";
		 */
		String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_discussionid 
				+ ", " + sql_content 
				+ ", " + sql_senderid 
				+ ", " + sql_datesent
				+ ") values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, m.getDiscussionId());
			ps.setString(2, m.getContent());
			ps.setInt(3, m.getSender().getId());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			int result = ps.executeUpdate();
			if(result != 0) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successfuly inserted");
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
	
	@Override
	public ArrayList read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Discussion readByDiscussionId(int discussion_id) {

		String sqlRequest = "SELECT " + sql_messageid 
				+ " FROM " + sql_table 
				+ " WHERE " + sql_discussionid + " = ? ORDER BY " + sql_messageid;
		Discussion result = new Discussion();
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, discussion_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				Message m = null;
				m = readById(rs.getInt(sql_messageid));
				//Adding the User to the list of results	
				result.add(m);
			}
			//If no results were returned then no one was found
			if(result.isEmpty()) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return null;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "Users found");
				Collections.sort(result, new MessageComparator());
				return result;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding Users");
			e.printStackTrace();
			return null;
		}
	}
	
	public Message readById(int message_id) {
		String sqlRequest = "SELECT " + sql_messageid 
				+ ", " + sql_discussionid  
				+ ", " + sql_content 
				+ ", " + sql_senderid  
				+ ", " + sql_datesent 
				+ ", " + sql_encrypted  
				+ ", " + sql_expiration 
				+ ", " + sql_urgent  
				+ ", " + sql_receipt 
				+ ", " + sql_isread 
				+ " FROM " + sql_table 
				+ " WHERE " + sql_messageid + " = ?";
		try {
			Message m = null;
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, message_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				m = new Message(UserMapper.getInstance().readById(rs.getInt(sql_senderid)), rs.getString(sql_content), rs.getTimestamp(sql_datesent));
				//u = new ProxyUser(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				//Adding the User to the list of results	
			}
			//If no results were returned then no one was found
			if(m == null) {
				LogUtils.log(TAG, Constants.INFO, "No rows found");
				return null;
			} else {
				//If a result is here, then we send the result
				LogUtils.log(TAG, Constants.SUCCESS, "Users found");
				return m;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding Users");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean update(Discussion discussion) {
		// TODO Auto-generated method stub
		return false;
	}

}
