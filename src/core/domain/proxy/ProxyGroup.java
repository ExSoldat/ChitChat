package core.domain.proxy;

import core.App;
import core.domain.Group;
import core.domain.VirtualProxy;

public class ProxyGroup extends Group implements VirtualProxy<Group> {

	
	public Group instance = null;
	
	public ProxyGroup(int id, String name, String description) {
		super(id, name, description);
		initialize();
	}
	
	
	@Override
	public void initialize() {
		//WIP
		this.setAdministrator(App.getInstance().getServicesProvider().getAdministratorOfGroup(this));
		this.setParticipants(App.getInstance().getServicesProvider().getParticipantsOfGroup(this));
	}

	@Override
	public void ensureIsInitialized() {
		if(instance==null) {
			instance = new Group(getId(), getName(), getDescription());
			initialize();
		}
	}
}
