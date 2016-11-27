package ui.components;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.App;
import core.domain.User;
import ui.CCPrivateChatFrame;
import ui.actions.TriggerButtonOnType;
import ui.components.form.CCFormTextEntry;
import utils.Constants;
import utils.LogUtils;

public class CCManagmentPane extends JPanel {
	public static String TAG = "Managment";
	public ArrayList<User> users = new ArrayList<User>();
	User selecteduser = null;
	public CCTitlePanel titlepanel;
	public CCFormTextEntry firstname, username, lastname, password;
	public CCManagmentPane() {
		users = App.getInstance().getServicesProvider().getFriendsForUser(App.getInstance().getLoggedUser().getId());
		
		//Creating username and password fields inside their own label
		JPanel main = new JPanel();
		GridBagConstraints mainCs = new GridBagConstraints();
				
		mainCs.fill = GridBagConstraints.VERTICAL;
		mainCs.insets = new Insets(0, 10, 0, 10);
		
		JPanel actionpanel = new JPanel();
		actionpanel.setLayout(new BoxLayout(actionpanel, BoxLayout.Y_AXIS));
		actionpanel.setPreferredSize(Constants.FRAGMENT_DIMENSION);
		
		CCUserList userslist = new CCUserList(users);
		
		actionpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), TAG));		
		
		JPanel actionbuttons = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.fill = GridBagConstraints.VERTICAL;
		cs.insets = new Insets(0, 10, 0, 10);
		CCButton addbutton = new CCButton("search", Constants.BUTTON_MAIN);
		CCButton deletebutton = new CCButton("remove", Constants.BUTTON_DANGER);
		
		cs.gridx = 0;
		actionbuttons.add(addbutton, cs);
		cs.gridx = 1;
		actionbuttons.add(deletebutton, cs);
		
		deletebutton.setEnabled(false);
		
		JPanel selecteduserInfo = new JPanel();
		selecteduserInfo.setLayout(new BoxLayout(selecteduserInfo, BoxLayout.Y_AXIS));
		selecteduserInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Selected Friend Informations"));
		selecteduserInfo.setPreferredSize(Constants.FRAGMENT_DIMENSION);
		
		ImageIcon imageurl = new ImageIcon("img/ic_person_orange.png");
		titlepanel = new CCTitlePanel(imageurl, "No user selected yet");
		username = new CCFormTextEntry("Username", true, false);
		firstname = new CCFormTextEntry("Firstname", true, false);
		lastname = new CCFormTextEntry("Lastname", true, false);
		password = new CCFormTextEntry("Password", true, false);
		CCButton confirmbutton = new CCButton("Confirm", Constants.BUTTON_MAIN);
		
		username.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		firstname.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		lastname.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		password.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		
		username.getTextField().setEnabled(false);
		firstname.getTextField().setEnabled(false);
		lastname.getTextField().setEnabled(false);
		password.getTextField().setEnabled(false);
		
		confirmbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selecteduser.setFirstname(firstname.getText() != null ? firstname.getText() : selecteduser.getFirstname());
				selecteduser.setUsername(username.getText() != null ? username.getText() : selecteduser.getUsername());
				selecteduser.setLastname(lastname.getText() != null ? lastname.getText() : selecteduser.getLastname());
				selecteduser.setPassword(password.getText() != null ? password.getText() : selecteduser.getPassword());
				User tempUser = selecteduser;
				users.remove(selecteduser);
				users.add(tempUser);
				selecteduser = null;
				refreshUserInfo();
				userslist.setListData(users.toArray());
			}
		});
		
		
		userslist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selecteduser = (User)userslist.getSelectedValue();
				if(selecteduser != null) {
					refreshUserInfo();
					username.getTextField().setEnabled(true);
					firstname.getTextField().setEnabled(true);
					lastname.getTextField().setEnabled(true);
					password.getTextField().setEnabled(true);
					deletebutton.setEnabled(true);
				}
			}
		});
		
		addbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				users.add(new User(0, lastname.getText(), username.getText(), firstname.getText(), password.getText()));
				userslist.setListData(users.toArray());
			}
		});
		
		deletebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Remove the selected user from our friends list :( (opens a popup before)
				//CALLS A SERVICE
				CCConfirmation confirmDialog = new CCConfirmation("You are going to delete a user");
				confirmDialog.setModal(true);
				confirmDialog.onPositiveClicked(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						deletebutton.setEnabled(false);	
						User deleteduser = (User)userslist.getSelectedValue();
						LogUtils.log(TAG, Constants.INFO, "User to be deleted : " + deleteduser);
						users.remove(deleteduser);
						userslist.setListData(users.toArray());
						titlepanel.setTitle("Nan");
						username.getTextField().setText("");
						firstname.getTextField().setText("");
						lastname.getTextField().setText("");
						confirmDialog.dispose();						
					}
				});
				confirmDialog.setVisible(true);
			}
		});
		
		selecteduserInfo.add(titlepanel);
		selecteduserInfo.add(username);
		selecteduserInfo.add(firstname);
		selecteduserInfo.add(lastname);
		selecteduserInfo.add(password);
		selecteduserInfo.add(confirmbutton);
		actionpanel.add(userslist);
		actionpanel.add(actionbuttons);
		
		main.add(actionpanel);
		main.add(selecteduserInfo);
		this.add(main);
	}
	
	public void refreshUserInfo() {
		if(selecteduser != null) {
			titlepanel.setTitle(selecteduser.getFirstname() + " " + selecteduser.getLastname());
			username.getTextField().setText(selecteduser.getUsername());
			firstname.getTextField().setText(selecteduser.getFirstname());
			lastname.getTextField().setText(selecteduser.getLastname());
		} else {
			titlepanel.setTitle("No user selected yet");
			username.getTextField().setText("");
			firstname.getTextField().setText("");
			lastname.getTextField().setText("");
		}
	}
}
