package com.weather.assignment.Weather;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class WeatherComparator {
	int iTempdifference = 0;
	static Logger logger = Logger.getLogger(WeatherComparator.class);

	public void compareCityTemprature(WeatherPOJO weather) throws Exception {
		
		try {
			logger.info("Start Testcase ==>compareCityTemprature");
			Assert.assertEquals(true, weather.getApiCity().equalsIgnoreCase(weather.getUiCity()));
			logger.info("City name validated successfully");
			this.iTempdifference = weather.getApiTemprature() - weather.getUiTemprature();
			if (this.iTempdifference < 0) {
				this.iTempdifference = this.iTempdifference * -1;
			}

			Assert.assertTrue(Integer
					.parseInt(ConfigReader.getProperty("config.properties", "TEMP_DIFF")) >= this.iTempdifference);
			logger.info("Temprature difference validated successfully");
			logger.info("Finish Testcase ==>compareCityTemprature");
		}

		catch (Exception e) {
			throw new Exception("Error occured in compareCityTemprature" + e.getMessage());
		}

	}

}
