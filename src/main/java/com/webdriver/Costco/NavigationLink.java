package com.webdriver.Costco;

import org.openqa.selenium.WebDriver;


public class NavigationLink {

	WebDriver driver=null;
	
	public NavigationLink(WebDriver driver) {
		this.driver=driver;		
	}

	public String GoToPage(String baseUrl,	String pageUrl) {					
		driver.get(baseUrl + pageUrl);		
		return driver.getTitle();
	}
	
}
