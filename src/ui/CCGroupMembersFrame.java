package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.App;
import core.domain.Group;
import core.domain.User;
import ui.actions.TriggerButtonOnType;
import ui.components.CCButton;
import ui.components.CCColor;
import ui.components.CCDialog;
import ui.components.CCLabel;
import ui.components.CCMessagesList;
import ui.components.CCUserList;
import ui.components.form.CCFormTextEntry;
import utils.Constants;

public class CCGroupMembersFrame extends JFrame {
		private Group group;
		public static String TAG = "Members";
		public CCGroupChatFrame mFrameParent;
		CCUserList ul;

		public CCGroupMembersFrame(Group g) {
			super(g.getName() + " - " + TAG);
			this.group = g;
		}
		
		public void init(CCGroupChatFrame mParent) {
			this.setIconImage(Constants.APP_LOGO.getImage());
			this.mFrameParent = mParent;
			JPanel main = new JPanel();
			JScrollPane scroll = new JScrollPane();
		    scroll.setLayout(new ScrollPaneLayout());
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			ul = group.getParticipants() != null ? new CCUserList(group.getParticipants()) : new CCUserList();
			scroll.setViewportView(ul);
			main.add(scroll);
			
			JPanel actionbuttons = new JPanel(new GridBagLayout());
			GridBagConstraints cs = new GridBagConstraints();
			
			cs.fill = GridBagConstraints.VERTICAL;
			cs.insets = new Insets(10, 10, 10, 10);
			CCButton addbutton = new CCButton("add", Constants.BUTTON_MAIN);
			CCButton leavebutton = new CCButton("leave group", Constants.BUTTON_SECONDARY);
			CCButton removebutton = new CCButton("remove", Constants.BUTTON_DANGER);
			
			if(App.getInstance().getLoggedUser().equals(group.getAdministrator())) {
				cs.gridx = 0;
				actionbuttons.add(addbutton, cs);
				cs.gridx = 2;
				actionbuttons.add(removebutton, cs);
			}
			cs.gridx = 1;
			actionbuttons.add(leavebutton, cs);
			removebutton.setEnabled(false);
			removebutton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					group.getParticipants().remove(ul.getSelectedValue());
					ul.setData(group.getParticipants());
				}
			});
			
			ul.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					removebutton.setEnabled(true);					
				}
			});
			
			addbutton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
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
					
					nameField.getTextField().addKeyListener(new TriggerButtonOnType(nameField.getTextField(), (CCButton)dialog.getPositiveButton()));
					firstnameField.getTextField().addKeyListener(new TriggerButtonOnType(firstnameField.getTextField(), (CCButton)dialog.getPositiveButton()));
					usernameField.getTextField().addKeyListener(new TriggerButtonOnType(usernameField.getTextField(), (CCButton)dialog.getPositiveButton()));
					
					textFields.add(nameField);
					textFields.add(firstnameField);
					textFields.add(usernameField);
					
					CCUserList users = new CCUserList();
					textFields.setVisible(true);
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
							dialog.morphButtons("ADD", "CANCEL");
							dialog.showInfo("Select a user profile then click on \"ADD\" to add the user to your conversation group");
							
							dialog.onPositiveClicked(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									//SERVICE ADD FRIEND
									group.getParticipants().add((User)users.getSelectedValue());
									ul.setData(group.getParticipants());
									dialog.dispose();

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
			
			CCGroupMembersFrame me = this;
			leavebutton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(App.getInstance().getServicesProvider().removeUserFromGroup(App.getInstance().getLoggedUser(), group)) {
						me.dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
						mFrameParent.closeAfterLeaving();
					}
				}
			});
			main.add(actionbuttons);
			scroll.setPreferredSize(Constants.CHAT_MEMBERS_LIST);
			
			//main.add(ul);
			this.setSize(Constants.CHAT_MEMBERS_FRAME);
		    this.setContentPane(main);	
			//Configure the frame
			this.setAlwaysOnTop(false);
			this.setResizable(false);
			//this.setLocationRelativeTo(null);
		}
}
