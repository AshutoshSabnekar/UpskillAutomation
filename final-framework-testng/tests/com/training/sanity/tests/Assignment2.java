package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CouponsPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.ProductsPOM;
import com.training.pom.SalesOrderPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Assignment2 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private JavascriptExecutor jScript;
	private DashboardPOM dashboardPOM;
	private SalesOrderPOM salesOrderPOM;
	private CouponsPOM couponsPOM;
	private ProductsPOM productsPOM;
	
  @Test (enabled = false)
  public void deleteOrderHistoryRecord() {
	  loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.sales);
		dashboardPOM.elementClick(dashboardPOM.Orders);
		jScript.executeScript("window.scrollBy(0,600)");
		screenShot.captureScreenShot("deleteOrderHistoryRecord_OrderDetailsTable");
		boolean flag = salesOrderPOM.deleteByOrderID("295");
		if(flag == false){
			System.out.println("OrderID not present");
		}
		screenShot.captureScreenShot("deleteOrderHistoryRecord_UpdatedOrderDetails");
  
  }
  
  @Test(enabled = false)
  public void addDiscountToProduct() {
	  	loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.Marketing);
		dashboardPOM.elementClick(dashboardPOM.Coupons);
		screenShot.captureScreenShot("addDiscountToProduct_CouponListTable");
		couponsPOM.addCoupon();
		couponsPOM.createCouponDetails("Gift Voucher","COVID","Percentage",20,"Blazer Girls");
		screenShot.captureScreenShot("addDiscountToProduct_UpdatedCouponListTable");
		couponsPOM.addCoupon();
		couponsPOM.createCouponDetails("Gift Voucher","COVID1","Fixed Amount",25,"Blazer Girls");
		screenShot.captureScreenShot("addDiscountToProduct_UpdatedCouponListTable");
  }
  
  @Test (priority = 3)
  public void addProduct() {
	  	loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.Catalog);
		dashboardPOM.elementClick(dashboardPOM.Products);
		productsPOM.addProductButton();
		
		productsPOM.addGeneralDetails("Blazer Girls(7-8)","Blazer for Girls");
		productsPOM.addDataDetails("BLG-112",3000,100);
		productsPOM.addLinkDetails("Smart Uniforms","School Uniform");
		productsPOM.addDiscountDetails("Premium Member",5,200);
		productsPOM.addRedeempoints(20);
		productsPOM.clickSave();
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  	driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		dashboardPOM = new DashboardPOM(driver);
		salesOrderPOM = new SalesOrderPOM(driver);
		couponsPOM = new CouponsPOM(driver);
		productsPOM = new ProductsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		jScript = (JavascriptExecutor)driver;
		// open the browser 
		driver.get(baseUrl);
  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  Thread.sleep(1000);
	  driver.quit();
  }

  @BeforeClass
  public void beforeClass() throws IOException {
	  properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
  }

}
