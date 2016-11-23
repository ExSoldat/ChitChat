package ui.components;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import core.domain.User;

public class CCUserList extends JList {

	private ArrayList<User> data;
	private JPanel parent; //Useful ?
	
	public CCUserList(ArrayList<User> users) {
		super(users.toArray());
		this.data = users;
		this.setCellRenderer(new CCFriendsListCellRenderer());
		this.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
		this.setBackground(CCColor.CCNEARLYWHITE.getColor());
	}

	public void removeUser(User deleteduser) {
		data.remove(deleteduser);
		this.parent = (JPanel) this.getParent();
		//this.parent.remove(this);
	}

	public void render() {
		this.setListData(data.toArray());
		//this.parent.add(this);
		//this.setVisible(true);
	}
	

}