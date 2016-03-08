package com.webdriver.Costco;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webdriver.Library.*;

public class Checkout {

	WebDriver driver=null;

	public Checkout(WebDriver driver) {
		this.driver=driver;
	}

	public Checkout FillNewDeliveryAddress(String addressName, String personTitle, String firstName, 
			String lastName, String contactNumber, String companyName, String houseDetail, String postalCode, String country, 
			String streetName, String townCity, String county) throws InterruptedException {

		WebDriverExtensions bl = new WebDriverExtensions(driver);
		/*WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkoutAddressBookAdd_left_enterManually']")));	*/	
		
		Thread.sleep(5000);
		bl.ClickButton(By.xpath("//p[@id='checkoutAddressBookAdd_left_enterManually']"));
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_addressname']"),addressName);		
		bl.SelectVisibleValueFromDropDown(By.xpath("//select[@id='checkoutAddressBookAdd_left_title']"),personTitle);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_firstname']"),firstName);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_lastname']"), lastName);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_phoneNumber']"), contactNumber);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_companyName']"), companyName);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_streetnumber']"), houseDetail);						
		bl.SelectVisibleValueFromDropDown(By.xpath("//select[@id='checkoutAddressBookAdd_left_country']"),country);			
		
		
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_streetname']"),streetName);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_town']"),townCity);		
		bl.SelectVisibleValueFromDropDown(By.xpath("//select[@id='checkoutAddressBookAdd_left_county']"), county);
		bl.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_left_postalcode'][@name='postalcode']"),postalCode);	
		
		if(!driver.findElement(By.xpath("//input[@id='checkoutAddressBookAdd_left_postalcode'][@name='defaultDeliveryAddress']")).isSelected()){
			driver.findElement(By.xpath("//input[@id='checkoutAddressBookAdd_left_postalcode'][@name='defaultDeliveryAddress']")).click();
		}
		

		
		return this;
	}
	

	
	public void saveAndDelivertoCurrentAddress() {
		
		WebElement element = driver.findElement(By.xpath("//div[@id='expandAddressAddButton']//input[@id='addAddress']"));
		if(element!=null)	
			element.click();
	}
	
	/*private void SelectVisibleValueFromDropDown(By by, String optionToSelect) {

		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(optionToSelect);*/
	
			/*	WebElement select = driver.findElement(By.xpath("//select[@id='checkoutAddressBookAdd_left_title']"));	// Old Method
			List<WebElement> options = select.findElements(By.tagName("option"));

			for (WebElement option : options) {
				if(option.getText().equals(optionToSelect)){
					option.click();
					break;
				}

		}*/

		
	}

