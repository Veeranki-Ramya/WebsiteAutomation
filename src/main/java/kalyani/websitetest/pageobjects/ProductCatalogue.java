package kalyani.websitetest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kalyani.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	//WebElement prod=products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	
	
	By waitby=By.cssSelector(".mb-3");
	
	By toast=By.cssSelector("#toast-container");
	
	By ani=By.cssSelector(".ng-animating");
	//driver.findElement(By.cssSelector("button[routerlink*='cart']"))
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartbutton;
	
	public List<WebElement> getProductList()
	{
		 waitforElementToAppear(waitby);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		
		WebElement prod=getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
				prod.findElement(addToCart).click();
				waitforElementToAppear(toast);
				waitforElementToDisappear(ani);
				waitforwebElementToAppear(cartbutton);
				Thread.sleep(1000);
				
				
				
	}
	

}
