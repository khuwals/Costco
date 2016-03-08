package com.webdriver.Costco;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


// 	Testing the feature of Locale

public class LanguageValidation {

	public  WebDriver driverMX;
	public static WebElement element;
	public static Actions builder ;	
	public static List<String> MexicanElementsDefault = new ArrayList<String>();	
	public static List<String> englishElements = new ArrayList<String>();
	public static List<String> MexicanElementsExplicitly = new ArrayList<String>();
	public static List<String> productDefaultElements = new ArrayList<String>();
	public static List<String> productMexicanElements = new ArrayList<String>();
	public static List<String> productEnglishElements = new ArrayList<String>();
	public static String itemNumber = "628287";
	
	
	public static String prodDescription="";
	public static String itemLabel="";
	public static String modelLabel = "";

	//public static void main(String args[]) throws Exception{
		
	public void executeLanguageValidation() throws Exception{
		
		LanguageValidation lv = new LanguageValidation();
		
		lv.goToApplication();		
		lv.ValidateHeaders(lv);			// Validating Headers	
		lv.ValidateProductPage(lv);		//Find Product and Go to Product Page
	}
		
	//}	
	
	public void ValidateProductPage(LanguageValidation lv) throws Exception {
		
		lv.GotoProductPage(itemNumber);
		lv.getDefaultProductInfo();
		lv.convertLanguageWindow();		
		lv.getEnglishProductDetails();
		lv.convertLanguageWindow();	
		lv.getMexicanProductDetails();
		
		System.out.println("------------------- Product Validation -------------------");
		System.out.println("Default & English: --> " + lv.validateElements(productDefaultElements, productEnglishElements));
		System.out.println("English & Mexican: --> " + lv.validateElements(productEnglishElements, productMexicanElements));
		System.out.println("Default & Mexican: --> " + lv.validateElements(productDefaultElements, productMexicanElements));
		
	}

	public void getMexicanProductDetails() throws Exception {
		
		/*WebDriverWait wait = new WebDriverWait(driverMX,30);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'productCode')]")));
		*/
		Thread.sleep(5000);
		
		String []productId = GetText(By.xpath("//div[contains(@class,'productCode')]")).split("#");			
		productMexicanElements.add(productId[0].trim());		
		productMexicanElements.add(GetText(By.xpath("//div[@class='productDetail_name_and_description mt30']/h1")));		
		productMexicanElements.add(GetText(By.xpath("//label[@for='quantity']")));
		
		String []modelID = GetText(By.xpath("//div[contains(@class,'productDescriptionText')]")).split(":");
		productMexicanElements.add(modelID[0].trim());		
		productMexicanElements.add(GetText(By.xpath("//a[contains(@class,'wishlistLink')]")));
		productMexicanElements.add(GetText(By.xpath("//a[@href='#productDetail_tab1']")));
		productMexicanElements.add(GetText(By.xpath("//a[@href='#productDetail_tab2']")));
		productMexicanElements.add(GetText(By.xpath("//a[@href='#productDetail_tab2']")));
		//Thread.sleep(2000);
		//productMexicanElements.add(getDetailedInfo("tab1"));		
		//productMexicanElements.add(getDetailedInfo("tab2"));		
		//productMexicanElements.add(getDetailedInfo("tab3"));
	
	}
	
	
	public void getEnglishProductDetails() throws Exception {		
		
		Thread.sleep(7000);		
		String productId[] = getHoveringText(By.xpath("//div[contains(@class,'productCode')]")).split("#");		
		productEnglishElements.add(productId[0].trim());		
		productEnglishElements.add(getHoveringText(By.xpath("//div[@class='productDetail_name_and_description mt30']/h1")));
		productEnglishElements.add(getHoveringText(By.xpath("//label[@for='quantity']")));		
		String []modelID = getHoveringText(By.xpath("//div[contains(@class,'productDescriptionText')]")).split(":");
		productEnglishElements.add(modelID[0].trim());		
		productEnglishElements.add(getHoveringText(By.xpath("//a[contains(@class,'wishlistLink')]")));
		productEnglishElements.add(getHoveringText(By.xpath("//a[@href='#productDetail_tab1']")));
		productEnglishElements.add(getHoveringText(By.xpath("//a[@href='#productDetail_tab2']")));
		productEnglishElements.add(getHoveringText(By.xpath("//a[@href='#productDetail_tab3']")));
		//driverMX.findElement(By.xpath("//a[@href='#productDetail_tab1']")).click();						// Specification tab selection
		/*Thread.sleep(2000);
		productEnglishElements.add(getHoveringText(By.xpath("//div[@id='productDetail_tab1']")));		// getting ready for hovering for the specification tab contents.
*/		
	}
	
	
	public String getDetailedInfo(String tabName){		
		driverMX.findElement(By.xpath("//a[@href='#productDetail_"+ tabName +"']")).click();			
		return driverMX.findElement(By.xpath("//div[@id='productDetail_"+ tabName +"']")).getText().replaceAll("\n", "");		
	}
	
