package test;

import org.junit.Test;

import home.decathlon.AthletesLoader;
import home.decathlon.AthletesOutput;
import home.decathlon.DecathlonCalculator;
import home.decathlon.ParamsParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static home.decathlon.DecathlonCalculator.InputMethod;
import static home.decathlon.DecathlonCalculator.OutputMethod;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DecathlonCalculatorTest {
	DecathlonCalculator decathlonCalculator = new DecathlonCalculator();

	@Test
	public void badLengthOfParametersExamples() {
		ParamsParser zeroLengthParser = mock(ParamsParser.class);
		when(zeroLengthParser.getKeys()).thenReturn(Collections.<String>emptyList());
		ParamsParser oneElementParser = mock(ParamsParser.class);
		when(oneElementParser.getKeys()).thenReturn(Collections.singletonList(new String()));
		ParamsParser threeElementParser = mock(ParamsParser.class);
		List<String> threeElementList = Arrays.asList("", "", "");
		when(threeElementParser.getKeys()).thenReturn(threeElementList);
		assertTrue(decathlonCalculator.notEnoughOrTooMuchParameters(zeroLengthParser));
		assertTrue(decathlonCalculator.notEnoughOrTooMuchParameters(oneElementParser));
		assertTrue(decathlonCalculator.notEnoughOrTooMuchParameters(threeElementParser));
	}

	
	//	public void helpExample() {
	//		String usageHelp = "usage: <program> -<input-method> [input-parameters] -<output-method> [output-parameters]";
	//		assertTrue(new DecathlonCalculator().helpMessage().startsWith(usageHelp));
	//	}

	@Test
	public void supportedInputMethodCLIParamExample() {
		for (String command : new String[]{"-console", "-csv"}) {
			assertThat(InputMethod.getInstanceFor(command).createAthleteLoader(), is(instanceOf(AthletesLoader.class)));
		}
	}

	@Test(expected = RuntimeException.class)
	public void tryToUseSomeUnsupportedInputMethod() {
		assertThat(InputMethod.getInstanceFor("-pdf").createAthleteLoader(), is(instanceOf(AthletesLoader.class)));
	}


	@Test
	public void supportedOutputMethodCLIParamExample() {
		for (String command : new String[]{"-console", "-csv"}) {
			assertThat(OutputMethod.getInstanceFor(command).createAthleteSaver(), is(instanceOf(AthletesOutput.class)));
		}
	}

	@Test(expected = RuntimeException.class)
	public void tryToUseSomeUnsupportedOutputMethod() {
		assertThat(OutputMethod.getInstanceFor("-odt").createAthleteSaver(), is(instanceOf(AthletesOutput.class)));
	}
}