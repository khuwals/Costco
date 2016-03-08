package com.webdriver.Costco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.webdriver.Library.WebDriverExtensions;

public class ShoppingBasket {
	
	WebDriver driver=null;
	WebDriverExtensions driverExtn = null;

	public ShoppingBasket(WebDriver driver) {
		this.driver = driver;
		driverExtn = new WebDriverExtensions(driver);
	}
	
	public void ClickToProceedToCheckout(){
		
		driverExtn.ClickButton(By.name("checkout"));
		
	}

}
