package core.domain;

import java.util.ArrayList;

public class User {
	private String lastname, username, firstname, password;
	private int id;
	private ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
	private ArrayList<User> friends = new ArrayList<User>();
	
	public User(int id, String lastname, String username, String firstname) {
		this.id = id;
		this.lastname = lastname;
		this.username = username;
		this.firstname = firstname;
		this.hobbies.add(new Hobby(0, "Sport"));
		this.hobbies.add(new Hobby(1, "Music"));
		this.hobbies.add(new Hobby(2, "Computer Science"));
	}

	public User(int id, String lastname, String username, String firstname, String password, ArrayList<User> friends) {
		this.id = id;
		this.lastname = lastname;
		this.username = username;
		this.firstname = firstname;
		this.password = password;
		this.friends = friends;
		this.hobbies.add(new Hobby(0, "Sport"));
		this.hobbies.add(new Hobby(1, "Music"));
		this.hobbies.add(new Hobby(2, "Computer Science"));	
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	public String getHobbiesAsAString() {
		String r = "";
		for(Hobby h : hobbies) {
			r += h.getLabel() +", ";
		}
		return r;
	}
	
	public boolean isAdmin() {
		return false;
	}

	@Override
	public String toString() {
		return this.firstname + " \"" + this.username + "\" " + this.lastname;
	}

	public void setPassword(String text) {
		this.password = text;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public boolean friendsListContains(User user) {
		for(User f : friends) {
			if(f.getId() == (user.getId()))
				return true;
		}
		return false;
	}
	
	
	
	
}
