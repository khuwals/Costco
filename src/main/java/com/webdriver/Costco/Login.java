package com.webdriver.Costco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.webdriver.Library.WebDriverExtensions;

public class Login {

	WebDriver driver=null;
	WebDriverExtensions driverextn = null;


	public Login(WebDriver driver)
	{
		this.driver=driver;
		driverextn= new WebDriverExtensions(driver);
		
	}
	
	public Login FillLoginForm(String userName, String password){
		
		driverextn.WaitForElement(By.xpath("//div[@class='login_content']//input[@name='j_username']"), 30);		
		driverextn.SetText(By.xpath("//div[@class='login_content']//input[@name='j_username']"), userName);		
		driverextn.SetText(By.xpath("//div[@class='login_content']//input[@id='loginpassword']"), password);
		
		return this;
	}

	public Login ClickSubmitButton()
	{
		WebElement element = driver.findElement(By.xpath("//li[@class='mt20']/input"));		
		if(element !=null){
			element.click();
		}
		return this;
	}	

}
