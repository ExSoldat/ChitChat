package core.domain.proxy;

import java.awt.Component;
import java.util.ArrayList;

import core.App;
import core.domain.User;
import core.domain.VirtualProxy;
import core.domain.notifications.Notification;

public class ProxyUser extends User implements VirtualProxy<User> {
	
	public User instance = null;
	
	public ProxyUser(int id, String lastname, String username, String firstname, String password) {
		super(id, lastname,username, firstname, password);
		isAdmin = false;
	}

	@Override
	public void initialize() {
		this.setFriends(App.getInstance().getServicesProvider().getFriendsForUser(getId()));
		this.setGroups(App.getInstance().getServicesProvider().getGroupsForUser(getId()));
		this.setPendingNotifications(App.getInstance().getServicesProvider().getNotificationsForUser(this));
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
	
	public ArrayList<ProxyGroup> getGroups() {
		ensureIsInitialized();
		return groups;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((ProxyUser) obj).getId() == this.getId();

	}

	@Override
	public ArrayList<Notification> getPendingNotifications() {
		ensureIsInitialized();
		return notifications;
	}

}
