package core.domain;

import java.util.ArrayList;

public class User {
	private String lastname, username, firstname;
	private int id;
	private ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
	
	public User(int id, String lastname, String username, String firstname) {
		this.id = id;
		this.lastname = lastname;
		this.username = username;
		this.firstname = firstname;
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

	@Override
	public String toString() {
		return this.firstname + " \"" + this.username + "\" " + this.lastname;
	}
	
	
}
