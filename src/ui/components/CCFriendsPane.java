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
//http://richard.jp.leguen.ca/tutoring/soen343-f2010/tutorials/implementing-data-mapper/
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
				// TODO opens another frame to search user depending on name etc
				//CALLS A SERVICE
				CCDialog dialog = new CCDialog("Search an existing user");
				JPanel main = new JPanel();
				main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
				
				JPanel textFields = new JPanel();
				textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
				textFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Search by user informations"));
				CCFormTextEntry nameField = new CCFormTextEntry("Name", false, false);
				CCFormTextEntry firstnameField = new CCFormTextEntry("Firstname", false, false);
				CCFormTextEntry usernameField = new CCFormTextEntry("Username", false, false);
				
				dialog.showInfo("You must fill one of the fields above in order to search");
				
				nameField.getTextField().addKeyListener(new TypeEvent(nameField.getTextField(), (CCButton)dialog.getPositiveButton()));
				firstnameField.getTextField().addKeyListener(new TypeEvent(firstnameField.getTextField(), (CCButton)dialog.getPositiveButton()));
				usernameField.getTextField().addKeyListener(new TypeEvent(usernameField.getTextField(), (CCButton)dialog.getPositiveButton()));
				
				textFields.add(nameField);
				textFields.add(firstnameField);
				textFields.add(usernameField);
				textFields.setVisible(true);
				
				CCUserList users = new CCUserList();
				users.setVisible(false);
				

				dialog.onPositiveClicked(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//TODO Action when performed
						ArrayList<User> usersfound = App.getInstance().getServicesProvider().searchUser(nameField.getText(), firstnameField.getText(), usernameField.getText());
						users.setData(usersfound);
						textFields.setVisible(false);
						users.setVisible(true);
						dialog.getPositiveButton().setEnabled(false);
						users.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								dialog.getPositiveButton().setEnabled(true);
							}
						});
						
						dialog.morphButtons("ADD", "RETURN");
						
						dialog.onPositiveClicked(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								//SERVICE ADD FRIEND
								if(App.getInstance().getServicesProvider().addFriend(App.getInstance().getLoggedUser(), (User)users.getSelectedValue())) {
									//Refresh the friends view
									friends = App.getInstance().getServicesProvider().getFriendsForUser(App.getInstance().getLoggedUser().getId(), true);
									friendslist.setData(friends);
									dialog.dispose();
								} else {
									dialog.showError();
								}
							}
						});
						
						dialog.onNegativeClicked(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								//TODO SHOW SEARCH FORM
							}
						});
					}
				});
				main.add(users);
				main.add(textFields);
				dialog.setMainComponent(main);
				dialog.setSize(Constants.FORM_DIMENSION);
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
						if(App.getInstance().getServicesProvider().deleteFriend(deleteduser)) {
							friends.remove(deleteduser);
							friendslist.setListData(friends.toArray());
							titlepanel.setTitle("NaN");
							username.setDisplayedValue("NaN");
							hobbies.setDisplayedValue("NaN");
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
