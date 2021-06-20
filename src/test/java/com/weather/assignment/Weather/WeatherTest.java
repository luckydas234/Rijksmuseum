package com.weather.assignment.Weather;

import org.testng.annotations.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WeatherTest {
	static RemoteWebDriver driver;
	static String sTempinCelcius;
	static String sTempinFarnhite;
	static WeatherPOJO weather;

	@BeforeTest
	public void setup() throws Exception {}
	public static void main(String[] args) throws Exception {
		
	

		driver = new DefaultDriverManager().getDriver();
		weather = new WeatherPOJO();
		new NdtvController().launchApplication(driver);
		new NdtvController().tempratureSearchCityController(driver, weather, sTempinFarnhite);
		new OpenWeatherController().tempratureSearchByCityNameController(sTempinCelcius, weather);
	    new WeatherComparator().compareCityTempratue(weather); 
	}

	/*
	 * @Test(priority=1) public void ndtvTest() throws Exception { this.weather =
	 * new WeatherPOJO(); new
	 * NdtvController().tempratureSearchCityController(driver, weather,
	 * sTempinFarnhite); }
	 * 
	 * @Test(priority=2) public void openWeathertest() throws Exception {
	 * this.weather = new WeatherPOJO(); new
	 * OpenWeatherController().tempratureSearchByCityNameController(sTempinCelcius,
	 * weather);
	 * 
	 * }
	 * 
	 * @Test(priority=3) public void compareWeathertest() throws Exception {
	 * this.weather = new WeatherPOJO(); new
	 * WeatherComparator().compareCityTempratue(weather); }
	 * 
	 * @AfterTest public void teardown() { driver.quit(); }
	 */

}
