package core.domain.notifications;

public class NotificationFactory {
	
	public Notification createNewNotification(int id, int typeId, int receiverId, int subjectId) {
		Notification n = null;
		switch(typeId) {
		case Notification.FRIEND_REQUEST :
			n = new FriendAddNotification(id, receiverId, subjectId);
			break;
		case Notification.FRIEND_REQUEST_ACCEPT :
			n = new FriendAcceptNotification(id, receiverId, subjectId);
			break;
		case Notification.PRIVATE_MESSAGE :
			n = new FriendMessageNotification(id, receiverId, subjectId);
			break;
		case Notification.GROUP_MESSAGE : 
			n = new GroupMessageNotification(id, receiverId, subjectId);
			break;
		}
		return n;
	}
	
}
