package core.domain;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Message> messages = new ArrayList<Message>();
	private User administrator;
	private ArrayList<User> moderators;
	private ArrayList<User> participants;
	private String description;
	public String getName() {
		return name;
	}
	
	public Group() {}
	
	public Group(String name, User administrator, String desc) {
		this.name = name;
		this.administrator = administrator;
		this.description=desc;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	public User getAdministrator() {
		return administrator;
	}
	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}
	public ArrayList<User> getModerators() {
		return moderators;
	}
	public void setModerators(ArrayList<User> moderators) {
		this.moderators = moderators;
	}
	public ArrayList<User> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}
	public Message getLastMessage() {
		return getMessages().get(messages.size()-1);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
		
	}
}
