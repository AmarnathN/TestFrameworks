package com.ofss.Utilities.frameworkUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import net.thucydides.core.steps.StepEventBus;

public class GenericUtilities {
	/**
	 * Gets the current time stamp.
	 * 
	 * @return the current time stamp
	 */
	public String getCurrentTimeStamp() {
		long currMilli = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
		String currentTimestamp = dateFormat.format(new Date(currMilli)); //+System.nanoTime(); --removed NanoTime append logic
		//LOGGER.info("currentTimestamp::" + currentTimestamp);

		return currentTimestamp;
	}
	
	@SuppressWarnings("deprecation")
	public static void customAssert(String actual, String Expected) {
		if(!ExecutionSettings.getInstance().isAbortForMajorCheckpointFailureOn()) {
    		StepEventBus.getEventBus().enableSoftAsserts();
    		System.out.println("Abbbort Execution " + ExecutionSettings.getInstance().isAbortForMajorCheckpointFailureOn());
    	}
		System.out.println();
		Assert.assertTrue(actual.equals(Expected));
	}
}
