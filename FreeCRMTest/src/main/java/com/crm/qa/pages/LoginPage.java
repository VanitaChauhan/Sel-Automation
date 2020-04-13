package com.crm.qa.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

// LoginPage class is child of TestBase class
public class LoginPage extends TestBase{
	
	// Page Factory - OR:
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy (name = "password")
	WebElement password;
	
	@FindBy (xpath = "//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy (xpath = "//img[contains(@class,'img-responsive')]")	
			WebElement crmLogo;
	
	//Initializing the Page Objects, 'this' is pointing to current
	// class object
	
	public LoginPage() {
		PageFactory.initElements(driver, this);	
		
	}
	//Actions
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
		
			}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
		
	}
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		//Click() was giving an error
//		Actions actions = new Actions (driver);
//		actions.moveToElement(loginBtn).click().perform();

		
		loginBtn.submit();
		
		//HomePage is the landing page of the Login page
		
		return new HomePage();
	}
	
	

}
