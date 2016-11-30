package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.App;
import core.domain.Group;
import core.domain.User;
import core.domain.notifications.GUINotificationVisitor;
import core.domain.notifications.GroupMessageNotification;
import core.domain.notifications.Notification;
import ui.actions.TriggerButtonOnType;
import ui.components.CCButton;
import ui.components.CCColor;
import ui.components.CCDialog;
import ui.components.CCUserList;
import ui.components.form.CCFormTextEntry;
import utils.Constants;

public class CCNotificationsFrame extends JFrame {

	public void init() {
		JPanel main = new JPanel();
		JScrollPane scroll = new JScrollPane();
	    scroll.setLayout(new ScrollPaneLayout());
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel notifications = new JPanel();
		notifications.setLayout(new BoxLayout(notifications, BoxLayout.Y_AXIS));
		//TODO
		ArrayList<Notification> usersNotifications = new ArrayList<Notification>();
		Group testgroup = new Group("Test", App.getInstance().getLoggedUser(), "Hello");
		Group testgroup2 = new Group("Test", App.getInstance().getLoggedUser(), "Hello");
		usersNotifications.add(new GroupMessageNotification(0, new Date(), true, false, new User(1,"Goku", "savioroftheworld", "Son"), testgroup2, true));
		usersNotifications.add(new GroupMessageNotification(1, new Date(), true, false, new User(2,"zeze", "saviorofthzefezfzeworld", "Sozfezfn"), testgroup, false));

		for(int i = 0; i < usersNotifications.size(); i++) {
			notifications.add(usersNotifications.get(i).accept(new GUINotificationVisitor()));
			notifications.add(usersNotifications.get(i).accept(new GUINotificationVisitor()));
		}
		
		scroll.setPreferredSize(Constants.NOTIFICATIONS_LIST);
		
		scroll.setViewportView(notifications);
		main.add(scroll);
		//main.add(ul);
		this.setSize(Constants.NOTIFICATIONS_FRAME_DIMENSION);
	    this.setContentPane(main);	
		//Configure the frame
		this.setAlwaysOnTop(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//this.setResizable(false);
		//this.setLocationRelativeTo(null);
	}
	
}
