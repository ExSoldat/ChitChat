package ui.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import utils.Constants;

public class CCDialog extends JDialog {
	CCButton positiveButton, negativeButton;
	JPanel main;
	JPanel componentHost;
	CCLabel info = new CCLabel("");
	GridBagConstraints cs = new GridBagConstraints(); //Allow this to be accessed from its daughter classes
	
	public CCDialog() {
		this("");
	}
	
	public CCDialog(String title) {
		this.setTitle(title);
		
		main = new JPanel();
		main.setLayout(new GridBagLayout());
		
		componentHost = new JPanel();
		componentHost.setLayout(new BorderLayout());
		
		positiveButton = new CCButton("YES", Constants.BUTTON_MAIN);
		negativeButton = new CCButton("NO", Constants.BUTTON_DANGER);
		
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.insets = new Insets(10, 10, 10, 10);
		
		cs.gridx = 0;
		cs.gridwidth = 4;
		main.add(componentHost, cs);
		
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 4;
		info.setHorizontalAlignment(CCLabel.CENTER);
		main.add(info, cs);
		
		cs.gridx = 2;
		cs.gridy = 2;
		cs.gridwidth = 1;
		cs.weightx = 0.5;
		main.add(positiveButton, cs);
		
		cs.gridx = 3;
		cs.gridy = 2;
		main.add(negativeButton, cs);
		
		onNegativeClicked(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.getRootPane().setDefaultButton(positiveButton);
		this.setSize(Constants.CCCONFIRMATION_DIMENSION);
		this.setLocationRelativeTo(null);
		this.add(main);
	}
	
	public void setMainComponent(Component mainComponent) {
		componentHost.add(mainComponent, BorderLayout.CENTER);
	}
	
	public void morphButtons(String positive, String negative) {
		positiveButton.setText(positive.toUpperCase());
		negativeButton.setText(negative.toUpperCase());
	}
	
	public void onPositiveClicked(ActionListener actionListener) {
		positiveButton.addActionListener(actionListener);
	}
	
	public void onNegativeClicked(ActionListener actionListener) {
		negativeButton.addActionListener(actionListener);
	}

	public Component getPositiveButton() {
		return positiveButton;
	}
	
	public void showInfo(String text) {
		this.info.setText(text);
	}
	
	public void showError() {
		this.info.setForeground(CCColor.CCDANGER.getColor());
		this.info.setText("An error occured. Please try again.");
	}
}
