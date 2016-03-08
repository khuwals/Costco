package com.webdriver.Costco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.webdriver.Library.WebDriverExtensions;

public class PaymentPage {

	WebDriver driver= null;
	WebDriverExtensions driverExtn = null;
	
	public PaymentPage(WebDriver driver) {
		this.driver=driver;
		driverExtn = new WebDriverExtensions(driver);
	}

	public PaymentPage FillCardDetails(String cardType, String cardNumber,
			String nameOnCard, String expiryMonth, String expiryYear,
			String securityCode) {

		driverExtn.SelectVisibleValueFromDropDown(By.id("card_type"), cardType);
		driverExtn.SetText(By.id("card_number"), cardNumber);
		driverExtn.SetText(By.id("cardHolderName"), nameOnCard);
		driverExtn.SelectVisibleValueFromDropDown(By.id("card_expirationMonth"), expiryMonth);
		driverExtn.SelectVisibleValueFromDropDown(By.id("card_expirationYear"), expiryYear);
		driverExtn.SetText(By.id("card_cvNumber"), securityCode);
		
		return this;
	}
	
	public PaymentPage ClickOnCompleteOrder(){
		//driverExtn.ClickButton(By.partialLinkText("Complete Order"));
		driverExtn.ClickButton(By.xpath(("//button[contains(@class,'card_submit')]")));
		return this;
	}

	
}
