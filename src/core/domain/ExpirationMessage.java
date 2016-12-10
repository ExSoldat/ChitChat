package core.domain;

import core.domain.proxy.ProxyUser;

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
