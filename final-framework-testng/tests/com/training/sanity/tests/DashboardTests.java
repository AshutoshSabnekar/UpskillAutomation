package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.ProductReturnsPOM;
import com.training.pom.SalesOrderPOM;
import com.training.pom.DashboardPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

public class DashboardTests {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private SalesOrderPOM salesOrderPOM;
	private ProductReturnsPOM productReturnsPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private JavascriptExecutor jScript;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
				properties = new Properties();
				FileInputStream inStream = new FileInputStream("./resources/others.properties");
				properties.load(inStream);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		dashboardPOM = new DashboardPOM(driver);
		salesOrderPOM = new SalesOrderPOM(driver);
		productReturnsPOM = new ProductReturnsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		jScript = (JavascriptExecutor)driver;
		// open the browser 
		driver.get(baseUrl);
	  }
	
	@Test(priority = 1)
    public void viewOrderHistory() throws InterruptedException {
		loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.sales);
		dashboardPOM.elementClick(dashboardPOM.Orders);
		jScript.executeScript("window.scrollBy(0,600)");
		screenShot.captureScreenShot("ViewOrderHistory_OrderDetailsTable");
		boolean flag = salesOrderPOM.searchByOrderID("220");
		if(flag == false){
			System.out.println("OrderID not present");
		}
		screenShot.captureScreenShot("ViewOrderHistory_ViewOrderDetails");
    }
	
	@Test(priority = 2)
    public void editOrderHistory() throws InterruptedException {
		loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.sales);
		dashboardPOM.elementClick(dashboardPOM.Orders);
		jScript.executeScript("window.scrollBy(0,600)");
		screenShot.captureScreenShot("EditOrderHistory_OrderDetailsTable");
		boolean flag = salesOrderPOM.clickEditByOrderID("223");
		if(flag == true){
			jScript.executeScript("window.scrollBy(0,600)");
			screenShot.captureScreenShot("EditOrderHistory_EditOrderPage");
			salesOrderPOM.editFirstName("Ashutosh");
			salesOrderPOM.editLirstName("Sabnekar");
			salesOrderPOM.editTelephoneNumber("8978001723");
			screenShot.captureScreenShot("EditOrderHistory_EditedOrderPage");
			salesOrderPOM.clickContinue();
		}
		else{
			System.out.println("ReturnID not present");
		}
    }
	
	@Test(priority = 3)
    public void editReturnOrder() throws InterruptedException {
		loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.sales);
		dashboardPOM.elementClick(dashboardPOM.Returns);
		jScript.executeScript("window.scrollBy(0,500)");
		screenShot.captureScreenShot("EditReturnOrder_OrderDetailsTable");
		boolean flag = productReturnsPOM.searchByReturnID("234");
		if(flag == true){
			jScript.executeScript("window.scrollBy(0,200)");
			screenShot.captureScreenShot("EditReturnOrder_EditOrderPage");
			productReturnsPOM.editFirstName("Ashutosh");
			productReturnsPOM.editLirstName("Sabnekar");
			productReturnsPOM.editMailID("assabnek@in.ibm.com");
			screenShot.captureScreenShot("EditReturnOrder_EditedOrderPage");
			productReturnsPOM.clickSaveButton();
			jScript.executeScript("window.scrollBy(0,500)");
			screenShot.captureScreenShot("EditReturnOrder_UpdatedOrderDetailsPage");
		}
		else{
			System.out.println("ReturnID not present");
		}
    }
  

    @AfterMethod
    public void afterMethod() throws InterruptedException {
	  Thread.sleep(1000);
	  driver.quit();
  }

}
