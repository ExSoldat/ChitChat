package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import core.App;
import core.domain.Message;
import core.domain.ReceiptMessage;
import ui.components.CCButton;
import ui.components.CCColor;
import ui.components.CCFriendsPane;
import ui.components.CCGroupsPane;
import ui.components.CCLabel;
import ui.components.CCMessagesList;
import ui.components.form.CCFormTextEntry;
import utils.Constants;

public abstract class CCChatFrame extends JFrame {
	protected JPanel header, messagesList, usersMessage;
	public CCMessagesList list = new CCMessagesList();
	CCLabel mainInfo = new CCLabel("Main information");
	protected CCButton send;
	protected JCheckBox receipt, urgent, encrypt, expirationtime;
	protected CCFormTextEntry time, mes;
	public static String TAG = "Chat";
	protected JScrollBar sb;
	protected JScrollPane scroll;
	public CCChatFrame() {
		super(Constants.APP_NAME +  " - " + TAG + " - ");
	}
	
	public void init() {
		this.setIconImage(Constants.APP_LOGO.getImage());
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		header = new JPanel();
		header.setLayout(new BorderLayout());
		mainInfo = new CCLabel("Main information");		
		JLabel image = new JLabel(imageurl);
		
		header.add(mainInfo, BorderLayout.EAST);
		header.add(image, BorderLayout.WEST);
		header.setPreferredSize(Constants.CHAT_HEADER_DIMENSION);
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Chat");
	    Border margin = new EmptyBorder(0,10,0,10);
	    header.setBorder(new CompoundBorder(border, margin));

	    scroll = new JScrollPane();
	    scroll.setLayout(new ScrollPaneLayout());
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		messagesList = new JPanel();
		//list.setPreferredSize(Constants.CHAT_MESSAGES_DIMENSION);
		scroll.setViewportView(list);
		
		messagesList.add(scroll);
		messagesList.setPreferredSize(Constants.CHAT_MESSAGES_DIMENSION);
		//messagesList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Messages"));
		
		usersMessage = new JPanel();
		usersMessage.setLayout(new BoxLayout(usersMessage, BoxLayout.Y_AXIS));
		JPanel sendingOptions = new JPanel();
		JPanel wroteText = new JPanel();
		
		receipt = new JCheckBox("Receipt");
		expirationtime = new JCheckBox("Expiration time");
		time = new CCFormTextEntry("Time", false, false);
		time.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		time.setVisible(false);
		encrypt = new JCheckBox("Encrypt");
		urgent = new JCheckBox("Urgent");
		
		sendingOptions.add(receipt);
		sendingOptions.add(expirationtime);
		sendingOptions.add(time);
		sendingOptions.add(encrypt);
		sendingOptions.add(urgent);
		
		mes = new CCFormTextEntry("Your message", false, false);
		send = new CCButton("Send", Constants.BUTTON_MAIN);
		
		//mes.getTextField().addKeyListener(new ButtonActionOnEnterKey(mes.getTextField(), send));
		
		wroteText.add(mes);
		wroteText.add(send);
		usersMessage.add(sendingOptions);
		usersMessage.add(wroteText);
		usersMessage.setPreferredSize(Constants.CHAT_USERSMESSAGE_DIMENSION);
		
		expirationtime.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(expirationtime.isSelected()) {
					time.setVisible(true);
				} else {
					time.setVisible(false);
				}
			}
		});
		
		main.setPreferredSize(Constants.CHAT_MESSAGES_DIMENSION);
		main.add(header);
		main.add(messagesList);
		main.add(usersMessage);
		
		this.getRootPane().setDefaultButton(send);
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
	
	public CCMessagesList getCCMessagesList() {
		return list;
	}
	
	public CCLabel getMainInfo() {
		return this.mainInfo;
	}
	
	public CCButton getSendButton() {
		return send;
		
	}
	
	public abstract void refreshMessages();
	
	public void scrollMessagesToBottom() {
		sb = scroll.getVerticalScrollBar();
		sb.setValue(sb.getMaximum()); // TODO
		messagesList.validate();
	}

}
