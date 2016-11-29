package core.domain.notifications;

import java.util.Date;

import core.interfaces.NotificationVisitable;

public abstract class Notification implements NotificationVisitable {
	public int id;
	public Date date;
	public boolean sent, received;
	
	public Notification(int id, Date date, boolean sent, boolean received) {
		this.id = id;
		this.date = date;
		this.sent = sent;
		this.received = received;
	}
}
