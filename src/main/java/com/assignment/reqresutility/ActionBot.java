package com.weather.assignment.Weather;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionBot 
{
	static Logger logger = Logger.getLogger(ActionBot.class);

	public static WebElement waitForElementToBeDisplayed(RemoteWebDriver driver, final WebElement webElement,int timeOutPeriod) throws Exception {
			String error = "error while locating element";

			try {
			WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
			webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));

			return webElement;



			} catch (NoSuchElementException ex) {
			throw new Exception(
			"\n NoSuchElementException in waitForElementToBeDisplayed Method While Finding " + error + " => \n"
			+ ex,
			ex);
			} catch (StaleElementReferenceException ex) {
			logger.info("StaleElementReferenceException in waitForElementToBeDisplayed Method While Finding " + error
			+ ". Again locating element...");
			try {
			return webElement;
			} catch (StaleElementReferenceException exx) {
			throw new StaleElementReferenceException(
			"\n StaleElementReferenceException in waitForElementToBeDisplayed Method While Finding " + error
			+ " => \n" + exx,
			exx);
			}
			} catch (NullPointerException ex) {
			throw new Exception("\n NullPointerException in waitForElementToBeDisplayed Method While Finding "
			+ error + " => \n" + ex, ex);
			} catch (ElementNotVisibleException e) {
			throw new Exception(
			"\n ElementNotVisibleException in waitForElementToBeDisplayed Method While Finding " + error
			+ " => \n" + e,
			e);
			} catch (Exception e) {
			throw new Exception(
			"\n Exception in waitForElementToBeDisplayed Method While Finding " + error + " => \n" + e, e);
			}
			}
	
	public static void enter_Text(RemoteWebDriver driver, WebElement element,String text) throws Exception {
		
		
		
		String error = element+"is not displayed";
		try {
			
		WebElement ele=waitForElementToBeDisplayed(driver, element, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: red; border: 4px solid green;");

		ele.clear();
		ele.sendKeys(text);
		} 
		catch (StaleElementReferenceException ex) 
		{
		logger.error("Stale Element Reference Exception has occurred... Locating element again... ");

		WebElement ele=waitForElementToBeDisplayed(driver, element, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: red; border: 4px solid green;");

		ele.clear();
		ele.sendKeys(text);
		}
		catch (Exception e) 
		{
		throw new Exception(
		"\n Exception in Click Method While performing click on " + " =>" + error + " \n" + e, e);
		}
	}
	
	public static void focusAndclick(RemoteWebDriver driver, WebElement webElement) throws Exception {
		
		String error = webElement+"Element is not clickable";
		try {
		
		// focus on element
		new Actions(driver).moveToElement(webElement).perform();
		// click on element
		webElement.click();
		} catch (StaleElementReferenceException ex) {
		logger.error("Stale Element Reference Exception has occurred... Locating element again... ");
		try {
		
		if (webElement != null) {
		// focus on element
		new Actions(driver).moveToElement(webElement).perform();
		// click on element
		webElement.click();
		} else {
		logger.error("Element is Null Hence Unable To Click ON ELEMENT : " + error);
		}
		} catch (Exception exx) {
		throw new Exception(
		"\n Exception in Click Method While performing click on " + error + " \n" + exx, exx);
		}
		} catch (Exception e) {
		try {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", webElement);
		} catch (StaleElementReferenceException ex) {
		logger.error("Stale Element Reference Exception has occurred... Locating element again... ");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", webElement);
		} catch (Exception ex) {
		logger.error("\n Exception in Click Method While performing click on " + error + " \n" + ex, ex);
		}
		}
		}
}
