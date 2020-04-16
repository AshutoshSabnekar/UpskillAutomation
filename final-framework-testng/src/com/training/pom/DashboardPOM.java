package com.training.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPOM {
	private WebDriver driver;
	
	public DashboardPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@id='sale']/a")
	public WebElement sales;
	
	@FindBy(xpath="//li[@id='design']/a")
	public WebElement design;
	
	@FindBy(xpath="//li[@id='dashboard']/a")
	public WebElement dashboard;
	
	@FindBy(xpath="//ul[@id='menu']//li[@id='catalog']/a")
	public WebElement Catalog;
	
	@FindBy(xpath="//li[@class='open']/a")
	public WebElement marketing;
	
	@FindBy(xpath="//li[@id='customer']/a")
	public WebElement Customer;
	
	@FindBy(id="button-menu")
	public WebElement Menu;
	
	@FindBy(xpath="//ul[@id='menu']")
	public List<WebElement> menuList;
	
	@FindBy(xpath="//li[@id='sale']//a[text()='Orders']")
	public WebElement Orders;
	
	@FindBy(xpath="//li[@id='sale']//a[text()='Returns']")
	public WebElement Returns;
	
	@FindBy(xpath="//span[text()='Marketing']")
	public WebElement Marketing;
	
	@FindBy(xpath="//a[contains(@href,'route=marketing/coupon')]")
	public WebElement Coupons;
	
	@FindBy(xpath="//li[@id='catalog']//a[contains(text(),'Products')]")
	public WebElement Products;
	
	@FindBy(xpath="//a[contains(text(),'Categories')]")
	public WebElement Categories;
	
	@FindBy(xpath="//li[@id='customer']//a[text()='Customers']")
	public WebElement CustomerCustomers;

	public boolean elementClick(WebElement elementToBeClicked)
	{
	    try{

	     elementToBeClicked.click();
	     return true;
	    }
	catch(Exception e){
	     return false;
		}
	}
}
