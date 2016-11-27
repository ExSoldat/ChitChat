package core.domain;

public class UrgentMessage extends Message {
	public UrgentMessage(User sender, User receiver, String content) {
		super(sender, receiver, content);
	}
	
	public UrgentMessage(User sender, Group receivers, String content) {
		super(sender, receivers, content);
	}
}
