package kalyani.websitetest.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import kalyani.websitetest.pageobjects.LandingPage;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("phani12@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Phani@13");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[routerlink*='cart']"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart")));
		List<WebElement> selected=driver.findElements(By.className("cart"));
		
		for (WebElement item : selected) {
    	    System.out.println(item.findElement(By.xpath("//h3[normalize-space()='ZARA COAT 3']")).getText());
    	}

		
		
		boolean match=selected.stream().anyMatch(s->s.findElement(By.cssSelector("div ul li div div h3")).getText().equalsIgnoreCase("ZARA COAT 3"));
        Assert.assertTrue(match);
	
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
        driver.findElement(By.cssSelector("button[class*='ng-star-inserted']:nth-child(3)")).click();
        driver.findElement(By.cssSelector("a[class*='ng-star-inserted']")).click();
        String thanksmessage=driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
        Assert.assertEquals(thanksmessage, "THANKYOU FOR THE ORDER.");

	}

}
