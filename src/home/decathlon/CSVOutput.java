package home.decathlon;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static home.decathlon.DecathlonEvent.*;

/**
 * Provides functionality to sort and save data in some file in CSV format.
 *
 */
public class CSVOutput implements AthletesOutput {

	private static final NumericDataRepresentHelper NUMERIC_DATA_REPRESENT_HELPER = NumericDataRepresentHelper.getInstance();
	private static final AthletePlaceComputeHelper ATHLETE_PLACE_COMPUTE_HELPER = AthletePlaceComputeHelper.getInstance();

	/**
	 * @param athletes             Athletes to be saved.
	 * @param additionalParameters Only one parameter accepted - name of file, where to save info.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void output(List<Athlete> athletes, Object... additionalParameters) {
		if (additionalParameters.length != 1)
			throw new IllegalArgumentException("Only one parameter should be given.");
		if (!(additionalParameters[0] instanceof String))
			throw new IllegalArgumentException("Parameter should be string!");

		try {
			File file = new File((String) additionalParameters[0]);
			FileUtils.write(file, buildStringForAllAthletes(athletes));
		} catch (Exception e) {
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
				stringBuilder.append(System.getProperty("line.separator"));

			buildOnePersonsDataForRepresentation(stringBuilder, athlete,places);
		}
		return stringBuilder.toString();
	}

	private void buildOnePersonsDataForRepresentation(StringBuilder stringBuilder, Athlete athlete, Map<Athlete, String> places) {
		stringBuilder
				.append(places.get(athlete)).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representPoints(athlete.computePoints())).append(',')
				.append('"').append(athlete.getName()).append('"').append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representSeconds(athlete.get(ONE_HUNDRED_METER_SPRINT))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(LONG_JUMP))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(SHOT_PUT))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(HIGH_JUMP))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMinuteAndSeconds(athlete.get(FOUR_HUNDRED_METER_SPRINT))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representSeconds(athlete.get(ONE_HUNDRED_TEN_METER_HURDLES))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(DISCUS_THROW))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(POLE_VAULT))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(JAVELIN_THROW))).append(',')
				.append(NUMERIC_DATA_REPRESENT_HELPER.representMinuteAndSeconds(athlete.get(THOUSAND_FIVE_HUNDRED_METER_SPRINT)));
	}
}
