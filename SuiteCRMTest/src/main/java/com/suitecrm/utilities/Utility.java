package com.suitecrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.suitecrm.testbase.TestBase;

public class Utility extends TestBase{

	
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static void ExplicitWait(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constant.EXPLICIT_WAIT));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static String getScreenshot(WebDriver driver) throws IOException {
//		String datename = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshot/"+ System.currentTimeMillis()
				+ ".png";
		File filedestination = new File(destination);
		FileUtils.copyFile(source, filedestination);

		return destination;
	}
	
	public static Object[][] readExcel(String sheetname) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(Constant.TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}
	
	public static void activeLink(String linkurl) {
		try {
			URL url = new URL(linkurl);

			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setConnectTimeout(3000);
			urlcon.connect();
			if (urlcon.getResponseCode() == 200) {
				System.out.println(linkurl + "-" + urlcon.getResponseMessage());
				log.info(linkurl + "-" + urlcon.getResponseMessage());
				log.info(linkurl + " is Working Fine.......");
			}
			if (urlcon.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out
						.println(linkurl + "-" + urlcon.getResponseMessage() + "-" + HttpURLConnection.HTTP_NOT_FOUND);
				log.info(linkurl + "-" + urlcon.getResponseMessage() + "-" + HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
