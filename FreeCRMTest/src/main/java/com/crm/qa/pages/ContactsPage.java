package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath ="//a[contains(text(),'Contacts')]")
	 @CacheLookup //improve performance, its a memory. Stores element in cache
	              // if the page element get updated then it will give error- staleElementException
	WebElement contactsLabel;
	@FindBy(xpath="//form[@id='vContactsForm']/table/tbody/tr[6]/td/input")
			WebElement contactsName;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id = "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//input[@type ='submit' and @value = 'Save']")
	WebElement saveBtn;
	
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
  
	  public boolean verifyContactsLabel() {
		  return contactsLabel.isDisplayed();
	  }
	  
//	  public void selectContactsByName(String name) {
//		  driver.findElement (By.xpath("//a[contains(text()='"+name+"')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistraw']//input[@name='contact_id']")).click();
//	  }
	  
	  public void selectContactsByName() {
		  contactsName.click();
		 
	  }
	  public void createNewContact(String title, String ftName, String ltName, String comp) {
		  Select select = new Select(driver.findElement(By.name("title")));
		  select.selectByVisibleText(title);
		  
		  firstName.sendKeys(ftName);
		  lastName.sendKeys(ltName);
		  company.sendKeys(comp);
		  saveBtn.click();
	  }
}
