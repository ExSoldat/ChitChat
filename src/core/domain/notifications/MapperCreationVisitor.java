package core.domain.notifications;

import javax.swing.JPanel;

import core.interfaces.NotificationVisitor;
import core.persistence.mapper.NotificationMapper;

public class MapperCreationVisitor implements NotificationVisitor {

	@Override
	public String visit(GroupMessageNotification v) {
		return "INSERT INTO " + NotificationMapper.sql_table + "(" + NotificationMapper.sql_type 
				+ ", " + NotificationMapper.sql_seen 
				+ ", " + NotificationMapper.sql_receiverid
				+ ", " + NotificationMapper.sql_subjectid
				+ ") values (" + Notification.GROUP_MESSAGE + ", ?, ?, ?);";
	}

	@Override
	public String visit(FriendMessageNotification v) {
		return "INSERT INTO " + NotificationMapper.sql_table + "(" + NotificationMapper.sql_type 
				+ ", " + NotificationMapper.sql_seen 
				+ ", " + NotificationMapper.sql_receiverid
				+ ", " + NotificationMapper.sql_subjectid
				+ ") values (" + Notification.PRIVATE_MESSAGE + ", ?, ?, ?);";
	}

	@Override
	public String visit(FriendAddNotification v) {
		return "INSERT INTO " + NotificationMapper.sql_table + "(" + NotificationMapper.sql_type 
				+ ", " + NotificationMapper.sql_seen 
				+ ", " + NotificationMapper.sql_receiverid
				+ ", " + NotificationMapper.sql_subjectid
				+ ") values (" + Notification.FRIEND_REQUEST + ", ?, ?, ?);";
	}

	@Override
	public String visit(FriendAcceptNotification v) {
		return "INSERT INTO " + NotificationMapper.sql_table + "(" + NotificationMapper.sql_type 
				+ ", " + NotificationMapper.sql_seen 
				+ ", " + NotificationMapper.sql_receiverid
				+ ", " + NotificationMapper.sql_subjectid
				+ ") values (" + Notification.FRIEND_REQUEST_ACCEPT + ", ?, ?, ?);";
	}

}
