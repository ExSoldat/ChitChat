package ui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
		CCFormTextEntry password = new CCFormTextEntry("Password", false, false);
		CCDesc hobbies = new CCDesc("Hobbies", me.getHobbiesAsAString());
		JPanel addHobbyForm = new JPanel();
		CCFormTextEntry hobbyForm = new CCFormTextEntry("Add a hobby", false, false);
		hobbyForm.getTextField().setPreferredSize(Constants.FORMTEXTFIELD_MINI_DIMENSION);
		CCButton addhobby = new CCButton("add hobby", Constants.BUTTON_SECONDARY);
		
		CCButton savebutton = new CCButton("Save", Constants.BUTTON_MAIN);
		CCButton cancelbutton = new CCButton("Cancel", Constants.BUTTON_DANGER);
		JPanel actionspanel = new JPanel();
		actionspanel.add(savebutton);
		actionspanel.add(cancelbutton);
		
		JLabel info = new JLabel();
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setVisible(false);
		info.setPreferredSize(new Dimension(main.getWidth(), 30)); //Does not work
		
		savebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				me.setFirstname(firstname.getText().equals("") ? me.getFirstname() : firstname.getText());
				me.setLastname(last.getText().equals("") ? me.getLastname() : last.getText());
				me.setPassword(password.getText().equals("") ? me.getPassword() : password.getText());
				if(App.getInstance().getServicesProvider().updateUser(me)) {
					info.setText("Successfully updated");
					info.setForeground(CCColor.CCGREEN.getColor());
					info.setVisible(true);
				} else {
					info.setText("An error occured. Please retry");
					info.setForeground(CCColor.CCDANGER.getColor());
					info.setVisible(true);
				}
			}
		});
		
		addhobby.setEnabled(false);
		addHobbyForm.add(hobbyForm);
		addHobbyForm.add(addhobby);
		
		hobbyForm.getTextField().addKeyListener(new TriggerButtonOnType(hobbyForm.getTextField(), addhobby));
		
		last.getTextField().setText(me.getLastname());
		firstname.getTextField().setText(me.getFirstname());
		password.getTextField().setText(me.getPassword());
		
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
		main.add(password);
		main.add(addHobbyForm);
		main.add(hobbies);
		main.add(actionspanel);
		main.add(info);
		this.add(main);
	}
}
