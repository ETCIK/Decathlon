package test;

import home.decathlon.Athlete;
import home.decathlon.AthleteDataLoaderHelper;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AthleteDataLoaderHelperTest {

	private static final AthleteDataLoaderHelper DATA_LOADER_HELPER = AthleteDataLoaderHelper.getInstance();
	private static final Athlete.Builder BUILDER = new Athlete.Builder();

	@Test
	public void readingDefaultFormats() {
		assertThat(AthleteDataLoaderHelper.MIN_SEC_MILLI_FORMAT.toPattern(), is("mm:ss.SSS"));
		assertThat(AthleteDataLoaderHelper.SEC_MILLI_FORMAT.toPattern(), is("ss.SSS"));
	}

	@Test
	public void athleteDataLoaderIsSingleton() throws Exception {
		assertTrue(AthleteDataLoaderHelper.getInstance() == AthleteDataLoaderHelper.getInstance());
	}

	@Test
	public void exampleOfLoadThousandFiveHundredMeterRaceTime() throws Exception {
		DATA_LOADER_HELPER.loadThousandFiveHundredMeterRaceTimeUsing(BUILDER, "1:23.4");
		DATA_LOADER_HELPER.loadThousandFiveHundredMeterRaceTimeUsing(BUILDER, "23.4");
		DATA_LOADER_HELPER.loadThousandFiveHundredMeterRaceTimeUsing(BUILDER, "234");
	}

	@Test
	public void exampleOfLoadOneHundredTenMeterHurdlesTime() throws Exception {
		DATA_LOADER_HELPER.loadOneHundredTenMeterHurdlesTimeUsing(BUILDER, "1:23.4");
		DATA_LOADER_HELPER.loadOneHundredTenMeterHurdlesTimeUsing(BUILDER, "23.4");
		DATA_LOADER_HELPER.loadOneHundredTenMeterHurdlesTimeUsing(BUILDER, "234");
	}

	@Test
	public void exampleOfLoadOneHundredMeterSprintTime() throws Exception {
		DATA_LOADER_HELPER.loadOneHundredMeterSprintTimeUsing(BUILDER, "1:23.4");
		DATA_LOADER_HELPER.loadOneHundredMeterSprintTimeUsing(BUILDER, "23.4");
		DATA_LOADER_HELPER.loadOneHundredMeterSprintTimeUsing(BUILDER, "234");
	}

	@Test
	public void exampleOfLoadFourHundredMeterSprintTime() throws Exception {
		DATA_LOADER_HELPER.loadFourHundredMeterSprintTimeUsing(BUILDER, "1:23.4");
		DATA_LOADER_HELPER.loadFourHundredMeterSprintTimeUsing(BUILDER, "23.4");
		DATA_LOADER_HELPER.loadFourHundredMeterSprintTimeUsing(BUILDER, "234");
	}

	@Test
	public void exampleOfLoadShotPutLength() throws Exception {
		DATA_LOADER_HELPER.loadShotPutLengthUsing(BUILDER, "1.4");
		DATA_LOADER_HELPER.loadShotPutLengthUsing(BUILDER, "1");
	}

	@Test
	public void exampleOfLoadPoleVaultHeight() throws Exception {
		DATA_LOADER_HELPER.loadPoleVaultHeightUsing(BUILDER, "1.4");
		DATA_LOADER_HELPER.loadPoleVaultHeightUsing(BUILDER, "1");
	}

	@Test
	public void exampleOfLoadLongJumpLength() throws Exception {
		DATA_LOADER_HELPER.loadLongJumpLengthUsing(BUILDER, "1.4");
		DATA_LOADER_HELPER.loadLongJumpLengthUsing(BUILDER, "1");
	}

	@Test
	public void exampleOfLoadJavelinThrowLength() throws Exception {
		DATA_LOADER_HELPER.loadJavelinThrowLengthUsing(BUILDER, "1.4");
		DATA_LOADER_HELPER.loadJavelinThrowLengthUsing(BUILDER, "1");
	}

	@Test
	public void exampleOfLoadHighJumpHeight() throws Exception {
		DATA_LOADER_HELPER.loadHighJumpHeightUsing(BUILDER, "1.4");
		DATA_LOADER_HELPER.loadHighJumpHeightUsing(BUILDER, "1");
	}

	@Test
	public void exampleOfLoadDiscusThrowLength() throws Exception {
		DATA_LOADER_HELPER.loadDiscusThrowLengthUsing(BUILDER, "1.4");
		DATA_LOADER_HELPER.loadDiscusThrowLengthUsing(BUILDER, "1");
	}

	@Test
	public void exampleOfLoadName() throws Exception {
		DATA_LOADER_HELPER.loadNameUsing(BUILDER, "\"Suur Karu\"");
		DATA_LOADER_HELPER.loadNameUsing(BUILDER, "\"Langa Malia\"");
		DATA_LOADER_HELPER.loadNameUsing(BUILDER, "\"Martin Lobeck\"");
	}
}
