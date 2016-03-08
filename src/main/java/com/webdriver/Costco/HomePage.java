package com.webdriver.Costco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webdriver.Library.*;


public class HomePage {

	WebDriver driver=null;
	WebDriverExtensions driverextn = null;
	
	public  HomePage(WebDriver driver){
		this.driver=driver;
		driverextn = new WebDriverExtensions(driver);
	}
	public void ClickOnLinkForLoginOrRegistration()		
	{		
		driverextn.ClickButton(By.partialLinkText("Login or Register"));		
	}
	
	public void Search(String productItemID)
	{
		/*WebDriverWait wait = new WebDriverWait(driver,30);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchBox")));
*/
		driverextn.SetText(By.id("searchBox"),productItemID);
		driverextn.ClickButton(By.id("searchButton"));
	}
	
	public void GoToBasket(){
		driverextn.ClickButton(By.xpath("//div[@class = 'myBasket_name']/a"));
	}
	
	public void GoToOrderHistory(){
		driverextn.ClickButton(By.xpath(("//a[contains(@href,'orderhistorylist')]")));
	}
	
	public void GoToMyAccount(){
		driverextn.ClickButton(By.xpath("//a[@id='myAccountLinkId']"));
	}
}
