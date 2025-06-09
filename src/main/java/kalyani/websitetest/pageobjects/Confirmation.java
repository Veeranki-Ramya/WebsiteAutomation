package kalyani.websitetest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation {

	WebDriver driver;
	public Confirmation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	 //  String thanksmessage=driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
    @FindBy(css="h1[class='hero-primary']")
    WebElement thankmsg;
    
    public String getConfirmationMessage()
    {
    	String thanksmessage=thankmsg.getText();
    	return thanksmessage;
    }

}
