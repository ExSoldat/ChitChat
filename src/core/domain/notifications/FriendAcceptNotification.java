package core.domain.notifications;

import java.util.Date;

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
	public void accept(GUINotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