	public void ValidateHeaders(LanguageValidation lv) throws Exception{
		lv.getMexicanElementsDetails( MexicanElementsDefault);
		lv.convertLanguageWindow();
		lv.getEnglishElementsDetails();
		lv.convertLanguageWindow();
		lv.getMexicanElementsDetails(MexicanElementsExplicitly);
		System.out.println("------------------- Header Validation -------------------");
		System.out.println("Default & English: --> "+ lv.validateElements(MexicanElementsDefault, englishElements));	
		System.out.println("English & Mexican: --> "+ lv.validateElements(englishElements, MexicanElementsExplicitly));
		System.out.println("Default & Mexican: --> "+ lv.validateElements(MexicanElementsDefault, MexicanElementsExplicitly));		
		
	}
	
	public LanguageValidation(){
		driverMX =  new FirefoxDriver();
		builder = new Actions(driverMX);		
	}
	
	public void GotoProductPage(String itemNumber){		
		driverMX.findElement(By.id("searchBox")).sendKeys(itemNumber);
		driverMX.findElement(By.className("banner_search_go")).click();
	}

	public void getDefaultProductInfo() throws Exception{			
		String []productId = GetText(By.xpath("//div[contains(@class,'productCode')]")).split("#");			
		productDefaultElements.add(productId[0].trim());		
		productDefaultElements.add(GetText(By.xpath("//div[@class='productDetail_name_and_description mt30']/h1")));		
		productDefaultElements.add(GetText(By.xpath("//label[@for='quantity']")));
		String []modelID = GetText(By.xpath("//div[contains(@class,'productDescriptionText')]")).split(":");
		productDefaultElements.add(modelID[0].trim());		
		productDefaultElements.add(GetText(By.xpath("//a[contains(@class,'wishlistLink')]")));
		productDefaultElements.add(GetText(By.xpath("//a[@href='#productDetail_tab1']")));
		productDefaultElements.add(GetText(By.xpath("//a[@href='#productDetail_tab2']")));
		productDefaultElements.add(GetText(By.xpath("//a[@href='#productDetail_tab2']")));
		
		//productDefaultElements.add(getDetailedInfo("tab1"));	// Not behaving properly		
		//productDefaultElements.add(getDetailedInfo("tab2"));		
		//productDefaultElements.add(getDetailedInfo("tab3"));
	
	}
	
	public  int ip(String s){		
		if(s!=null){
			return Integer.parseInt(s);
		}
		return 0;
		
	}
	
	public  void  goToApplication() throws Exception{

		driverMX.manage().window().maximize();
		driverMX.get("http://qa2.costco.com.mx/");
	}
	
	public  String validateElements(List<String> typeOneElement , List<String> typeTwoElement){

		if(typeOneElement.size()!=typeTwoElement.size()){
			return "Elements not captured correctly";
		}
		else{
			for(int i=0,j=0; i < typeOneElement.size() && j<typeTwoElement.size();i++,j++){

				if(!typeOneElement.get(i).equals(typeTwoElement.get(j))){
					return typeTwoElement.get(j) + " is Different!";
				}
			}
			return "String is matching";
		}
	}

	public  void getMexicanElementsDetails(List<String> MexicanElements){
		
		MexicanElements.add(GetText(By.xpath("//ul[@id='main_nav']//a[@id='catlink-cos_1']")));			
		MexicanElements.add(GetText(By.xpath("//ul[@id='main_nav']//a[@id='catlink-cos_12']")));
		MexicanElements.add(GetText(By.xpath("//ul[@class='logdIn']//a[@id='wishlistLink']")));
		MexicanElements.add(GetText(By.xpath("//ul[@class='logdIn']//a[@href='/j_spring_security_check']")));
	}

	public  void convertLanguageWindow(){

		String windowName= driverMX.getWindowHandle();		
		driverMX.findElement(By.xpath("//div[@id='google_translate_element']//a[@class='goog-te-menu-value']")).click();		
		driverMX.switchTo().frame(driverMX.findElement(By.className("goog-te-menu-frame")));
		driverMX.findElement(By.xpath("//div[@id=':1.menuBody']//a[@class='goog-te-menu2-item']")).click();		
		driverMX.switchTo().window(windowName);		
	}

	public  void getEnglishElementsDetails() throws Exception{

		englishElements = new ArrayList<String>();
		
		englishElements.add(getHoveringText(By.xpath("//ul[@id='main_nav']//a[@id='catlink-cos_1']")));			
		englishElements.add(getHoveringText(By.xpath("//ul[@id='main_nav']//a[@id='catlink-cos_12']")));
		englishElements.add(getHoveringText(By.xpath("//ul[@class='logdIn']//a[@id='wishlistLink']")));
		englishElements.add(getHoveringText(By.xpath("//ul[@class='logdIn']//a[@href='/j_spring_security_check']")));
	}

	public  String getHoveringText(By by) throws Exception{

		element = driverMX.findElement(by);
		builder.moveToElement(element).perform();
		Thread.sleep(2000);
		return GetText(By.xpath ("//div[contains(@id,'goog-gt-tt')]//div[@class='original-text']"));
	}
	
	public  String GetText(By by) {		
		WebElement element = driverMX.findElement(by);
		
		if(element!=null){
			return element.getText();
		}
		return "";
	}
	


}
