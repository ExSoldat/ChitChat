package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.interfaces.NotificationVisitor;

public class FriendMessageNotification extends Notification {
	private boolean urgent = false;

	public FriendMessageNotification(int id, Date date, boolean sent, boolean received, boolean urgent) {
		super(id, date, sent, received);
		this.urgent = urgent;
	}

	@Override
	public void accept(NotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
	
	
}
