package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductsPOM {
	private WebDriver driver;
	
	public ProductsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(@href,'general')]")
	public WebElement GeneralTab;
	
	@FindBy(xpath="//a[contains(@href,'data')]")
	public WebElement DataTab;
	
	@FindBy(xpath="//a[contains(@href,'links')]")
	public WebElement LinksTab;
	
	@FindBy(xpath="//a[contains(@href,'discount')]")
	public WebElement DiscountTab;
	
	@FindBy(xpath="(//a[contains(text(),'Reward Points')])[2]")
	public WebElement RewardPointsTab;
	
	//General Tab Elements
	@FindBy(id="input-name1")
	public WebElement ProductName;
	
	@FindBy(id="input-meta-title1")
	public WebElement MetaTitle;
	
	//Data Tab Elements
	@FindBy(id="input-model")
	public WebElement Model;
		
	@FindBy(id="input-price")
	public WebElement Price;
	
	@FindBy(id="input-quantity")
	public WebElement Quantity;
	
	//Link Tab Elements
	@FindBy (xpath="//input[@id='input-manufacturer']")
	public WebElement Manufacturer;
	
	@FindBy (xpath="//input[@id='input-category']")
	public WebElement Category;
	
	//Discount Tab Elements
	@FindBy(xpath="//table[@id='discount']//input[contains(@name,'quantity')]")
	public WebElement DiscountQuantity;
	
	@FindBy(xpath="//table[@id='discount']//input[contains(@name,'price')]")
	public WebElement DiscountPrice; 
	
	//RedeemPoints Tab Elements
	@FindBy(xpath="//input[@id='input-points']")
	public WebElement RedeemPoints;
	
	public void addProductButton() {
		driver.findElement(By.xpath("//a[contains(@href,'route=catalog/product/add')]")).click();
	}

	public void addGeneralDetails(String productName, String metaTitle) {
		this.GeneralTab.click();
		this.ProductName.sendKeys(productName);
		this.MetaTitle.sendKeys(metaTitle);
		
	}

	public void addDataDetails(String model, int price, int quantity) {
		this.DataTab.click();
		this.Model.sendKeys(model);
		this.Price.sendKeys(String.valueOf(price));
		this.Quantity.clear();
		this.Quantity.sendKeys(String.valueOf(quantity));
	}
	
	public void addDataDetailsExcel(String model, String price, String quantity) {
		this.DataTab.click();
		this.Model.sendKeys(model);
		this.Price.sendKeys(price);
		this.Quantity.clear();
		this.Quantity.sendKeys(quantity);
	}

	public void addLinkDetails(String manufacturer, String category) {
		this.LinksTab.click();
		this.Manufacturer.sendKeys(manufacturer);
		this.Category.sendKeys(category);
	}

	public void addDiscountDetails(String customerGroup,int discountQuantity, int discountPrice) {
		this.DiscountTab.click();
		driver.findElement(By.xpath("//button[@onclick='addDiscount();']")).click();
		Select customerGroupList = new Select(driver.findElement(By.xpath("//select[contains(@name,'customer_group_id')]")));
		customerGroupList.selectByVisibleText(customerGroup);
		this.DiscountQuantity.sendKeys(String.valueOf(discountQuantity));
		
		
		driver.findElement(By.xpath("//tr[@id='discount-row0']/td[5]//i[@class='fa fa-calendar']")).click();
		driver.findElement(By.xpath("//div[5]//td[contains(@class,'today')]")).click();
		driver.findElement(By.xpath("//tr[1]/td[5]//i[@class='fa fa-calendar']")).click();
		driver.findElement(By.xpath("//div[5]//td[contains(@class,'today')]")).click();
		driver.findElement(By.xpath("//tr[1]/td[6]//i[@class='fa fa-calendar']")).click();
		driver.findElement(By.xpath("//div[6]//td[contains(@class,'today')]/following-sibling::td")).click();
		this.DiscountPrice.sendKeys(String.valueOf(discountPrice));
	}
	
	public void addDiscountDetailsExcel(String customerGroup,String discountQuantity, String discountPrice) {
		this.DiscountTab.click();
		driver.findElement(By.xpath("//button[@onclick='addDiscount();']")).click();
		Select customerGroupList = new Select(driver.findElement(By.xpath("//select[contains(@name,'customer_group_id')]")));
		customerGroupList.selectByVisibleText(customerGroup);
		this.DiscountQuantity.sendKeys(discountQuantity);
		
		
		driver.findElement(By.xpath("//tr[@id='discount-row0']/td[5]//i[@class='fa fa-calendar']")).click();
		driver.findElement(By.xpath("//div[5]//td[contains(@class,'today')]")).click();
		driver.findElement(By.xpath("//tr[1]/td[5]//i[@class='fa fa-calendar']")).click();
		driver.findElement(By.xpath("//div[5]//td[contains(@class,'today')]")).click();
		driver.findElement(By.xpath("//tr[1]/td[6]//i[@class='fa fa-calendar']")).click();
		driver.findElement(By.xpath("//div[6]//td[contains(@class,'today')]/following-sibling::td")).click();
		this.DiscountPrice.sendKeys(discountPrice);
	}

	public void clickSave() {
		driver.findElement(By.xpath("//button[@data-original-title='Save']")).click();
	}

	public void addRedeempoints(int redeemPoints) {
		this.RewardPointsTab.click();
		this.RedeemPoints.clear();
		this.RedeemPoints.sendKeys(String.valueOf(redeemPoints));
		
	}
	
	public void addRedeempointsExcel(String redeemPoints) {
		this.RewardPointsTab.click();
		this.RedeemPoints.clear();
		this.RedeemPoints.sendKeys(redeemPoints);
		
	}
	

}
