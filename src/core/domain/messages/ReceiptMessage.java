package core.domain.messages;

import core.domain.Group;
import core.domain.User;
import core.domain.proxy.ProxyUser;
/**
 * A class representing receipt messages - WIP
 * @author Mathieu
 *
 */
public class ReceiptMessage extends Message {
	//Receipt message : when the sent message is read, send a notification to the sender.
	public ReceiptMessage(ProxyUser sender, User receiver, String content) {
		super();
	}
	
	public ReceiptMessage(ProxyUser sender, Group receivers, String content) {
		super(sender, receivers, content);
	}
}
