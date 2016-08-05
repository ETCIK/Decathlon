package test;

import org.junit.Test;

import home.decathlon.ConsoleLoader;
import home.decathlon.SkipLoadingAthleteException;
import home.decathlon.StopLoadingAthletesException;

import java.io.*;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleLoaderTest {
	@Test
	public void consoleLoadingExample() throws IOException, StopLoadingAthletesException, SkipLoadingAthleteException {
		ConsoleLoader loader = new ConsoleLoader();
		loader.in = new ByteArrayInputStream(readFileToString(new File(getClass().getResource("input.console").getFile()), "UTF-8").getBytes("UTF-8"));
		loader.err = new PrintStream(new ByteArrayOutputStream());
		loader.out = loader.err;
		assertThat(loader.load().size(), is(4));
	}
}
