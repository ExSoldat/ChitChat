package core.domain.messages;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.domain.Group;
import core.domain.User;
import core.domain.proxy.ProxyUser;
/**
 * A class representing a message
 * @author Mathieu
 *
 */
public class Message {
	private ProxyUser sender, receiver;
	private Group groupReceiver;
	private Integer discussionId;
	private String content;
	private Date date;
	public DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
	
	public Message() {
	}
	
	/**
	 * Many constructors depending on where the message belongs
	 * @param sender
	 * @param content
	 * @param date
	 */
	public Message(ProxyUser sender, String content, Timestamp date) {
		this.sender = sender;
		this.content = content;
		this.date = new Date(date.getTime());
	}
	/**
	 * 
	 * @param sender
	 * @param receiver
	 * @param content
	 */
	public Message(ProxyUser sender, ProxyUser receiver, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}
	/**
	 * 
	 * @param sender
	 * @param receivers
	 * @param content
	 */
	public Message(ProxyUser sender, Group receivers, String content) {
		this.sender = sender;
		this.groupReceiver = receivers;
		this.content = content;
	}
	/**
	 * 
	 * @param sender
	 * @param receiver
	 * @param content
	 * @param date
	 */
	public Message(ProxyUser sender, ProxyUser receiver, String content, Date date) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.date = date;
	}
	/**
	 * 
	 * @param sender
	 * @param receiver
	 * @param content
	 * @param date
	 */
	public Message(ProxyUser sender, Group receiver, String content, Date date) {
		this.sender = sender;
		this.groupReceiver = receiver;
		this.content = content;
		this.date = date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(ProxyUser sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFormattedDate() {
		return dateFormat.format(date);
	}

	public Integer getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(Integer discussionId) {
		this.discussionId = discussionId;
	}
	
	public long getDate() {
		return date.getTime();
	}
	
	
}
