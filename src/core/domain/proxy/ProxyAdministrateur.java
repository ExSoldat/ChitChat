package core.domain.proxy;

import java.util.ArrayList;

import core.App;
import core.domain.Administrateur;
import core.domain.notifications.Notification;

/**
 * A proxy used to load friends/group/notifications list from the database only when needed
 * @author Mathieu
 *
 */
public class ProxyAdministrateur extends ProxyUser {
	
	public Administrateur instance = null;
	/**
	 * The constructor of the class
	 * @param id
	 * @param lastname
	 * @param username
	 * @param firstname
	 * @param password
	 */
	public ProxyAdministrateur(int id, String lastname, String username, String firstname, String password) {
		super(id, lastname, username, firstname, password);
		isAdmin = true;
	}

	@Override
	/**
	 * The initializer. it sets the friends, groups and notifications
	 */
	public void initialize() {
		this.setFriends(App.getInstance().getServicesProvider().getFriendsForUser(getId()));
		this.setGroups(App.getInstance().getServicesProvider().getGroupsForUser(getId()));
		this.setPendingNotifications(App.getInstance().getServicesProvider().getNotificationsForUser(this));		
	}

	@Override
	/**
	 * A method to call when we want to perform an actions on friends/groups/notifications. It is used to initialize them if neede
	 */
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
