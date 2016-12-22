package core.domain.messages;

import core.domain.Group;
import core.domain.User;
import core.domain.proxy.ProxyUser;
/**
 * A class representing urgent messages - WIP
 * @author Mathieu
 *
 */
public class UrgentMessage extends Message {
	public UrgentMessage(ProxyUser sender, User receiver, String content) {
		super();
	}
	
	public UrgentMessage(ProxyUser sender, Group receivers, String content) {
		super(sender, receivers, content);
	}
}
