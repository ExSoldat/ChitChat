package core.domain;

import java.util.ArrayList;

public class Administrateur extends User {
	
	private boolean isAdmin = true;
	
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
