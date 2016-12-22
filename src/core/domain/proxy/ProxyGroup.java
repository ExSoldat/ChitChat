package core.domain.proxy;

import core.App;
import core.domain.Group;
import core.domain.messages.Discussion;

/**
 * A proxy for the groups. It is used to load the participants, messages and administrator only hen needed
 * @author Mathieu
 *
 */
public class ProxyGroup extends Group implements VirtualProxy<Group> {

	
	public Group instance = null;
	
	public ProxyGroup(int id, String name, String description) {
		super(id, name, description);
		initialize();
	}
	
	
	public ProxyGroup(String text, ProxyUser loggedUser, String text2) {
		super(text, loggedUser, text2);
	}


	@Override
	/**
	 * Initialize the heaby data of the groups
	 */
	public void initialize() {
		this.setAdministrator(App.getInstance().getServicesProvider().getAdministratorOfGroup(this));
		this.setParticipants(App.getInstance().getServicesProvider().getParticipantsOfGroup(this));
		this.setMessages(App.getInstance().getServicesProvider().getMessagesForGroup(this));
	}

	@Override
	/**
	 * Used when we call a method depending on what should be initialized (messages, participants, administrators etc)
	 */
	public void ensureIsInitialized() {
		if(instance==null) {
			instance = new Group(getId(), getName(), getDescription());
			initialize();
		}
	}

	public Discussion getMessages() {
		ensureIsInitialized();
		return messages;
	}


}
