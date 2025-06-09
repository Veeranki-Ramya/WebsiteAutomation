package kalyani.abstractComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> orderedList=driver.findElements(By.cssSelector("[class*='ng-star-inserted'] "));
	@FindBy(css="[class*='ng-star-inserted']")
	List<WebElement> orderedTable;
	
	By orderList=By.cssSelector("[class*='ng-star-inserted'] tr td:nth-child(3)");
//	orderedList.stream().filter(s->s.findElement(By.cssSelector("[class*='ng-star-inserted'] tr td:nth-child(3)")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
  
    public boolean verifyProductInOrders(String productName)
    {
    	boolean bb=orderedTable.stream().anyMatch(s->s.findElement(orderList).getText().equalsIgnoreCase(productName));
    	return bb;
    	  
    }
}
