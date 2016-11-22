package ui.components;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.UIManager;

import utils.Constants;

public class CCButton extends JButton {
	//JButton signin = new JButton("SIGN IN");
	Color mBGColor;
	public CCButton(String label, int type) {
		super(label.toUpperCase());
		switch (type) {
		case Constants.BUTTON_DANGER :
			this.mBGColor = CCColor.CCDANGER.getColor();
			this.setBackground(mBGColor);
			this.setForeground(CCColor.CCNEARLYWHITE.getColor());
			break;
		case Constants.BUTTON_SECONDARY:
			this.mBGColor = CCColor.CCPRIMARYLIGHT.getColor();
			this.setBackground(mBGColor);
			this.setForeground(CCColor.CCNEARLYWHITE.getColor());
			break;
		case Constants.BUTTON_MAIN :
		default :
			this.mBGColor = CCColor.CCPRIMARY.getColor();
			this.setBackground(mBGColor);
			this.setForeground(CCColor.CCNEARLYWHITE.getColor());
			break;
		}
	}
	public void retrieveBackgroundColor() {
		if (isEnabled())
			this.setBackground(mBGColor);
		else
			this.setBackground(CCColor.CCNEARLYGREY.getColor());
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		retrieveBackgroundColor();
	}
}
