package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CategoryPOM;
import com.training.pom.CouponsPOM;
import com.training.pom.CustomersPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.ProductsPOM;
import com.training.pom.SalesOrderPOM;
import com.training.readexcel.ReadExcel;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

public class Assignment3 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private ProductsPOM productsPOM;
	private CategoryPOM categoryPOM;
	private CustomersPOM customersPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private JavascriptExecutor jScript;
	
	
  @Test(dataProvider = "TestData064", enabled = false)
  public void addCategory(String categoryName, String categoryDescription, String metaTagTitle, String metaTagDescription) {
	
	  loginPOM.LoginUser("admin", "admin@123");
	  dashboardPOM.elementClick(dashboardPOM.Menu);
	  dashboardPOM.elementClick(dashboardPOM.Catalog);
	  dashboardPOM.elementClick(dashboardPOM.Categories);
	  categoryPOM.addCategoryButton();
	  categoryPOM.sendCategoryName(categoryName);
	  categoryPOM.sendCategoryDescription(categoryDescription);
	  categoryPOM.sendMataTagTitle(metaTagTitle);
	  categoryPOM.sendMetaTagDescription(metaTagDescription);
	  categoryPOM.saveButton();
	  
  }
  
  @DataProvider(name="TestData064")
  public Object[][] getDataTC64()
  {
          
	  String[][] result = new ReadExcel().getExcelData("C:/Users/AshutoshSabnekar/Desktop/TextInputExcel.xls", "UNF_064");
	  return result;
  }
  
  @Test(dataProvider = "TestData065", enabled = false)
  public void addProduct(String productName, String metaTitle, String model, String price, String quantity, String manufacturer, String category, String customerGroup,String discountQuantity, String discountPrice, String redeemPoints) {
	  	loginPOM.LoginUser("admin", "admin@123");
		dashboardPOM.elementClick(dashboardPOM.Menu);
		dashboardPOM.elementClick(dashboardPOM.Catalog);
		dashboardPOM.elementClick(dashboardPOM.Products);
		productsPOM.addProductButton();
		
		productsPOM.addGeneralDetails(productName,metaTitle);
		productsPOM.addDataDetailsExcel(model,price,quantity);
		productsPOM.addLinkDetails(manufacturer,category);
		productsPOM.addDiscountDetailsExcel(customerGroup,discountQuantity,discountPrice);
		productsPOM.addRedeempointsExcel(redeemPoints);
		productsPOM.clickSave();
  }
  
  @DataProvider(name="TestData065")
  public Object[][] getDataTC65()
  {          
	  String[][] result = new ReadExcel().getExcelData("C:/Users/AshutoshSabnekar/Desktop/TextInputExcel.xls", "UNF_065");
	  return result;
  }
  
  @Test(dataProvider = "TestData088", priority = 1)
  public void addCustomer(String customerGroup, String firstName, String lastName, String mailID, String phoneNumber, String password, String confirmPassword) {
	  	if(password.equals(confirmPassword)){
	  		loginPOM.LoginUser("admin", "admin@123");
			dashboardPOM.elementClick(dashboardPOM.Menu);
			dashboardPOM.elementClick(dashboardPOM.Customer);
			dashboardPOM.elementClick(dashboardPOM.CustomerCustomers);
			customersPOM.AddCustomerButton();
			customersPOM.selectCustomerGroup(customerGroup);
			customersPOM.addCustomerDetails(firstName, lastName, mailID, phoneNumber, password, confirmPassword);
			customersPOM.clickSaveButton();
	  	}
	  	else{
	  		System.out.println("Password and Cofrim Password did not match");
	  	}
  }
  
  @DataProvider(name="TestData088")
  public Object[][] getDataTC88()
  {          
	  String[][] result = new ReadExcel().getExcelData("C:/Users/AshutoshSabnekar/Desktop/TextInputExcel.xls", "UNF_088");
	  return result;
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		dashboardPOM = new DashboardPOM(driver);
		categoryPOM = new CategoryPOM(driver);
		productsPOM = new ProductsPOM(driver);
		customersPOM = new CustomersPOM(driver);
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
