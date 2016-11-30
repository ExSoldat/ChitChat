package core.interfaces;

import java.awt.Component;

import javax.swing.JPanel;

import core.domain.notifications.GUINotificationVisitor;

public interface NotificationVisitable {
	public void accept(NotificationVisitor v);
	public JPanel accept(GUINotificationVisitor v);
}
