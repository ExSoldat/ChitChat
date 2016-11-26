package ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import core.App;
import core.domain.EncryptMessage;
import core.domain.ExpirationMessage;
import core.domain.Message;
import core.domain.ReceiptMessage;
import core.domain.UrgentMessage;
import core.domain.User;
import ui.CCChatFrame;
import utils.Constants;

public class CCPrivateChatFrame extends CCChatFrame{
	ArrayList<Message> m = new ArrayList<Message>();
	public CCPrivateChatFrame(User me, User him) {
		super();
		m = App.getInstance().getServicesProvider().getMessagesBetween(me, him);
		this.setTitle(Constants.APP_NAME +  " - " + TAG + " - " + him.getFirstname() + " " + him.getLastname());
		this.getCCMessagesList().setData(m);
		this.getMainInfo().setText(him.getFirstname() + " " + him.getLastname());
		this.validate();
		
		this.send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m;
				if(receipt.isSelected()) {
					m = new ReceiptMessage(me, him, mes.getText());
				} else if (expirationtime.isSelected() && !time.getText().equals("")) {
					m = new ExpirationMessage(me, him, mes.getText(), time.getText());
				} else if (encrypt.isSelected()) {
					m = new EncryptMessage(me, him, mes.getText());
				} else if (urgent.isSelected()) {
					m = new UrgentMessage(me, him, mes.getText());
				} else {
					m = new Message();
				}
			}
		});
	}
}
