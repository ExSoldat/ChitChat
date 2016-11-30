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
import core.interfaces.NotificationVisitor;
import ui.CCGroupChatFrame;
import ui.components.CCButton;
import ui.components.CCColor;
import ui.components.CCLabel;
import ui.components.CCPlainText;
import utils.Constants;

public class GUINotificationVisitor implements NotificationVisitor {
	
	private String title, date, content;
	private CCButton positive, negative;

	@Override
	public JPanel visit(GroupMessageNotification v) {
		
		title = v.getGroup().getName();
		date = "["+v.getFormattedDate()+"]";
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(true);
				CCGroupChatFrame ccgcf = new CCGroupChatFrame(App.getInstance().getLoggedUser(), v.getGroup());
				//v.save();
			}
		});

		negative.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setReceived(false);
				//v.save();
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
		
		cs.gridx = 0;
		cs.gridy = 0;
		notificationContent.add(notifTitle, cs);
		cs.gridx = 1;
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
		// TODO Auto-generated method stub
	}

	@Override
	public JPanel visit(FriendAddNotification v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel visit(FriendAcceptNotification v) {
		// TODO Auto-generated method stub
		
	}

}
