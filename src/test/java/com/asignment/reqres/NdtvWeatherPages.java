package com.weather.assignment.Weather;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class NdtvWeatherPages {

	RemoteWebDriver driver;
	int timeOutPeriod;
	/* All WebElements are identified by @FindBy annotation */

	public NdtvWeatherPages(RemoteWebDriver driver) {

		this.driver = driver;

		// This initElements method will initialize all webelements

		PageFactory.initElements(driver, this);
		timeOutPeriod = Integer.parseInt(ConfigReader.getProperty("config.properties", "TIMEOUT"));

	}

	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement searchBoxTextBox;

	@FindBy(xpath = "//div[@class='message']/label/input")
	List<WebElement> cityCheckBox;

	@FindBy(xpath = "//span[@class='tempRedText']")
	WebElement tempCentigradeLabel;

	@FindBy(xpath = "//span[@class='tempWhiteText']")
	WebElement tempFarenhiteLabel;

	@FindBy(xpath = "//div[@class='cityText']")
	WebElement cityNameOnMapLabel;

	public void uncheckExistingCities() throws Exception {

		ActionBot.waitForElementToBeDisplayed(driver, cityCheckBox.get(0), timeOutPeriod);
		for (WebElement wb : cityCheckBox) {
			if (wb.isSelected()) {
				ActionBot.focusAndclick(driver, wb);
			}
		}

	}

	public void searchRequiredCity(String sInput) throws Exception {
		ActionBot.enter_Text(driver, searchBoxTextBox, sInput);

	}

	public void checkRequiredCity(String sInput) throws Exception {
		WebElement wb = driver.findElement(By.xpath("//*[@id='messages']//label[@for='" + sInput + "']/input"));

		ActionBot.focusAndclick(driver, wb);
	}

	public String getTempInCentigrade() throws Exception {

		ActionBot.waitForElementToBeDisplayed(driver, tempCentigradeLabel, timeOutPeriod);
		return tempCentigradeLabel.getText();

	}

	public String getTempInFarenhite() throws Exception {

		ActionBot.waitForElementToBeDisplayed(driver, tempFarenhiteLabel, timeOutPeriod);
		return tempFarenhiteLabel.getText();

	}

	public String getCityNameOnMap() throws Exception {

		ActionBot.waitForElementToBeDisplayed(driver, cityNameOnMapLabel, timeOutPeriod);
		return cityNameOnMapLabel.getText();

	}

	// div[@class="message"]/label[@for='Bengaluru']/input

}
