package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPOM {
	WebDriver driver;
	
	public CategoryPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-name1")
	private WebElement CategoryName; 
	
	@FindBy(xpath="//div[@class='note-editable panel-body']")
	private WebElement CategoryDescription;
	
	@FindBy(id="input-meta-title1")
	private WebElement MetaTagTitle; 
	
	@FindBy(id="input-meta-description1")
	private WebElement MetaTagDescription;
	
	public void sendCategoryName(String categoryName) {
		this.CategoryName.clear();
		this.CategoryName.sendKeys(categoryName);
	}
	
	public void sendCategoryDescription(String categoryDescription) {
		this.CategoryDescription.clear(); 
		this.CategoryDescription.sendKeys(categoryDescription);
	}
	
	public void sendMataTagTitle(String metaTagTitle) {
		this.MetaTagTitle.clear();
		this.MetaTagTitle.sendKeys(metaTagTitle);
	}
	
	public void sendMetaTagDescription(String metaTagDescription) {
		this.MetaTagDescription.clear(); 
		this.MetaTagDescription.sendKeys(metaTagDescription);
	}
	
	public void saveButton() {
		driver.findElement(By.xpath("//button[@data-original-title='Save']")).click();
	}
	
	public void addCategoryButton() {
		driver.findElement(By.xpath("//a[contains(@href,'catalog/category/add')]")).click();
	}

}
