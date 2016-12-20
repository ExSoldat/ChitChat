package ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;

import core.App;
import core.domain.notifications.GUINotificationVisitor;
import ui.components.CCColor;
import ui.components.CCFriendsPane;
import utils.Constants;

public class CCNotifications extends JPanel {
	JTabbedPane mParent;
	CCFriendsPane friendsPane;
	int notificationsNumber = 0;

	public CCNotifications(JTabbedPane parent, CCFriendsPane fp) {
		JPanel main = new JPanel();
		this.mParent = parent;
		this.friendsPane = fp;
		notificationsNumber = 0;
		JScrollPane scroll = new JScrollPane();
	    scroll.setLayout(new ScrollPaneLayout());
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel notifications = new JPanel();
		notifications.setLayout(new BoxLayout(notifications, BoxLayout.Y_AXIS));
		if(App.getInstance().getLoggedUser().getPendingNotifications() != null) {
			notificationsNumber = App.getInstance().getLoggedUser().getPendingNotifications().size();
			for(int i = 0; i < App.getInstance().getLoggedUser().getPendingNotifications().size(); i++) {
				notifications.add(App.getInstance().getLoggedUser().getPendingNotifications().get(i).accept(new GUINotificationVisitor(this)));
			}
		}
		
		scroll.setPreferredSize(Constants.ALL_NOTIFICATIONS);
		
		scroll.setViewportView(notifications);
		main.add(scroll);
		this.add(main);
	}

	public void refreshTitle() {
		if(notificationsNumber == 0) {
			mParent.setTitleAt(mParent.getTabCount()-1, "Notifications");
			mParent.setBackgroundAt(mParent.getTabCount()-1, UIManager.getColor ( "Panel.background" ));
		} else {
			mParent.setTitleAt(mParent.getTabCount()-1, "Notifications (" + notificationsNumber + ")");
			mParent.setBackgroundAt(mParent.getTabCount()-1, CCColor.CCDANGER.getColor());
		}
	}
	
	public void handleAction() {
		this.notificationsNumber--;
		//I only refresh the friends view becaue we can only accept friend requests
		friendsPane.refreshFriends();
		refreshTitle();
	}
}
