package core.persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.Statement;

import core.App;
import core.domain.Administrateur;
import core.domain.User;
import core.domain.proxy.ProxyAdministrateur;
import core.domain.proxy.ProxyUser;
import utils.Constants;
import utils.LogUtils;

public class UserMapper implements Mapper<ProxyUser> {
	
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

	//Updates the user with the created id
	public User create(User user) {
		//I don't insert the id because it's autoincreented
		String sqlRequest = "INSERT INTO " + sql_table + "(" + sql_firstname 
				+ ", " + sql_lastname 
				+ ", " + sql_username 
				+ ", " + sql_password
				+ ") values (?, ?, ?, ?);";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			
			int result = ps.executeUpdate();
			if(result != 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                user.setId(generatedKeys.getInt(1));
		            }
		            else {
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
		        }
				LogUtils.log(TAG, Constants.SUCCESS, "Successful inserted");
				//App.getConnection().commit();
				return user;
			}
			//If no return has been made, no rows could be inserted
			LogUtils.log(TAG, Constants.ERROR, "No rows inserted");
			return user;
		} catch (SQLException e) {
			LogUtils.log(TAG, Constants.ERROR, "Error while inserting new user");
			e.printStackTrace();
			return user;
		}
	}

	@Override
	public ArrayList<ProxyUser> read() {
		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_username 
				+ ", " + sql_firstname  
				+ ", " + sql_lastname 
				+ ", " + sql_password 
				+ ", " + sql_isadmin 
				+ " FROM " + sql_table;
		ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
		
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				ProxyUser u = null;
				if(rs.getBoolean(sql_isadmin))
					u = new ProxyAdministrateur(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				else 
					u = new ProxyUser(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
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

	public ProxyUser readById(int id) {
		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_username 
				+ ", " + sql_firstname  
				+ ", " + sql_lastname 
				+ ", " + sql_password 
				+ ", " + sql_isadmin 
				+ " FROM " + sql_table
				+ " WHERE " + sql_id + " = ? ";	
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ProxyUser u = null;
			//We wait only for one result to come. We can't have more normally, but in this case we would only send one
			if(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				if(rs.getBoolean(sql_isadmin))
					u = new ProxyAdministrateur(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				else 
					u = new ProxyUser(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				//Adding the User to the list of results	
			}
			//If no results were returned then no one was found
			if(u == null) {
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
	/**
	 * Updates an user. Here we only update the users data. The users friends list id updated by another function
	 * @param user the user we want to update
	 * @return boolean if everything went fine. False otherwise
	 */
	public boolean update(ProxyUser user) {
		String sqlRequest = "UPDATE " + sql_table + " SET " + sql_username 
				+ "= ?, " + sql_firstname
				+ "= ?, " + sql_lastname
				+ "= ?, " + sql_password
				+ "= ? " + " WHERE " + sql_id + " = ?";
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());
			LogUtils.log(TAG, Constants.SQL, "Executing update : " + ps);
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

	public boolean delete(User user) {
		String sqlRequest = "DELETE FROM " + sql_table + " WHERE " + sql_id + " = ?";
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
	
	public ArrayList<ProxyUser> readByNameFields(String un, String fn, String ln) {
		ArrayList<String> fields = new ArrayList<String>();
		String where = "";
		String sqlRequest = "SELECT " + sql_id 
				+ ", " + sql_username 
				+ ", " + sql_firstname  
				+ ", " + sql_lastname 
				+ ", " + sql_password 
				+ ", " + sql_isadmin 
				+ " FROM " + sql_table;
		
		if(!un.equals("")) {
			if(where.equals(""))
				where += " WHERE " + sql_username + " LIKE ?";
			else 
				where += " OR " + sql_username + " LIKE ?";
			fields.add(un);
		}
		if(!fn.equals("")) {
			if(where.equals(""))
				where += " WHERE " + sql_firstname + " LIKE ?";
			else 
				where += " OR " + sql_firstname + " LIKE ?";
			fields.add(fn);
		}
		if(!ln.equals("")) {
			if(where.equals(""))
				where += " WHERE " + sql_lastname + " LIKE ?";
			else 
				where += " OR " + sql_lastname + " LIKE ?";
			fields.add(ln);
		}
				
		if(!un.equals("") || !fn.equals("") || !ln.equals(""))
			sqlRequest += where;
		ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
		try {
			PreparedStatement ps = App.getConnection().prepareStatement(sqlRequest);
			for(int i = 0; i<fields.size(); i++) {
				ps.setString(i+1, "%"+fields.get(i)+"%");
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				LogUtils.log(TAG, Constants.INFO, "Row found");
				ProxyUser u = null;
				if(rs.getBoolean(sql_isadmin))
					u = new ProxyAdministrateur(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
				else 
					u = new ProxyUser(rs.getInt(sql_id), rs.getString(sql_lastname), rs.getString(sql_username), rs.getString(sql_firstname), rs.getString(sql_password));
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
	
}
