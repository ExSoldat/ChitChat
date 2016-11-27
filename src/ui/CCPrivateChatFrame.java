package ui;

import java.awt.Component;
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
import utils.Constants;

public class CCPrivateChatFrame extends CCChatFrame {
	ArrayList<Message> m = new ArrayList<Message>();
	User me, him;
	public CCPrivateChatFrame(User me, User him) {
		super();
		this.me = me;
		this.him = him;
	}
	
	@Override
	public void init() {
		super.init();
		m = App.getInstance().getServicesProvider().getMessagesBetween(me, him);
		this.setTitle(Constants.APP_NAME +  " - " + TAG + " - " + him.getFirstname() + " " + him.getLastname());
		getCCMessagesList().setData(m);
		scrollMessagesToBottom();
		getMainInfo().setText(him.getFirstname() + " " + him.getLastname());
		getSendButton().addActionListener(new ActionListener() {
		
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
					m = new Message(me, him, mes.getText());
				}
				mes.getTextField().setText("");
				App.getInstance().getServicesProvider().sendMessage(m);
				refreshMessages();
			}
		});
	}

	@Override
	public void refreshMessages() {
		ArrayList<Message> m = App.getInstance().getServicesProvider().getMessagesBetween(me, him, true);
		list.setData(m);
	}
}
