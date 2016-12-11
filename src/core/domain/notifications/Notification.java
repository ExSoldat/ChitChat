package core.domain.notifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.interfaces.NotificationVisitable;

public abstract class Notification implements NotificationVisitable {
	public int id, subjectId, receiverId;
	public Date date;
	public boolean sent, received;
	public DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy à HH:mm");
	
	public static final int FRIEND_REQUEST = 1;
	public static final int FRIEND_REQUEST_ACCEPT = 2;
	public static final int PRIVATE_MESSAGE = 3;
	public static final int GROUP_MESSAGE = 4;
	
	
	public Notification(int id, Date date, boolean sent, boolean received) {
		this.id = id;
		this.date = date;
		this.sent = sent;
		this.received = received;
	}
	
	public Notification(int receiverId, int subjectId) {
		this.subjectId = subjectId;
		this.receiverId = receiverId;
	}
	
	public Notification(int id, int receiverId, int subjectId) {
		this.id = id;
		this.subjectId = subjectId;
		this.receiverId = receiverId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}
	
	public String getFormattedDate() {
		return dateFormat.format(date);
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	
	
}
