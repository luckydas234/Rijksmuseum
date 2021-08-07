package com.weather.assignment.Weather;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NdtvHomePages {

	RemoteWebDriver driver;
	int timeOutPeriod;
	/* All WebElements are identified by @FindBy annotation */

	public NdtvHomePages(RemoteWebDriver driver) {

		this.driver = driver;

		// This initElements method will initialize all webelements

		PageFactory.initElements(driver, this);
		timeOutPeriod=Integer.parseInt(ConfigReader.getProperty("config.properties", "TIMEOUT"));

	}

	
	@FindBy(xpath= "//*[text()='No Thanks']")

	WebElement popupCancelButton;

	@FindBy(xpath= "//a[@id='h_sub_menu']")

	WebElement headerExpandSubMenu;

	@FindBy(xpath = "//a[contains(text(),'WEATHER')]")

	WebElement weatherSubMenu;
	
	public void clickOnPopupCancelButton() throws Exception{

	    ActionBot.waitForElementToBeDisplayed(driver, popupCancelButton, timeOutPeriod);
	    ActionBot.focusAndclick(driver, popupCancelButton);

	    }

    public void clickOnExpandSubMenu() throws Exception{

    ActionBot.waitForElementToBeDisplayed(driver, headerExpandSubMenu, timeOutPeriod);
    ActionBot.focusAndclick(driver, headerExpandSubMenu);

    }
    public void clickOnWeatherSubMenu() throws Exception{

        ActionBot.waitForElementToBeDisplayed(driver, weatherSubMenu, timeOutPeriod);
        ActionBot.focusAndclick(driver, weatherSubMenu);
}
}
