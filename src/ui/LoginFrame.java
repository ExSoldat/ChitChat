package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.App;
import core.domain.User;
import ui.components.CCButton;
import ui.components.CCColor;
import ui.components.CCError;
import ui.components.CCLabel;
import utils.Constants;

public class LoginFrame extends JFrame {
	public static String TAG = "Login";
	public LoginFrame() {
		super(Constants.APP_NAME +  " - " + TAG);
	}
	
	public void init() {
		//Creating the frame
		this.setLayout(new BorderLayout(10,0));
		
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		JLabel image = new JLabel(imageurl);
		
		//Creating username and password fields inside their own label
		JPanel credentials = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.fill = GridBagConstraints.HORIZONTAL;
		
		CCLabel labelUS = new CCLabel("Username : ");
		cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        credentials.add(labelUS, cs);
        
        JTextField textFieldUS = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        credentials.add(textFieldUS, cs);
        
        CCLabel labelPW = new CCLabel("Password : ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        credentials.add(labelPW, cs);
 
        JPasswordField textFieldPW = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        credentials.add(textFieldPW, cs);
        
        CCLabel errorView = new CCLabel("Invalid credentials");
        errorView.setForeground(CCColor.CCDANGER.getColor());
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        credentials.add(errorView, cs);
        errorView.setVisible(false);
        
        //Creating the connect button
        JPanel signinPanel = new JPanel(new GridBagLayout());
        CCButton signin = new CCButton("LOG IN", Constants.BUTTON_MAIN);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(0, 0, 0, 25);
        signinPanel.add(signin, cs);
		
		//Adding elements into the frame
        this.add(credentials, BorderLayout.CENTER);
		this.add(image, BorderLayout.WEST);		
		this.add(signinPanel, BorderLayout.EAST);
		LoginFrame mFrame = this;
		//Configure the signin button
		signin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				User r = App.getInstance().getServicesProvider().connect(textFieldUS.getText(), textFieldPW.getText());
				if (r == null) {
					errorView.setVisible(true);
				} else {
					App.getInstance().setConnectedUser(r);
					errorView.setVisible(false);
					LogoutFrame lo = new LogoutFrame();
					HomeFrame hf = new HomeFrame();
					hf.init();
					lo.init(hf);
					lo.setVisible(true);
					hf.setVisible(true);
					dispose();
				}
			}
		});
		
		//Configure the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Constants.LOGIN_FRAME_DIMENSION_MAXIMIZED);
		//this.setSize(800,200);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth() - 60, Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()*2);
	}
}
