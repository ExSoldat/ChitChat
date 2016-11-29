package core.interfaces;

import core.domain.notifications.GUINotificationVisitor;

public interface NotificationVisitable {
	public void accept(NotificationVisitor v);
	public void accept(GUINotificationVisitor v);
}
