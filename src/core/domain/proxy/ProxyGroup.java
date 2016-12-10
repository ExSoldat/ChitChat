package core.domain.proxy;

import core.App;
import core.domain.Discussion;
import core.domain.Group;
import core.domain.VirtualProxy;

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
	public void initialize() {
		//WIP
		this.setAdministrator(App.getInstance().getServicesProvider().getAdministratorOfGroup(this));
		this.setParticipants(App.getInstance().getServicesProvider().getParticipantsOfGroup(this));
		this.setMessages(App.getInstance().getServicesProvider().getMessagesForGroup(this));
	}

	@Override
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
