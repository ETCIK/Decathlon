package test;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;

import home.decathlon.Athlete;
import home.decathlon.DecathlonEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public abstract class AthleteOutputTest {
	List<Athlete> setUpMockedAthletes() {
		List<Athlete> athletes = new ArrayList<Athlete>();
		Athlete athlete0 = mockedSamuelSolnson();
		Athlete athlete1 = mockedJoseMananaPerez();
		when(athlete0.compareTo(athlete1)).thenCallRealMethod();
		when(athlete1.compareTo(athlete0)).thenCallRealMethod();
		when(athlete1.compareTo(athlete1)).thenCallRealMethod();
		when(athlete0.compareTo(athlete0)).thenCallRealMethod();
		athletes.add(athlete0);
		athletes.add(athlete1);

		return athletes;
	}

	Athlete mockedSamuelSolnson() {
		Athlete athlete = mock(Athlete.class);
		when(athlete.getName()).thenReturn("Samuel Solnson");
		when(athlete.get(DecathlonEvent.ONE_HUNDRED_METER_SPRINT)).thenReturn(13.43);
		when(athlete.get(DecathlonEvent.LONG_JUMP)).thenReturn(4.35);
		when(athlete.get(DecathlonEvent.SHOT_PUT)).thenReturn(8.64);
		when(athlete.get(DecathlonEvent.HIGH_JUMP)).thenReturn(1.50);
		when(athlete.get(DecathlonEvent.FOUR_HUNDRED_METER_SPRINT)).thenReturn(66.06);
		when(athlete.get(DecathlonEvent.ONE_HUNDRED_TEN_METER_HURDLES)).thenReturn(19.05);
		when(athlete.get(DecathlonEvent.DISCUS_THROW)).thenReturn(24.89);
		when(athlete.get(DecathlonEvent.POLE_VAULT)).thenReturn(2.20);
		when(athlete.get(DecathlonEvent.JAVELIN_THROW)).thenReturn(33.48);
		when(athlete.get(DecathlonEvent.THOUSAND_FIVE_HUNDRED_METER_SPRINT)).thenReturn(411.01);
		when(athlete.computePoints()).thenReturn(100);//NB! not real points
		return athlete;
	}

	Athlete mockedJoseMananaPerez() {
		Athlete athlete = mock(Athlete.class);
		when(athlete.getName()).thenReturn("Josco Macana Perez");
		when(athlete.get(DecathlonEvent.ONE_HUNDRED_METER_SPRINT)).thenReturn(13.75);
		when(athlete.get(DecathlonEvent.LONG_JUMP)).thenReturn(4.84);
		when(athlete.get(DecathlonEvent.SHOT_PUT)).thenReturn(10.12);
		when(athlete.get(DecathlonEvent.HIGH_JUMP)).thenReturn(1.50);
		when(athlete.get(DecathlonEvent.FOUR_HUNDRED_METER_SPRINT)).thenReturn(68.44);
		when(athlete.get(DecathlonEvent.ONE_HUNDRED_TEN_METER_HURDLES)).thenReturn(19.18);
		when(athlete.get(DecathlonEvent.DISCUS_THROW)).thenReturn(30.85);
		when(athlete.get(DecathlonEvent.POLE_VAULT)).thenReturn(2.80);
		when(athlete.get(DecathlonEvent.JAVELIN_THROW)).thenReturn(33.88);
		when(athlete.get(DecathlonEvent.THOUSAND_FIVE_HUNDRED_METER_SPRINT)).thenReturn(382.75);
		when(athlete.computePoints()).thenReturn(200);//NB! not real points
		return athlete;
	}

	abstract String someBasicOutputString();

	File tempFile() throws IOException {
		File temp = File.createTempFile("temp", ".tmp");
		temp.deleteOnExit();
		return temp;
	}

	String loadFile(@SuppressWarnings("rawtypes") Class clazz, String fileName) throws IOException {
		return FileUtils.readFileToString(new File(clazz.getResource(fileName).getFile()), "UTF-8");
	}
}
