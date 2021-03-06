package utils;

import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Constants {

	//---------Configuration constants :
	public static final String LOCAL_URL = "jdbc:mysql://localhost/chitchat"; // Change the name to the database depending on the one you use
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	//End of the configuration constants.
	
	public static final String APP_NAME = "Chit Chat";
	
	public static final String COLOR_PRIMARY = "#00AEEF";
	public static final String COLOR_DANGER = "#F21837";
	public static final String COLOR_PRIMARYDARK = "#00516F";
	public static final String COLOR_PRIMARYLIGHT = "#48BDF2";
	public static final String COLOR_NEARLY_WHITE = "#FCF9F8";
	public static final String COLOR_SECONDARY = "#0133B9";
	public static final String COLOR_NEARLY_GREY = "#EEEEFF";	
	public static final String COLOR_GREEN = "#4FF80C";
	//-------------------------Button actions
	public static final int BUTTON_MAIN = 20;
	public static final int BUTTON_DANGER = 21;
	public static final int BUTTON_SECONDARY = 22;

	//-------------------------Dimensions
	public static final Dimension HOME_DIMENSION = new Dimension(700,700);
	public static final Dimension GROUP_BOX_DIMENSION = new Dimension(400,70);
	public static final Dimension LIST_ITEM_DIMENSION = new Dimension(300, 30);
	public static final Dimension FRAGMENT_DIMENSION = new Dimension(300,300);
	public static final Dimension CCCONFIRMATION_DIMENSION = new Dimension(400,200);
	public static final Dimension FORM_DIMENSION = new Dimension(700, 300);
	public static final Dimension FORMTEXTFIELD_DIMENSION = new Dimension(300,25);
	public static final Dimension ALL_GROUPS_DIMENSION = new Dimension(450, 300);
	public static final Dimension ALL_NOTIFICATIONS = new Dimension(450,600);
	public static final Dimension CHAT_MEMBERS_FRAME = new Dimension(330, 700);
	public static final Dimension NOTIFICATIONS_FRAME_DIMENSION = new Dimension(450, 700);
	public static final Dimension CHAT_MEMBERS_LIST = new Dimension(330, 600);
	public static final Dimension NOTIFICATIONS_LIST = new Dimension(400, 675);
	public static final Dimension CHAT_MEMBERS_BUTTONSPANEL = new Dimension(330, 100);
	public static final Dimension LOGIN_FRAME_DIMENSION_MAXIMIZED = new Dimension(550,100);
	public static final Dimension LOGOUT_FRAME_DIMENSION = new Dimension(200,100);
	public static final Dimension CHAT_FRAME_DIMENSION = new Dimension(650,650);
	public static final Dimension CHAT_HEADER_DIMENSION = new Dimension(650,20);
	public static final Dimension CHAT_MESSAGES_DIMENSION = new Dimension(650,410);
	public static final Dimension CHAT_USERSMESSAGE_DIMENSION = new Dimension(650,120);
	public static final Dimension MESSAGE_DIMENSION = new Dimension(400,50);	
	public static final Dimension FORMTEXTFIELD_MINI_DIMENSION = new Dimension(100,25);
	//--------------------------Other Constants Constants
	public static final String ERROR = "ERROR";
	public static final String SUCCESS = "SUCCESS";
	public static final String INFO = "INFO";
	public static final String RESPONSE = "RESPONSE";
	public static final String USE_CASE = "USE-CASE";
	public static final String NOTE = "NOTE";
	public static final String SQL = "SQL";

	public static final ImageIcon APP_LOGO = new ImageIcon("img/chitchat_mini.png");
	public static final ImageIcon DEFAULT_PROFILE_PICTURE = new ImageIcon("img/ic_person_orange.png");
	public static final boolean DEV_MODE = true;
}
