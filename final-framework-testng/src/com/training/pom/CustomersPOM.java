package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomersPOM {
	WebDriver driver;
	
	public CustomersPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@id='input-customer-group']")
	public WebElement CustomerGroup;
	
	@FindBy(id="input-firstname")
	public WebElement FirstName;
	
	@FindBy(id="input-lastname")
	public WebElement LastName;
	
	@FindBy(id="input-email")
	public WebElement MailID;
	
	@FindBy(id="input-telephone")
	public WebElement PhoneNumber;
	
	@FindBy(id="input-password")
	public WebElement Password;
	
	@FindBy(id="input-confirm")
	public WebElement ConfirmPassword;

	public void AddCustomerButton() {
		driver.findElement(By.xpath("//a[contains(@href,'route=customer/customer/add')]")).click();
	}
	
	public void selectCustomerGroup(String customerGroup){
		this.CustomerGroup.click();
		Select custGrp = new Select(CustomerGroup);
		custGrp.selectByVisibleText(customerGroup);
	}
	
	public void addCustomerDetails(String firstName, String lastName, String mailID, String phoneNumber, String password, String confirmPassword) {
		this.FirstName.sendKeys(firstName);
		this.LastName.sendKeys(lastName);
		this.MailID.sendKeys(mailID);
		this.PhoneNumber.sendKeys(phoneNumber);
		this.Password.sendKeys(password);
		this.ConfirmPassword.sendKeys(confirmPassword);
	}

	public void clickSaveButton() {
		driver.findElement(By.xpath("//button[@data-original-title='Save']")).click();
	}
	

}
