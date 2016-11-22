package ui.behavior;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.components.CCColor;

public class HoverBehavior implements MouseListener{
	Component component;
	public HoverBehavior(Component c) {
		this.component = c;
	}
		
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub			
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		component.setBackground(CCColor.CCNEARLYWHITE.getColor());
		component.setCursor(new Cursor(Cursor.HAND_CURSOR));;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		component.setBackground(CCColor.CCNEARLYGREY.getColor());
		component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));;

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
