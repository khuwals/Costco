package com.webdriver.CostcoTest;


import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.webdriver.Costco.*;

public class TestOrderCycle extends BaseTest {

	
	@BeforeTest
	public void setUpTest()
	{
		LaunchBrowser();
		loginPage = new Login(driver);
		homePage = new HomePage(driver);
		productDetails=new ProductDetailsPage(driver);
		checkout = new Checkout(driver);
		navigateToPage = new NavigationLink(driver);
		shoppingBasket = new ShoppingBasket(driver);
		orderSummary = new OrderSummary(driver);
		paymentDetails = new PaymentPage(driver);
		orderConfirmation = new OrderConfirmationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		orderDetails = new OrderDetails(driver);
		registration = new Registration(driver);
		
		
	}
	
	
	
	
	@Test(priority=1)
	public void launchApplication() throws Exception{
		
		GoToApplication();
		currTitle = driver.getTitle();	
		Assert.assertTrue(currTitle.startsWith("Costco UK"));
		homePage.ClickOnLinkForLoginOrRegistration();
	}
	
	
	@Test(dependsOnMethods="launchApplication")
	public void LoginApplication()throws Exception{
		
		loginPage
		.FillLoginForm(emailId, passwd)
		.ClickSubmitButton();
	}
	@Test(dependsOnMethods="LoginApplication")
	public void deleteDelAddress() throws Exception{	
		
		
		homePage.GoToMyAccount();
		//myAccountPage.DeleteAddress(6, "hyperplace1");
		
		myAccountPage.CreateUpdateAddress(4, addressName, personTitle, firstName, lastName, contactNumber, companyName, houseDetail,
				postalCode, country, streetName, townCity, county);
	}
	
	
	
	/*
	
	
	
	public void Register() throws Exception{
		
		registration.fillRegForm(fName, lName, emailId, confirmEmail, passwd, confirmPasswd); 	// Filling User Details for first time
		registration.submitForm();	//Submitting the request	
				
		
		//Assert.assertEquals(productDetails.GetPageTitle(),"Costco UK" );
	}
	
	@Test(dependsOnMethods="LoginApplication")
	public void AddToCartItem() throws Exception{
		
		homePage.Search("167436");		//Product id is being searched here
		itemToBeAdded = productDetails.RetrieveProductDetails();		
		productDetails.ClickaddToCart();		
		homePage.GoToBasket();
		
		shoppingBasket.ClickToProceedToCheckout();	// Link for Checkout and delivery
				
		checkout
		.FillNewDeliveryAddress(addressName, personTitle, firstName, lastName, contactNumber, companyName, houseDetail, postalCode, country, streetName, townCity, county)
		.saveAndDelivertoCurrentAddress();
			
		orderSummary
		.ClickToProceedToCheckout();		
	}
	
	@Test(dependsOnMethods="AddToCartItem")
	public void PaymentDetails() throws Exception{
		
		paymentDetails
		.FillCardDetails(cardType, cardNumber,  nameOnCard, expiryMonth, expiryYear, securityCode)
		.ClickOnCompleteOrder();		
		
		Thread.sleep(70000);
		OrderNumber = orderConfirmation.GetOrderNumber();
		orderConfirmation.ClickToGoToHomePage();		
	}
	
	
	@Test(dependsOnMethods="PaymentDetails")
	public void ValidateOrder()throws Exception{
		
		//String OrderNumber="17420004";
		homePage.GoToOrderHistory();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		myAccountPage
		.findRowAndClick(OrderNumber);
		
		String OrderNum = orderDetails.GetOrderNumber();
		Product product  = orderDetails.GetProductsFromOrder(OrderNum);		
		
		Assert.assertEquals(OrderNum, OrderNumber);
		Assert.assertEquals(product.getProductId(), itemToBeAdded.getProductId());
		Assert.assertEquals(product.getpName(), itemToBeAdded.getpName());
		Assert.assertEquals(product.getpPrice(), itemToBeAdded.getpPrice());
		Assert.assertEquals(product.getpQty(), itemToBeAdded.getpQty());
		
	}*/
	
}
