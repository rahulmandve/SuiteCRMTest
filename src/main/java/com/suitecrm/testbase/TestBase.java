package com.suitecrm.testbase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import com.suitecrm.utilities.Constant;
import com.suitecrm.utilities.WebEventListner;

public class TestBase {
	public static  Logger log;
	public static WebDriver e_driver;
	public static Properties prop;
	public static WebDriver driver;

	
	public TestBase() {
		log=	Logger.getLogger(this.getClass());
	try {
		
		prop=new Properties();
		FileInputStream inpt=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\suitecrm\\config\\Configuration.properties");
		prop.load(inpt);
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	

	}
	
	
	public static void initialization()
	{
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Software Setup\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			e_driver=new ChromeDriver();
		}else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "");
			e_driver=new FirefoxDriver();
		}
		
		WebEventListner listner=new WebEventListner(); 
		
		driver = new EventFiringDecorator<WebDriver>(listner).decorate(e_driver);
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.PAGELOAD_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.IMPLICITLY_WAIT));
		driver.get(prop.getProperty("url"));
	}
	
	

}
