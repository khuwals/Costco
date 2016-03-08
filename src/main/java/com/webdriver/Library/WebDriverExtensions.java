package com.webdriver.Library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverExtensions {
	
	WebDriver driver=null;
	
	public WebDriverExtensions(WebDriver driver) {		
		this.driver = driver;		
	}
	
	
	public  void SetText(By by, String valueEntered) {		
		WebElement element = driver.findElement(by);
		if(element!=null && valueEntered!=null){
			element.sendKeys(valueEntered);
		}			
	}
	
	public  void ClickButton(By by){		
		WebElement element = driver.findElement(by);
		if(element!=null){
			element.click();
		}				
	}
	
	public  void SelectVisibleValueFromDropDown(By by, String optionToSelect) {

		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(optionToSelect);
	}
	
	public boolean  WaitForElement(By by, int waitTime){
		
		WebDriverWait wait = new WebDriverWait(driver,waitTime);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		return true;
	}

	public String GetPageTitle(){
		
		String title = driver.getTitle();
		if(title!=null){
			return title;
		}
		return "No Title";	
	}
	
	public  String GetText(By by) {		
		WebElement element = driver.findElement(by);
		
		if(element!=null){
			return element.getText();
		}
		return "";
	}
	
	public int ip(String s){		
		if(s!=null){
			return Integer.parseInt(s);
		}
		return 0;
		
	}
}


