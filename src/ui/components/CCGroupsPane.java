package ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import core.App;
import core.domain.Group;
import ui.actions.TriggerButtonOnType;
import ui.components.form.CCFormTextEntry;
import utils.Constants;

public class CCGroupsPane extends JPanel {
	
	public static String TAG = "Groups";
	public JPanel scrollMain;
	
	public CCGroupsPane() {
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		//Creating username and password fields inside their own label
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(Constants.ALL_GROUPS_DIMENSION);
		scrollMain = new JPanel();
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMain.setLayout(new BoxLayout(scrollMain, BoxLayout.Y_AXIS));
		//main.setPreferredSize(Constants.ALL_GROUPS_DIMENSION);
		
		refreshGroups();
		scrollMain.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), TAG));
		scroll.setViewportView(scrollMain);
		
		//Group creation view
		
		JPanel groupCreationForm = new JPanel();
		groupCreationForm.setLayout(new BoxLayout(groupCreationForm, BoxLayout.Y_AXIS));
		groupCreationForm.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), "Create a new group"));
		CCFormTextEntry nameField = new CCFormTextEntry("Group name", true, false);
		CCFormTextEntry descField = new CCFormTextEntry("Description", false, false);
		CCLabel info = new CCLabel("");
		
		info.setHorizontalAlignment(CCLabel.CENTER);
		info.setVisible(false);
		
		CCButton create = new CCButton("create", Constants.BUTTON_MAIN);
		create.setEnabled(false);
		nameField.getTextField().addKeyListener(new TriggerButtonOnType(nameField.getTextField(), create));
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Group mGroup = new Group();
				if(App.getInstance().getServicesProvider().createGroup(mGroup)) {
					info.setText("Group successfully created !");
					info.setForeground(CCColor.CCPRIMARY.getColor());
					info.setVisible(true);
					refreshGroups();
				} else {
					info.setText("An error occured. Please try again");
					info.setForeground(CCColor.CCDANGER.getColor());
					info.setVisible(true);
				}
			}
		});
		
		groupCreationForm.add(nameField);
		groupCreationForm.add(descField);
		groupCreationForm.add(create);
		main.add(scroll);
		main.add(groupCreationForm);
		main.add(info);
		this.add(main);
	}
	
	public void refreshGroups() {
		//Getting the data :
		scrollMain.removeAll();
		ArrayList<Group> groups = App.getInstance().getServicesProvider().getGroupsForUser(App.getInstance().getLoggedUser().getId());
		for(int i = 0; i<groups.size(); i++) {
			CCGroup ccg = new CCGroup(groups.get(i));
			scrollMain.add(ccg);
		}
		scrollMain.validate();
	}
}
