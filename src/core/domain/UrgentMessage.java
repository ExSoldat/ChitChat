package core.domain;

import core.domain.proxy.ProxyUser;

public class UrgentMessage extends Message {
	public UrgentMessage(ProxyUser sender, User receiver, String content) {
		super();
	}
	
	public UrgentMessage(ProxyUser sender, Group receivers, String content) {
		super(sender, receivers, content);
	}
}
