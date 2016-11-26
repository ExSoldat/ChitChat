package core.service;

import java.util.ArrayList;
import java.util.Random;

import core.domain.Administrateur;
import core.domain.Group;
import core.domain.Message;
import core.domain.User;
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
	public User connect(String user, String pwd) {
		//Appeler les couches inf�rieures pour v�rifier que l'utilisateur a donn� les bons credentials
		//Dans le retour, si le compte est administrateur, c'est renseign�
		LogUtils.log("DBG", "DBG", "connect : " + user + pwd);
		//M2thode bidon
		LogUtils.log(TAG, Constants.RESPONSE, user + "logged !");
		if(user.equals("admin") && pwd.equals("admin")) {
			return new Administrateur(29,"SAAB", user, "Mathieu");
		} else if (user.equals("null") && pwd.equals("null")) {
			return null;
		} else {
			return new User(10,"DOE", "lejohndoe", "John");
		} 
		//LogUtils.log(TAG, Constants.RESPONSE, user + "logged !");
	}

	/**
	 * A function used to get groups for a given user
	 * @param id the id of the user
	 * @return the list of the groups 
	 */
	public ArrayList<Group> getGroupsForUser(int id) {
		//Return dummy content
		ArrayList<Group> result = new ArrayList<Group>();
		Group g = new Group();
		g.setName("The first group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu"));
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "KONICHIWA"));
		result.add(g);
		g = null;
		g = new Group();
		g.setName("The second group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu"));
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Hello it's me Mario"));
		result.add(g);
		g = null;
		g = new Group();
		g.setName("The third group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu"));
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Say whaaaaaaat ?"));
		result.add(g);
		
		g = null;
		g = new Group();
		g.setName("The fourth group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu"));
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Hello it's me Mario"));
		result.add(g);
		
		g = null;
		g = new Group();
		g.setName("The fourth group");
		g.setAdministrator(new Administrateur(29,"SAAB", "admin", "Mathieu"));
		for (int i =0; i<15; i++) {
			g.getMessages().add(new Message());
		}
		g.getMessages().add(new Message(new User (0, "DOE", "janedoe", "Jane"), "Hello it's me Mario"));
		result.add(g);
		LogUtils.log(TAG, Constants.RESPONSE, "Groups found !");
		return result;
	}

	/**
	 * A function to get the Friends of an user
	 * @param id the user of the user we want to ghave the friends for
	 * @return the list of users found
	 */
	public ArrayList<User> getFriendsForUser(int id) {
		ArrayList<User> result = new ArrayList<User>();
		result.add(new User(0,"DOE", "janedoe", "Jane"));
		result.add(new User(1,"DOE", "johndoe", "John"));
		result.add(new User(3,"Zamasu", "zamasu", "Zamasu"));
		result.add(new User(4,"Ornitier", "vivi", "Vivi"));
		result.add(new User(5,"Sanchez", "bopabeloola", "Rick"));
		LogUtils.log(TAG, Constants.RESPONSE, "Friends list retrieved !");
		return result;
	}
	
	public ArrayList<User> getFriendsForUser(int id, boolean flag) {
		ArrayList<User> result = new ArrayList<User>();
		result.add(new User(0,"DOE", "janedoe", "Jane"));
		result.add(new User(1,"DOE", "johndoe", "John"));
		result.add(new User(3,"Zamasu", "zamasu", "Zamasu"));
		result.add(new User(4,"Ornitier", "vivi", "Vivi"));
		result.add(new User(5,"Sanchez", "bip bop baloola", "Rick"));
		result.add(new User(6,"JAEGER", "ej", "Eren"));
		LogUtils.log(TAG, Constants.RESPONSE, "Friends list retrieved !");
		return result;
	}

	/**
	 * Delete one of our friends
	 * @param deletedFriend the friend to be delmeted
	 * @return true if eerything went fine, false otherwise
	 */
	public boolean deleteFriend(User deletedFriend) {
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
	public ArrayList<User> searchUser(String name, String firstname, String username) {
		ArrayList<User> result = new ArrayList<User>();
		result.add(new User(0,"DOE", "janedoe", "Jane"));
		result.add(new User(1,"DOE", "johndoe", "John"));
		result.add(new User(3,"Zamasu", "zamasu", "Zamasu"));
		result.add(new User(4,"Ornitier", "vivi", "Vivi"));
		LogUtils.log(TAG, Constants.RESPONSE, "Users found using parameters : " + name + ", " + firstname + ", " + username);
		return result;
	}
	
	/**
	 * Add a friend to our friendslist
	 * @param loggedUser the user currently logged
	 * @param addedUser the user to add
	 * @return true if everything went fine, false either
	 */
	public boolean addFriend(User loggedUser, User addedUser) {
		boolean r = rand.nextBoolean();
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
}
