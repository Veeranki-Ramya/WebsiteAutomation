package kalyani.websitetest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kalyani.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> selected=driver.findElements(By.className("cart"));
	
	@FindBy(className="cart")
	List<WebElement> selected;
	
	// driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutbutton;
			
	
	By iem=By.cssSelector("div ul li div div h3");
	
	public List<WebElement> cartProducts()
	{
		return selected;
		
	}
	public boolean selectedItem(String productName)
	{
		
	for (WebElement item : selected) {
	    System.out.println(item.findElement(By.cssSelector(".ng-star-inserted li div div h3")).getText());
	}
	
	boolean match=cartProducts().stream().anyMatch(s->s.findElement(iem).getText().equalsIgnoreCase(productName));
    return match;
	}
	
	public CheckOut goToCheckout()
	{
		checkoutbutton.click();
		CheckOut co= new CheckOut(driver);
		return co;
	}
	
	
	

}
