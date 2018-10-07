package com.ofss.frameworkUtilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;






public class GenericUtilities {
	
	private static final Logger LOGGER = Logger.getLogger(GenericUtilities.class.getName());
	/**
	 * Gets the current time stamp.
	 * 
	 * @return the current time stamp
	 */
	public static String getCurrentTimeStamp() {
		long currMilli = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
		String currentTimestamp = dateFormat.format(new Date(currMilli)); //+System.nanoTime(); --removed NanoTime append logic
		//LOGGER.info("currentTimestamp::" + currentTimestamp);

		return currentTimestamp;
	}
	  

}
