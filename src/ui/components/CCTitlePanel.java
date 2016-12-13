package ui.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CCTitlePanel extends JPanel {
	JLabel picture;
	CCLabel title;
	
	public CCTitlePanel(ImageIcon imageurl, String label) {
		picture = new JLabel(imageurl);
		picture.setHorizontalAlignment(JLabel.CENTER);
		title = new CCLabel(label);
		title.setHorizontalAlignment(CCLabel.CENTER);
		add(picture);
		add(title);
	}
	
	public void setTitle(String label) {
		title.setText(label);
	}
	
	public void setTitleColor(Color c) {
		title.setForeground(c);
	}
}