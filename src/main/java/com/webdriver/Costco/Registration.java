package com.webdriver.Costco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webdriver.Library.WebDriverExtensions;



public class Registration {

	WebDriver driver=null;
	WebDriverExtensions  driverExtn =null;
	
	public Registration(WebDriver driver){
		
		this.driver = driver;
		driverExtn = new WebDriverExtensions(driver);
	}
	
	public  Registration fillRegForm(String fName,String lName,  String emailId, String confirmEmail, String passwd,  String confirmPasswd ) throws InterruptedException {
		
		driverExtn.SetText(By.id("firstname"), fName);
		driverExtn.SetText(By.id("lastname"), lName);
		driverExtn.SetText(By.xpath("//input[@name='email']"),emailId);
		driverExtn.SetText(By.xpath("//input[@name='confirmEmail']"),confirmEmail);
		driverExtn.SetText(By.xpath("//input[@id='regpassword']"),passwd);
		driverExtn.SetText(By.xpath("//input[@id='confirmPassword']"),confirmPasswd);
		
		return this;
		
	}
	
	public boolean submitForm(){		
		
		WebElement element  = driver.findElement(By.xpath("//input[@id='registerButton']"));
		
		if(element !=null){
			element.click();
			return true;
		}		
		return false;
	}
	
	
}
