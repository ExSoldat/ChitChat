package core.domain;

import core.App;

public class ProxyAdministrateur extends ProxyUser {
	
	public Administrateur instance = null;
	
	public ProxyAdministrateur(int id, String lastname, String username, String firstname, String password) {
		super(id, lastname, username, firstname, password);
		isAdmin = true;
	}

	@Override
	public void initialize() {
		this.setFriends(App.getInstance().getServicesProvider().getFriendsForUser(getId()));		
	}

	@Override
	public void ensureIsInitialized() {
		if(instance==null) {
			instance = new Administrateur(getId(), getLastname(), getUsername(), getFirstname(), getPassword());
			initialize();
		}
	}

}
