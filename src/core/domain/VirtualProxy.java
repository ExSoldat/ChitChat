package core.domain;

import java.util.ArrayList;

public interface VirtualProxy<T> {
	public void initialize(); //All high cost operations
	public void ensureIsInitialized(); //Before any actions, call this to ensured all the data inside the Proxified class has been loaded
}
