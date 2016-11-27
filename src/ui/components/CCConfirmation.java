package ui.components;

//TODO : change teh extends, instead extends CCDialog
public class CCConfirmation extends CCDialog {
	
	CCLabel causedisplayed;
	
	
	public CCConfirmation(String confirmationcause) {
		super("Are you sure ?");
		
		causedisplayed = new CCLabel(confirmationcause);
		causedisplayed.setHorizontalAlignment(CCLabel.CENTER);
		
		setMainComponent(causedisplayed);
		morphButtons("YES", "NO");
	}
	
	//public void showError() {
		//causedisplayed.setForeground(CCColor.CCDANGER.getColor());
		//causedisplayed.setText("An error occured. Please try again");
	//}
}
