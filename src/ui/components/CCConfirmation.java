package ui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.Constants;

public class CCConfirmation extends JFrame {
	
	CCButton okbutton, nobutton;
	
	public CCConfirmation(String confirmationcause) {
		super("Are you sure ?");
		JPanel main = new JPanel();
		main.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(10, 10, 10, 10);
		
		CCLabel causedisplayed = new CCLabel(confirmationcause);
		causedisplayed.setHorizontalAlignment(CCLabel.CENTER);
		okbutton = new CCButton("OK", Constants.BUTTON_MAIN);
		nobutton = new CCButton("NO", Constants.BUTTON_DANGER);
		
		cs.gridx = 0;
		cs.gridwidth = 4;
		main.add(causedisplayed, cs);
		
		cs.gridx = 2;
		cs.gridy = 1;
		cs.gridwidth = 1;
		cs.weightx = 0.5;
		main.add(okbutton, cs);
		cs.gridx = 3;
		cs.gridy = 1;
		main.add(nobutton, cs);
		this.setSize(Constants.CCDIALOG_DIMENSION);
		this.setLocationRelativeTo(null);
		this.add(main);
	}

	public void setModal(boolean b) {
		this.setAutoRequestFocus(true);
		this.setAlwaysOnTop(true);
	}

	public void onOkClicked(ActionListener actionListener) {
		okbutton.addActionListener(actionListener);
	}
	
	public void onNoClicked(ActionListener actionListener) {
		nobutton.addActionListener(actionListener);
	}
}
