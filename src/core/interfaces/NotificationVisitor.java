package core.interfaces;

import core.domain.notifications.FriendMessageNotification;
import core.domain.notifications.GroupMessageNotification;
import core.domain.notifications.FriendAddNotification;

import java.awt.Component;

import javax.swing.JPanel;

import core.domain.notifications.FriendAcceptNotification;

public interface NotificationVisitor {
	public Object visit(GroupMessageNotification v);
	public Object visit(FriendMessageNotification v);
	public Object visit(FriendAddNotification v);
	public Object visit(FriendAcceptNotification v);
}
	