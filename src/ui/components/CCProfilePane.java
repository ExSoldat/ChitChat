package ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import core.App;
import core.domain.Hobby;
import core.domain.User;
import ui.actions.TriggerButtonOnType;
import ui.components.form.CCFormTextEntry;
import utils.Constants;

public class CCProfilePane extends JPanel {
	User me;
	public static String TAG = "Profile";
	public CCProfilePane() {
		me = App.getInstance().getLoggedUser();
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), TAG));
		main.setPreferredSize(Constants.ALL_GROUPS_DIMENSION);
		CCFormTextEntry last = new CCFormTextEntry("Lastname", false, false);
		CCFormTextEntry firstname = new CCFormTextEntry("Firstname", false, false);
		CCDesc hobbies = new CCDesc("Hobbies", me.getHobbiesAsAString());
		JPanel addHobbyForm = new JPanel();
		CCFormTextEntry hobbyForm = new CCFormTextEntry("Add a hobby", false, false);
		hobbyForm.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		CCButton addhobby = new CCButton("add hobby", Constants.BUTTON_MAIN);
		addhobby.setEnabled(false);
		addHobbyForm.add(hobbyForm);
		addHobbyForm.add(addhobby);
		
		hobbyForm.getTextField().addKeyListener(new TriggerButtonOnType(hobbyForm.getTextField(), addhobby));
		
		last.getTextField().setText(me.getLastname());
		firstname.getTextField().setText(me.getFirstname());
		
		addhobby.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				me.getHobbies().add(new Hobby(0, hobbyForm.getText()));
				hobbies.setDisplayedValue(me.getHobbiesAsAString());
				hobbyForm.getTextField().setText("");
			}
		});
		main.add(firstname);
		main.add(last);
		main.add(addHobbyForm);
		main.add(hobbies);
		this.add(main);
	}
	//My info
		//Name
		//Lastname
	
	//My groups
		//Administration
		//Manage members
	//
}
