package core.domain.messages;

import core.domain.Group;
import core.domain.proxy.ProxyUser;

/**
 * A class representing messages with an expiration date - WIP
 * @author Mathieu
 *
 */
public class ExpirationMessage extends Message {
	int expirationtime;
	public ExpirationMessage(ProxyUser sender, ProxyUser receiver, String content, String time) {
		super(sender, receiver, content);
		this.expirationtime = (Integer.valueOf(time));
	}
	
	public ExpirationMessage(ProxyUser sender, Group receivers, String content, String time) {
		super(sender, receivers, content);
		this.expirationtime = (Integer.valueOf(time));
	}
}
