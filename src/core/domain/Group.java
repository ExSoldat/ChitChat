package core.domain;

import java.util.ArrayList;

import core.App;
import core.domain.proxy.ProxyUser;

public class Group {
	private String name;
	private int id;
	private Discussion messages = new Discussion();
	private User administrator;
	private ArrayList<ProxyUser> moderators;
	private ArrayList<ProxyUser> participants;
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
	
	public Group(int id, String name, String desc) {
		this.name = name;
		this.description=desc;
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Discussion getMessages() {
		return messages;
	}
	public void setMessages(Discussion messages) {
		this.messages = messages;
	}
	public User getAdministrator() {
		return administrator;
	}
	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}
	public ArrayList<ProxyUser> getModerators() {
		return moderators;
	}
	public void setModerators(ArrayList<ProxyUser> moderators) {
		this.moderators = moderators;
	}
	public ArrayList<ProxyUser> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<ProxyUser> participants) {
		this.participants = participants;
	}
	public Message getLastMessage() {
		return getMessages().isEmpty() ? new Message(App.getInstance().getLoggedUser(), this, "It seems no message have been added yet, be the first to send yours !") : getMessages().get(messages.size()-1);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public int getId() {
		return id;
	}
}
