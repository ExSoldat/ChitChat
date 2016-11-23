package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.App;
import core.domain.User;
import ui.components.CCButton;
import ui.components.CCError;
import ui.components.CCLabel;
import utils.Constants;

public class DevFrame extends JFrame{
	public static String TAG = "Developper Tool Box";
	JPanel main;
	public DevFrame() {
		super(Constants.APP_NAME +  " - " + TAG);
	}
	
	public void init() {
		//Creating the frame
		this.setLayout(new BorderLayout(10,10));
		
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		JLabel image = new JLabel(imageurl);
		
		//Creating username and password fields inside their own label
		main = new JPanel(new BoxLayout(main, BoxLayout.Y_AXIS));
        
		
		//Adding elements into the frame
        this.add(main, BorderLayout.CENTER);
		this.add(image, BorderLayout.WEST);		
		App.getInstance().setDevFrame(this);
		
		//Configure the frame
		this.setSize(550,550);
		//this.setSize(800,200);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setLocation(0,0);
	}
	
	public void addLog(JLabel label) {
		main.add(label);
	}
}
