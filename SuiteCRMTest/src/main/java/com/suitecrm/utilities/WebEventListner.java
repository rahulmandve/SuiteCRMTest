package com.suitecrm.utilities;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.support.events.WebDriverListener;

import com.suitecrm.testbase.TestBase;

public class WebEventListner extends TestBase implements WebDriverListener{
	
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
		
		if(e.getCause() instanceof AssertionError) {
		try {
			Utility.getScreenshot(driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	}

}
