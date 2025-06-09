package kalyani.websitetest.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kalyani.websitetest.pageobjects.CartPage;
import kalyani.websitetest.pageobjects.CheckOut;
import kalyani.websitetest.pageobjects.Confirmation;
import kalyani.websitetest.pageobjects.LandingPage;
import kalyani.websitetest.pageobjects.ProductCatalogue;
import kalyani.websitetest.testcomponents.BaseTest;

public class StepDefinitionImple extends BaseTest {
	
	public LandingPage lp;
	public ProductCatalogue pc;
	public CartPage cp;
	public Confirmation confirmation;
	
	@Given("I landed on Ecommerce website")
	public void I_landed_on_Ecommerce_website() throws IOException
	{
		 lp=launchApplication();
	}
	
	
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String password)
	{
		 pc=lp.loginApplication(userName, password);
	
	}
	
	 @When("^adding product (.+) to cart$")
	 public void adding_product_to_cart(String productName) throws InterruptedException
	 {
			List<WebElement> products=pc.getProductList();
			pc.addProductToCart(productName);
	 }
	 
	 @When("^checkout (.+) and submit the order$")
	 public void checkout_product_and_submit_order(String productName) throws InterruptedException
	 {
		 pc.addProductToCart(productName);
			 cp=pc.goToCartPage();
			
			boolean match=cp.selectedItem(productName);
			Assert.assertTrue(match);
			CheckOut co=cp.goToCheckout();
			co.selectingcountry("india");
		         
	         confirmation= co.checkingOut();  
	 }
	 
	 @Then("{string} confirmation message is displayed on confirmation page")
	 public void confirmation_message_is_displayed_on_confirmation_page(String string)
	 {
		 String thanksmessage= confirmation.getConfirmationMessage();
	       
	        Assert.assertEquals(thanksmessage, string);
		 
	 }
	 
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String arg)
	 {
		 Assert.assertEquals(arg, lp.getTheErrorMessage());
	 }

}
