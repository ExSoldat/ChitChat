package ui.components;

import java.awt.Color;

import utils.Constants;

public enum CCColor {
	CCPRIMARY(Color.decode(Constants.COLOR_PRIMARY)),
	CCDANGER(Color.decode(Constants.COLOR_DANGER)),
	CCPRIMARYDARK(Color.decode(Constants.COLOR_PRIMARYDARK)),
	CCPRIMARYLIGHT(Color.decode(Constants.COLOR_PRIMARYLIGHT)),
	CCNEARLYWHITE(Color.decode(Constants.COLOR_NEARLY_WHITE)),
	CCNEARLYGREY(Color.decode(Constants.COLOR_NEARLY_GREY)),
	CCSECONDARY(Color.decode(Constants.COLOR_SECONDARY)),
	CCGREEN(Color.decode(Constants.COLOR_GREEN));
	
	private Color mColor;
	
	CCColor(Color color) {
		this.mColor = color;
	}
	
	public Color getColor() {
		return mColor;
	}
}
