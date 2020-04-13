package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

// LoginPageTest is the child page of TestBase

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	// super keyword calls TestBase class to initialize the properties
	//LoginPageTest class constructor to call TestBase class
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization(); //calls initialization method from TestBase class
		 loginPage = new LoginPage();//to access all the functions and methods of LoginPage class
	}
	
	@Test (priority =1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		//Assertion is added for validation of the page
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	@Test (priority =2)
	public void crmLogoImageTest() {
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
		
	}
	
	@Test (priority =3)
	public void loginTest() {
		//this is returning homePage class object
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	// Invoked after each test is run
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
