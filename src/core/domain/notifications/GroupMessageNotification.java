package core.domain.notifications;

import java.util.Date;

import core.domain.Group;
import core.domain.User;
import core.interfaces.NotificationVisitor;

public class GroupMessageNotification extends Notification {

	private User sender;
	private Group group;
	
	public GroupMessageNotification(int id, Date d, boolean s, boolean r, User u, Group g) {
		super(id, d, s, r);
		this.sender = u;
		this.group = g;
	}
	
	@Override
	public void accept(NotificationVisitor nv) {
		nv.visit(this);
	}

	@Override
	public void accept(GUINotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
