package com.assignment.reqresutility;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DefaultDriverManager {
	static Logger logger = Logger.getLogger(DefaultDriverManager.class);

	private static boolean configLoaded = false;

	public static void loadConfig()

	{
		BasicConfigurator.configure();
		Map<String, String> systemProperties = ConfigReader.getAllProperties("system.properties");
		for (Entry<String, String> eachProperty : systemProperties.entrySet()) {
			System.setProperty(eachProperty.getKey(), eachProperty.getValue());
		}
		configLoaded = true;

	}

}
