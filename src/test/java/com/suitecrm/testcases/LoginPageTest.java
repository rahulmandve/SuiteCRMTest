package com.suitecrm.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suitecrm.pages.LoginPage;
import com.suitecrm.testbase.TestBase;

public class LoginPageTest extends TestBase {

	LoginPage login;

	public LoginPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		login = new LoginPage();
	}

	@Test(priority = 1)
	public void verifyURLTest() {
		String url = login.verifyURL();
		System.out.println(url);
		Assert.assertEquals(url, "https://suite8demo.suiteondemand.com/#/Login");
	}

	@Test
	public void verifyLogoTest() {
		boolean l = login.verifyLogo();
		Assert.assertTrue(l, "Logo is Displayed on page");
	}

	@Test(priority = 3)
	public void verifyTitleTest() {
		String title = login.verifyTitle();
		Assert.assertEquals(title, "SuiteCRM");
	}

	@Test(priority = 4)
	public void loginTest() {
		login.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}