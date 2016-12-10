package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import core.App;
import core.domain.Discussion;
import core.domain.EncryptMessage;
import core.domain.ExpirationMessage;
import core.domain.Group;
import core.domain.Message;
import core.domain.ReceiptMessage;
import core.domain.UrgentMessage;
import core.domain.User;
import core.domain.proxy.ProxyUser;
import ui.components.CCGroupsPane;
import utils.Constants;

public class CCGroupChatFrame extends CCChatFrame {
	CCGroupsPane mParent;
	Discussion m = new Discussion();
	ProxyUser me;
	Group them;
	public CCGroupChatFrame(ProxyUser me, Group them) {
		super();
		this.me = me;
		this.them = them;
	}
	
	@Override
	public void init() {
		this.setIconImage(Constants.APP_LOGO.getImage());
		super.init();
		them.getMessages();
		m = them.getMessages();
		this.setTitle(Constants.APP_NAME +  " - " + TAG + " - " + them.getName());
		getCCMessagesList().setData(m);
		scrollMessagesToBottom();
		getMainInfo().setText(them.getName());
		getSendButton().addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Message m;/*
				if(receipt.isSelected()) {
					m = new ReceiptMessage(me, them, mes.getText());
				} else if (expirationtime.isSelected() && !time.getText().equals("")) {
					m = new ExpirationMessage(me, them, mes.getText(), time.getText());
				} else if (encrypt.isSelected()) {
					m = new EncryptMessage(me, them, mes.getText());
				} else if (urgent.isSelected()) {
					m = new UrgentMessage(me, them, mes.getText());
				} else {
					m = new Message(me, them, mes.getText());
				}*/
				m = new Message(me, them, mes.getText());
				m.setDiscussionId(them.getDiscussionId());
				mes.getTextField().setText("");
				App.getInstance().getServicesProvider().sendMessage(m);
				refreshMessages();
			}
		});
		CCGroupMembersFrame gmf = new CCGroupMembersFrame(them);
		gmf.init(this);
		gmf.setLocationRelativeTo(this);
		gmf.setVisible(true);
	}

	public void setParent(CCGroupsPane parent) {
		this.mParent = parent;
	}
	
	@Override
	public void refreshMessages() {
		them.setMessages(App.getInstance().getServicesProvider().getMessagesForGroup(them));
		list.setData(them.getMessages());
		messagesList.validate();
	}
	
	public void closeAfterLeaving() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		mParent.refreshGroups();
	}
}
