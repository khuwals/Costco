package com.webdriver.Costco;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CategoryLinksDetails {
	
	public WebDriver driverMX;
	
	
	public static void main(String []args) throws Exception{
		
		CategoryLinksDetails classObject = new CategoryLinksDetails();
		
		classObject.goToApplication();
		classObject.getNavigationLinkDetails();
		
	}
	
	public CategoryLinksDetails(){
		
		driverMX = new FirefoxDriver();
	}
	
	
	public void getNavigationLinkDetails(){
		
		List<WebElement> elements = driverMX.findElements(By.xpath("//li[contains(@class,'parent')]/a[@id='catlink-cos_12']/../descendant::a/font "));
		
		
		for (int i = 0; i < elements.size(); i++) {
			WebElement singleElements = elements.get(i);
			
			String contentValue = singleElements.findElement(By.xpath("//a")).getText();
			int wordLength =contentValue.length();
			if(wordLength>0){
				System.out.println(contentValue);
			}
			else{
				System.out.println(singleElements.findElement(By.xpath("//a/font")).getText());
			}
				
				
		}
		
			
	}
	
	
	
	public  void  goToApplication() throws Exception{

		driverMX.manage().window().maximize();
		driverMX.get("http://qa2.costco.com.mx/");
	}

}
