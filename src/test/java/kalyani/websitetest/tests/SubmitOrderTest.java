package kalyani.websitetest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import kalyani.abstractComponents.OrderPage;
import kalyani.websitetest.pageobjects.CartPage;
import kalyani.websitetest.pageobjects.CheckOut;
import kalyani.websitetest.pageobjects.Confirmation;
import kalyani.websitetest.pageobjects.LandingPage;
import kalyani.websitetest.pageobjects.ProductCatalogue;
import kalyani.websitetest.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest	{

	
	
	@Test(dataProvider="getData" ,groups= {"purchasingproducts"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
		
		
		ProductCatalogue pc=lp.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		CartPage cp=pc.goToCartPage();
		
		boolean match=cp.selectedItem(input.get("productName"));
		Assert.assertTrue(match);
		CheckOut co=cp.goToCheckout();
		co.selectingcountry("india");
	         
        Confirmation confirmation= co.checkingOut();  
       String thanksmessage= confirmation.getConfirmationMessage();
       
        Assert.assertEquals(thanksmessage, "THANKYOU FOR THE ORDER.");

	}
	
	//String productName="ZARA COAT 3";
	@Test(dependsOnMethods= {"submitOrder"}, dataProvider = "getProductName")
	
	public void ordersList(String productName)
	{
		
		ProductCatalogue pc=lp.loginApplication("phani12@gmail.com", "Phani@13");
		OrderPage orderPage=pc.goToOrderPage();
		boolean bb=orderPage.verifyProductInOrders(productName);
		Assert.assertTrue(bb);
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getjsonData(System.getProperty("user.dir")+"/src/test/java/kalyani/websitetest/data/data.json");
		return new Object [] []{{data.get(0)}, {data.get(1)}};
		//return new Object[][] {{"Veeranki@gmail.com","Veeranki@1", "ADIDAS ORIGINAL"}, {"phani12@gmail.com", "Phani@13", "ZARA COAT 3"}};
	}
	
	 @DataProvider
	    public Object[][] getProductName() {
	        return new Object[][] { {"ZARA COAT 3"} };
	 }


}
