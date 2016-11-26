package ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import core.App;
import ui.components.CCColor;
import ui.components.CCFriendsPane;
import ui.components.CCGroupsPane;
import ui.components.CCLabel;
import utils.Constants;

public class CCChatFrame extends JFrame {
	JPanel header, messagesList, usersMessage;
	
	public static String TAG = "Chat";
	public CCChatFrame() {
		super(Constants.APP_NAME +  " - " + TAG + " - ");
	}
	
	public void init() {
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		header = new JPanel();
		header.setLayout(new BorderLayout());
		CCLabel mainInfo = new CCLabel("Main information");		
		JLabel image = new JLabel(imageurl);
		
		header.add(mainInfo, BorderLayout.EAST);
		header.add(image, BorderLayout.WEST);
		header.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Chat"));

		//CCMessagesList messageList = new CCMessagesList();
		
		main.add(header);
		main.add(messagesList);
		main.add(usersMessage);
		
	    this.setContentPane(main);	
		//Configure the frame
		this.setSize(Constants.CHAT_FRAME_DIMENSION);
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public JPanel getHeader() {
		return header;
	}

	public void setHeader(JPanel header) {
		this.header = header;
	}

	public JPanel getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(JPanel messagesList) {
		this.messagesList = messagesList;
	}

	public JPanel getUsersMessage() {
		return usersMessage;
	}

	public void setUsersMessage(JPanel usersMessage) {
		this.usersMessage = usersMessage;
	}
	
	

}
