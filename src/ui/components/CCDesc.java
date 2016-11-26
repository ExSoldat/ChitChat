package ui.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constants;

public class CCDesc extends JPanel {
	private CCLabel label;
	private CCPlainText displayedValue;
	
	public CCDesc() {
		this.label = new CCLabel("Nan");
		this.displayedValue = new CCPlainText("NaN");
		this.add(this.label);
		this.add(this.displayedValue);
	}
	
	public CCDesc(String title) {
		this.label = new CCLabel(title + " : ");
		this.displayedValue = new CCPlainText("NaN");	
		this.add(this.label);
		this.add(this.displayedValue);
	}
	
	public CCDesc(String title, String value) {
		this.label = new CCLabel(title);
		this.displayedValue = new CCPlainText(value);
		this.add(this.label);
		this.add(this.displayedValue);
	}
	
	public void setDisplayedValue(String value) {
		this.displayedValue.setText(value);
	}
	
	public void setTitle(String title) {
		this.label.setText(title + " : ");
	}
}
