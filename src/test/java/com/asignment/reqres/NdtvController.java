package com.weather.assignment.Weather;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NdtvController {
	static Logger logger = Logger.getLogger(NdtvController.class);
	private String appURL = null;
	private String sInputCity = null;

	public void launchApplication(RemoteWebDriver driver) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		appURL = ConfigReader.getProperty("UI.properties", "APPURL");
		driver.get(appURL);

	}

	public void tempratureSearchCityController(RemoteWebDriver driver, WeatherPOJO weather, String sTempinFarnhite)
			throws Exception {
		try {
			logger.info("Start Testcase ==>UI tempratureSearchCity");
			NdtvHomePages homepages = new NdtvHomePages(driver);
			NdtvWeatherPages weatherpages = new NdtvWeatherPages(driver);
			homepages.clickOnPopupCancelButton();
			homepages.clickOnExpandSubMenu();
			homepages.clickOnWeatherSubMenu();

			weatherpages.uncheckExistingCities();
			this.sInputCity = ConfigReader.getProperty("UI.properties", "City");
			weatherpages.searchRequiredCity(sInputCity);
			weatherpages.checkRequiredCity(sInputCity);

			String sTempinCelcius = weatherpages.getTempInCentigrade();
			logger.info("Temprature in Celcius" + sTempinCelcius);
			Assert.assertNotNull(sTempinCelcius);

			weather.setUiTemprature(Integer.parseInt(sTempinCelcius.substring(0, sTempinCelcius.length() - 1)));
			sTempinFarnhite = weatherpages.getTempInFarenhite();
			logger.info("Temprature in Farenhite" + sTempinFarnhite);
			Assert.assertNotNull(sTempinFarnhite);
			String sCityUI = weatherpages.getCityNameOnMap();
			logger.info("City Name on Map " + sCityUI);
			Assert.assertEquals(sInputCity, sCityUI, "City name displayed properly");
			weather.setUiCity(sCityUI);
			logger.info("Finish Testcase ==>UI tempratureSearchCity");
		} catch (Exception e) {
			throw new Exception("Error occured in tempratureSearchCityController " + e.getMessage());
		}

	}

}
