package home.decathlon;

import java.util.List;

public interface AthletesLoader {
	/**
	 * Load list of athletes and return them.
	 *
	 * @param additionalParams Some additional parameters could be passed if necessary.
	 * @return List of loaded athletes or empty list.
	 */
	List<Athlete> load(Object... additionalParams);
}
