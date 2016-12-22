package core.domain.notifications;

import java.awt.Component;

import javax.swing.JPanel;

/**
 * The Visitable notiiations interface, to ensure they accept the methd they should
 * @author Mathieu
 *
 */
public interface NotificationVisitable {
	public JPanel accept(GUINotificationVisitor v);
	public String accept(MapperCreationVisitor v);
}
