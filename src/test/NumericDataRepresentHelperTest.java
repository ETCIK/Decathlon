package test;

import org.junit.Test;

import static home.decathlon.NumericDataRepresentHelper.getInstance;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NumericDataRepresentHelperTest {
	@Test
	public void instanceReturnsAlwaysTheSameInstance() {
		assertTrue(getInstance() == getInstance());
	}

	@Test
	public void minutesSecondsMillisecondsExample() {
		assertThat(getInstance().representMinuteAndSeconds(60.0 + 4.72), equalTo("1:04.72"));
		assertThat(getInstance().representMinuteAndSeconds(4.72), equalTo("0:04.72"));
	}

	@Test
	public void metersRepresentationExample() {
		assertThat(getInstance().representMeters(4.72), equalTo("4.72"));
	}

	@Test
	public void pointsRepresentationExample() {
		assertThat(getInstance().representPoints(4.70), equalTo("4.7"));
	}
}
