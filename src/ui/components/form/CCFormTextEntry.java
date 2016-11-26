package ui.components.form;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.components.CCColor;
import ui.components.CCLabel;
import utils.Constants;

public class CCFormTextEntry extends JPanel {	
	private CCLabel label;
	private JTextField tf;
	
	public CCFormTextEntry(String display, boolean required, boolean hidden) {
		this.label = required ? new CCLabel(display + " * : ") : new CCLabel(display + " : ");
		this.label.setForeground(required ? CCColor.CCDANGER.getColor() : CCColor.CCPRIMARYDARK.getColor());
		this.tf = hidden ? new JPasswordField() : new JTextField();
		tf.setPreferredSize(Constants.FORMTEXTFIELD_DIMENSION);
		this.add(this.label);
		this.add(this.tf);
	}

	public JTextField getTextField() {
		return tf;
	}
	
	public String getText() {
		return tf.getText();
	}
}