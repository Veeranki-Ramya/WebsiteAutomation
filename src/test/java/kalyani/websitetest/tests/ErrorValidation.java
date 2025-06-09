package kalyani.websitetest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import kalyani.websitetest.pageobjects.CartPage;
import kalyani.websitetest.pageobjects.CheckOut;
import kalyani.websitetest.pageobjects.Confirmation;
import kalyani.websitetest.pageobjects.ProductCatalogue;
import kalyani.websitetest.testcomponents.BaseTest;
import kalyani.websitetest.testcomponents.Retry;

public class ErrorValidation extends BaseTest	{

	@Test(groups= {"Errorhandling"}, retryAnalyzer=Retry.class)
	public void loginValidation() throws IOException, InterruptedException
	{
		
		String productName="ZARA COAT 3";
		lp.loginApplication("phani12@gmail.com", "Phani333@13");
		Assert.assertEquals("Incorrect email  password.", lp.getTheErrorMessage());
		
	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		
		String productName="ZARA COAT 3";
		ProductCatalogue pc=lp.loginApplication("anishaa@gmail.com", "Anishaa@1");
		List<WebElement> products=pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp=pc.goToCartPage();	
		boolean match=cp.selectedItem("ZARA COAT 33");
		Assert.assertFalse(match);
		

	}


}
