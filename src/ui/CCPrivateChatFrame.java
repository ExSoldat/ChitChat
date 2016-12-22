package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import core.App;
import core.domain.User;
import core.domain.messages.Discussion;
import core.domain.messages.EncryptMessage;
import core.domain.messages.ExpirationMessage;
import core.domain.messages.Message;
import core.domain.messages.ReceiptMessage;
import core.domain.messages.UrgentMessage;
import core.domain.proxy.ProxyUser;
import utils.Constants;

public class CCPrivateChatFrame extends CCChatFrame {
	Discussion m = new Discussion(); //TODO change this
	ProxyUser me, him;
	public CCPrivateChatFrame(ProxyUser me, ProxyUser him) {
		super();
		this.me = me;
		this.him = him;
	}
	
	@Override
	public void init() {
		super.init();
		this.setIconImage(Constants.DEFAULT_PROFILE_PICTURE.getImage());
		image.setIcon(Constants.DEFAULT_PROFILE_PICTURE);
		//image.validate();
		m = App.getInstance().getServicesProvider().getMessagesBetween(me, him);
		this.setTitle(Constants.APP_NAME +  " - " + TAG + " - " + him.getFirstname() + " " + him.getLastname());
		getCCMessagesList().setData(m);
		scrollMessagesToBottom();
		getMainInfo().setText(him.getFirstname() + " " + him.getLastname());
		getSendButton().addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
/*if(receipt.isSelected()) {
					m = new ReceiptMessage(me, them, mes.getText());
				} else if (expirationtime.isSelected() && !time.getText().equals("")) {
					m = new ExpirationMessage(me, them, mes.getText(), time.getText());
				} else if (encrypt.isSelected()) {
					m = new EncryptMessage(me, them, mes.getText());
				} else if (urgent.isSelected()) {
					m = new UrgentMessage(me, them, mes.getText());
				} else {
					m = new Message(me, them, mes.getText());
				}
				m = new Message(me, them, mes.getText());
				m.setDiscussionId(them.getDiscussionId());
				mes.getTextField().setText("");
				App.getInstance().getServicesProvider().sendMessage(m);
				refreshMessages();
				Message m;*/
				Message mMessage = new Message(me, him, mes.getText());
				mMessage.setDiscussionId(App.getInstance().getServicesProvider().getDiscussionIdBetween(me, him)); //TODO Change this
				mes.getTextField().setText("");
				App.getInstance().getServicesProvider().sendMessage(mMessage);
				refreshMessages();
			}
		});
	}

	@Override
	public void refreshMessages() {
		Discussion m = App.getInstance().getServicesProvider().getMessagesBetween(me, him);
		list.setData(m);
		messagesList.validate();
	}
}
