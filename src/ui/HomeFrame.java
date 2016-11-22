package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import ui.components.CCGroupsPane;
import core.App;
import core.domain.User;
import ui.components.CCButton;
import ui.components.CCError;
import ui.components.CCFriendsPane;
import ui.components.CCLabel;
import utils.Constants;
import utils.LogUtils;

public class HomeFrame extends JFrame {
	public static String TAG = "Home";
	public HomeFrame() {
		super(Constants.APP_NAME +  " - " + TAG + " - " + App.getInstance().getLoggedUser().getFirstname() + " " + App.getInstance().getLoggedUser().getLastname());
	}
	
	public void init() {
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		//JLabel image = new JLabel(imageurl);
		
		//Creating username and password fields inside their own label
		JPanel mainView = new JPanel();
		mainView.setLayout(new BorderLayout());
		JTabbedPane panes = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		panes.add("Groups", new CCGroupsPane());
		panes.add("Friends", new CCFriendsPane());
		panes.add("Chats", new CCGroupsPane());
		panes.add("Managment", new CCGroupsPane());
		panes.add("Profile", new CCGroupsPane());
		
		
		//mainView.setSize(Constants.HOME_DIMENSION);
		//mainView.setPreferredSize(mainView.getSize());
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
