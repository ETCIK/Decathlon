package home.decathlon;

import java.util.List;

public interface AthletesOutput {
	/**
	 * Save list of athletes.
	 *
	 * @param athletes             Athletes to be saved.
	 * @param additionalParameters Some additional parameters that could be needed for persistence.
	 */
	void output(List<Athlete> athletes, Object... additionalParameters);
}
