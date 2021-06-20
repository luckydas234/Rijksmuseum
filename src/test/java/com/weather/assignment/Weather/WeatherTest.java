package com.weather.assignment.Weather;

import org.testng.annotations.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Biswa
 *
 */
public class WeatherTest {
	static RemoteWebDriver driver;
	static String sTempinCelcius;
	static String sTempinFarnhite;
	static WeatherPOJO weather;

	@BeforeTest
	public void setup() throws Exception {
		driver = new DefaultDriverManager().getDriver();
		weather = new WeatherPOJO();
		new NdtvController().launchApplication(driver);
	}

	@Test
	public void tempCompareTest() throws Exception {

		new NdtvController().tempratureSearchCityController(driver, weather, sTempinFarnhite);

		new OpenWeatherController().tempratureSearchByCityNameController(sTempinCelcius, weather);

		new WeatherComparator().compareCityTempratue(weather);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
