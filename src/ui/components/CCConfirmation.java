package ui.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.Constants;

//TODO : change teh extends, instead extends CCDialog
public class CCConfirmation extends JDialog {
	
	CCButton yesbutton, nobutton;
	CCLabel causedisplayed;
	JPanel main;
	
	public CCConfirmation(String confirmationcause) {
		this.setTitle("Are you sure ?");
		main = new JPanel();
		main.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(10, 10, 10, 10);
		
		causedisplayed = new CCLabel(confirmationcause);
		causedisplayed.setHorizontalAlignment(CCLabel.CENTER);
		yesbutton = new CCButton("YES", Constants.BUTTON_MAIN);
		nobutton = new CCButton("NO", Constants.BUTTON_DANGER);

		cs.gridx = 0;
		cs.gridwidth = 4;
		main.add(causedisplayed, cs);
		
		cs.gridx = 2;
		cs.gridy = 1;
		cs.gridwidth = 1;
		cs.weightx = 0.5;
		main.add(yesbutton, cs);
		
		//cs.insets = new Insets(10,100,10,100);
		cs.gridx = 3;
		cs.gridy = 1;
		main.add(nobutton, cs);
		this.setSize(Constants.CCDIALOG_DIMENSION);
		this.setLocationRelativeTo(null);
		this.add(main);
	}

	public void onYesClicked(ActionListener actionListener) {
		yesbutton.addActionListener(actionListener);
	}
	
	public void onNoClicked(ActionListener actionListener) {
		nobutton.addActionListener(actionListener);
	}
	
	public void morphCauseDisplayed(String cause, String newType) {
		if(newType.equals(Constants.ERROR))
			causedisplayed.setForeground(CCColor.CCDANGER.getColor());
		causedisplayed.setText(cause);
	}
	
	public void morphButtons(String positive, String negative) {
		yesbutton.setText(positive);
		nobutton.setText(negative);
	}
}
