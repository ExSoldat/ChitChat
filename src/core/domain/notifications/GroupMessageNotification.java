package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.domain.Group;
import core.domain.User;

public class GroupMessageNotification extends Notification {

	private User sender;
	private Group group;
	private boolean urgent = false;
	/*
	public GroupMessageNotification(int id, Date d, boolean s, boolean r, User u, Group g, boolean urgent) {
		super(id, d, s, r);
		this.sender = u;
		this.group = g;
		this.urgent = urgent;
	}
	
	public GroupMessageNotification(int subjectId, int senderId) {
		super(subjectId, senderId);
	}
	*/

	/**
	 * The notification constructor 
	 * @param id
	 * @param receiverId
	 * @param subjectId
	 */
	public GroupMessageNotification(int id, int receiverId, int subjectId) {
		super(id, receiverId, subjectId);
	}
	
	@Override
	/**
	 * The MapperCreationVisitor. It sends a correct sql query to insert this data in the database
	 */
	public String accept(MapperCreationVisitor v) {
		return v.visit(this);
	}

	@Override
	/**
	 * The GUINOtificationVisitor acceptation method. It sends back a JPanel to be displayed in the ui
	 */
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
