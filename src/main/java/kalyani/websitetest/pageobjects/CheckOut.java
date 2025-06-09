package kalyani.websitetest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut {
	
	WebDriver driver;

	public CheckOut(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	// driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	// driver.findElement(By.cssSelector("button[class*='ng-star-inserted']:nth-child(3)")).click();

    @FindBy(css="button[class*='ng-star-inserted']:nth-child(3)")
    WebElement fixcou;
    
   // driver.findElement(By.cssSelector("a[class*='ng-star-inserted']")).click();
    @FindBy(css="a[class*='ng-star-inserted']")
    WebElement checkoutbutton;
    
 
    
    public void selectingcountry(String countrry)
    {
    	country.sendKeys(countrry);
    	fixcou.click();
    }
    
    public Confirmation checkingOut()
    {
    	
    	checkoutbutton.click();
    	Confirmation confirmation= new Confirmation(driver);
    	return confirmation;
    	
    }
}
