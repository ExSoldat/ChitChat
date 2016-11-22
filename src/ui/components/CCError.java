package ui.components;

import javax.swing.JLabel;

public class CCError extends JLabel {
	public CCError(String arg) {
		super(arg);
		this.setForeground(CCColor.CCDANGER.getColor());
	}
}
