package ui.components;

import javax.swing.JPanel;

public class CCDesc extends JPanel {
	private CCLabel label;
	private CCPlainText displayedValue;
	
	public CCDesc(String title) {
		this.label = new CCLabel(title + " : ");
		this.displayedValue = new CCPlainText("NaN");	
		this.label.setHorizontalAlignment(CCLabel.CENTER);
		this.displayedValue.setHorizontalAlignment(CCPlainText.CENTER);
		this.add(this.label);
		this.add(this.displayedValue);
	}
	
	public CCDesc(String title, String value) {
		this.label = new CCLabel(title);
		this.displayedValue = new CCPlainText(value);
	}
	
	public void setDisplayedValue(String value) {
		this.displayedValue.setText(value);;
	}
	
	
	
}
