package home.decathlon;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.io.FileUtils.readLines;

/**
 * Decathlon calculator provides functionality for
 * calculating points of athletes and output them to different sources etc.
 */
public class DecathlonCalculator {
	AthletesLoader athleteLoader;
	AthletesOutput athleteSaver;

	public static void main(String[] args) {
		new DecathlonCalculator().handleRequest(args);
	}

	void handleRequest(String[] parameters) {
		ParamsParser paramsParser = new ParamsParser(parameters);
		if (notEnoughOrTooMuchParameters(paramsParser)) {
			System.out.println(helpMessage());
			return;
		}

		System.out.println("Params:" + Arrays.asList(parameters));
		int paramCounter = 0;
		List<Athlete> athletes = null;
		try {
			InputMethod inputMethod = InputMethod.getInstanceFor(paramsParser.getKeys().get(paramCounter));
			athleteLoader = inputMethod.createAthleteLoader();
			List<String> param = paramsParser.getValues().get(paramCounter++);
			athletes = athleteLoader.load((Object[]) param.toArray(new String[param.size()]));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}


		try {
			OutputMethod outputMethod = OutputMethod.getInstanceFor(paramsParser.getKeys().get(paramCounter));
			athleteSaver = outputMethod.createAthleteSaver();
			List<String> param = paramsParser.getValues().get(paramCounter);
			athleteSaver.output(athletes, (Object[]) param.toArray(new String[param.size()]));
			
			ConsoleOutputSimple consoleOutputSimple = new ConsoleOutputSimple();			
			consoleOutputSimple.output(athletes, (Object[]) param.toArray(new String[param.size()]));			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public boolean notEnoughOrTooMuchParameters(ParamsParser parameters) {
		System.out.println("param(KEY)Counter: " + parameters.getKeys().size());
		return parameters.getKeys().size() != 2;
	}

	public String helpMessage() {
		final String newLine = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder(8000);
		try {
			for (String line : readLines(new File(getClass().getResource("help.txt").getFile()), "UTF-8")) {
				builder.append(line).append(newLine);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return builder.toString();
	}

	public static enum InputMethod {
		CONSOLE {
			public AthletesLoader createAthleteLoader() {
				return new ConsoleLoader();
			}
		},
		CSV {
			public AthletesLoader createAthleteLoader() {
				return new CSVLoader();
			}
		};

		public abstract AthletesLoader createAthleteLoader();

		public static InputMethod getInstanceFor(String inputMethodString) {
			inputMethodString = inputMethodString.trim().toLowerCase();
			if (inputMethodString.equals("-console"))
				return CONSOLE;
			else if (inputMethodString.equals("-csv"))
				return CSV;
			else
				throw new UnsupportedOperationException();
		}
	}

	public static enum OutputMethod {
		CONSOLE {
			public ConsoleOutput createAthleteSaver() {
				return new ConsoleOutput();
			}
		},
		CSV {
			public AthletesOutput createAthleteSaver() {
				return new CSVOutput();
			}
		};

		public abstract AthletesOutput createAthleteSaver();

		public static OutputMethod getInstanceFor(String outputMethodString) {
			outputMethodString = outputMethodString.trim().toLowerCase();
			if (outputMethodString.equals("-console"))
				return CONSOLE;
			else if (outputMethodString.equals("-csv"))
				return CSV;
			else
				throw new UnsupportedOperationException();
		}
	}
}
