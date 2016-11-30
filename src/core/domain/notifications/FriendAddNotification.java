package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.domain.User;
import core.interfaces.NotificationVisitor;

public class FriendAddNotification extends Notification {
	
	private boolean accepted;
	private User sender;

	public FriendAddNotification(int id, Date date, boolean sent, boolean received, boolean accepted, User s) {
		super(id, date, sent, received);
		this.accepted = accepted;
		this.sender = s;
	}

	@Override
	public void accept(NotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
}
