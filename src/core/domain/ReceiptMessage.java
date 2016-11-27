package core.domain;

public class ReceiptMessage extends Message {
	//Receipt message : when the sent message is read, send a notification to the sender.
	public ReceiptMessage(User sender, User receiver, String content) {
		super(sender, receiver, content);
	}
	
	public ReceiptMessage(User sender, Group receivers, String content) {
		super(sender, receivers, content);
	}
}
