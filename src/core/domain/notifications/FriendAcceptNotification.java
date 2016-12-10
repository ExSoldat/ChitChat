package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.domain.User;
import core.domain.proxy.ProxyUser;
import core.interfaces.NotificationVisitor;

public class FriendAcceptNotification extends Notification {

	private ProxyUser sender;
	public FriendAcceptNotification(int id, Date date, boolean sent, boolean received, ProxyUser sender) {
		super(id, date, sent, received);
		this.sender = sender;
	}

	@Override
	public void accept(NotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}

	public ProxyUser getSender() {
		return sender;
	}

	public void setSender(ProxyUser sender) {
		this.sender = sender;
	}
	
	

}
