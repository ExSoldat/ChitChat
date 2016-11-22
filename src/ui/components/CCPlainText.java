package ui.components;

import java.awt.Font;

import javax.swing.JLabel;

public class CCPlainText extends JLabel {
	public CCPlainText(String content) {
		super(content);
		setFont(getFont().deriveFont(Font.PLAIN));
	}
}
