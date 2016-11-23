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
	public ServicesProvider() {
		
	}
	
	public User connect(String user, String pwd) {
		//Appeler les couches inférieures pour vérifier que l'utilisateur a donné les bons credentials
		//Dans le retour, si le compte est administrateur, c'est renseigné
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
		LogUtils.log(TAG, Constants.RESPONSE, "Groups found !");
		return result;
	}

	public ArrayList<User> getFriendsForUser(int id) {
		ArrayList<User> result = new ArrayList<User>();
		result.add(new User(0,"DOE", "janedoe", "Jane"));
		result.add(new User(1,"DOE", "johndoe", "John"));
		result.add(new User(2,"JAEGER", "anonymoustitan", "Eren"));
		result.add(new User(3,"Zamasu", "zamasu", "Zamasu"));
		result.add(new User(4,"Ornitier", "vivi", "Vivi"));
		result.add(new User(5,"Sanchez", "bopabeloola", "Rick"));
		LogUtils.log(TAG, Constants.RESPONSE, "Friends list retrieved !");
		return result;
	}

	public boolean deleteUser(User deleteduser) {
		Random rand = new Random();
		boolean r = rand.nextBoolean();
		if (r)
			LogUtils.log(TAG, Constants.RESPONSE, "Friend deleted !");
		else 
			LogUtils.log(TAG, Constants.ERROR, "Unable to delete user !");
		return r;
	}
}
