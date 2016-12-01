package core.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.App;
import core.domain.Administrateur;
import core.domain.User;
import utils.Constants;
import utils.LogUtils;

public class UserMapper implements Mapper<User> {
	
	static UserMapper instance;
	public String TAG = "UserMapper";
	public Connection connection;
	public static final String sql_firstname = "firstname", 
			sql_lastname = "lastname", sql_password = "password", 
			sql_username = "username", sql_id = "id",
			sql_isadmin = "isadmin",
			sql_table = "User";
	
	private UserMapper() {
		connection = App.getInstance().getConnection();
	}
	
	/**
	 * Singleton's getInstance
	 * @return the current UserMapper instance
	 */
	public static UserMapper getInstance() {
		if(instance != null)
			return instance;
		else
			return new UserMapper();
	}

	@Override
	public boolean create(User user) {
		//I don't insert the id because it's autoincreented
		String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_firstname 
				+ ", " + sql_lastname 
				+ ", " + sql_lastname 
				+ ", " + sql_password
				+ ") values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			
			int result = ps.executeUpdate();
			if(result != 0) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successful inserted");
				App.getConnection().commit();
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
	public ArrayList<User> read() {
		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_username 
				+ ", " + sql_firstname  
				+ ", " + sql_lastname 
				+ ", " + sql_password 
				+ ", " + sql_isadmin 
				+ " FROM " + sql_table;
		ArrayList<User> result = new ArrayList<User>();
		
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				User u = null;
				if(rs.getBoolean(sql_isadmin))
					u = new Administrateur(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				else 
					u = new User(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
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

	@Override
	public User readById(int id) {
		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_username 
				+ ", " + sql_firstname  
				+ ", " + sql_lastname 
				+ ", " + sql_password 
				+ ", " + sql_isadmin 
				+ " FROM " + sql_table
				+ " WHERE " + sql_id + " = ? ";
		ArrayList<User> result = new ArrayList<User>();
		
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			User u = null;
			//We wait only for one result to come. We can't have more normally, but in this case we would only send one
			if(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				if(rs.getBoolean(sql_isadmin))
					u = new Administrateur(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				else 
					u = new User(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
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
				return u;
			}
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while finding Users");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(User user) {
		String sqlRequest = "UPDATE " + sql_table + " SET " + sql_username 
				+ "= ?, " + sql_firstname
				+ "= ?, " + sql_lastname
				+ "= ?, " + sql_password
				+ "= ?, " + " WHERE " + sql_id + " = ?";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());
			int result = ps.executeUpdate();
			if(result == 1) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successfully updated");
				App.getConnection().commit();
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

	@Override
	public boolean delete(User user) {
		String sqlRequest = "DELETE FROM " + sql_table + " WHERE " + sql_id + " = ?";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, user.getId());
			int result = ps.executeUpdate();
			if(result == 1) {
				LogUtils.log(TAG, Constants.SUCCESS, "Successfully deleted");
				App.getConnection().commit();
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

	
}
