package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.App;
import core.domain.User;
import core.domain.proxy.ProxyUser;
import core.interfaces.NotificationVisitor;

public class FriendAddNotification extends Notification {
	
	private boolean accepted;
	private ProxyUser sender;

	public FriendAddNotification(int id, Date date, boolean sent, boolean received, boolean accepted, ProxyUser s) {
		super(id, date, sent, received);
		this.accepted = accepted;
		this.sender = s;
	}
	
	public FriendAddNotification(int receiverId, int subjectId) {
		super(receiverId, subjectId);
		this.setSender(App.getInstance().getServicesProvider().getUserById(subjectId));
	}
	
	public FriendAddNotification(int id, int receiverId, int subjectId) {
		super(id, receiverId, subjectId);
		this.setSender(App.getInstance().getServicesProvider().getUserById(subjectId));
	}

	@Override
	public void accept(NotificationVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}
	
	@Override
	public String accept(MapperCreationVisitor v) {
		return v.visit(this);
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public ProxyUser getSender() {
		return sender;
	}

	public void setSender(ProxyUser sender) {
		this.sender = sender;
	}
	
	
}
