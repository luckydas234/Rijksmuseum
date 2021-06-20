package com.weather.assignment.Weather;

import org.apache.log4j.Logger;
import org.testng.Assert;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class OpenWeatherController {
	static Logger logger = Logger.getLogger(OpenWeatherController.class);
	private String BaseURI = null;
	private String CityName = null;
	private String APIKey = null;
	private int statuscode = 0;

	public void tempratureSearchByCityNameController(String sTempinCelcius, WeatherPOJO weather) throws Exception {
		try {
			this.CityName = ConfigReader.getProperty("API.properties", "CITY");
			this.APIKey = ConfigReader.getProperty("API.properties", "APIKEY");
			this.statuscode = Integer.parseInt(ConfigReader.getProperty("API.properties", "STATUSCODE"));
			this.BaseURI = ConfigReader.getProperty("API.properties", "BASEURI");
			BaseURI = BaseURI.replace("{CHANGEING}", this.CityName) + this.APIKey;
			// System.out.println(BaseURI);
			logger.info("Base URI is" + BaseURI);

			RestAssured.baseURI = this.BaseURI;

			Response response = RestAssured.get(BaseURI);

			int statusCode = response.getStatusCode();
			Assert.assertEquals(true, statusCode == this.statuscode);

			logger.info("Response Body is =>" + response.getBody().asString());

			logger.info("Response City Name is =>" + response.getBody().jsonPath().get("name"));
			logger.info("Response Temprature is =>"
					+ response.getBody().jsonPath().get("main.temp").toString().substring(0, 2));
			weather.setApiTemprature(
					Integer.parseInt(response.getBody().jsonPath().get("main.temp").toString().substring(0, 2)));
			weather.setApiCity(response.getBody().jsonPath().get("name").toString());

		} catch (Exception e) {
			throw new Exception("Error occured in tempratureSearchByCityNameController" + e.getMessage());
		}

	}
}
