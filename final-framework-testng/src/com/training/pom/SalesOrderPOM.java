package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author AshutoshSabnekar
 *
 */
public class SalesOrderPOM {
	private WebDriver driver;
	private int index = 0;
	
	public SalesOrderPOM(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tbody//td")
	public List<WebElement> tableData;
	
	//Order Edit Page Elements follow from now
	
	@FindBy(id = "input-firstname")
	public WebElement FirstName;
	
	@FindBy(id = "input-lastname")
	public WebElement LastName;
	
	@FindBy(id = "input-telephone")
	public WebElement Telephone;
	
	@FindBy(id = "button-customer")
	public WebElement continueButton;
	
	
	
	public boolean searchByOrderID(String orderID){
		index = 0;
		try{
			for(index = 0;index < tableData.size();index++){
				   if(tableData.get(index).getText().equals(orderID)){
					   break;
				   }   
				}
			driver.findElement(By.xpath("//tbody//td[contains(text(),'"+orderID+"')]/parent::tr/td/a[1]")).click();
			return true;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
            return false;
		}
		
	}
	
	public void editFirstName(String firstName) {
		this.FirstName.clear(); 
		this.FirstName.sendKeys(firstName); 
	}

	public void editLirstName(String lastName) {
		this.LastName.clear(); 
		this.LastName.sendKeys(lastName);
		
	}

	public void editTelephoneNumber(String phoneNumber) {
		this.Telephone.clear(); 
		this.Telephone.sendKeys(phoneNumber);
		
	}

	public void clickContinue() {
		this.continueButton.click();
		
	}
	
	public boolean clickEditByOrderID(String orderID)
	{
		index = 0;
		try{
			for(index = 0;index < tableData.size();index++){
				   if(tableData.get(index).getText().equals(orderID)){
					   break;
				   }   
				}
			driver.findElement(By.xpath("//tbody//td[contains(text(),'"+orderID+"')]/parent::tr/td/a[2]")).click();
			return true;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
            return false;
		}
		
	}

	public boolean deleteByOrderID(String orderID) {
		// TODO Auto-generated method stub
		index = 0;
		try{
			for(index = 0;index < tableData.size();index++){
				   if(tableData.get(index).getText().equals(orderID)){
					   break;
				   }   
				}
			driver.findElement(By.xpath("//tbody//td[contains(text(),'"+orderID+"')]/parent::tr/td/button")).click();
			driver.switchTo().alert().accept();
			return true;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
            return false;
		}
		
	}
	
}

