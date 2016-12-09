package ui.components;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import core.domain.Discussion;
import core.domain.Message;
import core.domain.User;

public class CCMessagesList extends JList {

	private Discussion data;
	//private JPanel parent; //Useful ?
	
	public CCMessagesList(Discussion messages) {
		super(messages.toArray());
		this.data = messages;
		this.setCellRenderer(new CCMessagesListCellRenderer());
		this.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
		this.setBackground(CCColor.CCNEARLYWHITE.getColor());
	}
	
	public CCMessagesList() {
		super();
		this.setCellRenderer(new CCMessagesListCellRenderer());
		this.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
		this.setBackground(CCColor.CCNEARLYWHITE.getColor());
	}

	public void removeUser(User deleteduser) {
		data.remove(deleteduser);
		//this.parent = (JPanel) this.getParent();
		//this.parent.remove(this);
	}
	
	public void setData(Discussion messages) {
		this.data = messages;
		this.setListData(messages.toArray());
	}

	public void render() {
		this.setListData(data.toArray());
		//this.parent.add(this);
		//this.setVisible(true);
	}
	

}
