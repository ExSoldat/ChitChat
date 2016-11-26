package ui.components;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import utils.Constants;

public class CCUsersListCellRenderer extends JLabel implements ListCellRenderer {

	public CCUsersListCellRenderer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		String s = value.toString(); //get the user's last and firstnames
		
		setBorder(BorderFactory.createLineBorder(CCColor.CCNEARLYGREY.getColor()));
		setHorizontalAlignment(CENTER);
		setPreferredSize(Constants.LIST_ITEM_DIMENSION);
		if(!isSelected) {
			setBackground(CCColor.CCNEARLYWHITE.getColor());
		} else {
			setBackground(CCColor.CCPRIMARYLIGHT.getColor());
		}
			
		Border border = getBorder();
		Border margin = new EmptyBorder(0, 1, 0, 1);
		setBorder(new CompoundBorder(border, margin));
		setText(s);
		setEnabled(list.isEnabled());
		setOpaque(true);
		setFont(list.getFont());
		return this;
	}
	
	

}
