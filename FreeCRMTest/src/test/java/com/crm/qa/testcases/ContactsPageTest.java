package com.crm.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "contacts";
	public ContactsPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp() throws InterruptedException {
	initialization();
	testUtil = new TestUtil();
	contactsPage = new ContactsPage();
	loginPage = new LoginPage();
	homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	// switches to frame
	 testUtil.switchToFrame(); 
	contactsPage = homePage.clickOnContactsLink();
	 

}
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts label is missing on the page");
	 
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage.selectContactsByName();
	  
	}
	// Reads data from excel sheet, calls the method to read the data 
	@DataProvider
	public Object[][]getCRMTestData() throws InvalidFormatException {
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
	}
	// The data is read from excel file in the com.crm.qa.testdata package
	@Test(priority=3, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String title, String ftName, String ltName, String comp) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.","Tom","Peter","Google"); this is hard coded value
		contactsPage.createNewContact(title, ftName, ltName, comp);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}