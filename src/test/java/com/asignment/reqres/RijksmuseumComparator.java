package com.asignment.reqres;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.assignment.reqresutility.ConfigReader;

public class RijksmuseumComparator {

	static Logger logger = Logger.getLogger(RijksmuseumComparator.class);

	public void compareRijkAttributes(RijksmuseumPOJO rijkpojo) throws Exception {

		try {
			logger.info("Start Testcase ==>Verify Title");
			Assert.assertEquals(true,
					rijkpojo.getsOnDisplay().equalsIgnoreCase(ConfigReader.getProperty("API.properties", "ONDISPLAY")));
			
			logger.info("Title name validated successfully");

			Assert.assertEquals(true, rijkpojo.getName().equalsIgnoreCase(ConfigReader.getProperty("API.properties", "NAME")));
			logger.info("Facets validated successfully");

		}

		catch (Exception e) {
			throw new Exception("Error occured in compareRijkAttributes" + e.getMessage());
		}

	}

}
