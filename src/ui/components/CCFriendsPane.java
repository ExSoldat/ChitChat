package ui.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.App;
import core.domain.Group;
import core.domain.User;
import ui.actions.TypeEvent;
import ui.components.form.CCFormTextEntry;
import utils.Constants;
import utils.LogUtils;

public class CCFriendsPane extends JPanel {
	public ArrayList<User> friends = new ArrayList<User>();
	public static String TAG = "Friends";
	public CCFriendsPane() {
		//Getting the data :
		friends = App.getInstance().getServicesProvider().getFriendsForUser(App.getInstance().getLoggedUser().getId());
				
		//Creating username and password fields inside their own label
		JPanel main = new JPanel();
		GridBagConstraints mainCs = new GridBagConstraints();
		
		mainCs.fill = GridBagConstraints.VERTICAL;
		mainCs.insets = new Insets(10, 10, 10, 10);
		
		JPanel actionpanel = new JPanel();
		actionpanel.setLayout(new BoxLayout(actionpanel, BoxLayout.Y_AXIS));
		actionpanel.setPreferredSize(Constants.FRAGMENT_DIMENSION);
		
		CCUserList friendslist = new CCUserList(friends);
		
		actionpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), TAG));
		//Action buttons, Add, Search and Remove
		
		
		JPanel actionbuttons = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.fill = GridBagConstraints.VERTICAL;
		cs.insets = new Insets(10, 10, 10, 10);
		CCButton searchbutton = new CCButton("search", Constants.BUTTON_MAIN);
		CCButton removebutton = new CCButton("remove", Constants.BUTTON_DANGER);
		
		cs.gridx = 0;
		actionbuttons.add(searchbutton, cs);
		cs.gridx = 1;
		actionbuttons.add(removebutton, cs);
		
		removebutton.setEnabled(false);
		
		JPanel selecteduserInfo = new JPanel();
		selecteduserInfo.setLayout(new BoxLayout(selecteduserInfo, BoxLayout.Y_AXIS));
		selecteduserInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Selected Friend Informations"));
		selecteduserInfo.setPreferredSize(Constants.FRAGMENT_DIMENSION);
		
		ImageIcon imageurl = new ImageIcon("img/ic_person_orange.png");
		CCTitlePanel titlepanel = new CCTitlePanel(imageurl, "No friend selected yet");
		CCDesc username = new CCDesc("Username");
		CCDesc hobbies = new CCDesc("Hobbies");
		
		friendslist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				User selecteduser = (User)friendslist.getSelectedValue();
				if(selecteduser != null) {
					titlepanel.setTitle(selecteduser.getFirstname() + " " + selecteduser.getLastname());
					username.setDisplayedValue(selecteduser.getUsername());
					hobbies.setDisplayedValue(selecteduser.getHobbiesAsAString());
					removebutton.setEnabled(true);
				}
			}
		});
		
		searchbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO opens another frame to search user depending on name/hobbies etc
				//CALLS A SERVICE
				CCDialog dialog = new CCDialog("Search an existing user");
				JPanel main = new JPanel();
				main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
				
				JPanel textFields = new JPanel();
				textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
				textFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Search by user informations"));
				CCLabel info = new CCLabel("You must fill one of the field below in order to search", true);
				textFields.add(info);
				CCFormTextEntry nameField = new CCFormTextEntry("Name", false, false);
				CCFormTextEntry firstnameField = new CCFormTextEntry("Firstname", false, false);
				CCFormTextEntry usernameField = new CCFormTextEntry("Username", false, false);
				
				nameField.getTextField().addKeyListener(new TypeEvent(nameField.getTextField(), (CCButton)dialog.getPositiveButton()));
				firstnameField.getTextField().addKeyListener(new TypeEvent(firstnameField.getTextField(), (CCButton)dialog.getPositiveButton()));
				usernameField.getTextField().addKeyListener(new TypeEvent(usernameField.getTextField(), (CCButton)dialog.getPositiveButton()));
				
				textFields.add(nameField);
				textFields.add(firstnameField);
				textFields.add(usernameField);
				
				dialog.onPositiveClicked(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//TODO Action when performed
						//ArrayList<User> usersfound = App.getInstance().getServicesProvider().searchUsers(nameField.getText(), firstnameField.getText(), usernameField.getText());
						//CCUserList users = new CCUserList(usersfound);
						//main.remove(textFields)
					}
				});
				
				main.add(textFields);
				dialog.setSize(Constants.FORM_DIMENSION);
				dialog.setMainComponent(main);
				dialog.getPositiveButton().setEnabled(false);
				dialog.morphButtons("SEARCH", "CANCEL");
				dialog.setVisible(true);
			}
		});
		
		removebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Remove the selected user from our friends list :( (opens a popup before)
				//CALLS A SERVICE
				CCConfirmation confirmDialog = new CCConfirmation("If you continue, a friend will be deleted from your friends list :(");
				confirmDialog.setModal(true);
				confirmDialog.onPositiveClicked(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						removebutton.setEnabled(false);	
						User deleteduser = (User)friendslist.getSelectedValue();
						LogUtils.log(TAG, Constants.INFO, "User to be deleted : " + deleteduser);
						if(App.getInstance().getServicesProvider().deleteUser(deleteduser)) {
							friends.remove(deleteduser);
							friendslist.setListData(friends.toArray());
							confirmDialog.dispose();
						} else {
							confirmDialog.showError();
						}
						
					}
				});
				confirmDialog.setVisible(true);
			}
		});
		
		selecteduserInfo.add(titlepanel);
		selecteduserInfo.add(username);
		selecteduserInfo.add(hobbies);
		actionpanel.add(friendslist);
		actionpanel.add(actionbuttons);
		
		main.add(actionpanel);
		main.add(selecteduserInfo);
		this.add(main);
	}
}
