package com.suitecrm.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.suitecrm.pages.LoginPage;
import com.suitecrm.testbase.TestBase;
import com.suitecrm.utilities.Utility;

public class LoginPageTestWithDataProvider extends TestBase{

	LoginPage login;

	public LoginPageTestWithDataProvider() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		login = new LoginPage();
	}
	
	@Test(dataProvider = "testdata")
	public void loginTestd(String uname,String pass)
	{
		login.login(uname, pass);
	}
	@DataProvider(name="testdata")
	public Object[][] data()
	{
		return Utility.readExcel("Login");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}