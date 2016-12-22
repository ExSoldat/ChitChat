package core.domain.notifications;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.App;
import core.service.ServicesProvider;
import ui.CCGroupChatFrame;
import ui.CCNotifications;
import ui.CCPrivateChatFrame;
import ui.components.CCButton;
import ui.components.CCColor;
import ui.components.CCLabel;
import ui.components.CCPlainText;
import utils.Constants;

/**
 * A class that implements the visitor fr the notifications.
 * It is used to get the data we need to display the notification
 * @author Mathieu
 *
 */
public class GUINotificationVisitor implements NotificationVisitor {
	
	private String title, date, content;
	private CCButton positive, negative;
	private CCNotifications mParent;
	
	public GUINotificationVisitor(CCNotifications parentpanel) {
		this.mParent = parentpanel;
	}
	
	@Override
	/**
	 * The implementation of the visitor for a friend add notfication
	 */
	public JPanel visit(FriendAddNotification v) {
		//We set the title of the notification usin the notification given in parameters
		title = "New friend request by :" + v.getSender().getFirstname() + " " + v.getSender().getLastname();
		//date = "["+v.getFormattedDate()+"]";
		//The content is what is left by the hobies
		content = ("Likes : " + v.getSender().getHobbiesAsAString()).substring(0, 30) + "...";
		
		//We create the panel of the notification
		JPanel notificationContent = new JPanel();
		notificationContent.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		ImageIcon imageurl = new ImageIcon("img/ic_person_orange.png");
		JLabel notifImage = new JLabel(imageurl);
		
		// We set the title, the content and the two action buttons
		CCLabel notifTitle = new CCLabel(title);
		JLabel notifDate = new JLabel(date);
		CCPlainText notifContent = new CCPlainText(content);
		positive = new CCButton("Accept", Constants.BUTTON_MAIN);
		negative = new CCButton("Ignore", Constants.BUTTON_DANGER);
		
		positive.addActionListener(new ActionListener() {
			//A click on the positive button should update the notification status. Te parent andleAction() method is used to refresh the users friend list.
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				//AcceptFriendRequest
				App.getInstance().getServicesProvider().acceptFriendRequest(App.getInstance().getLoggedUser(), v.getSender(), v);
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});

		negative.addActionListener(new ActionListener() {
			
			@Override
			//a click to the negative button should ignore the notification
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				//v.save();
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});
		

		notificationContent.setBackground(CCColor.CCNEARLYGREY.getColor());
		
		notificationContent.setBorder(BorderFactory.createLineBorder(CCColor.CCNEARLYWHITE.getColor()));
		
		cs.insets = new Insets(10, 10, 10, 10);
		
		cs.gridx = 1;
		cs.gridy = 0;
		notificationContent.add(notifTitle, cs);
		cs.gridx = 2;
		cs.gridy = 0;
		notificationContent.add(notifDate, cs);
		cs.gridx = 1;
		cs.gridy = 1;
		notificationContent.add(notifImage, cs);
		cs.gridx = 2;
		cs.gridy = 1;
		notificationContent.add(notifContent, cs);
		cs.gridx = 1;
		cs.gridy = 2;
		notificationContent.add(positive, cs);
		cs.gridx = 2;
		cs.gridy = 2;
		notificationContent.add(negative, cs);
		
		//notificationContent.setSize(Constants.ALL_GROUPS_DIMENSION);
		return notificationContent;
	}

	@Override
	/**
	 * Create a group message notification visitor.
	 * It is the same pattern of the others...
	 */
	public JPanel visit(GroupMessageNotification v) {
		
		//The title of the notification is the name of the group
		title = v.getGroup().getName();
		//date = "["+v.getFormattedDate()+"]";
		//We create a notification for the last message
		content = v.getGroup().getLastMessage().getContent().substring(0, 30) + "...";
		
		JPanel notificationContent = new JPanel();
		notificationContent.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		JLabel notifImage = new JLabel(imageurl);
		
		CCLabel notifTitle = new CCLabel(title);
		JLabel notifDate = new JLabel(date);
		CCPlainText notifContent = new CCPlainText(content);
		positive = new CCButton("See", Constants.BUTTON_MAIN);
		negative = new CCButton("Ignore", Constants.BUTTON_DANGER);
		
		positive.addActionListener(new ActionListener() {
			//The click on the positive button shoulf open the corresponding chatframe
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				CCGroupChatFrame ccgcf = new CCGroupChatFrame(App.getInstance().getLoggedUser(), v.getGroup());
				ccgcf.init();
				ccgcf.setVisible(true);
				App.getInstance().getServicesProvider().updateNotification(v);
				//v.save();
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});

		negative.addActionListener(new ActionListener() {
			//We update the notification status after, and we destroy the notification
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(false);
				App.getInstance().getServicesProvider().updateNotification(v);
				//v.save();
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});
		
		if(v.isUrgent()) {
			notificationContent.setBackground(CCColor.CCDANGER.getColor());
		} else {
			notificationContent.setBackground(CCColor.CCNEARLYGREY.getColor());
		}
		
		notificationContent.setBorder(BorderFactory.createLineBorder(CCColor.CCNEARLYWHITE.getColor()));
		
		cs.insets = new Insets(10, 10, 10, 10);
		
		cs.gridx = 1;
		cs.gridy = 0;
		notificationContent.add(notifTitle, cs);
		cs.gridx = 2;
		cs.gridy = 0;
		notificationContent.add(notifDate, cs);
		cs.gridx = 1;
		cs.gridy = 1;
		notificationContent.add(notifImage, cs);
		cs.gridx = 2;
		cs.gridy = 1;
		notificationContent.add(notifContent, cs);
		cs.gridx = 1;
		cs.gridy = 2;
		notificationContent.add(positive, cs);
		cs.gridx = 2;
		cs.gridy = 2;
		notificationContent.add(negative, cs);
		
		//notificationContent.setSize(Constants.ALL_GROUPS_DIMENSION);
		return notificationContent;
	}

