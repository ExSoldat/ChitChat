package core.domain.proxy;

import java.util.ArrayList;

import core.App;
import core.domain.Administrateur;
import core.domain.notifications.Notification;

public class ProxyAdministrateur extends ProxyUser {
	
	public Administrateur instance = null;
	
	public ProxyAdministrateur(int id, String lastname, String username, String firstname, String password) {
		super(id, lastname, username, firstname, password);
		isAdmin = true;
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
			instance = new Administrateur(getId(), getLastname(), getUsername(), getFirstname(), getPassword());
			initialize();
		}
	}
	
	@Override
	public ArrayList<Notification> getPendingNotifications() {
		ensureIsInitialized();
		return notifications;
	}
	

	public ArrayList<ProxyUser> getFriends() {
		ensureIsInitialized();
		return friends;
	}
	
	public ArrayList<ProxyGroup> getGroups() {
		ensureIsInitialized();
		return groups;
	}

}
