package core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import core.App;
import core.domain.Administrateur;
import core.domain.Group;
import core.domain.Message;
import core.domain.ProxyUser;
import core.domain.User;
import core.persistence.mapper.UserFriendsMapper;
import core.persistence.mapper.UserMapper;
import utils.Constants;
import utils.LogUtils;

public class ServicesProvider {
	public static String TAG = "ServicesProvider";
	Random rand = new Random();
	public ServicesProvider() {
		
	}
	
	/**
	 * A service used to connect an user
	 * @param user the username used to leg
	 * @param pwd the password used to log
	 * @return an user if the credentials are correct
	 */
	public ProxyUser connect(String user, String pwd) {
		ArrayList<ProxyUser> allusers = UserMapper.getInstance().read();
		for(int i = 0; i<allusers.size(); i++) {
			if(allusers.get(i).getUsername().equals(user) && allusers.get(i).getPassword().equals(pwd))
				return allusers.get(i);
		}
		return null;
	}

	/**
	 * A function used to get groups for a given user
	 * @param id the id of the user
	 * @return the list of the groups 
	 */
	public ArrayList<Group> getGroupsForUser(int id) {
		//Return dummy content
		ArrayList<Group> result = new ArrayList<Group>();
		ArrayList<ProxyUser> p = new ArrayList<ProxyUser>();
		
		Group g = new Group();
		g.setName("The first group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu", "azerty"));
		g.setDescription("A group");
		g.setParticipants(p);
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "KONICHIWA", new Date()));
		result.add(g);
		g = null;
		g = new Group();
		g.setName("The second group");
		g.setAdministrator(new Administrateur(10,"gzg", "zef", "zef", "azerty"));
		g.setDescription("A second group");
		g.setParticipants(p);
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Hello it's me Mario", new Date()));
		result.add(g);
		g = null;
		g = new Group();
		g.setName("The third group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu", "azerty"));
		g.setDescription("A third group");
		g.setParticipants(p);
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Say whaaaaaaat ?", new Date()));
		result.add(g);
		
		g = null;
		g = new Group();
		g.setName("The fourth group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu", "azerty"));
		g.setDescription("A gfrouth roup");
		g.setParticipants(p);
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Hello it's me Mario", new Date()));
		result.add(g);
		
		g = null;
		g = new Group();
		g.setName("The fourth group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu", "azerty"));
		g.setDescription("Another group");
		g.setParticipants(p);
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Last one", new Date()));
		result.add(g);
		LogUtils.log(TAG, Constants.RESPONSE, "Groups found !");
		return result;
	}

	/**
	 * A function to get the Friends of an user
	 * @param id the user of the user we want to ghave the friends for
	 * @return the list of users found
	 */
	public ArrayList<ProxyUser> getFriendsForUser(int id) {
		return UserFriendsMapper.getInstance().readByUserId(id);
	}
	
	

	/**
	 * Delete one of our friends
	 * @param deletedFriend the friend to be delmeted
	 * @return true if eerything went fine, false otherwise
	 */
	public boolean deleteFriend(User deletedUser) {
		boolean r = rand.nextBoolean();
		if (r)
			LogUtils.log(TAG, Constants.RESPONSE, "Friend deleted !");
		else 
			LogUtils.log(TAG, Constants.ERROR, "Unable to delete user !");
		return r;
	}

	/**
	 * A function used to get an user when he is not already a friend
	 * @param name the name field of the search 
	 * @param firstname the firstname field of the search
	 * @param username the username field of the search
	 * @return the list of the suers found
	 */
	public ArrayList<ProxyUser> searchUser(String lastname, String firstname, String username) {
		ArrayList<ProxyUser> mapperResult =  UserMapper.getInstance().readByNameFields(username, firstname, lastname);
		ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
		if(App.getInstance().getLoggedUser().getFriends() != null) {
			if(mapperResult != null) {
				for(int i = 0; i < mapperResult.size(); i++) {
					if(!App.getInstance().getLoggedUser().getFriends().contains(mapperResult.get(i))) {
						result.add(mapperResult.get(i));
					}
				}
			}
			return result;
		} else {
			return mapperResult;
		}
		
	}
	
	/**
	 * Add a friend to our friendslist
	 * @param addedUser the user to add
	 * @return true if everything went fine, false either
	 */
	public boolean addFriend(User addedUser) {
		//TODO Create a Notification
		boolean r = UserFriendsMapper.getInstance().addUserToFriendsList(App.getInstance().getLoggedUser(), addedUser);
		if (r)
			LogUtils.log(TAG, Constants.RESPONSE, "Friend added !");
		else 
			LogUtils.log(TAG, Constants.ERROR, "Unable to add user !");
		return r;
	}

	/**
	 * Create a group in the database
	 * @param mGroup the group we want to create
	 * @return true if everything went fine, false either
	 */
	public boolean createGroup(Group mGroup) {
		boolean r = rand.nextBoolean();
		if(r)
			LogUtils.log(TAG, Constants.RESPONSE, "Group created !");
		else 
			LogUtils.log(TAG, Constants.ERROR, "Unable to create group");
		return r;
			
	}

	/**
	 * Disconnect a logged user
	 * @param loggedUser
	 * @return true if everything went fine. False otherwise
	 */
	public boolean disconnect(User loggedUser) {
		return true;
	}

	/**
	 * Get the conversation between a logged user and another
	 * @param me the logged user
	 * @param him the user we want to have a conversation with
	 * @return the list of the messages between the two users
	 */
	public ArrayList<Message> getMessagesBetween(User me, User him) {
		ArrayList<Message> r = new ArrayList<Message>();
		r.add(new Message(him, "Hello", new Date()));
		r.add(new Message(him, "How are you ?", new Date()));
		r.add(new Message(me, "Fine thanks and you ?", new Date()));
		r.add(new Message(him, "Pretty good", new Date()));
		r.add(new Message(him, "Got any plans tonight ?", new Date()));
		r.add(new Message(me, "Not much", new Date()));
		r.add(new Message(him, "Okay", new Date()));
		r.add(new Message(him, "Where the hood at ?", new Date()));
		r.add(new Message(me, "gnaaaaa", new Date()));
		r.add(new Message(him, "Another way", new Date()));
		r.add(new Message(him, "Yea", new Date()));
		r.add(new Message(me, "yea.", new Date()));
		return r;
	}
	
	public ArrayList<Message> getMessagesBetween(User me, User him, boolean b) {
		ArrayList<Message> r = new ArrayList<Message>();
		r.add(new Message(him, "Hello", new Date()));
		r.add(new Message(him, "How are you ?", new Date()));
		r.add(new Message(me, "Fine thanks and you ?", new Date()));
		r.add(new Message(him, "Pretty good", new Date()));
		r.add(new Message(him, "Got any plans tonight ?", new Date()));
		r.add(new Message(me, "Not much", new Date()));
		r.add(new Message(him, "Okay", new Date()));
		r.add(new Message(him, "Where the hood at ?", new Date()));
		r.add(new Message(me, "gnaaaaa", new Date()));
		r.add(new Message(him, "Another way", new Date()));
		r.add(new Message(him, "Yea", new Date()));
		r.add(new Message(me, "yea.", new Date()));
		r.add(new Message(me, "NEWMESSAGE", new Date()));
		return r;
	}

	/**
	 * Sends a message to the user (Use something in the persistence so we can have all fields set in the database depending on the message type
	 * @param m the sent message
	 */
	public boolean sendMessage(Message m) {
		return rand.nextBoolean();
	}

	/**
	 * Get the messages for a given group
	 * @param group the group we want to have the data from
	 * @return a list of the messages
	 */
	public ArrayList<Message> getMessagesForGroup(User me, Group group) {
		ArrayList<Message> r = new ArrayList<Message>();
		r.add(new Message(me, group, "Hello", new Date()));
		r.add(new Message(new User(0, "DOE", "janedoe", "Jane"), group, "How are you ?", new Date()));
		r.add(new Message(me, group, "Fine thanks and you ?", new Date()));
		r.add(new Message(new User(0, "Zamasu", "Zamasu", "Zamasu"), group, "Filthy humans....", new Date()));
		r.add(new Message(new User(0, "Black", "Son", "Goku"), group, "Damn right bro :*", new Date()));
		r.add(new Message(new User(0, "DOE", "janedoe", "Jane"), group, "That's not nice Bro", new Date()));
		r.add(new Message(me, group, "Okay", new Date()));
		r.add(new Message(me, group, "Where's Trunks ?", new Date()));
		r.add(new Message(new User(0, "Sama", "killergodtuber", "Gowasu"), group, "Zamas ! Bring me some tea !", new Date()));
		r.add(new Message(new User(0, "Zamasu", "Zamasu", "Zamasu"), group, "No way. I'm Justice bitch", new Date()));
		r.add(new Message(me, group, "Damn", new Date()));
		return r;
	}

	public boolean deleteUser(User deleteduser) {
		return UserMapper.getInstance().delete(deleteduser);
	}

	public boolean createUser(User createdUser) {
		return UserMapper.getInstance().create(createdUser);
	}
	
	public boolean updateUser(User updatedUser) {
		return rand.nextBoolean();
	}

	public boolean removeUserFromGroup(User loggedUser, Group group) {
		//user.removeGroup(group);group.removeUser(loggedUser);return DataMapper.update()
		return rand.nextBoolean();
	}

	/**
	 * A function that returns all users except the administrator, it is only user by the Administrator
	 * @return the list of all the users
	 */
	public ArrayList<ProxyUser> getAllUsers() {
		ArrayList<ProxyUser> result = UserMapper.getInstance().read();
		result.remove(App.getInstance().getLoggedUser());
		return result;
	}
}
