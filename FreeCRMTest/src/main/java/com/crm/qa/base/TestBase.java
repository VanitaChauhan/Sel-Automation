package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	
	/* This is the parent class for all the classes. All the initialization of properties is done in this 
	class. Concept of Inheritance is used */
	
	
	public static WebEventListener eventListener;
	
	// Constructor of the class to read the properties
	public TestBase() {
		// Global variables 
		prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream("C:\\Users\\Vanita\\eclipse-workspace\\FreeCRMTest\\src\\"
					+ "main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		// This is a initialization method which initializes the browser		
		public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Driver_for_Selenium\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Driver_for_Selenium\\geckodriver.exe");
//			File pathToBinary = new File("C:\\Program Files\\Driver_for_Selenium\\geckodriver.exe");
//			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
			System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Driver_for_Selenium\\geckodriver.exe");       
			
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	//The wait time is coming from TestUtil class
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		// This launches URL
		driver.get(prop.getProperty("url"));
			
		}
				
				
				
		
	}


