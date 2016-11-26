package core.domain;

public class Message {
	private User sender, receiver;
	private Group groupReceiver;
	private String content;
	
	public Message() {
		this.sender = new User(0, "DOE", "janedoe", "Jane");
		this.content = "Hello, just sending a message";
	}
	
	public Message(User sender, String content) {
		this.sender = sender;
		this.content = content;
	}
	
	public Message(User sender, User receiver, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}
	
	public Message(User sender, Group receiver, String content) {
		this.sender = sender;
		this.groupReceiver = receiver;
		this.content = content;
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
}
