package ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import core.App;
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
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(main);
        scrollPane.setBounds(50, 30, 300, 50);
		
		//Adding elements into the frame
        scrollPane.add(main);
        this.add(main, BorderLayout.CENTER);
		this.add(image, BorderLayout.WEST);		
		App.getInstance().setDevFrame(this);
		
		main.add(new CCLabel("Welcome to the dev log window"));
		
		//Configure the frame
		this.setSize(550,550);
		//this.setSize(800,200);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setLocation(0,0);
	}
	
	public void addLog(JLabel label) {
		main.add(label);
		validate();
	}
}
