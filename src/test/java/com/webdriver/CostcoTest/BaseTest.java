package com.webdriver.CostcoTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.webdriver.Costco.MyAccountPage;
import com.webdriver.Costco.OrderConfirmationPage;
import com.webdriver.Costco.OrderDetails;
import com.webdriver.Costco.OrderSummary;
import com.webdriver.Costco.PaymentPage;
import com.webdriver.Costco.Product;
import com.webdriver.Costco.ProductDetailsPage;
import com.webdriver.Costco.Checkout;
import com.webdriver.Costco.HomePage;
import com.webdriver.Costco.Login;
import com.webdriver.Costco.NavigationLink;
import com.webdriver.Costco.Registration;
import com.webdriver.Costco.ShoppingBasket;

public class BaseTest {

	public WebDriver driver;	
	public Login loginPage ;
	public HomePage homePage;
	public ProductDetailsPage productDetails;
	public Checkout checkout ;
	public NavigationLink navigateToPage;
	public ShoppingBasket shoppingBasket;
	public OrderSummary orderSummary;
	public PaymentPage paymentDetails;
	public OrderConfirmationPage orderConfirmation;
	public MyAccountPage myAccountPage;
	public OrderDetails orderDetails;
	public Registration registration;



	public String baseUrl = "http://qa2.costco.co.uk";
	public String homeTitle="Costco UK - TVs, Appliances, Toys, Sheds, Furniture, Food, Wine & More!";
	public String fName = "xyz", lName="Kumar",emailId ="xyz1@metacube.com", 
			confirmEmail="xyz1@metacube.com", passwd="test1234", confirmPasswd="test1234";
	public String currTitle="";

	String addressName="hyperplace3",  personTitle="Mr.",  firstName="firsta", 
			lastName="lasta",  contactNumber="95326598236",  companyName="xyz",  houseDetail="abc",  postalCode="321323",  country="United Kingdom", 
			streetName="abcx",  townCity="spaceCity",  county="Berkshire";

	String cardType="Visa", cardNumber="4111111111111111", nameOnCard="testUser", expiryMonth="11",expiryYear="2016", securityCode="123";

	public String OrderNumber="";	
	Product itemToBeAdded;

	public void LaunchBrowser()
	{
		driver = new FirefoxDriver();		
		driver.manage().window().maximize();		 
	}

	public void GoToApplication()
	{
		driver.get(baseUrl);
	}

}
