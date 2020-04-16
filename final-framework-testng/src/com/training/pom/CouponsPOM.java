package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CouponsPOM {
	private WebDriver driver;
	
	public CouponsPOM(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-name")
	public WebElement CouponName;
	
	@FindBy(id = "input-code")
	public WebElement CouponCode;
	
	@FindBy(id = "input-type")
	public WebElement CouponType;
	
	@FindBy(id = "input-discount")
	public WebElement Discount;
	
	@FindBy(id = "input-product")
	public WebElement ProductDesc;
	
	@FindBy(xpath= "//button[@data-original-title='Save']")
	public WebElement Save;

	public void addCoupon() {
		driver.findElement(By.xpath("//a[contains(@href,'marketing/coupon/add')]")).click();
	}

	public void createCouponDetails(String couponName, String couponCode, String couponType, int discount, String productDesc) {
		if(couponType.equals("Percentage")){
			if(discount < 100){
				this.CouponName.clear();
				this.CouponName.sendKeys(couponName);
				this.CouponCode.clear();
				this.CouponCode.sendKeys(couponCode);
				Select couponTypeDropdown = new Select(driver.findElement(By.id("input-type")));
				couponTypeDropdown.selectByIndex(0);
				this.Discount.clear();
				this.Discount.sendKeys(String.valueOf(discount));
				this.ProductDesc.clear();
				this.ProductDesc.sendKeys(productDesc);
				driver.findElement(By.xpath("//input[@name='date_start']/following-sibling::span")).click();
				driver.findElement(By.xpath("//td[contains(@class,'today')]")).click();
				driver.findElement(By.xpath("//input[@name='date_end']/following-sibling::span")).click();
				driver.findElement(By.xpath("//div[3]//div[@class='datepicker-days']/table[@class='table-condensed']//th[@class='prev']")).click();
				driver.findElement(By.xpath("//div[3]//td[contains(@class,'today')]/following-sibling::td[1]")).click();
				
			}
			else{
				System.out.println("Discount Percentage Invalid");
			}
		}
		if(couponType.equals("Fixed Amount")){
			this.CouponName.clear();
			this.CouponName.sendKeys(couponName);
			this.CouponCode.clear();
			this.CouponCode.sendKeys(couponCode);
			Select couponTypeDropdown = new Select(driver.findElement(By.id("input-type")));
			couponTypeDropdown.selectByValue("F");
			this.Discount.clear();
			this.Discount.sendKeys(String.valueOf(discount));
			this.ProductDesc.clear();
			this.ProductDesc.sendKeys(productDesc);
			driver.findElement(By.xpath("//input[@name='date_start']/following-sibling::span")).click();
			driver.findElement(By.xpath("//td[contains(@class,'today')]")).click();
			driver.findElement(By.xpath("//input[@name='date_end']/following-sibling::span")).click();
			driver.findElement(By.xpath("//div[3]//div[@class='datepicker-days']/table[@class='table-condensed']//th[@class='prev']")).click();
			driver.findElement(By.xpath("//div[3]//td[contains(@class,'today')]/following-sibling::td[1]")).click();
		}
		this.Save.click();
	}
	
	

}
