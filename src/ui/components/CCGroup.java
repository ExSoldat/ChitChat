package ui.components;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.App;
import core.domain.Group;
import core.domain.User;
import ui.CCGroupChatFrame;
import ui.behavior.HoverBehavior;
import utils.Constants;

public class CCGroup extends JPanel {
	
	private Group group;
	private CCGroupsPane globalParent;
	
	public CCGroup(Group group) {
		this.group = group;
		JPanel main = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(10, 10, 10, 10);
		
		CCLabel groupname = new CCLabel(group.getName());
		cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        main.add(groupname, cs);
        
        //JLabel lastMessageTime = new JLabel(group.getLastMessage().getFormattedDate());
        //cs.gridx = 0;
        //cs.gridy = 1;
        //main.add(lastMessageTime);
        
        JLabel lastmessageSender = new JLabel(group.getLastMessage().getSender().getFirstname() + " " + group.getLastMessage().getSender().getLastname() + ", le " + group.getLastMessage().getFormattedDate() + " : ");
		cs.gridx = 0;
        cs.gridy = 1;
        //cs.gridwidth = 2;
        main.add(lastmessageSender, cs);
        
        CCPlainText lastmessage = new CCPlainText(group.getLastMessage().getContent());
        cs.gridx = 3;
        cs.gridy = 1;
        cs.gridwidth = 30;
        main.add(lastmessage, cs);
        
        main.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
        main.setBackground(CCColor.CCNEARLYWHITE.getColor());
        
        this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					Group g = ((CCGroup) e.getSource()).getGroup();
					System.out.println("Opens a group chat window between " + App.getInstance().getLoggedUser() + " and " + g);
					CCGroupChatFrame groupChat = new CCGroupChatFrame(App.getInstance().getLoggedUser(), g);
					groupChat.init();
					groupChat.setParent(globalParent);
					groupChat.setVisible(true);
				}
			}
		});
        
        this.addMouseListener(new HoverBehavior(main));
        this.setToolTipText(group.getDescription());
        main.setPreferredSize(Constants.GROUP_BOX_DIMENSION);
        this.add(main);
	}

	public Group getGroup() {
		return group;
	}
	
	public void setGlobalParent(CCGroupsPane gp) {
		this.globalParent = gp;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
