package core.domain.notifications;

import java.awt.Component;

import javax.swing.JPanel;

/**
 * The Visitor notifications interface, to ensure the implement the god methods
 * @author Mathieu
 *
 */
public interface NotificationVisitor {
	public Object visit(GroupMessageNotification v);
	public Object visit(FriendMessageNotification v);
	public Object visit(FriendAddNotification v);
	public Object visit(FriendAcceptNotification v);
}
	