package com.suitecrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.suitecrm.testbase.TestBase;
import com.suitecrm.utilities.Utility;

public class LoginPage extends TestBase{
	
	//page factory or Object Repo
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//button[@id='login-button']")
	WebElement loginbtn;
	
	@FindBy(xpath ="//title[text()='SuiteCRM']")
	WebElement title;
	
	@FindBy(xpath="//img[@class='image-company_logo ng-star-inserted']")
	WebElement logo;
	
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String verifyURL()
	{
		return driver.getCurrentUrl();
	}
	
	public boolean verifyLogo()
	{
		Utility.ExplicitWait(logo);
		boolean l= logo.isDisplayed();
		return l;
	}

	public String verifyTitle()
	{
		String til= driver.getTitle();
		return til;
	}
	
	public void login(String userName,String pass)
	{
		username.sendKeys(userName);
		password.sendKeys(pass);
		loginbtn.click();
		
	}
}
