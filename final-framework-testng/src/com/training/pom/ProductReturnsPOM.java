package com.training.pom;

import java.util.List;

import javax.swing.plaf.synth.SynthStyle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductReturnsPOM {
	private WebDriver driver;
	
	public ProductReturnsPOM(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@id='sale']/a")
	public WebElement sales;
	
	@FindBy(xpath = "//tbody//td")
	public List<WebElement> returnTableData;
	
//Object from Product returns page
	@FindBy(id="input-firstname")
	public WebElement FirstName;
	
	@FindBy(id="input-lastname")
	public WebElement LastName;
	
	@FindBy(id="input-email")
	public WebElement eMail;
	
	@FindBy(xpath="//button[@data-original-title='Save']")
	public WebElement SaveButton;
	
	public boolean searchByReturnID(String orderID){
		int index=0;
		int Size = returnTableData.size();
		System.out.println(Size);
		try{	
			for(index = 0;index < Size;index++){
				 if(returnTableData.get(index).getText().equals(orderID)){
					 break;
				 	}  
				}
			driver.findElement(By.xpath("//tbody//td[contains(text(),'"+orderID+"')]/parent::tr/td/a")).click();
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

	public void editMailID(String phoneNumber) {
		this.eMail.clear(); 
		this.eMail.sendKeys(phoneNumber);
		
	}

	public void clickSaveButton() {
		this.SaveButton.click();
		
	}
	
}


