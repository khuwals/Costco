package com.webdriver.Costco;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webdriver.Library.WebDriverExtensions;


public class OrderDetails {
	
	
	
	public String orderNumber="";
	WebDriver driver=null;
	WebDriverExtensions driverExtn = null;
	Product products =null;
	
	 
	

	public OrderDetails(WebDriver driver){
		this.driver=driver;
		driverExtn= new WebDriverExtensions(driver);
		products = new Product();
	}
	
	public String GetOrderNumber(){		
		return driverExtn.GetText(By.xpath("//span[contains(@class,'order_summary_code')]"));		
	}
	
	
	public Product GetProductsFromOrder(String orderNumber){
		
		//Product productsOnOrderHistory = new Product();
		
		List<Product> productsOnOrderHistory = new ArrayList<Product>();
		
		
		WebElement htmltable=driver.findElement(By.xpath("//table[contains(@class,'myOrderDetail_table')]/tbody"));
		List<WebElement> rows = htmltable.findElements(By.tagName("tr")); 
		
		for(int rnum=0;rnum<rows.size()-1;rnum++){
			
			products.setpName(rows.get(rnum).findElement(By.xpath("//td[@class='myOrderDetail_table_PRODUCT']/a")).getText());			
			products.setpQty(Integer.parseInt(rows.get(rnum).findElement(By.xpath("//td[@class='myOrderDetail_table_QUANTITY']/div")).getText()));			
			products.setpPrice(rows.get(rnum).findElement(By.xpath("//td[@class='myOrderDetail_table_PRICE']/div")).getText());			
			String []productId = rows.get(rnum).findElement(By.xpath("//td[@class='myOrderDetail_table_PRODUCT']/span[contains(@class,'prod_code')]")).getText().split("#");	
			products.setProductId(Integer.parseInt(productId[1].trim()));
			
			productsOnOrderHistory.add(products);	
			
		}	
		
		/*String []productId = driver.findElement(By.xpath("//td[@class='myOrderDetail_table_PRODUCT']/span[contains(@class,'prod_code')]")).getText().split("#");		
		products.setProductId(Integer.parseInt(productId[1].trim()));
		products.setpName(driverExtn.GetText(By.xpath("//td[@class='myOrderDetail_table_PRODUCT']/a")));
		products.setpQty(Integer.parseInt(driverExtn.GetText(By.xpath("//td[@class='myOrderDetail_table_QUANTITY']/div"))));
		products.setpPrice(driverExtn.GetText(By.xpath("//td[@class='myOrderDetail_table_PRICE']/div")));*/
		
		
		return products;
	}

}
