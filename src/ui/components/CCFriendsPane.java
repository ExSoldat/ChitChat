package ui.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import utils.Constants;

public class CCFriendsPane extends JPanel {
	public static String TAG = "Friends";
	public CCFriendsPane() {
		//Getting the data :
		ArrayList<User> friends = App.getInstance().getServicesProvider().getFriendsForUser(App.getInstance().getLoggedUser().getId());
				
		//Creating username and password fields inside their own label
		JPanel main = new JPanel();
		GridBagConstraints mainCs = new GridBagConstraints();
		
		mainCs.fill = GridBagConstraints.VERTICAL;
		mainCs.insets = new Insets(10, 10, 10, 10);
		
		JPanel actionpanel = new JPanel();
		actionpanel.setLayout(new BoxLayout(actionpanel, BoxLayout.Y_AXIS));
		actionpanel.setPreferredSize(Constants.FRAGMENT_DIMENSION);
		
		JList friendslist = new JList(friends.toArray());
		friendslist.setCellRenderer(new CCFriendsListCellRenderer());
		friendslist.setBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()));
		friendslist.setBackground(CCColor.CCNEARLYWHITE.getColor());
		
		actionpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), TAG));
		//Action buttons, Add, Search and Remove
		
		
		JPanel actionbuttons = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.fill = GridBagConstraints.VERTICAL;
		cs.insets = new Insets(10, 10, 10, 10);
		CCButton addbutton = new CCButton("add", Constants.BUTTON_MAIN);
		CCButton searchbutton = new CCButton("search", Constants.BUTTON_SECONDARY);
		CCButton removebutton = new CCButton("remove", Constants.BUTTON_DANGER);
		
		cs.gridx = 0;
		actionbuttons.add(addbutton, cs);
		cs.gridx = 1;
		actionbuttons.add(searchbutton, cs);
		cs.gridx = 2;
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
				titlepanel.setTitle(selecteduser.getFirstname() + " " + selecteduser.getLastname());
				username.setDisplayedValue(selecteduser.getUsername());
				hobbies.setDisplayedValue(selecteduser.getHobbiesAsAString());
				removebutton.setEnabled(true);
			}
		});
		
		addbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO opens another frame to add an user knowing its username
				//CALLS A SERVICE
			}
		});
		
		searchbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO opens another frame to search user depending on name/hobbies etc
				//CALLS A SERVICE
			}
		});
		
		removebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Remove the selected user from our friends list :( (opens a popup before)
				//CALLS A SERVICE
				CCConfirmation confirmDialog = new CCConfirmation("If you continue, a friend will be deleted from your friends list :(");
				confirmDialog.setModal(true);
				confirmDialog.onOkClicked(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						removebutton.setEnabled(false);	
						confirmDialog.dispose();
					}
				});
				confirmDialog.onNoClicked(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						confirmDialog.dispose();
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
