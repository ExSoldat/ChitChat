package core.interfaces;

import core.domain.notifications.FriendMessageNotification;
import core.domain.notifications.GroupMessageNotification;
import core.domain.notifications.FriendAddNotification;
import core.domain.notifications.FriendAcceptNotification;

public interface NotificationVisitor {
	public void visit(GroupMessageNotification v);
	public void visit(FriendMessageNotification v);
	public void visit(FriendAddNotification v);
	public void visit(FriendAcceptNotification v);
}
	