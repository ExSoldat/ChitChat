package core.domain.notifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.interfaces.NotificationVisitable;

public abstract class Notification implements NotificationVisitable {
	public int id, subjectId, senderId;
	public Date date;
	public boolean sent, received;
	public DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy à HH:mm");
	
	public Notification(int id, Date date, boolean sent, boolean received) {
		this.id = id;
		this.date = date;
		this.sent = sent;
		this.received = received;
	}
	
	public Notification(int subjectId, int senderId) {
		this.subjectId = subjectId;
		this.senderId = senderId;
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
}
