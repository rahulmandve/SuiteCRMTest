package com.suitecrm.utilities;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import org.testng.IResultMap;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtendReportListner implements IReporter{
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlsuite, List<ISuite> suites, String outputDirectory) {
		
		extent=new ExtentReports(outputDirectory+File.separator+"SuiteCRM_Extent.html",true);
		for(ISuite suite:suites)
		{
			Map<String, ISuiteResult>result=suite.getResults();
			for(ISuiteResult r:result.values())
			{
				ITestContext context=r.getTestContext();
				buildTestNodes(context.getPassedTests(),LogStatus.PASS);
				buildTestNodes(context.getFailedTests(),LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(),LogStatus.SKIP);	
						
			}
		}
		extent.flush();
	}
	
	private void buildTestNodes(IResultMap tests,LogStatus status) {
		ExtentTest test;
		if(tests.size()>0)
		{
			for(ITestResult result:tests.getAllResults())
			{
				test=extent.startTest(result.getMethod().getMethodName());
//				test.setStartedTime(getTime(result.getStartMillis()));
//				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group:result.getMethod().getGroups())
				{
					test.getTest();
				}
				if(result.getThrowable()!=null)
				{
					test.log(status,"Deatail steps");
				}else{
					test.log(status, "Test"+status.toString().toLowerCase()+"ed");
				}
				
				extent.endTest(test);
			}
		}
		
	}
	private Date getTime(long millis)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal.getTime();
	}

}