	@Override
	public JPanel visit(FriendMessageNotification v) {

		
		title = "New message from : " + v.getSender().getFirstname() + " " + v.getSender().getLastname();
		//date = "["+v.getFormattedDate()+"]";
		content = v.getMessage().substring(0, 30) + "...";
		
		JPanel notificationContent = new JPanel();
		notificationContent.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		JLabel notifImage = new JLabel(imageurl);
		
		CCLabel notifTitle = new CCLabel(title);
		JLabel notifDate = new JLabel(date);
		CCPlainText notifContent = new CCPlainText(content);
		positive = new CCButton("See", Constants.BUTTON_MAIN);
		negative = new CCButton("Ignore", Constants.BUTTON_DANGER);
		
		positive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				CCPrivateChatFrame ccpcf = new CCPrivateChatFrame(App.getInstance().getLoggedUser(), v.getSender());
				ccpcf.init();
				ccpcf.setVisible(true);
				//v.save();
				
				App.getInstance().getServicesProvider().updateNotification(v);
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});

		negative.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				App.getInstance().getServicesProvider().updateNotification(v);
				//v.save();
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});
		
		if(v.isUrgent()) {
			notificationContent.setBackground(CCColor.CCDANGER.getColor());
		} else {
			notificationContent.setBackground(CCColor.CCNEARLYGREY.getColor());
		}
		
		notificationContent.setBorder(BorderFactory.createLineBorder(CCColor.CCNEARLYWHITE.getColor()));
		
		cs.insets = new Insets(10, 10, 10, 10);
		
		cs.gridx = 1;
		cs.gridy = 0;
		notificationContent.add(notifTitle, cs);
		cs.gridx = 2;
		cs.gridy = 0;
		notificationContent.add(notifDate, cs);
		cs.gridx = 1;
		cs.gridy = 1;
		notificationContent.add(notifImage, cs);
		cs.gridx = 2;
		cs.gridy = 1;
		notificationContent.add(notifContent, cs);
		cs.gridx = 1;
		cs.gridy = 2;
		notificationContent.add(positive, cs);
		cs.gridx = 2;
		cs.gridy = 2;
		notificationContent.add(negative, cs);
		
		//notificationContent.setSize(Constants.ALL_GROUPS_DIMENSION);
		return notificationContent;
	}

	

	@Override
	public JPanel visit(FriendAcceptNotification v) {
		title = v.getSender().getFirstname() + " " + v.getSender().getLastname() + " accepted your request";
		//date = "["+v.getFormattedDate()+"]";
		content = ("Let's start some chit chat");
		
		JPanel notificationContent = new JPanel();
		notificationContent.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		ImageIcon imageurl = new ImageIcon("img/ic_person_orange.png");
		JLabel notifImage = new JLabel(imageurl);
		
		CCLabel notifTitle = new CCLabel(title);
		JLabel notifDate = new JLabel(date);
		CCPlainText notifContent = new CCPlainText(content);
		positive = new CCButton("Chat", Constants.BUTTON_MAIN);
		negative = new CCButton("Ignore", Constants.BUTTON_DANGER);
		
		positive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				//AcceptFriendRequest
				CCPrivateChatFrame ccpcf = new CCPrivateChatFrame(App.getInstance().getLoggedUser(), v.getSender());
				ccpcf.init();
				ccpcf.setVisible(true);
				App.getInstance().getServicesProvider().updateNotification(v);
				//v.save();
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});

		negative.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				App.getInstance().getServicesProvider().updateNotification(v);
				//v.save();
				mParent.handleAction();
				notificationContent.setVisible(false);
			}
		});
		

		notificationContent.setBackground(CCColor.CCNEARLYGREY.getColor());
		
		notificationContent.setBorder(BorderFactory.createLineBorder(CCColor.CCNEARLYWHITE.getColor()));
		
		cs.insets = new Insets(10, 10, 10, 10);
		
		cs.gridx = 1;
		cs.gridy = 0;
		notificationContent.add(notifTitle, cs);
		cs.gridx = 2;
		cs.gridy = 0;
		notificationContent.add(notifDate, cs);
		cs.gridx = 1;
		cs.gridy = 1;
		notificationContent.add(notifImage, cs);
		cs.gridx = 2;
		cs.gridy = 1;
		notificationContent.add(notifContent, cs);
		cs.gridx = 1;
		cs.gridy = 2;
		notificationContent.add(positive, cs);
		cs.gridx = 2;
		cs.gridy = 2;
		notificationContent.add(negative, cs);
		
		//notificationContent.setSize(Constants.ALL_GROUPS_DIMENSION);
		return notificationContent;
	}

}
