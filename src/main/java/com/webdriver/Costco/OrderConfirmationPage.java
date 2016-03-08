package com.webdriver.Costco;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webdriver.Library.WebDriverExtensions;

public class OrderConfirmationPage {

	WebDriver driver=null;
	WebDriverExtensions driverExtn=null;
	
	public OrderConfirmationPage(WebDriver driver) {		
		this.driver=driver;
		driverExtn = new WebDriverExtensions(driver);		
	}
	
	
	public String GetOrderNumber(){
		
		List<WebElement> element = driver.findElements(By.xpath("//div[contains(@class,'checkout_standard_container')]//p/span"));		
		
		if(element.size()!=0){
			return element.get(1).getText();
		}
		
		return "";
		
		
	}
	
	public void ClickToGoToHomePage(){		
		driverExtn.ClickButton(By.partialLinkText("Return to Home Page"));
	}
	
	
	
}
