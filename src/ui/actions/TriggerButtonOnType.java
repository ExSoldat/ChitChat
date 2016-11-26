package ui.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import ui.components.CCButton;

public class TriggerButtonOnType implements KeyListener {

	public JTextField tf;
	public CCButton buttonAffected;
	
	public TriggerButtonOnType(JTextField tf, CCButton button) {
		this.tf = tf;
		this.buttonAffected = button;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(tf.getText().equals(""))
			buttonAffected.setEnabled(false);
		else 
			buttonAffected.setEnabled(true);
	}

}
