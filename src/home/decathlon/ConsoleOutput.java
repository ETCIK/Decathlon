package home.decathlon;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static home.decathlon.DecathlonEvent.*;

/**
 * This class should be used if you want to output a List of athletes on a Standard output stream.
 *
 */
public class ConsoleOutput implements AthletesOutput {
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
		int maxLengthOfName = findMaxLengthOfNameIn(athletes);
		appendHeaderTo(stringBuilder, maxLengthOfName);
		for (Athlete athlete : athletes) {
			if (isFirstLine)
				isFirstLine = false;
			else
				stringBuilder.append(IOUtils.LINE_SEPARATOR);

			buildOnePersonsDataForRepresentation(stringBuilder, athlete, places, maxLengthOfName);
		}
		stringBuilder.append(IOUtils.LINE_SEPARATOR);
		appendLineSeparatorTo(stringBuilder, maxLengthOfName);
		return stringBuilder.toString();
	}

	private void appendHeaderTo(StringBuilder stringBuilder, int maxLengthOfName) {
		appendLineSeparatorTo(stringBuilder, maxLengthOfName);
		stringBuilder.append("|Place").append("|Points|")
				.append(String.format("%-" + maxLengthOfName + "s", "Name"))
				.append("|100 m   ")
				.append("|Long    ")
				.append("|Shot put")
				.append("|High    ")
				.append("|400 m   ")
				.append("|110 m   ")
				.append("|Discus  ")
				.append("|Pole    ")
				.append("|Javelin ")
				.append("|1500 m  |")
				.append(IOUtils.LINE_SEPARATOR);
		stringBuilder.append("|     ").append("|      |")
				.append(String.format("%" + maxLengthOfName + "s", ""))
				.append("|  sprint")
				.append("|    jump")
				.append("|        ")
				.append("|    jump")
				.append("|  sprint")
				.append("| hurdles")
				.append("|   throw")
				.append("|   vault")
				.append("|   throw")
				.append("|    race|")
				.append(IOUtils.LINE_SEPARATOR);
		appendLineSeparatorTo(stringBuilder, maxLengthOfName);
	}

	private void appendLineSeparatorTo(StringBuilder stringBuilder, int maxLengthOfName) {
		stringBuilder.append("+-----").append("+------+")
				.append(generateSequenceOfMinusesWithLength(maxLengthOfName))
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------")
				.append("+--------+")
				.append(IOUtils.LINE_SEPARATOR);
	}

	private String generateSequenceOfMinusesWithLength(int maxLengthOfName) {
		StringBuilder builder = new StringBuilder(maxLengthOfName);
		for (int i = 0; i < maxLengthOfName; i++) {
			builder.append('-');

		}
		return builder.toString();
	}

	private int findMaxLengthOfNameIn(List<Athlete> athletes) {
		int maxLength = "Name".length();
		for (Athlete athlete : athletes) {
			int nameLength = athlete.getName().length();
			if (nameLength > maxLength) maxLength = nameLength;
		}
		return maxLength;
	}

	private void buildOnePersonsDataForRepresentation(StringBuilder stringBuilder, Athlete athlete, Map<Athlete, String> places, int maxLengthOfName) {
		stringBuilder.append(
				String.format("|%-5s|%6s|%-" + maxLengthOfName + "s|%8s|%8s|%8s|%8s|%8s|%8s|%8s|%8s|%8s|%8s|",
						places.get(athlete),
						NUMERIC_DATA_REPRESENT_HELPER.representPoints(athlete.computePoints()),
						athlete.getName(),
						NUMERIC_DATA_REPRESENT_HELPER.representSeconds(athlete.get(ONE_HUNDRED_METER_SPRINT)),
						NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(LONG_JUMP)),
						NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(SHOT_PUT)),
						NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(HIGH_JUMP)),
						NUMERIC_DATA_REPRESENT_HELPER.representMinuteAndSeconds(athlete.get(FOUR_HUNDRED_METER_SPRINT)),
						NUMERIC_DATA_REPRESENT_HELPER.representSeconds(athlete.get(ONE_HUNDRED_TEN_METER_HURDLES)),
						NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(DISCUS_THROW)),
						NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(POLE_VAULT)),
						NUMERIC_DATA_REPRESENT_HELPER.representMeters(athlete.get(JAVELIN_THROW)),
						NUMERIC_DATA_REPRESENT_HELPER.representMinuteAndSeconds(athlete.get(THOUSAND_FIVE_HUNDRED_METER_SPRINT))
				)
		);
	}
}
