package com.weather.assignment.Weather;

import org.testng.Assert;

public class WeatherComparator 
{
	int iTempdifference=0;
	
	public void compareCityTempratue(WeatherPOJO weather) throws Exception
	{
		try{
			Assert.assertEquals(true, weather.getApiCity().equalsIgnoreCase(weather.getUiCity()));
			this.iTempdifference=weather.getApiTemprature()-weather.getUiTemprature();
			if(this.iTempdifference<0)
			{
				this.iTempdifference=this.iTempdifference*-1;
			}
				
			
			Assert.assertTrue(Integer.parseInt(ConfigReader.getProperty("config.properties", "TEMP_DIFF"))>=this.iTempdifference);
		}
			
		catch(Exception e){
			throw new Exception("Error occured in compareCityTempratue"+e.getMessage());
		}
		
		
	}

}
