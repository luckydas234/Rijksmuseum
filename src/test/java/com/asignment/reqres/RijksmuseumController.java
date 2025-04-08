package com.asignment.reqres;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.assignment.reqresutility.ConfigReader;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class RijksmuseumController {
	static Logger logger = Logger.getLogger(RijksmuseumController.class);
	private String BaseURI = null;
	private String APIKey = null;
	private int statuscode = 0;

	public void rijksmuseumController(RijksmuseumPOJO Rijsmuseumpojo) throws Exception {
		try {
			logger.info("Start Testcase ==>GET RIJSKSMUSEUM");
			this.APIKey = ConfigReader.getProperty("API.properties", "APIKEY");
			this.statuscode = Integer.parseInt(ConfigReader.getProperty("API.properties", "STATUSCODE"));
			this.BaseURI = ConfigReader.getProperty("API.properties", "BASEURI");

			BaseURI = BaseURI.replace("{CHANGEING}", this.APIKey);
			logger.info("Base URI is" + BaseURI);

			RestAssured.baseURI = this.BaseURI;

			Response response = RestAssured.get(BaseURI);

			int statusCode = response.getStatusCode();
			Assert.assertEquals(true, statusCode == this.statuscode);

			logger.info("Response Body is =>" + response.getBody().asString());
			Rijsmuseumpojo.setsOnDisplay("" + response.getBody().jsonPath().get("countFacets.hasimage"));
			Rijsmuseumpojo.setName(response.getBody().jsonPath().get("facets[0].name"));
			logger.info("Finish Testcase ==>GET RIJSKSMUSEUM");
		} catch (Exception e) {
			throw new Exception("Error occured in artObjects" + e.getMessage());
		}

	}
}
