package core.domain;

import java.util.ArrayList;

/**
 * A class representing the administrator of the app
 * @author Mathieu
 *
 */
public class Administrateur extends User {
	
	private boolean isAdmin = true;
	/**
	 * User's constructor, we just set the isAdmin ariable
	 * @param id the id of the user 
	 * @param lastname the lastname
	 * @param username the username
	 * @param firstname firstname 
	 * @param password password
	 */
	public Administrateur(int id, String lastname, String username, String firstname, String password) {
		super(id, lastname, username, firstname, password);
		this.isAdmin = true;
	}
	
	public Administrateur() {
		
	}
	
	public boolean isAdmin() {
		return true;
	}
}
