package core.domain.notifications;

import java.util.Date;

import javax.swing.JPanel;

import core.App;
import core.domain.User;
import core.domain.proxy.ProxyUser;

public class FriendAcceptNotification extends Notification {

	private ProxyUser sender;
//	public FriendAcceptNotification(int id, Date date, boolean sent, boolean received, ProxyUser sender) {
//		super(id, date, sent, received);
//		this.sender = sender;
//	}
//
//	public FriendAcceptNotification(int receiverId, int subjectId) {
//		super(receiverId, subjectId);
//		this.setSender(App.getInstance().getServicesProvider().getUserById(subjectId));
//	}

	/**
	 * The notification constructor
	 * @param id
	 * @param receiverId
	 * @param subjectId
	 */
	public FriendAcceptNotification(int id, int receiverId, int subjectId) {
		super(id, receiverId, subjectId);
		this.setSender(App.getInstance().getServicesProvider().getUserById(subjectId));
	}
	@Override
	/**
	 * The GUINOtificationVisitor acceptation method. It sends back a JPanel to be displayed in the ui
	 */
	public JPanel accept(GUINotificationVisitor v) {
		return v.visit(this);
	}
	
	@Override
	/**
	 * The MapperCreationVisitor. It sends a correct sql query to insert this data in the database
	 */
	public String accept(MapperCreationVisitor v) {
		return v.visit(this);
	}
	

	public ProxyUser getSender() {
		return sender;
	}

	public void setSender(ProxyUser sender) {
		this.sender = sender;
	}


	

}
