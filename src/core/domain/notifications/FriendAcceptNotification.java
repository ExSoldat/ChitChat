package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.interfaces.NotificationVisitor;

public class FriendAcceptNotification extends Notification {

	public FriendAcceptNotification(int id, Date date, boolean sent, boolean received) {
		super(id, date, sent, received);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(NotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}

}
