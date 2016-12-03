package core.domain;

import java.util.ArrayList;

public interface IUser {
		
	public String getLastname();

	public void setLastname(String lastname);

	public String getUsername();

	public void setUsername(String username);

	public String getFirstname();

	public void setFirstname(String firstname);

	public int getId();

	public void setId(int id);

	public ArrayList<Hobby> getHobbies();

	public void setHobbies(ArrayList<Hobby> hobbies);
	
	public String getHobbiesAsAString();
	
	public boolean isAdmin();

	@Override
	public String toString();

	public void setPassword(String text);

	public String getPassword();

	public ArrayList<User> getFriends();

	public void setFriends(ArrayList<User> friends);

	@Override
	boolean equals(Object obj);
	
	
}
	