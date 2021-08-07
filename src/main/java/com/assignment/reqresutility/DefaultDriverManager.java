package com.weather.assignment.Weather;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DefaultDriverManager {
	static Logger logger = Logger.getLogger(DefaultDriverManager.class);

	private String runEnvironment = null;
	private String hubURL = null;
	private String version = null;
	private String browser = null;
	
	private boolean configLoaded = false;

	public RemoteWebDriver getDriver() throws Exception {

		if (!configLoaded)

			loadConfig();

		// create a Web Driver and return it to the client

		RemoteWebDriver driver = null;

		DesiredCapabilities capabilities = null;

		if (runEnvironment.equals("GRID")) {

			try {

				if (browser.equalsIgnoreCase("FIREFOX")) {

					capabilities = DesiredCapabilities.firefox();

					FirefoxProfile firefoxProfile = new FirefoxProfile();

					firefoxProfile.setPreference("xpinstall.signatures.required", false);

					// JavaScriptError.addExtension(firefoxProfile);

					capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

				} else if (browser.equalsIgnoreCase("INTERNET EXPLORER")) {

					capabilities = DesiredCapabilities.internetExplorer();

					capabilities.setCapability("ignoreZoomSetting", true);

					capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");

				} else if (browser.equalsIgnoreCase("CHROME")) {

					capabilities = DesiredCapabilities.chrome();

					ChromeOptions options = new ChromeOptions();

					options.addArguments("test-type");

					options.addArguments("start-maximized");

					options.addArguments("--no-sandbox");

					capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				}

				capabilities.setCapability("version", version);

				capabilities.setCapability("platform", Platform.WINDOWS);

				driver = new RemoteWebDriver(new URL(hubURL), capabilities);

				if (!browser.equalsIgnoreCase("chrome")) {

					driver.manage().window().maximize();

				}

			} catch (MalformedURLException e) {

				logger.info(e.getMessage());

				throw new RuntimeException(e.getMessage(), e);

			} catch (WebDriverException e) {

				logger.info(e.getMessage());

				throw new RuntimeException(e.getMessage(), e);

			}

		} else if (runEnvironment.equals("LOCAL")) {

			if (browser.equals(Browser.FIREFOX)) {

				driver = new FirefoxDriver();

			} else if (browser.equals(Browser.CHROME)) {

				driver = new ChromeDriver();

			} else if (browser.equals(Browser.IE)) {

				driver = new InternetExplorerDriver();

			} else {

				throw new Exception("Browser [" + browser + "] Not Supported");

			}

		}
		driver.manage().window().maximize();
		logger.info(browser + "driver launched");
		return driver;

	}

	public void loadConfig()

	{
		BasicConfigurator.configure();
		Map<String, String> systemProperties = ConfigReader.getAllProperties("system.properties");
		for (Entry<String, String> eachProperty : systemProperties.entrySet()) {
			System.setProperty(eachProperty.getKey(), eachProperty.getValue());
		}

		runEnvironment = ConfigReader.getProperty("config.properties", "RUN_ENV");

		hubURL = ConfigReader.getProperty("config.properties", "HUB_URL");

		version = ConfigReader.getProperty("config.properties", "VERSION");

		browser = ConfigReader.getProperty("config.properties", "BROWSER");
		
		

		configLoaded = true;

	}
	
}
