package core.domain;

import core.domain.proxy.ProxyUser;

public class ReceiptMessage extends Message {
	//Receipt message : when the sent message is read, send a notification to the sender.
	public ReceiptMessage(ProxyUser sender, User receiver, String content) {
		super();
	}
	
	public ReceiptMessage(ProxyUser sender, Group receivers, String content) {
		super(sender, receivers, content);
	}
}
