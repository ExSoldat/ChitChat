package core.interfaces;

import core.domain.notifications.FriendMessageNotification;
import core.domain.notifications.GroupMessageNotification;
import core.domain.notifications.FriendAddNotification;

import java.awt.Component;

import javax.swing.JPanel;

import core.domain.notifications.FriendAcceptNotification;

public interface NotificationVisitor {
	public JPanel visit(GroupMessageNotification v);
	public JPanel visit(FriendMessageNotification v);
	public JPanel visit(FriendAddNotification v);
	public JPanel visit(FriendAcceptNotification v);
}
	