package com.falcon.driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;
import com.falcon.excelutils.Xls_Reader;
import com.falcon.mysql.MySqlutilities;
import com.falcon.reports.Reports;
import com.falcon.utils.UtilityMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class TestBase 
{
	
	static long responseTime;
	
	
	public static String globalApplicationId; //Added on 03-June-2021 AVOKA
	public static TreeMap<String, String> getMapData; //Added on 27-July-2021
	
	public static long stockspage,stockend;
	protected float ExecutionTime;
	
	protected float ExecutionTimelogin;
	public static String startTime,endTime;
	public static String atTime1;
	public static String atTime2;
	
	public static String myParentWindow; //Added on 24-Mar-2021
	
	public static String  ChkOperation; //Added on 25-Mar-2021
	public static String AuthOperation; //Added on 25-Mar-2021
	public static String OtherOperations; //Added on 08-Apr-2021
	
	public static String GlobalUserId; //Added on 23-Feb-2022
	
	//Declaring global variables
	public static WebDriver driver;
	//public static WiniumDriver driver1; // Added on 27-MAY-2019 for sync web and desktop
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());

	public static int globalIterationNumber; //Added on 18-Mar-2021 Start
	
	public static String rootPath = System.getProperty("user.dir");
	public static Properties OR;
	public static Properties OR1; //AddedPP on 03-APR-2019
	public static File f1;
	public static File f2;  //AddedPP on 03-APR-2019
	public static FileInputStream file; 
	public static FileInputStream file1; 
	//Global variable Added on 13-MAY-2019 Start
	public static String env;
	public static String dev;
	public static String os1;
	public static String ver;
	public static String browsr;
	public static String testCaseName;
	public static int myCapture = 0; //Added on 24-JUNE-2019 4DOC
	public static String[] str = new String[10000]; //Added on 24-JUNE-2019 4DOC
	public static String imageLocation; //Added on 24-JUNE-2019 4DOC
	//Global variable Added on 13-MAY-2019 End
	
	//Message Code
	public static String Currenttime;
	public static String globalMessageCode;
	public static String globalMessageRequestToVerify;
	public static String globalMessageRequestToVerify1;
	public static String globalMessageTypeToVerify;
	public static String globalMessageTypeAuditToVerify;
	public static String globalMessageTypeAuditToVerifyApprove;
	public static String globalMessageTypeAuditToVerifyApproveWithDesc;
	public static String globalMessageTypeCheckerPoolToVerify;
	
	//Reject
	public static String globalMessageTypeAuditToVerifyReject;
	public static String globalMessageTypeAuditToVerifyRejectWithDesc;

	//Field 
	public static String globalFieldCode;
	public static int NoOfLines;
	public static String globalFieldRequestToVerify;
	public static String globalFieldAuditToVerify;
	public static String globalFieldAuditToVerify1;
	public static String globalFieldCheckerPoolToVerify;
	public static String globalFieldCheckerPoolToVerify1;
	public static String globalFieldToVerify;
	public static String globalFieldAuditToVerifyApprove;
	public static String globalFieldAuditToVerifyApproveExp;
	public static String globalFieldAuditToVerifyReject;
	public static String globalFieldAuditToVerifyReject4M;
	
	//Message Creation
	public static String globalDealNumber;
	
	public static Reports report;

	public static Xls_Reader datatable = null;
	public static String testcaseid, testcasename, testdescription, testscriptname, diff;
	
	public static String PageName, ResponseTime, TimeSamp;
	
	public static int getTestScriptRowNum, getTestScriptRowNumstatic, rowcount; 

	public static int getTestScriptRowNum1, getTestScriptRowNumstatic1, rowcount1; 
	
	public static long startclass, start, endclass, end;	
	public static String atTime;
	public static DesiredCapabilities cap;
	public static boolean webDriverIntialized = false; // Added on 27-MAY-2019 for sync web and desktop
	public static String runningDriver; // Added on 27-MAY-2019 for sync web and desktop


	//Common
	public static ArrayList<String> gridRowData;

	//Static segment to initialize variables and objects
	static 
	{
		//Calender
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		TestBase.loadProperiesFile();
		logger.info("Static Initalization done");
		UtilityMethods.createReportFolderStructure(); // AddedPP on 02-Apr-2019
		
	/*// Commented on 15-MAR-2021 start
		try {
			UtilityMethods.generateXMLDynamically();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ///* Commented on 15-MAR-2021 start
		*/
		/*try {
			UtilityMethods.GenerateXMLSuite();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//htmlReporter = new ExtentHtmlReporter(rootPath+"/Report/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"+UtilityMethods.getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan()+".html"); //UpdatedPP 02-Apr-2019 //Commented on 23-APR-2019 AfterDemo
		//htmlReporter.config().setDocumentTitle("ASPEN - Execution Reports"); //PPAdded on 04-APR-2019 //Commented on 23-APR-2019 AfterDemo
		//htmlReporter.config().setReportName("Build-Regv2.0"); //PPAdded on 04-APR-2019 //Commented on 23-APR-2019 AfterDemo
		//extent = new ExtentReports(); //Commented on 23-APR-2019 AfterDemo
		//extent.attachReporter(htmlReporter); //Commented on 23-APR-2019 AfterDemo

		//extent = new ExtentReports(rootPath+"/Report/"+UtilityMethods.getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan()+".html", false); //Commented on 23-APR-2019 AfterDemo
		//logger.info("Loading extent report"); //Commented on 23-APR-2019 AfterDemo

		datatable = new Xls_Reader(rootPath+getPropertyValue("xlsPath"), rootPath+getPropertyValue("xlsTestDataPath") ); //Update on 06-MAY-2019
		logger.info("Loading xls Object");

		//report = new Reports(); //Added on 23-APR-2019 AfterDemo
		//logger.info("Loading Report Object");
		//exlr = new Excel_Reader();
	}

	@BeforeClass
	public void BeforeClass()
	{
		System.out.println("BeforeClass");
		/*
		startclass = System.currentTimeMillis();
		htmlReporter = new ExtentHtmlReporter(rootPath+"/Report/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"+UtilityMethods.getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan()+".html"); //UpdatedPP 02-Apr-2019
		htmlReporter.config().setDocumentTitle("ASPEN - Execution Reports"); //PPAdded on 04-APR-2019
		htmlReporter.config().setReportName("Build-Regv2.0"); //PPAdded on 04-APR-2019
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		 */
		//typeOfDevices = exlr.getExcelData(getPropertyValue("xlsPath"), getPropertyValue("xlsTypeOfDeviceSheetname"));
	}

	public static ThreadLocal<String> testName = new ThreadLocal<>();

	
	@BeforeMethod
	//public void BeforeMethod(ITestContext result)
	public void BeforeMethod(Method method, Object[] testData)
	{
		
		System.out.println("===BEFORE METHOD===");
		start = System.currentTimeMillis();
		atTime1 = UtilityMethods.getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan(); //PPAdded on 03-APR-2019
		testName.set(method.getName()+" ON "+testData[0].toString().toUpperCase()+" *** "+testData[1].toString().toUpperCase()+" *** "+testData[2].toString().toUpperCase()+" *** "+testData[3].toString().toUpperCase()+" *** "+testData[4].toString().toUpperCase());
		
		//test = extent.startTest(result.getName());
		//test.log(LogStatus.INFO, result.getName()+ "test started");
		// To Initialize global variable Added on 13-MAY-2019 START
		
		System.out.println("((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
		//System.out.println(method.getName()+" ON IE");
		System.out.println(method.getName());
		
		System.out.println(testData[0].toString());
		System.out.println(testData[1].toString());
		System.out.println(testData[2].toString());
		System.out.println(testData[3].toString());
		System.out.println(testData[4].toString());
		System.out.println("((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
		
		
		//Added on 18-Mar-2021 Start
		globalIterationNumber = extractIntForIteration(testData[0].toString());
		//Added on 18-Mar-2021 End

		myCapture = 0; // Added on 24-JUNE-2019 4DOC

		this.testCaseName = method.getName();
		this.env = testData[0].toString();
		this.dev = testData[1].toString();
		this.os1 = testData[2].toString();
		this.ver = testData[3].toString();
		this.browsr = testData[4].toString();
		// To Initialize global variable Added on 13-MAY-2019 END
	}

	//Added on 13-MAY-2019 to reuse this variable where ever needed START 
	// Getters
	public static String gettestCaseName() 
	{
		return testCaseName;
	}
	public static String getEnvironment() 
	{
		return env;
	}
	public static String getDevice() 
	{
		return dev;
	}
	public static String getOS1() 
	{
		return os1;
	}
	public static String getOSVersion() 
	{
		return ver;
	}
	public static String getBrowser() 
	{
		return browsr;
	}
	//Added on 13-MAY-2019 to reuse this variable where ever needed END

	@AfterMethod
	public void AfterMethodExecuteThis(ITestResult result)
	{

		logger.info("AfterMethod started execution for generating report");
		logger.info("result.getName() : "+result.getName());
		//logger.info("result.getTestName() : "+ result.getTestName());
		//testExecutionResult(result);

		end = System.currentTimeMillis();

		System.out.println((end - start) / 1000f + " seconds");
		logger.info("Total time (in Seconds) is :"+(end - start) / 1000f+" for method "+result.getName()) ;

		//PPAdded On 03-APR-2019 Start
		//Added this due to UnreachableBrowserException
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//PPAdded On 03-APR-2019 Start
		//driver.quit(); 
		//extent.endTest(test); Commented as this is applicable for old extent reports


		//extent.flush(); 
		//extent.close(); //PPSH added Again removed
		//driver.quit();
	}

	@AfterMethod
	//Added on 06-Apr-2021 Start
	public void flushh() throws InvalidFormatException, IOException, InterruptedException
	{
		System.out.println("After method invoked !@#$%#@#$#####################################################");
		Reports.writeExcelReport(TestBase.gettestCaseName(), getEnvironment(), getDevice(), getOS1(), getOSVersion(), getBrowser());
		Reports.writeExtentReport(); //Added on 06-Apr-2021
		
		Reports.writeExcelReportResponseTimes("", "", "");
		//Reports.writeExcelReportResponseTimes();
	}
	//Added on 06-Apr-2021 End


	//Setup SELENIUM property based on type of Browser
	//Setup SELENIUM property based on type of Browser
	public static void setupSelenium(String Environment,String OS, String Device, String BrowserType,String Version)
	{

		//BrowserType = "IE"; //Added for ICICIMWCustom 27-Nov-2020
		
		System.out.println("Environment is: "+Environment);
		System.out.println("OS is: "+OS);
		System.out.println("Device is: "+Device);
		System.out.println("BrowserType is: "+BrowserType);
		System.out.println("OS Version is: "+Version);
		//if(Environment.equalsIgnoreCase("Desktop")) //Added for ICICIMWCustom 27-Nov-2020
		if(Environment.contains("DataSet")) //Added for ICICIMWCustom 27-Nov-2020
		{
			try
			{
				
				if(Device.equalsIgnoreCase("Mobile") )
				{
					
					// Added on 14-June-2021 PP Start
					if(BrowserType.equalsIgnoreCase("Chrome") & Device.equalsIgnoreCase("Mobile") & OS.equalsIgnoreCase("Android"))
					{
						logger.info(BrowserType+" setup done");
						initalizeDriver(BrowserType);
						logger.info(BrowserType+" driver initialized");
					}
					// Added on 14-June-2021 PP End
					
					// Added on 14-June-2021 PP Start
					else if(BrowserType.equalsIgnoreCase("Safari") & Device.equalsIgnoreCase("Mobile") & OS.equalsIgnoreCase("iOS"))
					{
						logger.info(BrowserType+" setup done");
						initalizeDriver(BrowserType);
						logger.info(BrowserType+" driver initialized");
					}
				
					// Added on 14-June-2021 PP End
				}
				else
				{
					if(BrowserType.equalsIgnoreCase("Chrome"))
					{
						System.setProperty(getPropertyValue("ChromeKey"), rootPath+getPropertyValue("ChromePath")); //Update on 06-MAY-2019
						logger.info(BrowserType+" setup done");
						//driver = new ChromeDriver(options);
						initalizeDriver(BrowserType);
						logger.info(BrowserType+" driver initialized");
					}
					else if(BrowserType.equalsIgnoreCase("Firefox"))
					{
						System.setProperty(getPropertyValue("FFKey"), rootPath+getPropertyValue("FFPath")); //Update on 06-MAY-2019
						logger.info(BrowserType+" setup done");
						initalizeDriver(BrowserType);
						logger.info(BrowserType+" driver initialized");
					}
					else if(BrowserType.equalsIgnoreCase("IE"))
					{
						System.setProperty(getPropertyValue("IEKey"), rootPath+getPropertyValue("IEPath")); //Update on 06-MAY-2019
						logger.info(BrowserType+" setup done");
						initalizeDriver(BrowserType);
						logger.info(BrowserType+" driver initialized");


					}
					else if(BrowserType.equalsIgnoreCase("IEEdge"))
					{
						System.setProperty(getPropertyValue("IEEdgeKey"), rootPath+getPropertyValue("IEEdgePath")); //Update on 06-MAY-2019
						logger.info(BrowserType+" setup done");
						initalizeDriver(BrowserType);
						logger.info(BrowserType+" driver initialized");
					}
					else
					{
						System.setProperty(getPropertyValue("ChromeKey"), rootPath+getPropertyValue("ChromePath")); //Update on 06-MAY-2019
						logger.info(BrowserType+" setup done");
						initalizeDriver(BrowserType);
						logger.info(BrowserType+ "driver initialized");
					}
				}
			}
			catch(Exception e)
			{
				logger.error("Driver initialization failed due to "+e.getMessage());
				//test.log(Status.ERROR, "Driver initialization failed due to : "+e.getMessage());
				Assert.fail("Driver initialization failed due to : "+e.getMessage());

			}	
		}

	}
	// Initialize driver based on Browser type 
	@SuppressWarnings("deprecation")
	public static void initalizeDriver(String BrowserType)
	{

		try
		{
			
			// Added on 14-June-2021 PP Start
			if(getDevice().equalsIgnoreCase("Mobile"))
			{
				if (BrowserType.equalsIgnoreCase("Chrome") & getDevice().equalsIgnoreCase("Mobile") & getOS1().equalsIgnoreCase("Android"))
				{
					
					DesiredCapabilities caps = DesiredCapabilities.android();
					caps.setCapability("deviceName","Samsung Galaxy S8 GoogleAPI Emulator");
					caps.setCapability("deviceOrientation", "portrait");
					caps.setCapability("browserName", "Chrome");
					caps.setCapability("platformVersion", "8.1");
					caps.setCapability("platformName","Android");
					caps.setCapability("username", getPropertyValue("usernameSouceLab"));
					caps.setCapability("accesskey", getPropertyValue("accessKeySouceLab"));
					caps.setCapability("name","SampleTest");
					caps.setCapability("build", "Build11");

					caps.setCapability("idleTimeout", "90");
					caps.setCapability("newCommandTimeout", "90");
					//Emusim devices have Simulator/Emulator in the name

					driver = new RemoteWebDriver(
					new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"),caps
					);
				}
				
				
				else if(BrowserType.equalsIgnoreCase("Safari") & getDevice().equalsIgnoreCase("Mobile") & getOS1().equalsIgnoreCase("iOS"))
				{
					MutableCapabilities capabilities = new MutableCapabilities();
					capabilities.setCapability("browserName", "Safari");
					capabilities.setCapability("platformName", "iOS");
					capabilities.setCapability("platformVersion", "14.3");
					capabilities.setCapability("deviceName", "iPhone 11 Pro Max Simulator");
					capabilities.setCapability("username", getPropertyValue("usernameSouceLab"));
					capabilities.setCapability("accesskey", getPropertyValue("accessKeySouceLab"));
					capabilities.setCapability("name","SampleTest");
					capabilities.setCapability("build", "Build11");

					capabilities.setCapability("idleTimeout", "90");
					capabilities.setCapability("newCommandTimeout", "90");
					//Emusim devices have Simulator/Emulator in the name

					driver = new RemoteWebDriver(
					new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"),capabilities
					//https://oauth-infotechdemoacc1-9bdd0:a5fc16d7-6cab-451b-9f64-089e65ff9b9a@ondemand.eu-central-1.saucelabs.com:443/wd/hub
					);

				}
				
				/*
				
				DesiredCapabilities caps = DesiredCapabilities.iphone();
				caps.setCapability("deviceName","iPad Pro (12.9 inch) (3rd generation) Simulator");
				caps.setCapability("deviceOrientation", "portrait");
				caps.setCapability("platformVersion","14.3");
				caps.setCapability("platformName", "iOS");
				caps.setCapability("browserName", "Safari");
				*/
				
				// Added on 14-June-2021 PP End
			}
			else
			{
				
				if(getDevice().equalsIgnoreCase("iPad"))
				{
					if(BrowserType.equalsIgnoreCase("Safari") & getDevice().equalsIgnoreCase("iPad") & getOS1().equalsIgnoreCase("iOS"))
					{
						DesiredCapabilities caps = DesiredCapabilities.iphone();
						caps.setCapability("deviceName","iPad Pro (12.9 inch) (3rd generation) Simulator");
						caps.setCapability("deviceOrientation", "portrait");
						caps.setCapability("platformVersion","14.3");
						caps.setCapability("platformName", "iOS");
						caps.setCapability("browserName", "Safari");
						
						caps.setCapability("username", getPropertyValue("usernameSouceLab"));
						caps.setCapability("accesskey", getPropertyValue("accessKeySouceLab"));
						caps.setCapability("name","SampleTest");
						caps.setCapability("build", "Build11");

						caps.setCapability("idleTimeout", "90");
						caps.setCapability("newCommandTimeout", "90");
						//Emusim devices have Simulator/Emulator in the name

						driver = new RemoteWebDriver(
						new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"),caps
						//https://oauth-infotechdemoacc1-9bdd0:a5fc16d7-6cab-451b-9f64-089e65ff9b9a@ondemand.eu-central-1.saucelabs.com:443/wd/hub
						);
						
					}
				}
				else
				{
					if(BrowserType.equalsIgnoreCase("Chrome"))
					{
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--disable-notifications");
						//options.addArguments("--disable-popup-blocking"); //Added on 02-Nov-2021 Not working
						//options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking")); //Added on 02-Nov-2021
						//options.addArguments("user-data-dir=C:\\Users\\36582\\AppData\\Local\\Google\\Chrome\\User Data"); //Added on 02-Nov-2021
						//driver = new ChromeDriver(options); Commented on 02-Nov-2021
						
						// Added on 02-Nov-2021 Start
						DesiredCapabilities capabilities = new DesiredCapabilities();
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					    driver = new ChromeDriver(capabilities);
					    // Added on 02-Nov-2021 End
					    
						webDriverIntialized = true; // Updated on 27-MAY-2019 for sync web and desktop
						driver.manage().window().maximize();
						runningDriver = "Web"; // Added on 27-MAY-2019 for sync web and desktop
					}
					else if(BrowserType.equalsIgnoreCase("Firefox"))
					{
						driver = new FirefoxDriver();
						webDriverIntialized = true; // Updated on 27-MAY-2019 for sync web and desktop
						driver.manage().window().maximize();
						runningDriver = "Web"; // Added on 27-MAY-2019 for sync web and desktop
					}
					else if(BrowserType.equalsIgnoreCase("IE"))
					{
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						capabilities.setCapability("requireWindowFocus", true);
						capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true); //Added on 24-Mar-2021
						capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); //Added on 24-Mar-2021
						capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT); //Added on 24-Mar-2021
						driver = new InternetExplorerDriver(capabilities);
						//driver = new InternetExplorerDriver();
						webDriverIntialized = true; // Updated on 27-MAY-2019 for sync web and desktop
						driver.manage().window().maximize();
						runningDriver = "Web"; // Added on 27-MAY-2019 for sync web and desktop
					}
					else if(BrowserType.equalsIgnoreCase("IEEdge"))
					{
						driver = new EdgeDriver();
						webDriverIntialized = true; // Updated on 27-MAY-2019 for sync web and desktop
						driver.manage().window().maximize();
						runningDriver = "Web"; // Added on 27-MAY-2019 for sync web and desktop
					}
				}
			}
			
		}
		catch(Exception e)
		{
			logger.error("Driver initialization failed due to "+e.getMessage());
			//test.log(Status.ERROR, "Driver initialization failed due to : "+e.getMessage());
			Assert.fail("Driver initialization failed due to : "+e.getMessage());
		}
	}

	public static void deleteAllCookies()
	{
		try
		{
			getDriver().manage().deleteAllCookies();
			logger.info("All Cookies deleted successfully");
		}
		catch(Exception e)
		{
			Assert.fail("Cookies deletion failed due to : "+e.getMessage());
		}
	}
	
	
	//Added on 18-Mar-2021 Start
	public static int extractIntForIteration(String popuptext) 
	{
		popuptext=popuptext.replaceAll("[^\\d]", " ");
		popuptext=popuptext.trim();
		popuptext=popuptext.replaceAll(" +", " ");
		if(popuptext.equals(" "))
			return -1;
		
		return Integer.parseInt(popuptext);
	
	}
	//Added on 18-Mar-2021 End
	
	//To load all the values into object memory from property file
	public static void loadProperiesFile()
	{
		// General configuration
		OR = new Properties();
		f1 = new File(rootPath+"/Config/Config.properties");
		try {
			file = new FileInputStream(f1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			OR.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Log4j configuration
		f1 = new File(rootPath+"/Config/log4j.properties");
		try {
			file = new FileInputStream(f1);

			PropertyConfigurator.configure(file);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			OR.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//PPAdded on 03-APR-2019 Start
		// Runner configuration
		OR1 = new Properties();
		f2 = new File(rootPath+"/Config/Runner.properties");
		try {
			file1 = new FileInputStream(f2);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			OR1.load(file1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//PPAdded on 03-APR-2019 End
	}

	// To retrieve property value based on input
	public static String getPropertyValue(String value)
	{
		String catchValue = null;
		try
		{
			catchValue = OR.getProperty(value);
		}
		catch(Exception e)
		{
			logger.error("Exception when reading property value : "+e.getMessage());
			//test.log(Status.ERROR, "Exception when reading property value : "+e.getMessage()); //Commented on 23-APR-2019 AfterDemo
			Assert.fail("Exception when reading property value : "+e.getMessage());
		}
		return catchValue;		
	}


	//PPAdded on 03-APR-2019 Start
	// To retrieve property value based on input
	public static String getRunnerPropertyValue(String value)
	{
		String catchValue = null;
		try
		{
			catchValue = OR1.getProperty(value);

		}
		catch(Exception e)
		{
			logger.error("Exception when reading property value : "+e.getMessage());
			//test.log(Status.ERROR, "Exception when reading property value : "+e.getMessage()); //Commented on 23-APR-2019 AfterDemo
			Assert.fail("Exception when reading property value : "+e.getMessage());
		}
		return catchValue;		
	}
	//PPAdded on 03-APR-2019 Start


	//AddedPP on 03-APR-2019 Start
	public static void setProperyValue(String sProperty, String sValue) throws IOException
	{
		logger.info("===========setProperyValue Start=================");
		logger.info("sProperty : "+sProperty);
		logger.info("sValue : "+sValue);
		//f2 = new File(rootPath+"/Config/Runner.properties"); 
		OR1.setProperty(sProperty, sValue);
		FileOutputStream fileOut = new FileOutputStream(f2); 
		OR1.store(fileOut, "Updating "+sProperty+" Property with value "+sValue);
		logger.info("Updating "+sProperty+" Property with value "+sValue);
		fileOut.close();
		logger.info("===========setProperyValue End=================");
	}
	//AddedPP on 03-APR-2019 End

	// Write list of data into file
	public static void PBufferWriter(String FilePath, List<String> records) throws IOException
	{
		File FileName = new File (FilePath);
		FileWriter fileWriter = new FileWriter(FileName, true);
		BufferedWriter buffer = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(buffer);

		if(FileName.exists() == false)
		{
			FileName.createNewFile();
			//System.out.println("File has been creared");
		}
		{
			//System.out.println("File Already exists.");
			for (String record: records) {
				//printWriter.println(record);
			}
			printWriter.flush();
		}
		printWriter.close();
	}


	public static String getScreenshot(String screenShotName)
	{
		// Updated on 27-MAY-2019 for sync web and desktop START
		if(runningDriver == "Web") // Added on 27-MAY-2019
		{
			if(screenShotName.equalsIgnoreCase(""))
			{
				screenShotName = "blank@";
			}
			File image = ((TakesScreenshot)TestBase.getDriver()).getScreenshotAs(OutputType.FILE);
			//String imageLocation = rootPath+"/Screenshots/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"; //PPUpdated on 03-APR-2019 Commented on 24-JUNE-2018 4DOC
			//imageLocation = rootPath+"/Screenshots/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"+testCaseName+"_"+getEnvironment()+"/"; //Added on 24-JUNE-2018 4DOC Commented on 04Sept2020JIOGIT
			//imageLocation = rootPath+"/Screenshots/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"+testCaseName+"_"+getEnvironment()+"/"+getBrowser()+"/"; //Added on 24-JUNE-2018 4DOC Added on 04Sept2020JIOGIT //Added for ICICIMWCustom 27-Nov-2020
			imageLocation = rootPath+"/Screenshots/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"+testCaseName+"_"+getEnvironment()+"/"; //Added on 24-JUNE-2018 4DOC Added on 04Sept2020JIOGIT //Added for ICICIMWCustom 27-Nov-2020
			System.out.println("imageLocation : "+imageLocation);
			String actualImageName = imageLocation+screenShotName+UtilityMethods.getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan()+".png";
			System.out.println("actualImageName : "+actualImageName);
			File destFile = new File(actualImageName);
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileUtils.copyFile(image, destFile);

			} catch (IOException e) {
				e.printStackTrace();
			}

			str[myCapture] = actualImageName; //Added on 24-JUNE-2019 4DOC
			myCapture = myCapture + 1; //Added on 24-JUNE-2019 4DOC

			return actualImageName;
		}
		else // Added on 27-MAY-2019
		{
			if(screenShotName.equalsIgnoreCase(""))
			{
				screenShotName = "blank";
			}
			File image = ((TakesScreenshot)TestBase.getDriver()).getScreenshotAs(OutputType.FILE); // Updated on 27-MAY-2019
			String imageLocation = rootPath+"/Screenshots/"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"/"; //PPUpdated on 03-APR-2019
			System.out.println("imageLocation : "+imageLocation);
			String actualImageName = imageLocation+screenShotName+UtilityMethods.getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan()+".png";
			System.out.println("actualImageName : "+actualImageName);
			File destFile = new File(actualImageName);
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileUtils.copyFile(image, destFile);

			} catch (IOException e) {
				e.printStackTrace();
			}

			//str[myCapture] = actualImageName; //Added and commented on 24-JUNE-2019 4DOC
			//myCapture = myCapture + 1; //Added and commented on 24-JUNE-2019 4DOC

			return actualImageName;
		}
		// Updated on 27-MAY-2019 for sync web and desktop END


	}
	
	@BeforeSuite
	public static void beforeSuite() throws Exception
	{
		
		if(getPropertyValue("RecordingNeeded").equalsIgnoreCase("Yes"))
		{
			UtilityMethods.startRecording();
			System.out.println("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG STAAAAAAAAAAAAAART");
			logger.info("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG STAAAAAAAAAAAAAART");
		}
		else
		{
			System.out.println("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG NOT NEEDED");
			logger.info("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG NOT NEEDED");
		}
		

	}
	
	@AfterSuite
	public static void afterSuite() throws Exception
	{
		//MySqlutilities.writeReportToMySQL();
		//System.out.println("Test Results posted successfully in to the MYSqldb");
		if(getPropertyValue("RecordingNeeded").equalsIgnoreCase("Yes"))
		{
			UtilityMethods.stopRecording();
			System.out.println("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG END");
			logger.info("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG END");
		}
		else
		{
			System.out.println("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG NOT NEEDED");
			logger.info("RECORDINGGGGGGGGGGGGGGGGGGGGGGGGG NOT NEEDED");
		}
		

	}

	public void testExecutionResult(ITestResult result)
	{

		logger.info("===testExecutionResult Start===");
		logger.info(result.getStatus());
		logger.info(ITestResult.SUCCESS);

		//PPAdded on 03-APR-2019 Start
		// Added to handle excel report issue
		if(rowcount < 0 || Integer.toString(rowcount) == null) // Updated AfterDemo on 17-APR-2019
		{
			rowcount = 0;
		}

		// Updated AfterDemo on 17-APR-2019 Start - To resolve issue of header replaced with status if exception come
		if(rowcount == 0)
		{
			rowcount = rowcount + 1;
		}
		// Updated AfterDemo on 17-APR-2019 End - To resolve issue of header replaced with status if exception come

		//PPAdded on 03-APR-2019 End


		if(result.getStatus() == ITestResult.SUCCESS)

		{

			//test.log(Status.PASS, result.getName()+ " test is Pass"); //Commented on 23-APR-2019 AfterDemo
			logger.info(result.getName()+ " test is Pass");
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Status",rowcount+1, "PASS");
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"ExecutedAt",rowcount+1, atTime);
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"StartTime",rowcount+1, Long.toString(start));
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"EndTime",rowcount+1, Long.toString(end));
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Diff",rowcount+1, String.valueOf((end - start) / 1000f));
			//test.pass(MarkupHelper.createLabel(result.getName()+" test is passed", ExtentColor.GREEN)); //Commented on 23-APR-2019 AfterDemo
			/*
			String screen = getScreenshot("");
			//test.log(LogStatus.PASS, test.addScreenCapture(screen));
			try {
				test.addScreenCaptureFromPath(screen, "Screen title");
				logger.info("Screen shot captured from path : "+screen);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 */
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			//test.log(Status.SKIP, result.getName()+" test is skipped and skip resoan is :-"+result.getThrowable()); //Commented on 23-APR-2019 AfterDemo
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Status",rowcount+1, "SKIP");
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"ExecutedAt",rowcount+1, atTime);
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"StartTime",rowcount+1, Long.toString(start));
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"EndTime",rowcount+1, Long.toString(end));
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Diff",rowcount+1, String.valueOf((end - start) / 1000f));
			logger.info(result.getName()+" test is skipped and skip resoan is :-"+result.getThrowable());
			//test.skip(MarkupHelper.createLabel(result.getName()+" test is failed", ExtentColor.GREY)); //Commented on 23-APR-2019 AfterDemo
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			//test.log(Status.FAIL, result.getName()+" test is failed and fail resoan is : *** "+result.getThrowable()+" ***"); //Commented on 23-APR-2019 AfterDemo

			logger.info(result.getName()+" test is failed &nbsp; <br /> &nbsp; and fail resoan is :-"+result.getThrowable());
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Status",rowcount+1, "FAIL");
			//test.fail(MarkupHelper.createLabel(result.getName()+" test is failed", ExtentColor.RED)); //Commented on 23-APR-2019 AfterDemo
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"ExecutedAt",rowcount+1, atTime);
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"StartTime",rowcount+1, Long.toString(start));
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"EndTime",rowcount+1, Long.toString(end));
			//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Diff",rowcount+1, String.valueOf((end - start) / 1000f));
			/*
			String screen = getScreenshot("");
			//test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			try {
				test.addScreenCaptureFromPath(screen, "TitleHere1");
				logger.info("Screen shot captured from path : "+screen);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 */
		}
		else if(result.getStatus() == ITestResult.STARTED)
		{
			//test.log(Status.INFO, result.getName()+" test is started"); //Commented on 23-APR-2019 AfterDemo
			logger.info(result.getName()+" test is started");
		}

		logger.info("===testExecutionResult End===");

	}



	public static boolean getTestCaseDetailsBasedOnScriptName(String SheetName, String TestScriptName)
	{

		logger.info("===getTestCaseDetailsBasedOnScriptName Started====");
		boolean TestScriptPresent = false; 
		for(int r=1; r < datatable.getRowCount(SheetName)+1;r++)
		{
			for(int c=0; c< datatable.getColumnCount(SheetName);c++)
			{
				System.out.println("Values of datatable: "+datatable.getCellData(SheetName, c, r));

				if(datatable.getCellData(SheetName, c, r).equalsIgnoreCase(TestScriptName))
				{
					logger.info(datatable.getCellData(SheetName, c, r)+" Test Case is present to execute.");
					TestScriptPresent = true;
					logger.info(TestScriptName+" script name details found");
					break;
				}
				else
				{
					//logger.info(datatable.getCellData("TestCases", c, r)+" Test Case is NOT present to execute.");
					continue;
				}
			}
		}
		return TestScriptPresent;
	}


	public static int getTestCasePositionBasedOnScriptName(String SheetName, String TestScriptName)
	{
		logger.info("===getTestCasePositionBasedOnScriptName Started====");	
		int TestScriptPresentInt = 0; 
		for(int r=1; r < datatable.getRowCount(SheetName)+1;r++)
		{
			for(int c=0; c< datatable.getColumnCount(SheetName);c++)
			{
				//System.out.println("Values of datatable: "+datatable.getCellData(SheetName, c, r)); Commented on 04Sept2020JIOGIT

				if(datatable.getCellData(SheetName, c, r).equalsIgnoreCase(TestScriptName))
				{
					logger.info(datatable.getCellData(SheetName, c, r)+" Test Case is present to execute.");
					TestScriptPresentInt = r;
					break;
				}
				else
				{
					//logger.info(datatable.getCellData("TestCases", c, r)+" Test Case is NOT present to execute.");
					continue;
				}
			}
		}
		return TestScriptPresentInt;
	}

	@AfterSuite (alwaysRun = true)
	public void endTest()
	{

		endclass = System.currentTimeMillis();
		System.out.println((endclass - startclass) / 1000f + " seconds");
		logger.info("Total time for class (in Seconds) is :"+(endclass - startclass) / 1000f);
		UtilityMethods.copyExcelReportToSharedLocation();

		logger.info("After Class Setting RunCount");
		try {

			logger.info("********************************"); //AddedPP on 03-APR-2019
			logger.info(getRunnerPropertyValue("runcount")); //AddedPP on 03-APR-2019

			int value = Integer.parseInt(getRunnerPropertyValue("runcount")); //UpdatedPP on 03-APR-2019
			value = value + 1; 	
			setProperyValue("runcount",Integer.toString(value));

			//driver.close(); // Commented on 21-APR-2019 AfterDemo
			//extent.flush(); // Commented on 21-APR-2019 AfterDemo

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("After Class Setting RunCount Exception : "+e.getMessage()); //AddedPP on 03-APR-2019
		}

		//driver.quit(); 
		//extent.endTest(test);
		//extent.flush(); 
		//extent.close(); //PPSH Commneted
	}

	public static WebDriver getDriver()
	{
		return driver;
	}

	/*
	 //Commented on 05-Dec-2020 start
	//Added on 13-MAY-2019 Start
	@DataProvider(name="EnvironMentDeviceOSVersionAndBrowser",parallel=false)
	public Object[][] getDataFromDataprovider() throws Exception
	{
		
		Object[][] arrayObject =Xls_Reader.getExcelDataInArrayForProcessing(rootPath+getPropertyValue("xlsPath"),getPropertyValue("xlsTypeOfDeviceSheetname"));//Updated on 06-MAY-2019
		return arrayObject;
	}
	//Commented on 05-Dec-2020 End
	 */
	
	@DataProvider(name="EnvironMentDeviceOSVersionAndBrowser",parallel=false)
	public Object[][] getDataFromDataprovider(Method s) throws Exception
	{
		
		String methodName  = s.getName();
		//String className = s.getDeclaringClass().getSimpleName();
		
		
		//System.out.println("methodName : "+methodName);
		//System.out.println("className "+className);
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(methodName);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		
		int noOfDataSets = Xls_Reader.getNoOfDataSetTobeExecutedForTestCase(methodName);
		System.out.println("************************************->"+noOfDataSets);
		
		Object[][] arrayObject =Xls_Reader.getExcelDataInArrayForProcessingCustomizedForNoOfDataSetIteration(rootPath+getPropertyValue("xlsPath"),getPropertyValue("xlsTypeOfDeviceSheetname"),noOfDataSets);//Updated on 06-MAY-2019  
		
		return arrayObject;
	}
	
	public static void launchURL(String sURL)
	{
		long startTime = System.currentTimeMillis();
		driver.get(sURL);
		long endTime = System.currentTimeMillis();

		long totalTime = endTime - startTime;
		System.out.println("Total Page Load Time: " + totalTime);
		System.out.println("Total Page Load Time in seconds: " + totalTime/1000);
		String pageName="Login page";
		responseTime=totalTime/1000;
		
	}
	//Added on 13-MAY-2019 END
	// To test method created main method
	public static void main(String[] args) throws Exception 
	{
		/*
		setupSelenium("Chrome");
		driver.get(getPropertyValue("BaseURL"));
		logger.info(getPropertyValue("BaseURL")+" URL launched");
		 */
	}

}
