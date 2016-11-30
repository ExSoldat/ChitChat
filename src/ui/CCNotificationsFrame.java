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
import core.domain.Hobby;
import core.domain.User;
import core.domain.notifications.FriendAcceptNotification;
import core.domain.notifications.FriendAddNotification;
import core.domain.notifications.FriendMessageNotification;
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

	public static String TAG = "Notifications";
	public void init() {
		this.setIconImage(Constants.APP_LOGO.getImage());
		this.setTitle(TAG);
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
		User testuser = new User(1,"Goku", "savioroftheworld", "Son");
		ArrayList<Hobby>sgh = new ArrayList<Hobby>();
		sgh.add(new Hobby(0, "Food"));
		sgh.add(new Hobby(0, "Fight"));
		sgh.add(new Hobby(0, "Be Stronger"));
		sgh.add(new Hobby(0, "New ennemies"));
		testuser.setHobbies(sgh);
		usersNotifications.add(new GroupMessageNotification(0, new Date(), true, false, testuser, testgroup2, true));
		usersNotifications.add(new FriendMessageNotification(1,new Date(), true, false, false, testuser, "Hello ! mzefpirjgpreigjerpigjerpigjerpgijerpigjerpgijerpgijerpgij"));
		usersNotifications.add(new FriendAddNotification(1,new Date(), true, false, false, testuser));
		usersNotifications.add(new FriendAcceptNotification(2,new Date(), true, true, testuser));

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
