package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.domain.User;
import core.domain.proxy.ProxyUser;
import core.interfaces.NotificationVisitor;

public class FriendMessageNotification extends Notification {
	private boolean urgent = false;
	private ProxyUser sender;
	private String message; //Should have done a Conversation class but it is too late

	public FriendMessageNotification(int id, Date date, boolean sent, boolean received, boolean urgent, ProxyUser s, String content) {
		super(id, date, sent, received);
		this.urgent = urgent;
		this.sender = s;
		this.message = content;
	}

	public FriendMessageNotification(int subjectId, int senderId) {
		super(subjectId, senderId);
	}

	public FriendMessageNotification(int id, int receiverId, int subjectId) {
		super(id, receiverId, subjectId);
	}

	@Override
	public void accept(NotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String accept(MapperCreationVisitor v) {
		return v.visit(this);
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

	public ProxyUser getSender() {
		return sender;
	}

	public void setSender(ProxyUser sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
