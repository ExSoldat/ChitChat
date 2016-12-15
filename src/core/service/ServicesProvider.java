package core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import core.App;
import core.domain.Administrateur;
import core.domain.Discussion;
import core.domain.Group;
import core.domain.Message;
import core.domain.User;
import core.domain.notifications.FriendAcceptNotification;
import core.domain.notifications.Notification;
import core.domain.notifications.NotificationFactory;
import core.domain.proxy.ProxyGroup;
import core.domain.proxy.ProxyUser;
import core.persistence.mapper.DiscussionMapper;
import core.persistence.mapper.GroupAdministratorMapper;
import core.persistence.mapper.GroupMapper;
import core.persistence.mapper.GroupParticipantMapper;
import core.persistence.mapper.MessageMapper;
import core.persistence.mapper.NotificationMapper;
import core.persistence.mapper.UserFriendsMapper;
import core.persistence.mapper.UserMapper;
import utils.Constants;
import utils.LogUtils;

public class ServicesProvider {
	public static String TAG = "ServicesProvider";
	Random rand = new Random();
	NotificationFactory nf;
	public ServicesProvider() {
		nf = new NotificationFactory();
	}
	
	/**
	 * A service used to connect an user
	 * @param user the username used to leg
	 * @param pwd the password used to log
	 * @return an user if the credentials are correct
	 */
	public ProxyUser connect(String user, String pwd) {
		ArrayList<ProxyUser> allusers = UserMapper.getInstance().read();
		for(int i = 0; i<allusers.size(); i++) {
			if(allusers.get(i).getUsername().equalsIgnoreCase(user) && allusers.get(i).getPassword().equals(pwd))
				return allusers.get(i);
		}
		return null;
	}

	/**
	 * A function used to get groups for a given user
	 * @param id the id of the user
	 * @return the list of the groups 
	 */
	public ArrayList<ProxyGroup> getGroupsForUser(int id) {
		//TODO add a group list to users and call groupparticipantsmapper to get this list. then send it back
		return (GroupParticipantMapper.getInstance().readGroupsByUserId(id) != null) ? GroupParticipantMapper.getInstance().readGroupsByUserId(id) : new ArrayList<ProxyGroup>();
	}

	/**
	 * A function to get the Friends of an user
	 * @param id the user of the user we want to ghave the friends for
	 * @return the list of users found
	 */
	public ArrayList<ProxyUser> getFriendsForUser(int id) {
		return UserFriendsMapper.getInstance().readByUserId(id);
	}
	
	

	/**
	 * Delete one of our friends
	 * @param deletedFriend the friend to be delmeted
	 * @return true if eerything went fine, false otherwise
	 */
	public boolean deleteFriend(User deletedUser) {
		boolean r = UserFriendsMapper.getInstance().delete(deletedUser);
		if (r)
			LogUtils.log(TAG, Constants.RESPONSE, "Friend deleted !");
		else 
			LogUtils.log(TAG, Constants.ERROR, "Unable to delete user !");
		return r;
	}

	/**
	 * A function used to get an user when he is not already a friend
	 * @param name the name field of the search 
	 * @param firstname the firstname field of the search
	 * @param username the username field of the search
	 * @return the list of the suers found
	 */
	public ArrayList<ProxyUser> searchUser(String lastname, String firstname, String username) {
		ArrayList<ProxyUser> mapperResult =  UserMapper.getInstance().readByNameFields(username, firstname, lastname);
		ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
		if(App.getInstance().getLoggedUser().getFriends() != null) {
			if(mapperResult != null) {
				for(int i = 0; i < mapperResult.size(); i++) {
					if(!App.getInstance().getLoggedUser().getFriends().contains(mapperResult.get(i)) && mapperResult.get(i).getId() != App.getInstance().getLoggedUser().getId()) {
						result.add(mapperResult.get(i));
					}
				}
			}
			return result;
		} else {
			return mapperResult;
		}
	}
	
	/**
	 * A function used to get an user when he is not already a friend
	 * @param name the name field of the search 
	 * @param firstname the firstname field of the search
	 * @param username the username field of the search
	 * @return the list of the suers found
	 */
	public ArrayList<ProxyUser> searchUserToAddInGroups(String lastname, String firstname, String username) {
		ArrayList<ProxyUser> mapperResult =  UserMapper.getInstance().readByNameFields(username, firstname, lastname);
		ArrayList<ProxyUser> result = new ArrayList<ProxyUser>();
		if(App.getInstance().getLoggedUser().getFriends() != null) {
			if(mapperResult != null) {
				for(int i = 0; i < mapperResult.size(); i++) {
					if(mapperResult.get(i).getId() != App.getInstance().getLoggedUser().getId()) {
						result.add(mapperResult.get(i));
					}
				}
			}
			return result;
		} else {
			return mapperResult;
		}
	}
	
	/**
	 * Add a friend to our friendslist
	 * @param addedUser the user to add
	 * @return true if everything went fine, false either
	 */
	public boolean addFriend(User addedUser) {
		//TODO Create a Notification
		Notification n = nf.createNewNotification(0, Notification.FRIEND_REQUEST, addedUser.getId(), App.getInstance().getLoggedUser().getId());
		NotificationMapper.getInstance().create(n);
		boolean r = UserFriendsMapper.getInstance().addUserToFriendsList(App.getInstance().getLoggedUser(), addedUser);
		if (r)
			LogUtils.log(TAG, Constants.RESPONSE, "Friend added !");
		else 
			LogUtils.log(TAG, Constants.ERROR, "Unable to add user !");
		return r;
	}

