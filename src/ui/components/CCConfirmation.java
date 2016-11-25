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
public class CCConfirmation extends CCDialog {
	
	CCLabel causedisplayed;
	
	
	public CCConfirmation(String confirmationcause) {
		super("Are you sure ?");
		
		causedisplayed = new CCLabel(confirmationcause);
		causedisplayed.setHorizontalAlignment(CCLabel.CENTER);
		setMainComponent(causedisplayed);
		morphButtons("YES", "NO");
	}
	
	//public void showError() {
		//causedisplayed.setForeground(CCColor.CCDANGER.getColor());
		//causedisplayed.setText("An error occured. Please try again");
	//}
}
