package ui.components;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import core.domain.User;
import core.domain.proxy.ProxyUser;

public class CCUserList extends JList {

	private ArrayList<ProxyUser> data;
	private JPanel parent; //Useful ?
	
	public CCUserList(ArrayList<ProxyUser> users) {
		super();
		if(users != null && !users.isEmpty())
			this.setListData(users.toArray());
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
	
	public void setData(ArrayList<ProxyUser> users) {
		this.data = users;
		this.setListData(users == null ? new ArrayList<User>().toArray() : users.toArray());
	}

	public void render() {
		this.setListData(data.toArray());
		//this.parent.add(this);
		//this.setVisible(true);
	}
	

}
