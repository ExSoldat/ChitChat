package core.domain.proxy;

import java.util.ArrayList;

/**
 * An interface used to ensure the proxies implements these two methods
 * @author Mathieu
 *
 * @param <T> the type of data we want to to add a proxy to
 */
public interface VirtualProxy<T> {
	public void initialize(); //All high cost operations
	public void ensureIsInitialized(); //Before any actions, call this to ensured all the data inside the Proxified class has been loaded
}
