package ui.components;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.App;
import utils.Constants;
import utils.LogUtils;
import core.domain.Group;

public class CCGroupsPane extends JPanel {
	
	public static String TAG = "Groups";
	
	public CCGroupsPane() {
		//Getting the data :
		ArrayList<Group> groups = App.getInstance().getServicesProvider().getGroupsForUser(App.getInstance().getLoggedUser().getId());
				
		//Creating username and password fields inside their own label
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		for(int i = 0; i<groups.size(); i++) {
			CCGroup ccg = new CCGroup(groups.get(i));
			main.add(ccg);
		}
		main.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(CCColor.CCPRIMARYDARK.getColor()), TAG));
		this.add(main);
	}
}
