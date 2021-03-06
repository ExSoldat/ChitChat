package ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import core.App;
import ui.components.CCFriendsPane;
import ui.components.CCGroupsPane;
import ui.components.CCManagmentPane;
import ui.components.CCProfilePane;
import utils.Constants;

public class HomeFrame extends JFrame {
	public static String TAG = "Home";
	public HomeFrame() {
		super(Constants.APP_NAME +  " - " + TAG + " - " + App.getInstance().getLoggedUser().getFirstname() + " " + App.getInstance().getLoggedUser().getLastname());
	}
	
	public void init() {
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		this.setIconImage(Constants.APP_LOGO.getImage());
		//JLabel image = new JLabel(imageurl);
		
		//Creating username and password fields inside their own label
		JPanel mainView = new JPanel();
		mainView.setLayout(new BorderLayout());
		JTabbedPane panes = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		if(App.getInstance().getLoggedUser().isAdmin())  //Use of a isAdmin value beacause i don't know if i'm going to have enough time to implement visitor pattern here
			panes.add("Managment", new CCManagmentPane());
		CCFriendsPane fp = new CCFriendsPane();
		panes.add("Friends", fp);
		panes.add("Groups", new CCGroupsPane());
		panes.add("Profile", new CCProfilePane());
		CCNotifications notifsPane = new CCNotifications(panes, fp);
		panes.add("Notifications", notifsPane);
		notifsPane.refreshTitle();
		mainView.add(panes, BorderLayout.CENTER);
		
	    this.setContentPane(mainView);	
		//Configure the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Constants.HOME_DIMENSION);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
