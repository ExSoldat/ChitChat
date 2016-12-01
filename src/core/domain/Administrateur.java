package core.domain;

import java.util.ArrayList;

public class Administrateur extends User {
	
	private boolean isAdmin = true;
	
	public Administrateur(int id, String lastname, String username, String firstname, String password, ArrayList<User> friends) {
		super(id, lastname, username, firstname, password, friends);
		this.isAdmin = true;
	}
	
	public boolean isAdmin() {
		return true;
	}
}
