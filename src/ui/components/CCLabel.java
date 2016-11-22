package ui.components;

import java.awt.Color;

import javax.swing.JLabel;

import utils.Constants;

public class CCLabel extends JLabel {
	public CCLabel(String arg) {
		super(arg);
		this.setForeground(CCColor.CCPRIMARY.getColor());
	}
}