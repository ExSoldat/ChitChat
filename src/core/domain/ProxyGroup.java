package core.domain;

import core.App;

public class ProxyGroup extends Group implements VirtualProxy<Group> {

	
	public Group instance = null;
	
	public ProxyGroup(int id, String name, String description) {
		super(id, name, description);
	}
	
	
	@Override
	public void initialize() {
		//WIP
		this.setAdministrator(App.getInstance().getServicesProvider().getAdministratorOfGroup(this));
	}

	@Override
	public void ensureIsInitialized() {
		// TODO Auto-generated method stub
		
	}

}
