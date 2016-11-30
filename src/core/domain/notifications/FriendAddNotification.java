package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.interfaces.NotificationVisitor;

public class FriendAddNotification extends Notification {
	
	private boolean accepted;

	public FriendAddNotification(int id, Date date, boolean sent, boolean received, boolean accepted) {
		super(id, date, sent, received);
		this.accepted = accepted;
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
}
