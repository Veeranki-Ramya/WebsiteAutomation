package kalyani.websitetest.testcomponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import kalyani.websitetest.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver intializedriver() throws IOException
	{
	
	Properties properties= new Properties();
	FileInputStream fil= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//kalyani//websitetest//resources//globaldata.properties");
	properties.load(fil);
	String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");
			//properties.getProperty("browser");
	
	if(browserName.contains("chrome"))
	{
		ChromeOptions options=new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{
		options.addArguments("headless");
		}
		 driver=new ChromeDriver(options);
		

	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		//code edge
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		//code
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	return driver;
	
	
	
		}
	
	
	public List<HashMap<String, String>> getjsonData(String filepath) throws IOException
	{
		String jsoncontent= FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File fil= new File(System.getProperty("user.dir")+"//reports"+testcaseName+".png");
		FileUtils.copyFile(source, fil);
		return System.getProperty("user.dir")+"//reports"+testcaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=intializedriver();
		 lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
