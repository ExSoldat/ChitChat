package core.domain.notifications;

public class NotificationFactory {
	public static final int FRIEND_REQUEST = 1;
	public static final int FRIEND_REQUEST_ACCEPT = 2;
	public static final int PRIVATE_MESSAGE = 3;
	public static final int GROUP_MESSAGE = 4;
	
	public Notification createNewNotification(int typeId, int subjectId, int senderId) {
		Notification n = null;
		switch(typeId) {
		case FRIEND_REQUEST :
			n = new FriendAddNotification(subjectId, senderId);
			break;
		case FRIEND_REQUEST_ACCEPT :
			n = new FriendAcceptNotification(subjectId, senderId);
			break;
		case PRIVATE_MESSAGE :
			n = new FriendMessageNotification(subjectId, senderId);
			break;
		case GROUP_MESSAGE : 
			n = new GroupMessageNotification(subjectId, senderId);
			break;
		}
		return n;
	}
	
}
