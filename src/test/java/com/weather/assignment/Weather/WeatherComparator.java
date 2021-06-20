package com.weather.assignment.Weather;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class WeatherComparator {
	int iTempdifference = 0;
	static Logger logger = Logger.getLogger(WeatherComparator.class);

	public void compareCityTempratue(WeatherPOJO weather) throws Exception {
		//System.out.println(weather.getApiCity() + "--" + weather.getUiCity());
		try {
			Assert.assertEquals(true, weather.getApiCity().equalsIgnoreCase(weather.getUiCity()));
			logger.info("City name validated successfully");
			this.iTempdifference = weather.getApiTemprature() - weather.getUiTemprature();
			if (this.iTempdifference < 0) {
				this.iTempdifference = this.iTempdifference * -1;
			}

			Assert.assertTrue(Integer
					.parseInt(ConfigReader.getProperty("config.properties", "TEMP_DIFF")) >= this.iTempdifference);
			logger.info("Temprature difference validated successfully");
		}

		catch (Exception e) {
			throw new Exception("Error occured in compareCityTempratue" + e.getMessage());
		}

	}

}
