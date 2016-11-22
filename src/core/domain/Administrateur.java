package core.domain;

public class Administrateur extends User {
	
	private boolean isAdmin = true;
	
	public Administrateur(int id, String lastname, String username, String firstname) {
		super(id, lastname, username, firstname);
		this.isAdmin = true;
	}
}
