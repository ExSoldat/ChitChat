package ui.components;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import core.domain.Group;
import ui.behavior.HoverBehavior;
import utils.Constants;

public class CCGroup extends JPanel {
	
	public CCGroup(Group group) {
		JPanel main = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(10, 10, 10, 10);
		
		CCLabel groupname = new CCLabel(group.getName());
		cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        main.add(groupname, cs);
        
        JLabel lastmessageSender = new JLabel(group.getLastMessage().getSender().getFirstname() + " " + group.getLastMessage().getSender().getLastname() + " : ");
		cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 2;
        main.add(lastmessageSender, cs);
        
        JLabel lastmessage = new JLabel(group.getLastMessage().getContent());
        cs.gridx = 4;
        cs.gridy = 1;
        cs.gridwidth = 30;
        lastmessage.setFont(lastmessageSender.getFont().deriveFont(Font.PLAIN));
        main.add(lastmessage, cs);
        
        main.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
        main.setBackground(CCColor.CCNEARLYWHITE.getColor());
        
        this.addMouseListener(new HoverBehavior(main));
        
        main.setPreferredSize(Constants.GROUP_BOX_DIMENSION);
        this.add(main);
	}
}