	/**
	 * Create a group in the database
	 * @param mGroup the group we want to create
	 * @return true if everything went fine, false either
	 */
	public boolean createGroup(Group mGroup) {
		int dId = DiscussionMapper.getInstance().create();
		if(dId != -1) {
			mGroup.setDiscussionId(dId);
			
			int gId = GroupMapper.getInstance().create(mGroup);
			if(gId != -1) {
				mGroup.setId(gId);
				return GroupAdministratorMapper.getInstance().create(mGroup) && GroupParticipantMapper.getInstance().create(mGroup, App.getInstance().getLoggedUser());
			} else {
				return false;
			}
		} 
		
		return false;	
	}

	/**
	 * Disconnect a logged user
	 * @param loggedUser
	 * @return true if everything went fine. False otherwise
	 */
	public boolean disconnect(User loggedUser) {
		return true;
	}

	/**
	 * Get the conversation between a logged user and another
	 * @param me the logged user
	 * @param him the user we want to have a conversation with
	 * @return the list of the messages between the two users
	 */
	public Discussion getMessagesBetween(ProxyUser me, ProxyUser him) {
		return DiscussionMapper.getInstance().readById(UserFriendsMapper.getInstance().readDiscussionIdByMeAndHim(me, him));
	}

	/**
	 * Sends a message to the user (Use something in the persistence so we can have all fields set in the database depending on the message type
	 * @param m the sent message
	 */
	public boolean sendMessage(Message m) {
		//return rand.nextBoolean();
		return MessageMapper.getInstance().create(m);
	}

	/**
	 * Get the messages for a given group
	 * @param group the group we want to have the data from
	 * @return a list of the messages
	 */
	public Discussion getMessagesForGroup(Group group) {
		Discussion r = new Discussion();
		return DiscussionMapper.getInstance().readById(group.getDiscussionId());
		/*r.add(new Message(me, group, "Hello", new Date()));
		r.add(new Message(new User(0, "DOE", "janedoe", "Jane"), group, "How are you ?", new Date()));
		r.add(new Message(me, group, "Fine thanks and you ?", new Date()));
		r.add(new Message(new User(0, "Zamasu", "Zamasu", "Zamasu"), group, "Filthy humans....", new Date()));
		r.add(new Message(new User(0, "Black", "Son", "Goku"), group, "Damn right bro :*", new Date()));
		r.add(new Message(new User(0, "DOE", "janedoe", "Jane"), group, "That's not nice Bro", new Date()));
		r.add(new Message(me, group, "Okay", new Date()));
		r.add(new Message(me, group, "Where's Trunks ?", new Date()));
		r.add(new Message(new User(0, "Sama", "killergodtuber", "Gowasu"), group, "Zamas ! Bring me some tea !", new Date()));
		r.add(new Message(new User(0, "Zamasu", "Zamasu", "Zamasu"), group, "No way. I'm Justice bitch", new Date()));
		r.add(new Message(me, group, "Damn", new Date()));*/
	}

	public boolean deleteUser(User deleteduser) {
		boolean r1 = UserFriendsMapper.getInstance().delete(deleteduser);
		boolean r2 = UserMapper.getInstance().delete(deleteduser);
		
		return r1 && r2;
	}

	public boolean createUser(User createdUser) {
		return UserMapper.getInstance().create(createdUser);
	}
	
	public boolean updateUser(ProxyUser updatedUser) {
		return UserMapper.getInstance().update(updatedUser);
	}

	public boolean removeUserFromGroup(User user, Group group) {
		//TODO move this to the domain
		if(user.equals(group.getAdministrator())) {
			if(group.getParticipants().size() == 1) //In this case the administrator is the last 
				return GroupMapper.getInstance().delete(group); //We should delete the entire group if even the administrator leaves
		} else {
			return GroupParticipantMapper.getInstance().delete(user, group);

		}
		return false;
	}

	/**
	 * A function that returns all users except the administrator, it is only user by the Administrator
	 * @return the list of all the users
	 */
	public ArrayList<ProxyUser> getAllUsers() {
		ArrayList<ProxyUser> result = UserMapper.getInstance().read();
		result.remove(App.getInstance().getLoggedUser());
		return result;
	}

	public ProxyUser getAdministratorOfGroup(ProxyGroup proxyGroup) {
		return GroupAdministratorMapper.getInstance().readAdminById(proxyGroup.getId());
	}

	public ArrayList<ProxyUser> getParticipantsOfGroup(ProxyGroup group) {
		return GroupParticipantMapper.getInstance().readUsersByGroupId(group.getId());
	}

	public Integer getDiscussionIdBetween(ProxyUser me, ProxyUser him) {
		return UserFriendsMapper.getInstance().readDiscussionIdByMeAndHim(me, him);
	}

	public void addParticipantToGroup(Group group, ProxyUser user) {
		group.getParticipants().add(user); //TODO Keep this kind of things but implement it in service 
		GroupParticipantMapper.getInstance().create(group, user);		
	}

	public ArrayList<Notification> getNotificationsForUser(ProxyUser user) {
		return NotificationMapper.getInstance().readByReceiver(user);
	}

	public ProxyUser getUserById(int userId) {
		return UserMapper.getInstance().readById(userId);
	}

	public boolean acceptFriendRequest(ProxyUser me, User him, Notification old_notification) {
		boolean b1 = UserFriendsMapper.getInstance().saveIsActive(me, him);
		boolean b2 = NotificationMapper.getInstance().update(old_notification);
		if(b1) {
			Notification n = nf.createNewNotification(0, Notification.FRIEND_REQUEST_ACCEPT, him.getId(), me.getId());
			return NotificationMapper.getInstance().create(n);
		}
		return false;
	}

	public void updateNotification(Notification v) {
		NotificationMapper.getInstance().update(v);
	}
}
