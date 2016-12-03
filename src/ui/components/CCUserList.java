package ui.components;

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
		this.setCellRenderer(new CCUsersListCellRenderer());
		this.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
		this.setBackground(CCColor.CCNEARLYWHITE.getColor());
	}
	
	public CCUserList() {
		super();
		this.setCellRenderer(new CCUsersListCellRenderer());
		this.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
		this.setBackground(CCColor.CCNEARLYWHITE.getColor());
	}

	public void removeUser(User deleteduser) {
		data.remove(deleteduser);
		this.parent = (JPanel) this.getParent();
		//this.parent.remove(this);
	}
	
	public void setData(ArrayList<User> users) {
		this.data = users;
		this.setListData(users.toArray() == null ? new ArrayList<User>().toArray() : users.toArray());
	}

	public void render() {
		this.setListData(data.toArray());
		//this.parent.add(this);
		//this.setVisible(true);
	}
	

}
