package core.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	private User sender, receiver;
	private Group groupReceiver;
	private String content;
	private Date date;
	public DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy à HH:mm");
	
	public Message() {
		this.sender = new User(0, "DOE", "janedoe", "Jane");
		this.content = "Hello, just sending a message";
		this.date = new Date();
	}
	
	public Message(User sender, String content, Date date) {
		this.sender = sender;
		this.content = content;
		this.date = date;
	}
	
	public Message(User sender, User receiver, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}
	
	public Message(User sender, Group receivers, String content) {
		this.sender = sender;
		this.groupReceiver = receivers;
		this.content = content;
	}
	
	public Message(User sender, User receiver, String content, Date date) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.date = date;
	}
	
	public Message(User sender, Group receiver, String content, Date date) {
		this.sender = sender;
		this.groupReceiver = receiver;
		this.content = content;
		this.date = date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFormattedDate() {
		return dateFormat.format(date);
	}
}
