package core.domain;

import java.util.ArrayList;

import core.App;

public class ProxyUser extends User implements VirtualProxy<User> {
	
	public User instance = null;
	
	public ProxyUser(int id, String lastname, String username, String firstname, String password) {
		super(id, lastname,username, firstname, password);
		isAdmin = false;
	}

	@Override
	public void initialize() {
		this.setFriends(App.getInstance().getServicesProvider().getFriendsForUser(getId()));		
	}

	@Override
	public void ensureIsInitialized() {
		if(instance==null) {
			instance = new User(getId(), getLastname(), getUsername(), getFirstname(), getPassword());
			initialize();
		}
	}
	
	public ArrayList<ProxyUser> getFriends() {
		ensureIsInitialized();
		return friends;
	}

}
