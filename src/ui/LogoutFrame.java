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
import ui.components.CCLabel;
import utils.Constants;

public class LogoutFrame extends JFrame {
	public static String TAG = "Logout";
	public LogoutFrame() {
		super(Constants.APP_NAME +  " - " + TAG);
	}
	
	public void init(HomeFrame hf) {
		//Creating the frame
		this.setLayout(new BorderLayout(10,0));
		
		//Creating the logo
		ImageIcon imageurl = new ImageIcon("img/chitchat_mini.png");
		JLabel image = new JLabel(imageurl);
		
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.fill = GridBagConstraints.HORIZONTAL;
        
        //Creating the connect button
        JPanel signinPanel = new JPanel(new GridBagLayout());
        CCButton logout = new CCButton("LOG OUT", Constants.BUTTON_DANGER);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(0, 0, 0, 25);
        signinPanel.add(logout, cs);
		
		//Adding elements into the frame
		this.add(image, BorderLayout.WEST);		
		this.add(signinPanel, BorderLayout.EAST);
		//Configure the signin button
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean r = App.getInstance().getServicesProvider().disconnect(App.getInstance().getLoggedUser());
				if (r) {
					hf.dispose();
					LoginFrame lf = new LoginFrame();
					lf.init();
					lf.setVisible(true);
					dispose();
				}
			}
		});
		
		//Configure the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Constants.LOGOUT_FRAME_DIMENSION);
		//this.setSize(800,200);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth() - 60, Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()*2);
	}
}
