package com.webdriver.Costco;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webdriver.Library.WebDriverExtensions;

public class ProductDetailsPage {

	WebDriver driver=null;
	WebDriverExtensions driverExtn = null;

	public ProductDetailsPage(WebDriver driver) {
		this.driver=driver;
		driverExtn= new WebDriverExtensions(driver);
		
	}

	public Product RetrieveProductDetails() {
		
		Product product = new Product();	
		
		String []productId = driverExtn.GetText(By.xpath("//div[contains(@class,'productCode')]")).split("#");		//product.setProductId(Integer.parseInt(driver.findElement(By.xpath("//div[contains(@class,'productCode')]")).getText()));	
		product.setProductId(driverExtn.ip(productId[1].trim()));					
		product.setpName(driverExtn.GetText(By.xpath("//div[@class='productDetail_name_and_description mt30']/h1")));
		product.setpPrice(driverExtn.GetText(
				By.xpath("//div[@class='productDetail_name_and_description mt30']//span[contains(@class,'txtlrg')]")));
		product.setpQty(driverExtn.ip(driver.findElement(By.xpath("//input[@name ='uncheckedquantity']")).getAttribute("value")));
		
		return product;
	}
	
	

	public ProductDetailsPage ClickaddToCart() {
		WebElement element = driver.findElement(By.xpath("//input[contains(@class, 'btnlrgr') and contains(@class,'btn')]"));		
		if(element !=null){
			element.click();
		}
		return this;		
	}	
	
	public String GetPageTitle(){
		
		return driverExtn.GetPageTitle();		
		
	}
}


