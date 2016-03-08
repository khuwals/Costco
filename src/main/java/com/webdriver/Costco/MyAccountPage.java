package com.webdriver.Costco;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webdriver.Library.WebDriverExtensions;

public class MyAccountPage {

	WebDriver driver=null;
	WebDriverExtensions driverExtn = null;


	public MyAccountPage(WebDriver driver){
		this.driver = driver;
		driverExtn= new WebDriverExtensions(driver);
		//driverExtn.ClickButton(By.xpath("//a[@id='myAccountLinkId']"));
	}

	public void findRowAndClick(String orderIdNumber){


		WebElement htmltable=driver.findElement(By.xpath("//div['myaccount_holder']//table/tbody"));
		List<WebElement> rows = htmltable.findElements(By.tagName("tr")); 

		for(int rnum=0;rnum<rows.size();rnum++){
			String orderId = rows.get(rnum).findElement(By.className("myOrders_table_CODE")).getText();
			if(orderId.contentEquals(orderIdNumber)){
				rows.get(rnum).findElement(By.xpath("//td[@class='myOrders_table_ORDLINK']/a")).click();
			}
		}
	}
	
	
	
	public boolean UpdateContactDetails(String personTitle, String firstName, String lastName, String contactNumber, String gender){

		if(driverExtn.GetPageTitle().contains("My Account")){

			driverExtn.ClickButton(By.xpath("//a[contains(@href,'CONTACTDETAILS')]"));		//Going to page for editing/modifying Contact Details

			driverExtn.WaitForElement(By.xpath("//form[@id='contactDetailsForm']//select[@id='gender']"), 30);	// wait for last text item
			driverExtn.SelectVisibleValueFromDropDown(By.xpath("//form[@id='contactDetailsForm']//select[@id='title']"), personTitle);
			driverExtn.SetText(By.xpath("//form[@id='contactDetailsForm']//input[@id='firstName']"), firstName);
			driverExtn.SetText(By.xpath("//form[@id='contactDetailsForm']//input[@id='lastName']"), lastName);
			driverExtn.SetText(By.xpath("//form[@id='contactDetailsForm']//input[@id='phoneNumber']"), contactNumber);
			driverExtn.SelectVisibleValueFromDropDown(By.xpath("//form[@id='contactDetailsForm']//select[@id='gender']"), gender);

			driverExtn.ClickButton(By.xpath("//form[@id='contactDetailsForm']//input[@id='save']")); //Commit the changes	

			driverExtn.WaitForElement(By.xpath("//div[@class='success_border']"), 60);
			if(driverExtn.GetText(By.xpath("//div[@class='success_border']")).contains("success")){
				return true;
			}			
		}

		return false;
	}

	public boolean UpdateAccountDetails(String password){


		if(driverExtn.GetPageTitle().contains("My Account")){

			driverExtn.ClickButton(By.xpath("//a[contains(@href,'ACCOUNTDETAILS') and contains(text(),'Account Details')]"));	

			driverExtn.WaitForElement(By.xpath("//a[contains(@href,'ACCOUNTDETAILS') and contains(text(),'Change Password')]"), 30);	
			driverExtn.ClickButton(By.xpath("//a[contains(@href,'ACCOUNTDETAILS') and contains(text(),'Change Password')]"));		
			driverExtn.SetText(By.xpath("//form[@id='loginform']//input[@id='password']"), password);
			driverExtn.SetText(By.xpath("//form[@id='loginform']//input[@id='confirmPassword']"), password);

			driverExtn.ClickButton(By.xpath("//form[@id='loginform']//input[@type='submit']"));	// Click on button for changes made			
			driverExtn.WaitForElement(By.xpath("//div[@class='success_border']"), 60);

			if(driverExtn.GetText(By.xpath("//div[@class='success_border']")).contains("success")){
				return true;
			}		

		}
		return false;

	}
	
