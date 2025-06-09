package kalyani.websitetest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kalyani.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public ProductCatalogue loginApplication(String email,String passcode)
	{
		username.sendKeys(email);
		password.sendKeys(passcode);
		login.click();
		ProductCatalogue pc =new ProductCatalogue(driver);
		return pc;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("phani12@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Phani@13");
	//driver.findElement(By.id("login")).click();

    public String getTheErrorMessage()
    {
    	waitforwebElementToAppear(errormessage);
    	String text=errormessage.getText();
    	return text;
    }
}
