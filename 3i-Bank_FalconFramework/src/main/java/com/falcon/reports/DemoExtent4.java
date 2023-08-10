package com.falcon.reports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;
import com.falcon.actions.FalconActions;
import com.falcon.driver.TestBase;

public class DemoExtent4{


	//public WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	//Screenshot
	public static String screenShot(){

		TakesScreenshot scr = (TakesScreenshot)TestBase.driver;
		String shot = scr.getScreenshotAs(OutputType.BASE64);
		return "data:image/png;base64,"+shot;
	}

	//time stamp
	public static String timeStamp(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String stamp = sdf.format(date).replaceAll("/", "");
		return stamp;
	}
	//result folder
	public static String resultFolder(){
		File f = new File(System.getProperty("user.dir")+"\\Report\\"+timeStamp());
		String path = System.getProperty("user.dir")+"\\Report\\"+timeStamp();
		if(!f.exists())
			f.mkdirs();
		return path;
	}
	//initialize report
	public static void initializeReport(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Report//DemoExtent4Report.html");

		htmlReporter.config().setDocumentTitle("Automation report");
		htmlReporter.config().setReportName("Demo extent report by Satya");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostName", "Test host");
		extent.setSystemInfo("OS", "Windows 7");
		extent.setSystemInfo("Tester Name", "Satya");
		extent.setSystemInfo("Browser", "Chrome");
	}

	
	//flush report
		public static void flushReport(){
			extent.flush();
		}
		
		
	//start test 
		public static void startTest(String testToStart){
			test = extent.createTest(testToStart);
		}
		
	//end test
		public void endTest(){
			//extent.close();
		}
		
	//log event
		public static void logEvent(String description, String status) throws IOException{
			switch(status.toLowerCase().trim()){
			
			case "pass":
				test.log(Status.PASS, description+test.addScreenCaptureFromPath(screenShot()));
				break;
			
			case "fail":
				test.log(Status.FAIL, description+test.addScreenCaptureFromPath(screenShot()));
				break;	
				
			case "info":
				test.log(Status.INFO, description);
				break;	
				
			case "warning":
				test.log(Status.WARNING, description+test.addScreenCaptureFromPath(screenShot()));
				break;	
			
			}
		}
	/*@BeforeTest
	public void setReport()
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Report//DemoExtent4Report.html");

		htmlReporter.config().setDocumentTitle("Automation report");
		htmlReporter.config().setReportName("Demo extent report by Satya");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostName", "Test host");
		extent.setSystemInfo("OS", "Windows 7");
		extent.setSystemInfo("Tester Name", "Satya");
		extent.setSystemInfo("Browser", "Chrome");
	}

	@AfterTest
	public void endReport()
	{
		extent.flush();
	}

	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","D:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.nopcommerce.com/");
	}

	@Test
	public void titleTest()
	{
		test = extent.createTest("titleTest");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "eCommerce demo store");
	}
	@Test
	public void LogoTest()
	{
		test = extent.createTest("LogoTest");
		boolean status = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
		Assert.assertTrue(status);
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "TEST CASE FAILED IS "+result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getThrowable());

			String screenshotPath = DemoExtent4.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS "+result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASS IS "+result.getName());
			String screenshotPath = DemoExtent4.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		}
		driver.quit();
	}*/

	/*public static String getScreenshot(WebDriver driver, String screenshotname) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		//After execution, you could see a folder "FailedTestScreenshots" under src folder
		String destination = System.getProperty("user.dir")+"//Screenshots//"+screenshotname + dateName +".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}*/




}


