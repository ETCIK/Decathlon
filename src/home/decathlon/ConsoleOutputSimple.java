package home.decathlon;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Output: a List of athletes + place + points on a Standard output stream.
 *
 */
public class ConsoleOutputSimple implements AthletesOutput {
	private static final NumericDataRepresentHelper NUMERIC_DATA_REPRESENT_HELPER = NumericDataRepresentHelper.getInstance();
	private static final AthletePlaceComputeHelper ATHLETE_PLACE_COMPUTE_HELPER = AthletePlaceComputeHelper.getInstance();
	public OutputStream out = System.out;

	/**
	 * @param athletes             Athletes to be displayed.
	 * @param additionalParameters All parameters are ignored.
	 */
	@Override
	public void output(List<Athlete> athletes, Object... additionalParameters) {
		try {
			out.write(buildStringForAllAthletes(athletes).getBytes());
			out.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private String buildStringForAllAthletes(List<Athlete> athletes) {
		Collections.sort(athletes);
		StringBuilder stringBuilder = new StringBuilder(8000);
		boolean isFirstLine = true;
		Map<Athlete, String> places = ATHLETE_PLACE_COMPUTE_HELPER.computePlacesFor(athletes);

		for (Athlete athlete : athletes) {
			if (isFirstLine)
				isFirstLine = false;
			else
				stringBuilder.append(IOUtils.LINE_SEPARATOR);

			buildOnePersonsDataForRepresentation(stringBuilder, athlete, places );
		}
		stringBuilder.append(IOUtils.LINE_SEPARATOR);
		
		return stringBuilder.toString();
	}

	private void buildOnePersonsDataForRepresentation(StringBuilder stringBuilder, Athlete athlete, Map<Athlete, String> places) {
		stringBuilder.append(places.get(athlete)).append(". ")
					.append(athlete.getName())
					.append(" (")
					.append(NUMERIC_DATA_REPRESENT_HELPER.representPoints(athlete.computePoints()))
					.append(")");
	}
}