	public void DeleteAddress(int choice, String addressName){
		
				// 1=CreateDeliveryAddress
				// 2=UpdateDeliveryAddress
				// 3=DeleteDeliveryAddress	
				// 4=CreateBillingAddress
				// 5=UpdateBillingAddress
				// 6=DeleteBillingAddress
		String optionAddress="";
		switch (choice) {
		case 3:
			optionAddress="Delivery";	
			break;
		case 6:
			optionAddress="Billing";	
			break;

		default:
			break;
		}
		
		if(driverExtn.GetPageTitle().contains("My Account")){			
			driverExtn.ClickButton(By.xpath("//a[contains(@href,'myAccount"+ optionAddress +"AddressListing')][@class='tablink']"));	// Go to My Delivery Address Book
	
			driverExtn.ClickButton(By.xpath("//a[@data-addressname='" + addressName + "']"));
			driver.switchTo().alert().accept();				
		}		
	}
	
	public boolean CreateUpdateAddress(int choice,String addressName, String personTitle, String firstName, 
			String lastName, String contactNumber, String companyName, String houseDetail, String postalCode, String country, 
			String streetName, String townCity, String county){
		
		// 1=CreateDeliveryAddress
		// 2=UpdateDeliveryAddress
		// 3=DeleteDeliveryAddress	<-- Not Required
		// 4=CreateBillingAddress
		// 5=UpdateBillingAddress
		// 6=DeleteBillingAddress	<-- Not Required
		
		String optionAddress="";
		if(driverExtn.GetPageTitle().contains("My Account")){			
			
			switch (choice) {
			case 1:
				optionAddress="Delivery";			
				break;
			case 2:
				optionAddress="Delivery";			
				break;
			case 4:
				optionAddress="Billing";			
				break;
						
			case 5:
				optionAddress="Billing";				
				break;
			}
			
			driverExtn.ClickButton(By.xpath("//a[contains(@href,'myAccount" + optionAddress 
											+ "AddressListing')][@class='tablink']"));	// Go to My Delivery Address Book		
			
			if(choice == 1 ||  choice == 4){
				driverExtn.ClickButton(By.xpath("//a[contains(@href,'myAccount"+ optionAddress
											+ "AddressListing')][@id='addAddress']"));	//Add New  Address
			}			
			else if(choice == 2 || choice == 5){
				WebElement element = driver.findElement(By.xpath("//div[@id='addressListing_popup']//p[contains(text(),'" + addressName 
						+	"')]/ancestor::div[contains(@class,'listing_box')]"));			
				
				element.findElement(By.xpath("//div/a[@id='editBillingAddress'")).click();	//Click on Edit Address
			}
			
			return fillEntries(addressName, personTitle, firstName, lastName, contactNumber, 
					companyName, houseDetail, postalCode, country, streetName,  townCity,  county);				
		}
		
		return false;
	}
	
	public boolean fillEntries(String addressName, String personTitle, String firstName, 
			String lastName, String contactNumber, String companyName, String houseDetail, String postalCode, String country, 
			String streetName, String townCity, String county){
		
		driverExtn.ClickButton(By.xpath("//p[@id='checkoutAddressBookAdd_enterManually_right']"));
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_addressname']"),addressName);		
		driverExtn.SelectVisibleValueFromDropDown(By.xpath("//select[@id='checkoutAddressBookAdd_title']"),personTitle);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_firstname']"),firstName);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_lastname']"), lastName);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_phoneNumber']"), contactNumber);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_companyName']"), companyName);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_streetnumber']"), houseDetail);						
		driverExtn.SelectVisibleValueFromDropDown(By.xpath("//select[@id='checkoutAddressBookAdd_country']"),country);			


		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_streetname']"),streetName);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_town']"),townCity);		
		driverExtn.SelectVisibleValueFromDropDown(By.xpath("//select[@id='checkoutAddressBookAdd_county']"), county);
		driverExtn.SetText(By.xpath("//input[@id='checkoutAddressBookAdd_postalcode'][@name='postalcode']"),postalCode);	

		if(!driver.findElement(By.xpath("//input[@id='checkoutAddressBookAdd_postalcode'][@name='defaultDeliveryAddress']")).isSelected()){
			driver.findElement(By.xpath("//input[@id='checkoutAddressBookAdd_postalcode'][@name='defaultDeliveryAddress']")).click();
		}

		driverExtn.ClickButton(By.xpath("//div[@id='expandAddressAddButton']//input[@id='addAddress']"));		//Save Address

		driverExtn.WaitForElement(By.xpath("//div[@class='success_border']"), 30);
		if(driverExtn.GetText(By.xpath("//div[@class='success_border']")).contains("success")){
			return true;
		}
		
		return false;
		
	}

}
