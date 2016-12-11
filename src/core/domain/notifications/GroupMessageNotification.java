package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.domain.Group;
import core.domain.User;
import core.interfaces.NotificationVisitor;

public class GroupMessageNotification extends Notification {

	private User sender;
	private Group group;
	private boolean urgent = false;
	
	public GroupMessageNotification(int id, Date d, boolean s, boolean r, User u, Group g, boolean urgent) {
		super(id, d, s, r);
		this.sender = u;
		this.group = g;
		this.urgent = urgent;
	}
	
	public GroupMessageNotification(int subjectId, int senderId) {
		super(subjectId, senderId);
	}

	public GroupMessageNotification(int id, int receiverId, int subjectId) {
		super(id, receiverId, subjectId);
	}

	@Override
	public void accept(NotificationVisitor nv) {
		nv.visit(this);
	}
	
	@Override
	public String accept(MapperCreationVisitor v) {
		return v.visit(this);
	}

	@Override
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
	
	

}
