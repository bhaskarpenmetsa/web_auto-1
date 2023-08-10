package com.falcon.utils;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.internal.PackageUtils;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlMethodSelector;
import org.testng.xml.XmlScript;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

import com.falcon.driver.TestBase;
import com.falcon.excelutils.Excel_Reader;
import com.falcon.excelutils.Xls_Reader; 

public class UtilityMethods extends TestBase {
	public static WebDriver driver; // PPSH changed public from private

	public String childWindow;
	public String parentWindow;
	public String parentWinId;

	private static final Logger logger = Logger.getLogger(UtilityMethods.class.getName()); // Added 02-Apr-2019
	static String rootpath = System.getProperty("user.dir"); // PPAdded 02-Apr-2019
	public static File outputFile; // PPAdded 02-Apr-2019
	public static String sourcePath; // PPAdded 02-Apr-2019
	public static String destPath; // PPAdded 02-Apr-2019
	
	public UtilityMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	public UtilityMethods() {
		System.out.println("Added without driver constructure to generated dynamix TestNG.xml");
	}
	
	// Browser Methods
	public void goBack() {
		driver.navigate().back();
	}

	//=======================
	// Window Methods
	//=======================
	
	// To get list of windows id's
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	// To get parent window id
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	
	// To goto first child window - for doing operation on it
	public void switchToFirstChildWindowForDoingChildOperations() {
		try {
			Set<String> winIds = getWindowHandles();
			Iterator<String> it = winIds.iterator();
			parentWindow = it.next();
			System.out.println("MY REF WIN COUNT : "+winIds.size());
			System.out.println("parentWindow : "+parentWindow);
			for (int i = 0; i < winIds.size(); i++) {
				childWindow = it.next();
				System.out.println("childWindow : "+childWindow);
				if (!parentWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					logger.info("Navigation to First child window id : " + childWindow + " - Passed");
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Navigation to window id : " + childWindow + " - Failed", e);
			Assert.fail("Navigation to window id : " + childWindow + " - Failed due to :-> " + e.getMessage());
		}
	}
	
	// To goto first child window - for doing operation on it
		public void switchToFirstChildWindowForDoingChildOperations_AVOKA() 
		{
			String child_window = null;
			try {
					String parent=getDriver().getWindowHandle();
					
					System.out.println("PARENT "+parent);
					Set<String>s=getDriver().getWindowHandles();
					Iterator<String> I1= s.iterator();
					while(I1.hasNext())
					{
						child_window=I1.next();
						if(!parent.equals(child_window))
						{
							getDriver().switchTo().window(child_window);
							System.out.println("CHILD :"+getDriver().getTitle());
							while(!getDriver().getTitle().equalsIgnoreCase("Teachers - My Application"))
							{
								Thread.sleep(2000);
								System.out.println("WAITING.........................");
							}
							System.out.println("CHILD WINDOW TITLE:"+getDriver().getTitle());
							System.out.println("CHILD WINDOW URL:"+getDriver().getCurrentUrl());
						logger.info("Navigation to First child window id : " + child_window + " - Passed");	
						}
					}
			} catch (Exception e) 
			{
				logger.error("Navigation to window id : " + child_window + " - Failed", e);
				Assert.fail("Navigation to window id : " + child_window + " - Failed due to :-> " + e.getMessage());
			}
		}
	
	
		//To goto first child window - for doing operation on it - Some time 1 or some time 2 window comes for ICICI Project Hence customized
		public void switchToFirstChildWindowForDoingChildOperations_IciciProjectOLD() {
			try {
				Set<String> winIds = getWindowHandles();
				System.out.println("TOTAL WINDOW LIST SIZE :"+winIds.size());
				if(winIds.size() == 1)
				{
					Iterator<String> it = winIds.iterator();
					parentWindow = it.next();
					System.out.println("Parent : "+parentWindow);
					logger.info("PIYUSH PLEASE CHECK HERE (parentWindow): IF " +parentWindow);
					getDriver().switchTo().window(parentWindow);
					
				}
				else
				{
					Thread.sleep(1500); //added on 23-Mar-2021
					Iterator<String> it = winIds.iterator();
					parentWindow = it.next();
					System.out.println("TOTAL WINDOW LIST SIZE : "+winIds.size());
					System.out.println("parentWindow : "+parentWindow);
					logger.info("PIYUSH PLEASE CHECK HERE (parentWindow) ELSE: " +parentWindow);
					Thread.sleep(1500); //added on 23-Mar-2021
					childWindow = it.next();
					System.out.println("childWindow : "+childWindow);
					logger.info("PIYUSH PLEASE CHECK HERE (childWindow) ELSE: " +childWindow);
					/*for (int i = 0; i < winIds.size(); i++) {
						childWindow = it.next();
						System.out.println("childWindow : "+childWindow);
						if (!parentWindow.equals(childWindow)) {*/
							getDriver().switchTo().window(childWindow);
							logger.info("Navigation to First child window id : " + childWindow + " - Passed");
							/*break;
						}
					}*/
				}
			} catch (Exception e) {
				logger.error("Navigation to window id : " + childWindow + " - Failed", e);
				Assert.fail("Navigation to window id : " + childWindow + " - Failed due to :-> " + e.getMessage());
			}
		}
		
		//Added on 24-Mar-2021 Start
		public static String getDate_yymmdd() 
		{
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
			LocalDateTime now = LocalDateTime.now();
			return dtf.format(now);
			
		}
		//Added on 24-Mar-2021 End
		
		//Added on 24-MAr-2021 Start
		//To goto first child window - for doing operation on it - Some time 1 or some time 2 window comes for ICICI Project Hence customized
		public void switchToFirstChildWindowForDoingChildOperations_IciciProject() 
		{
			try {
					Set<String> winIds = getWindowHandles();
					Iterator<String> it = winIds.iterator();
					System.out.println("TOTAL WINDOW LIST SIZE : "+winIds.size());
					System.out.println("parentWindow : "+myParentWindow); //parentWindow changed to my myParentWindow on 28-Mar-2021
					for (int i = 0; i < winIds.size(); i++) 
					{
						Thread.sleep(1500);
						childWindow = it.next();
						System.out.println("childWindow : "+childWindow);
						if (!TestBase.myParentWindow.equals(childWindow)) 
						{
							getDriver().switchTo().window(childWindow);
							logger.info("Navigation to First child window id : " + childWindow + " - Passed");
							break;
						}
					}
			} catch (Exception e) {
				logger.error("Navigation to window id : " + childWindow + " - Failed", e);
				Assert.fail("Navigation to window id : " + childWindow + " - Failed due to :-> " + e.getMessage());
			}
		}
		//Added on 24-MAr-2021 End
		
		// This method is not recommended to use - Hence ignore this
	public void switchToSpecificWindowByIndex(int index) {
		LinkedList<String> winIds = new LinkedList<String>(getWindowHandles());
		try {
			driver.switchTo().window(winIds.get(index));
			logger.info("Navigation to window id : " + index + " - Passed");
		} catch (Exception e) {
			logger.error("Navigation to window id : " + index + " - Failed or index id is not present", e);
			Assert.fail("Navigation to window id : " + index + " - Failed or index id is not present" + e.getMessage());
		}

	}
	
	
	
	

	// To goto parent window - for doing operation
	public void switchToParentWindow() {
		try {
			
			Thread.sleep(3500); //Updated on 10-Apr-2021 3500 from 2500
			
			LinkedList<String> windowId = new LinkedList<String>(getWindowHandles());
			
			System.out.println("PAR windowId size: "+windowId.size());
			
			driver.switchTo().window(windowId.get(0)); //Commented on 09-Apr-2021
			
			logger.info("PIYUSH PLEASE CHECK HERE (parentWindow): CHECKER/AUTH PAR : " +windowId.get(0));
			
			logger.info("Navigation to parent window with id : "+parentWinId+" - Passed");
			}
			catch (Exception e) {
			logger.error("Navigation to parent window with id : "+parentWinId+" - failed :", e);
			Assert.fail("Navigation to parent window with id : "+parentWinId+" - failed due to :-> " + e.getMessage());
		}
	}
	
	public void switchToParentWindow_AVOKA() 
	{
		try {
				String parent=getDriver().getWindowHandle();
				getDriver().switchTo().window(parent);
				System.out.println("MAIN PARENT"+driver.getTitle());
				logger.info("Navigation to parent window with id : "+parentWinId+" - Passed");
			}
			catch (Exception e) {
			logger.error("Navigation to parent window with id : "+parentWinId+" - failed :", e);
			Assert.fail("Navigation to parent window with id : "+parentWinId+" - failed due to :-> " + e.getMessage());
		}
	}
	
	// To go to parent window with closing all the child windows
	public void switchToParentWindowWithAllClildClose() throws InterruptedException {
		
		Thread.sleep(6500); //Added on 28-Mar-2021
		LinkedList<String> winIds = new LinkedList<String>(getWindowHandles());
		String parent = driver.getWindowHandle();
		System.out.println("PARENT : "+parent);
		logger.info("PIYUSH PLEASE CHECK HERE (parentWindow): CHECKER/AUTH 000 " +parent);
		
		System.out.println("winIds.SIZE :"+winIds.size());
		

		for (int i = 1; i < winIds.size(); i++) {
			try {
				Thread.sleep(3500); //Added on 28-Mar-2021 //Un-Commented on 10-Apr-2021
				
				if(!parent.equals(winIds.get(i)))
				{
					System.out.println("WINDOWID NOT EQUAL PIYUSH");
				}
				else
				{
					System.out.println("WINDOWID EQUAL PIYUSH");
				}
				driver.switchTo().window(winIds.get(i));
				Thread.sleep(2000); //Added on 10-Apr-2021
				logger.info("PIYUSH PLEASE CHECK HERE (child): CHECKER/AUTH 111" +winIds.get(i));
				driver.close();
				logger.info("Closing child window " + winIds.get(i) + "- Passed");	
			
			} catch (Exception e) {
				logger.error("No such window id : " + winIds.get(i) + " exists to close hence - Failed", e);
				Assert.fail("No such window id : " + winIds.get(i) + " exists to close hence - Failed or exception: "
						+ e.getMessage());
			}
		}
		Thread.sleep(3500); //Added on 28-Mar-2021 //UnCommented on 10-Apr-2021
		switchToParentWindow();
		
	}
	
	
	// To go to parent window with closing all the child windows
		public void switchToParentWindowWithAllClildCloseAUTH() throws InterruptedException {
			
			Thread.sleep(6500); //Added on 28-Mar-2021
			LinkedList<String> winIds = new LinkedList<String>(getWindowHandles());
			String parent = driver.getWindowHandle();
			System.out.println("PARENT : "+parent);
			logger.info("PIYUSH PLEASE CHECK HERE (parentWindow): CHECKER/AUTH 000 " +parent);
			
			System.out.println("winIds.SIZE :"+winIds.size());
			

			for (int i = 1; i < winIds.size(); i++) {
				try {
					Thread.sleep(3500); //Added on 28-Mar-2021 //Un-Commented on 10-Apr-2021
					
					if(parent.equals(winIds.get(i)))
					{
						System.out.println("WINDOWID NOT EQUAL PIYUSH");
					}
					else
					{
						System.out.println("WINDOWID EQUAL PIYUSH");
					}
					
					driver.switchTo().window(winIds.get(i));
					Thread.sleep(2000); //Added on 10-Apr-2021
					logger.info("PIYUSH PLEASE CHECK HERE (child): CHECKER/AUTH 111" +winIds.get(i));
					driver.close();
					logger.info("Closing child window " + winIds.get(i) + "- Passed");	
				} catch (Exception e) {
					logger.error("No such window id : " + winIds.get(i) + " exists to close hence - Failed", e);
					Assert.fail("No such window id : " + winIds.get(i) + " exists to close hence - Failed or exception: "
							+ e.getMessage());
				}
			}
			Thread.sleep(3500); //Added on 28-Mar-2021 //UnCommented on 10-Apr-2021
			switchToParentWindow();
			
		}
	
	
	// To go to parent window with closing all the child windows
		public void switchToParentWindowWithAllClildCloseRND() throws InterruptedException {
			
			String testWindow;
			Thread.sleep(2500);
			LinkedList<String> winIds = new LinkedList<String>(getWindowHandles());
			Iterator<String> it = winIds.iterator();
			System.out.println("TOTAL WINDOW LIST SIZE : "+winIds.size());
			for (int i = 0; i < winIds.size(); i++) 
			{
				Thread.sleep(2500);
				testWindow = it.next();
				System.out.println("testWindow : "+testWindow);
				driver.switchTo().window(testWindow);
				System.out.println("TITLE HERE "+driver.getTitle());
			}
		}
	
		
		
		public static void getAllCookiesAndSavetoFile()
		{
			  // create file named Cookies to store Login Information		
	        File file = new File("Cookies.data");							
	        try		
	        {	  
	            // Delete old file if exists
				file.delete();		
	            file.createNewFile();			
	            FileWriter fileWrite = new FileWriter(file);							
	            BufferedWriter Bwrite = new BufferedWriter(fileWrite);							
	            // loop for getting the cookie information 		
	            	
	            // loop for getting the cookie information 		
	            for(Cookie ck : getDriver().manage().getCookies())							
	            {			
	                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));																									
	                Bwrite.newLine();             
	            }			
	            Bwrite.close();			
	            fileWrite.close();	
	            
	        }
	        catch(Exception ex)					
	        {		
	            ex.printStackTrace();			
	        }		
	    }		
	
		
		@SuppressWarnings("deprecation")
		public static void useCookiesInSession()
		{
			try{			
			     
		        File file = new File("Cookies.data");							
		        FileReader fileReader = new FileReader(file);							
		        BufferedReader Buffreader = new BufferedReader(fileReader);							
		        String strline;			
		        while((strline=Buffreader.readLine())!=null){									
		        StringTokenizer token = new StringTokenizer(strline,";");									
		        while(token.hasMoreTokens()){					
		        String name = token.nextToken();					
		        String value = token.nextToken();					
		        String domain = token.nextToken();					
		        String path = token.nextToken();					
		        Date expiry = null;					
		        		
		        String val;			
		        if(!(val=token.nextToken()).equals("null"))
				{		
		        	expiry = new Date(val);					
		        }		
		        Boolean isSecure = new Boolean(token.nextToken()).								
		        booleanValue();		
		        Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);			
		        System.out.println(ck);
		        getDriver().manage().addCookie(ck); // This will add the stored cookie to your current session					
		        }		
		        }		
		        }catch(Exception ex){					
		        ex.printStackTrace();			
		        }		
		}

	/*
	 * public static void GenerateXMLSuite() throws IOException,
	 * ClassNotFoundException {
	 * 
	 * XmlSuite suite = new XmlSuite(); suite.setName("Regression suite");
	 * suite.setParallel(ParallelMode.NONE);
	 * suite.addListener("com.falcon.utils.RetryListenerClass");
	 * suite.setPreserveOrder(true);
	 * 
	 * XmlTest testRT = new XmlTest(suite); testRT.setName("Regression test");
	 * testRT.setParallel(ParallelMode.NONE); testRT.setPreserveOrder(true);
	 * 
	 * ArrayList<XmlClass> Alxmlclass = new ArrayList<XmlClass>(); List<XmlInclude>
	 * includedMethodsRT = new ArrayList<XmlInclude>();
	 * 
	 * String[] testClasses =
	 * PackageUtils.findClassesInPackage("com.falcon.TestCases", new
	 * ArrayList<String>(), new ArrayList<String>()); ClassLoader cl =
	 * Thread.currentThread().getContextClassLoader(); List<String> classMetodList =
	 * new ArrayList<String>();
	 * 
	 * //File mf = new File(rootPath+"\\TestFileWhichIsFromExcelInFormOfList.txt");
	 * //Scanner msc = new Scanner(mf); //System.out.println(
	 * "TestCase_Id|Testcase_Name|Test_Desc|TestScript_Name|Testcase_Exec_Indicator"
	 * ); ArrayList<String> fromExcelFlags = new ArrayList<String>();
	 * 
	 * /* This is to read from text file pipe (|) separated data and generating list
	 * System.out.println("This is from text file - Need to read it from Excel");
	 * while(msc.hasNextLine()) { String line = msc.nextLine(); String []
	 * testcaseData = line.split("\\|",5); String TestCase_Id= testcaseData[0];
	 * String Testcase_Name= testcaseData[1]; String Test_Desc= testcaseData[2];
	 * String TestScript_Name= testcaseData[3]; String Testcase_Exec_Indicator=
	 * testcaseData[4]; fromExcelFlags.add(TestCase_Id+"|"+Testcase_Exec_Indicator);
	 * System.out.println(TestCase_Id+"|"+Testcase_Exec_Indicator); }
	 * 
	 * //This is to read from Excel file and generating list
	 * System.out.println("%%%%%%%%%Reading TestCase Data%%%%%%%%%%%"); String
	 * excellocation = rootpath+getPropertyValue("xlsPath"); String sheetName =
	 * getPropertyValue("xlsTestCasesSheetname"); String dataSets[][]; Xls_Reader
	 * xlsR = new Xls_Reader(excellocation, sheetName); int colCount =
	 * xlsR.getColumnCount(sheetName); int rowCount = xlsR.getRowCount(sheetName);
	 * System.out.println("colCount "+colCount);
	 * System.out.println("rowCount "+rowCount); Excel_Reader obj_Exc_Read = new
	 * Excel_Reader(); dataSets = obj_Exc_Read.getExcelData(excellocation,
	 * sheetName); for(int i=0;i < rowCount-1 ;i++) { //The below one is the
	 * reference for traking the fourth column in excel for Run_Status
	 * fromExcelFlags.add(dataSets[i][0]+"|"+dataSets[i][4]); } System.out.
	 * println("=====================TestCase Data from EXCEL with Flag==========");
	 * System.out.println(fromExcelFlags); System.out.
	 * println("===============Classes and Methods detail from Package============"
	 * ); for(int i =0; i < testClasses.length ; i++) {
	 * 
	 * @SuppressWarnings("rawtypes") Class currentClass =
	 * cl.loadClass(testClasses[i]);
	 * System.out.println("currentClass : "+currentClass); Alxmlclass.add(new
	 * XmlClass(currentClass)); Set<Method> allMethods =
	 * ClassHelper.getAvailableMethods(currentClass); Iterator<Method> iMethods =
	 * allMethods.iterator(); testRT.setClasses(Alxmlclass);
	 * testRT.setPreserveOrder(true); while (iMethods.hasNext()) { Method eachMethod
	 * = iMethods.next(); System.out.println("eachMethod : "+eachMethod); Test test
	 * = eachMethod.getAnnotation(Test.class); if (test != null) {
	 * classMetodList.add(eachMethod.getDeclaringClass().getName()+"|"+eachMethod.
	 * getName()); if(fromExcelFlags.contains(eachMethod.getName()+"|Yes")) {
	 * includedMethodsRT.add(new XmlInclude(eachMethod.getName())); }
	 * Alxmlclass.get(i).setIncludedMethods(includedMethodsRT); } } }
	 * XmlMethodSelector methodSelector = new XmlMethodSelector(); XmlScript
	 * xmlScrpt = new XmlScript(); xmlScrpt.setLanguage("beanshell");
	 * xmlScrpt.setScript("System.setProperty(\"testng.metrics.logo\",\r\n" +
	 * "             \"https://www.3i-infotech.com/wp-content/uploads/2018/02/3I-infotech-logo-109-x-100.png\");return true;"
	 * ); methodSelector.setScript(xmlScrpt);
	 * 
	 * List<XmlMethodSelector> methodSelectors = Lists.newArrayList();
	 * methodSelectors.add(methodSelector);
	 * suite.setMethodSelectors(methodSelectors);
	 * 
	 * System.out.
	 * println("=====================generated testNG.xml====================");
	 * System.out.println(suite.toXml()); FileWriter writer = new FileWriter(new
	 * File("testng1.xml")); writer.write(suite.toXml()); writer.flush();
	 * writer.close(); System.out.println("testNG.xml generated at : "+new
	 * File("testng1.xml").getAbsolutePath());
	 * 
	 * }
	 */

	public static ArrayList<String> checkReportFileExistance()
	{
		ArrayList<String> listOfReportFiles = new ArrayList<String>();
		String folderName = rootPath+"\\target\\surefire-reports";
		File[] listFiles = new File(folderName).listFiles();
		for (int i = 0; i < listFiles.length; i++) {
		    if (listFiles[i].isFile()) {
		        String fileName = listFiles[i].getName();
		        if (fileName.startsWith("emailable-") | fileName.startsWith("Metrics-2020")) 
		        {
		            System.out.println("found file" + " " + fileName);
		            listOfReportFiles.add(fileName);
		        }
		    }
		}
		return listOfReportFiles;
	}
	
	 public static TreeMap<String, String> getCellDataBasedOnRowValue(String xpath) 
	 {
	    	
		 System.out.println("===getCellDataBasedOnRowValue===");
		 	//Declaring TreeMap to Retrieve orderHeadings with ColumnNumber 
			TreeMap<String, String> orderHeadingMap = new TreeMap<String, String>();
			////table[contains(@id,'datatable-sticky')]//th
			List<WebElement> orderHeadingElements = getDriver().findElements(By.xpath(xpath));
			System.out.println("Örder Heading Count : "+orderHeadingElements.size());
			for(int i=1; i<=orderHeadingElements.size()/2;i++)
			{
				WebElement headingOfTable = getDriver().findElement(By.xpath(xpath+"["+i+"]"));
				String heading = headingOfTable.getText();
				String headCol = headingOfTable.getAttribute("data-column");
				orderHeadingMap.put(heading, headCol);
			}
			System.out.println("OrderHeadingMap");
			System.out.println(orderHeadingMap);
			return orderHeadingMap;
	    }
	 
	 public static String getTableHeadingColumnNumberBasedOnColumnName(String headingKey)
	 {
		System.out.println("===getTableHeadingColumnNumberBasedOnColumnName===");
		System.out.println(headingKey);
		 TreeMap<String, String> orderHeadingMap = new TreeMap<String, String>();
		 String headingColumNo ;
		 //String xpath = "//table[contains(@id,'datatable')]//th";
		 String xpath = "//table[contains(@id,'datatable')]//th[not(contains(@style,'display: none;'))]";
		 
		 
		 orderHeadingMap = getCellDataBasedOnRowValue(xpath);
		 headingColumNo =  orderHeadingMap.get(headingKey);
		 System.out.println("headingColumNo"+headingColumNo);
		 return headingColumNo;
	 }
	 
	 
	 public static String getTableHeadingColumnNumberBasedOnColumnNameSplecialTrade(String headingKey)
	 {
		System.out.println("===getTableHeadingColumnNumberBasedOnColumnNameSplecialTrade===");
		System.out.println(headingKey);
		 TreeMap<String, String> orderHeadingMap = new TreeMap<String, String>();
		 String headingColumNo ;
		 //String xpath = "//table[contains(@id,'datatable')]//th";
		 String xpath = "//div[contains(@id,'datatable-custScroll')]//th[not(contains(@style,'display: none;'))]";
		 
		 
		 orderHeadingMap = getCellDataBasedOnRowValue(xpath);
		 headingColumNo =  orderHeadingMap.get(headingKey);
		 System.out.println("headingColumNo"+headingColumNo);
		 return headingColumNo;
	 }
	 
	 public static String getTableHeadingColumnNumberBasedOnColumnNameMyPortfolio(String headingKey)
	 {
		System.out.println("===getTableHeadingColumnNumberBasedOnColumnNameMyPortfolio===");
		System.out.println(headingKey);
		 TreeMap<String, String> orderHeadingMap = new TreeMap<String, String>();
		 String headingColumNo ;
		 //String xpath = "//table[contains(@id,'datatable')]//th";
		 String xpath = "//table[@id='1_VIEW_SIH_StockInHand']//th[not(contains(@style,'display: none;'))]";
		 
		 
		 orderHeadingMap = getCellDataBasedOnRowValue(xpath);
		 headingColumNo =  orderHeadingMap.get(headingKey);
		 System.out.println("headingColumNo"+headingColumNo);
		 return headingColumNo;
	 }
	 
	 public static Long getSetofTranId(String transIdNo) 
	 {
		 
		 Long MaxTranNo = null;
		 ArrayList<Long> a = new ArrayList<Long>();
		 //List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr"));
		 List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]"));
			System.out.println("TOTAL ROW SIZE IS : "+totalRows.size());
			
			for(int i=2;i<totalRows.size()-2;i++)
			{
				/*
				System.out.println("RROOWW : "+totalRows.get(i).getText());
				if(totalRows.get(i).getText().contains("11130397"))
				{
					System.out.println("RETURN "+i);
				}
				*/
				int j = Integer.parseInt(transIdNo);
				WebElement b = getDriver().findElement(By.xpath("//table[contains(@id,'datatable')]//tr["+i+"]//td["+j+"]"));
				System.out.println("Row "+i+" Col"+j+" : "+b.getText());
				int len = b.getText().length();
				System.out.println("LEN "+len);
				if(len <= 0) 
				{
					break;
				}
				
				a.add(Long.parseLong(b.getText()));
				
				
			}
		System.out.println("MAX NUMBER IS : "+Collections.max(a));
		MaxTranNo = Collections.max(a);
		return MaxTranNo;
	 }
	 
	 public static Long getSetofOrderNos(String transIdNo) 
	 {
		 
		 Long MaxTranNo = null;
		 ArrayList<Long> a = new ArrayList<Long>();
		 //List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr"));
		 List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]"));
			System.out.println("TOTAL ROW SIZE IS : "+totalRows.size());
		
			
			////table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]//td//a[text()='TCS EQ']
			//TrdNetQtySecurity
			
			for(int i=2;i<totalRows.size()-2;i++)
			{
				/*
				System.out.println("RROOWW : "+totalRows.get(i).getText());
				if(totalRows.get(i).getText().contains("11130397"))
				{
					System.out.println("RETURN "+i);
				}
				*/
				int j = Integer.parseInt(transIdNo);
				WebElement b = getDriver().findElement(By.xpath("//table[contains(@id,'datatable')]//tr["+i+"]//td["+j+"]"));
				System.out.println("Row "+i+" Col"+j+" : "+b.getText());
				int len = b.getText().length();
				System.out.println("LEN "+len);
				if(len <= 0) 
				{
					break;
				}
				if(len > 20)
				{
					continue;
				}
				a.add(Long.parseLong(b.getText()));
				
				
			}
		System.out.println("MAX NUMBER IS : "+Collections.max(a));
		MaxTranNo = Collections.max(a);
		return MaxTranNo;
	 }
	 
	 public static Long getSetofOrderNosCustomized(String transIdNo, String disSymbol, String securityCode) 
	 {
		 
		 Long MaxTranNo = null;
		 ArrayList<Long> a = new ArrayList<Long>();
		 //List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr"));
		 List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]"));
			System.out.println("TOTAL ROW SIZE IS : "+totalRows.size());
		
			
			////table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]//td//a[text()='TCS EQ']
			//TrdNetQtySecurity
			
			for(int i=2;i<totalRows.size()-2;i++) //UnCommented22Sept2020
			//for(int i=2;i<totalRows.size();i++) //Added22Sept2020
			{
				
				System.out.println("IIIIIIIIIIIIIIIIIIIIII"+i);
				System.out.println("SIZEEEEEEEEEEEEEEEE"+totalRows.size());
				/*
				System.out.println("RROOWW : "+totalRows.get(i).getText());
				if(totalRows.get(i).getText().contains("11130397"))
				{
					System.out.println("RETURN "+i);
				}
				*/
				int j = Integer.parseInt(transIdNo);
				WebElement b = getDriver().findElement(By.xpath("//table[contains(@id,'datatable')]//tr["+i+"]//td["+j+"]"));
				
				int lenforIf = b.getText().length();
				System.out.println("lenforIf "+lenforIf);
				if(lenforIf > 20)
				{
					System.out.println("Continue");
					continue;
				}
				else
				{
					int disSymb = Integer.parseInt(disSymbol);
					WebElement c = getDriver().findElement(By.xpath("//table[contains(@id,'datatable')]//tr["+i+"]//td["+disSymb+"]"));
					
					System.out.println("Row "+i+" Col"+j+" : "+b.getText());
					System.out.println("Row "+i+" Col"+j+" : "+c.getText());
					
					int len = b.getText().length();
					int lenc = c.getText().length();
					
					System.out.println("LEN "+len);
					System.out.println("LEN "+lenc);
					
					if(len <= 0 | lenc <=0) 
					{
						System.out.println("Break");
						break;
					}
//					if(len > 20 | lenc > 20) Commented on 22Sept2020
//					{
//						continue;
//					}
					if(c.getText().contains(securityCode))
					{
						a.add(Long.parseLong(b.getText()));	
						System.out.println("Adding "+b.getText());
					}
				}
				
			}
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(a);
			logger.info(a);
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("MAX NUMBER IS : "+Collections.max(a));
		MaxTranNo = Collections.max(a);
		return MaxTranNo;
	 }
	 
	 public static boolean getTradedSequrityAvailabilityStatusInMyTrade(String Product, String DisplaySymbol, String compareSymbol)
	 {
		boolean tradeAvailabilityStatus = false;
		List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]"));
		System.out.println("TOTAL ROW SIZE IS : "+totalRows.size());
		for(int i=1;i<totalRows.size()-2;i++)
		{
			int j = Integer.parseInt(Product);
			int k = Integer.parseInt(DisplaySymbol);
			WebElement p = getDriver().findElement(By.xpath("//table[contains(@id,'datatable')]//tr["+i+"]//td["+j+"]"));
			WebElement ds = getDriver().findElement(By.xpath("//table[contains(@id,'datatable')]//tr["+i+"]//td["+k+"]"));
			System.out.println("Row "+i+" Col"+j+" : "+p.getText());
			System.out.println("Row "+i+" Col"+j+" : "+ds.getText());
			logger.info("Row "+i+" Col"+j+" : "+p.getText());
			logger.info("Row "+i+" Col"+j+" : "+ds.getText());
			
			String Prod = p.getText();
			String ProdSymb = ds.getText();
			String ProdPlusSymb = Prod+" "+ProdSymb;
			
			System.out.println("ProdPlusSymb : "+ProdPlusSymb);
			logger.info("ProdPlusSymb : "+ProdPlusSymb);
			if(ProdPlusSymb.equals(compareSymbol))
			{
				System.out.println("BIIIIIIIIIIG YES");
				break;
			}
			else
			{
				System.out.println("CONTINUE");
				continue;
			}
			
		}
		
		return tradeAvailabilityStatus;
		 
	 }
	 
	 public static boolean getTradedSequrityAvailabilityStatusInMyPortfolio(String Product, String DisplaySymbol, String compareSymbol)
	 {
		boolean tradeAvailabilityStatus = false;
		List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[@id='1_VIEW_SIH_StockInHand']//tr[not(contains(@style,'display: none;'))]"));
		System.out.println("TOTAL ROW SIZE IS : "+totalRows.size());
		for(int i=1;i<totalRows.size()-2;i++)
		{
			int j = Integer.parseInt(Product);
			int k = Integer.parseInt(DisplaySymbol);
			WebElement p = getDriver().findElement(By.xpath("//table[@id='1_VIEW_SIH_StockInHand']//tr["+i+"]//td["+j+"]"));
			WebElement ds = getDriver().findElement(By.xpath("//table[@id='1_VIEW_SIH_StockInHand']//tr["+i+"]//td["+k+"]"));
			System.out.println("Row "+i+" Col"+j+" : "+p.getText());
			System.out.println("Row "+i+" Col"+j+" : "+ds.getText());
			
			String Prod = p.getText();
			String ProdSymb = ds.getText();
			String ProdPlusSymb = Prod+" "+ProdSymb;
			
			System.out.println("ProdPlusSymb : "+ProdPlusSymb);
			
			if(ProdPlusSymb.contains((compareSymbol)))
			{
				System.out.println("BIIIIIIIIIIG YES");
				break;
			}
			else
			{
				System.out.println("CONTINUE");
				continue;
			}
			
		}
		
		return tradeAvailabilityStatus;
		 
	 }
	
	 public static String getdymanicXPath(String maxTranId, String td) 
	 {
		String dynamicXpath = null;
		List<WebElement> totalRows=getDriver().findElements(By.xpath("//table[contains(@id,'datatable')]//tr[not(contains(@style,'display: none;'))]"));
		System.out.println("TOTAL ROW SIZE IS : "+totalRows.size());
		for(int i=2;i<totalRows.size()-2;i++)
		{
			System.out.println("RROOWW : "+totalRows.get(i).getText());
			if(totalRows.get(i).getText().contains(maxTranId))
			{
				System.out.println("RETURN "+i);
				dynamicXpath = "//table[contains(@id,'datatable')]//tr["+i+"]//td["+td+"]";
				break;
			}
		}
		return dynamicXpath;
	 }
	 
	 
	 public static void testTagwise()
	 {
		 
		 String s;
		 List<WebElement> links = getDriver().findElements(By.tagName("tr"));
		 Iterator<WebElement> it = links.iterator();
		 while(it.hasNext())
		  {
			 WebElement anchor = it.next();
			 s = anchor.getText();
			 System.out.println("ROW TEXT : "+s);
		  }
	 }
	 
	 public static final String USER_DIR = "user.dir";
	 public static final String DOWNLOADED_FILES_FOLDER = "downloadFiles";
	 public static ScreenRecorder screenRecorder;
	 public static void startRecording() throws Exception {
	        File file = new File(System.getProperty(USER_DIR) + File.separator + DOWNLOADED_FILES_FOLDER);

	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int width = screenSize.width;
	        int height = screenSize.height;

	        Rectangle captureSize = new Rectangle(0, 0, width, height);

	        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	        screenRecorder = new SpecializedScreenRecorder(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
	                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
	                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, "MyVideo");
	        screenRecorder.start();

	    }

	    public static void stopRecording() throws Exception {
	        screenRecorder.stop();
	    }
	
	public static void sendExecutionReportToEmailRecipient() throws AddressException, MessagingException
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy"); // Added on 05-Dec-2018
		LocalDateTime now = LocalDateTime.now();
				
		String to = "piyush.prajapati@3i-infotech.com";
	    String subject = "Gio-3i-Infotech - POC exection Report";
	    String msg ="Email Notification";
	    final String username ="Giojit.3i.pocproject@gmail.com";
	    final String password ="GioInfotech@12";
	    
	    Properties props = new Properties();  
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.starttls.enable", "true");
	    Session session1 = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() 
	    {
	       protected PasswordAuthentication getPasswordAuthentication() 
	       {  
	       return new PasswordAuthentication(username,password);  
	       }  
	   });  
	    
	   Message message = new MimeMessage(session1);  
	   message.setFrom(new InternetAddress(username));
	   //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("gnaneshwar.raju@3i-infotech.com"));
	   message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("piyush.prajapati@3i-infotech.com"));
	   message.setSubject(subject);  
	   message.setContent(msg, "text/plain");  
	   BodyPart MessageText = new MimeBodyPart();
	 
	   
	   MessageText.setText("PFA - GioJit POC Execution Report");
	   MimeBodyPart Exec = new MimeBodyPart();
	   MimeBodyPart Execution = new MimeBodyPart();
	   MimeBodyPart Execution1 = new MimeBodyPart();
	   MimeBodyPart Execution2 = new MimeBodyPart();
	   MimeBodyPart S3Upload = new MimeBodyPart();
	   MimeBodyPart Execution4 = new MimeBodyPart();
	   //MimeBodyPart errorlog = new MimeBodyPart();
	    
	   Multipart multipart = new MimeMultipart();
	  
	   String reportFileTobeSentInEmail1;
	   String reportFileTobeSentInEmail2;
	   ArrayList<String> listOfReportFiles = new ArrayList<String>();
	   listOfReportFiles = checkReportFileExistance();
	   
	   
	   /*
	    if(FN.exists())
	   {
		   ListOfFilesDownloadedDirec = rootPath+"\\"+currentYear+"\\"+monthOfYear+"\\"+currentDate+"\\FileDownloadLog_"+getDate_yyyyMMdd()+".txt"; //Added on 02-Mar-2020 updated on 04-Mar-2020
		   System.out.println("FN.exists() : "+FN.exists());
		   DataSource src = new FileDataSource(ListOfFilesDownloadedDirec); 
		   Exec.setDataHandler(new DataHandler(src));
		   Exec.setFileName(ListOfFilesDownloadedDirec);
		   multipart.addBodyPart(Exec);
	   }
	   */
	   multipart.addBodyPart(MessageText);
	   
	   message.setContent(multipart);
	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  
	 
	   Transport.send(message);
	   System.out.println("GioJit POC Email Sent Successfully.");
	}
 	
	
	
	
	
	public static void generateXMLDynamically() throws IOException, ClassNotFoundException {
		XmlSuite suite = new XmlSuite();
		suite.setName("Regression suite");
		suite.setParallel(ParallelMode.NONE);
		suite.addListener("com.falcon.utils.RetryListenerClass");
		suite.setPreserveOrder(true);

		XmlTest testRT = new XmlTest(suite);
		testRT.setName("Falcon test");
		testRT.setParallel(ParallelMode.NONE);
		testRT.setPreserveOrder(true);

		ArrayList<XmlClass> Alxmlclass = new ArrayList<XmlClass>();

		String[] testClasses = PackageUtils.findClassesInPackage("com.falcon.TestCases", new ArrayList<String>(),
				new ArrayList<String>());
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		ArrayList<String> fromExcelFlags = new ArrayList<String>();

		// This is to read from Excel file and generating list
		System.out.println("%%%%%%%%%Reading TestCase Data%%%%%%%%%%%");
		String excellocation = rootpath + "\\ExcelData\\TestCases.xlsx";
		String TestDataLoc = rootpath + "\\ExcelData\\TestData.xlsx";
		String sheetName = "TestCases";
		String dataSets[][];
		Xls_Reader xlsR = new Xls_Reader(excellocation, TestDataLoc);
		int colCount = xlsR.getColumnCount(sheetName);
		int rowCount = xlsR.getRowCount(sheetName);
		System.out.println("colCount " + colCount);
		System.out.println("rowCount " + rowCount);
		Excel_Reader obj_Exc_Read = new Excel_Reader();
		dataSets = obj_Exc_Read.getExcelData(excellocation, sheetName);

		for (int i = 0; i < rowCount - 1; i++) {
			fromExcelFlags.add(dataSets[i][3] + "#" + dataSets[i][4]);
		}
		System.out.println("=====================TestCase Data from EXCEL with Flag==========");
		System.out.println(fromExcelFlags);
		System.out.println("===============Classes and Methods detail from Package============");
		for (int i = 0; i < testClasses.length; i++) {
			@SuppressWarnings("rawtypes")
			Class currentClass = cl.loadClass(testClasses[i]);
			// System.out.println("currentClass : "+currentClass);
			int dotLen;
			dotLen = currentClass.toString().lastIndexOf(".");
			if (fromExcelFlags.contains(currentClass.toString().substring(dotLen + 1) + "#Yes")) {
				Alxmlclass.add(new XmlClass(currentClass));
			} else {
				continue;
			}
			testRT.setClasses(Alxmlclass);
			testRT.setPreserveOrder(true);
		}
		XmlMethodSelector methodSelector = new XmlMethodSelector();
		XmlScript xmlScrpt = new XmlScript();
		xmlScrpt.setLanguage("beanshell");
		xmlScrpt.setScript("System.setProperty(\"testng.metrics.logo\",\r\n"
		//		+ "             \"https://www.3i-infotech.com/wp-content/uploads/2018/02/3I-infotech-logo-109-x-100.png\");return true;");
				+ "             \"https://www.3i-infotech.com/wp-content/uploads/2021/04/3I-infotech-logo.png\");return true;");
		methodSelector.setScript(xmlScrpt);

		List<XmlMethodSelector> methodSelectors = Lists.newArrayList();
		methodSelectors.add(methodSelector);
		suite.setMethodSelectors(methodSelectors);

		System.out.println("=====================generated testNG.xml====================");
		System.out.println(suite.toXml());
		FileWriter writer = new FileWriter(new File(rootpath + "\\TestNGDynXml"+"\\"+"testng_11.xml"));
		writer.write(suite.toXml());
		writer.flush();
		writer.close();
		System.out.println("testNG.xml generated at : " + new File("testng.xml").getAbsolutePath());
		
		//================================================
		// Create object of TestNG Class
		TestNG runner=new TestNG();

		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();

		// Add xml file which you have to execute
		suitefiles.add("TestNGDynXml//testng_11.xml"); // Provide testng.xml path which we want to execute as package

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();
		//================================================
	}

	// Verification methods
	public static synchronized boolean verifyElementIsPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
			logger.info(element.getText() + " : is displayed returning " + isDisplayed);
		} catch (Exception e) {
			logger.error("No such element : " + element + " exists hence returning " + isDisplayed, e);
			Assert.fail("No such element : " + element + " exists hence returning " + isDisplayed + "or exception"
					+ e.getMessage());
		}
		return isDisplayed;
	}

	public static synchronized boolean verifyTextInTheElement(WebElement element, String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equalsIgnoreCase(expectedText)) {
				logger.info("Element text is matched with expeted Text hence return " + flag);
				return flag = true;
			} else {
				logger.info("Element text is NOT matched with expeted Text hence return " + flag);
				return flag;
			}
		} catch (Exception e) {
			logger.error("No such element : " + element + " exists for comparing hence text returning " + flag, e);
			Assert.fail("No such element : " + element + " exists for comparing hence text returning " + flag
					+ " or exception " + e.getMessage());
		}

		return flag;
	}

	// Wait Methods
	public static void applyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Long.valueOf(getPropertyValue("implecitWait")), TimeUnit.SECONDS);
		logger.info("implecit wait as " + getPropertyValue("implecitWait") + " SECONDS applied");
	}

	@SuppressWarnings("deprecation")
	public WebDriverWait applyWebDriverWait() {
		logger.debug("");
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(getPropertyValue("explicitWait")));
		wait.pollingEvery(Long.valueOf(getPropertyValue("pollingInterval")), TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementToBeVisible(WebElement locator) {
		logger.info(locator);
		WebDriverWait wait = applyWebDriverWait();
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	
	public void waitForFrameTobeVisible(String iFrame) {
		logger.info(iFrame);
		WebDriverWait wait = applyWebDriverWait();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
	}
	

	public void waitForElementToBeClickable(WebElement locator) {
		logger.info(locator);
		WebDriverWait wait = applyWebDriverWait();
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}

	public WebElement waitForElementTillVisibleAndReturnElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(getPropertyValue("explicitWait")));
		logger.info("element found..." + element.getText());
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementTillClickableAndReturnElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(getPropertyValue("explicitWait")));
		logger.info("element found..." + element.getText());
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Generic methods
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			logger.info("element is displayed " + element);
			return true;
		} catch (Exception e) {
			logger.info(e);
			Assert.fail("Element is not displayed due to exception : " + e.getMessage());
			return false;
		}
	}

	public String readValueFromElement(WebElement element) {
		if (element == null) {
			logger.info("Web Element is null");
			return null;
		}
		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			logger.error(e);
			Assert.fail("Element is not displayed due to exception : " + e.getMessage());
			return null;
		}
		if (!displayed) {
			return null;
		}
		String text = element.getText();
		logger.info("WebElement value is : " + text);
		return text;
	}

	// Java Script Methods
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		logger.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		logger.info(script);
		return exe.executeScript(script, args);
	}

	public void HighlightElement(WebElement element) {
		executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	public void scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1]", element.getLocation().x, element.getLocation().y);
		logger.info(element);
	}

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		logger.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		logger.info(element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		logger.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public void zoomInByPercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	// Date Methods
	public static String getDate_ddMMyyyy() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public static String getDateAndTime_dd_MM_yyyy_HHmmss_TimeWithHyphan() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	public static String getTimeHHmmss() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public static String getDateAndTimeForConsoleAndLogs() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	// Added 02-Apr-2019 Start
	public static void createReportFolderStructure() {
		outputFile = new File(rootPath + "\\Report\\" + getRunnerPropertyValue("releaseVersion") + "_Run"
				+ getRunnerPropertyValue("runcount") + "_" + UtilityMethods.getDate_ddMMyyyy()); // PPUpdated on
																									// 03-APR-2019
		if (!outputFile.exists()) {
			outputFile.mkdirs();
			System.out.println("Report folder structure created : " + outputFile);
			logger.info("Report folder structure created : " + outputFile);
		} else {
			System.out.println("Report folder structure already available : " + outputFile);
			logger.info("Report folder structure already available : " + outputFile);
		}
	}

	public static void copyExcelReportToSharedLocation() {
		sourcePath = rootpath + "\\ExcelData\\TestCases.xlsx";
		destPath = rootPath + "\\Report\\" + getRunnerPropertyValue("releaseVersion") + "_Run"
				+ getRunnerPropertyValue("runcount") + "_" + UtilityMethods.getDate_ddMMyyyy(); // PPUpdated on
																								// 03-APR-2019
		System.out.println("Move directory from " + sourcePath + " to " + destPath);
		logger.info("Move directory from " + sourcePath + " to " + destPath);

		File source = new File(sourcePath);
		File dest = new File(destPath);

		try {
			FileUtils.copyFileToDirectory(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void killBrowserInstances(String browserType)
	{
		try 
		{
			System.out.println("Closing all the opened instance of : "+browserType);
			Runtime.getRuntime().exec("taskkill /F /IM "+browserType+".exe");
			System.out.println("Closing of "+browserType+" instances is done..!!");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
		// Added 02-Apr-2019 End

	// To test method created main method
	public static void main(String[] args) throws Exception {
		System.out.println("MAIN METHOD EXECUTION");
		/*
		 * String CompanyName = ""; //String FullRecall =
		 * "Domega International Ltd., Inc Issues An Alert On Undeclared Sulfites In Guangdayuan Brand Dried Sweet Potato"
		 * ; //String FullRecall =
		 * "KV Pharmaceutical Voluntarily Suspends All Shipments of its Approved Tablet-form Drugs"
		 * ; //String FullRecall = "PANOS Brands Recalls Vegan Rella Cheddar Block";
		 * //String FullRecall =
		 * "Johnson and Johnson--Merck Consumer Pharmaceuticals Company Announces Urgent Voluntary Nationwide Recall Of Infants' Mylicon Gas Relief Dye Free Drops (Simethicone-Antigas) Non-Staining Due To Possible Metal Fragments"
		 * ; String FullRecall =
		 * " ETHEX Corporation Initiated Nationwide Voluntary Recalls of Specific Lots of Five Generic Products Due to the Potential for Oversized Tablets	"
		 * ; //String FullRecall =
		 * "Consumer Alert: Undeclared Sulfites in Shad Raisins";
		 * 
		 * int CompanyNameAt = UtilityMethods.getCompanyNameBasedOnKeywords(FullRecall);
		 * 
		 * if(CompanyNameAt == 0) { CompanyName =
		 * "No Company Name Identified - Need to update manually";
		 * System.out.println(FullRecall);
		 * System.out.println("CompanyName =  "+CompanyName ); } else { CompanyName =
		 * FullRecall.substring(0, CompanyNameAt); System.out.println(FullRecall);
		 * System.out.println("CompanyName =  "+CompanyName.trim() ); }
		 */

		/*
		 * setupSelenium("Chrome"); driver.get(getPropertyValue("DrugURL"));
		 * logger.info(getPropertyValue("DrugURL")+" URL launched"); String xpath =
		 * "//table[@class='table table-bordered table-striped footable toggle-square-filled tablesorter default footable-loaded']//tbody/tr[@style='display: table-row;']"
		 * ; int noofrows = getListofRecallsCountInthePage(xpath);
		 * System.out.println("noofrows : "+noofrows);
		 * logger.info("Number of rows identified to travarse through are : "+noofrows);
		 * 
		 * String xpath1 = "//div[@class='pagination pagination-centered']//li"; int
		 * noofPages = getListofRecallsCountInthePage(xpath1);
		 * System.out.println("noofPages : "+noofrows);
		 * logger.info("Number of pages identified to travarse through are : "+noofPages
		 * );
		 * 
		 * setupSelenium("Chrome"); driver.get(getPropertyValue("DrugURLForParagraph"));
		 * String xpath2 = "//div[@class='release-text']//div[@class='col-md-9']/p"; int
		 * noofParagraphs = getListofRecallsCountInthePage(xpath2);
		 * System.out.println("noofParagraphs : "+noofParagraphs);
		 * logger.info("Number of paragraphs identified to travarse through are : "
		 * +noofParagraphs);
		 */

	}

}
