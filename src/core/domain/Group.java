package core.domain;

import java.util.ArrayList;

import core.App;
import core.domain.messages.Discussion;
import core.domain.messages.Message;
import core.domain.proxy.ProxyUser;

/**
 * A class representing groups
 * @author Mathieu
 *
 */
public class Group {
	private String name;
	private int id;
	protected Discussion messages = new Discussion();
	private User administrator;
	private int discussion_id;
	private ArrayList<ProxyUser> moderators;
	private ArrayList<ProxyUser> participants;
	private String description;
	public String getName() {
		return name;
	}
	
	public Group() {}
	/**
	 * A constructor ithout id to create new groups
	 * @param name
	 * @param administrator
	 * @param desc
	 */
	public Group(String name, User administrator, String desc) {
		this.name = name;
		this.administrator = administrator;
		this.description=desc;
	}
	
	/**
	 * A contructor with an id bt no administrator, the administrator is usually set after
	 * @param id
	 * @param name
	 * @param desc
	 */
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
	/**
	 * A function used to return a string if no message is in the group, and the last message f there is any
	 * @return the last mesage or an empty placeholder
	 */
	public Message getLastMessage() {
		return getMessages().isEmpty() ? new Message(App.getInstance().getLoggedUser(), this, "It seems no message have been added yet, be the first to send yours !") : getMessages().get(messages.size()-1);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDiscussionId(int discussion_id) {
		this.discussion_id = discussion_id;
	}
	
	public int getDiscussionId() {
		return this.discussion_id;
	}

	/**
	 * Get all participants except oe in particular, in the case we want to see the list of users except the loged user
	 * @param user the user we don't want to have
	 * @return an arraylist of the participants except the specified user
	 */
	public ArrayList<ProxyUser> getParticipantsExcept(ProxyUser user) {
		ArrayList<ProxyUser> participants = getParticipants();
		participants.remove(user);
		return participants;
	}
}
