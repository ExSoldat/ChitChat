package core.domain;

public class ExpirationMessage extends Message {
	int expirationtime;
	public ExpirationMessage(User sender, User receiver, String content, String time) {
		super(sender, receiver, content);
		this.expirationtime = (Integer.valueOf(time));
	}
	
	public ExpirationMessage(User sender, Group receivers, String content, String time) {
		super(sender, receivers, content);
		this.expirationtime = (Integer.valueOf(time));
	}
}
