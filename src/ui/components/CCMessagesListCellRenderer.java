package ui.components;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import core.App;
import core.domain.messages.Message;
import core.domain.proxy.ProxyUser;
import utils.Constants;

public class CCMessagesListCellRenderer extends JLabel implements ListCellRenderer {
	
	public CCMessagesListCellRenderer() {
		// TODO Auto-generated constructor stub
		Border border = getBorder();
		Border margin = new EmptyBorder(0, 10, 0, 10);
		setBorder(new CompoundBorder(border, margin));
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Message message = (Message) value;
		this.setPreferredSize(Constants.MESSAGE_DIMENSION);
		ProxyUser loggedUser = App.getInstance().getLoggedUser();
		if(message.getSender().equals(loggedUser)) {
			this.setHorizontalAlignment(JLabel.RIGHT);
		} else {
			this.setHorizontalAlignment(JLabel.LEFT);
		}
		setBackground(CCColor.CCNEARLYWHITE.getColor());		
		setText("<html><font color = " + Constants.COLOR_SECONDARY + ">[" + message.getFormattedDate() + "] </font><font color="+ Constants.COLOR_PRIMARY + ">" + message.getSender().getFirstname() + " " + message.getSender().getLastname() + " : " + "</font><font>" +  message.getContent() + "</font></html>");
		return this;
	}
}