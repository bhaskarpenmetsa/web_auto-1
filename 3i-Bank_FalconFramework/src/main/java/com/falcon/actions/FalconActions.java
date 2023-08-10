package com.falcon.actions;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.falcon.driver.TestBase;
import com.falcon.reports.DemoExtent4;
import com.falcon.reports.Reports;
import com.falcon.utils.UtilityMethods;


public class FalconActions extends Reports

{
	public static final Logger logger = Logger.getLogger(FalconActions.class.getName());
	//public WebDriver driver;
	/*public Actions actionObj;
	public Action action;*/

	//Added on 04-APR-2019 start
	public FalconActions(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	//Added on 04-APR-2019 End

	/* To enter specified text inside web element
	@Parameters : web element and text to type */
	public static void EnterText(WebElement element, String textToType, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			//System.out.println("Enter text 1");
			waitForElementToBeVisible(element);
			//System.out.println("Enter text 2");
			//element.click(); //Commented for JEOJIT boost Commented on 28-Mar-2021
			//System.out.println("Enter text 3");
			//FalconWebElement.clearOut(element); //Commented for JEOJIT boost Commented on 28-Mar-2021
			//System.out.println("Enter text 4");
			FalconWebElement.typeTextIn(element, textToType);
			//System.out.println("Enter text 5");
			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("User Entered text into the "+"<b>"+elementDesc +"</b>"+" field", "pass"); //Commented on on 20-JUNE-2019 4DOC
			//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to enter text into the input field "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	/* To enter specified text inside web element
	@Parameters : web element and text to type */
	public static void ClearText(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	ClearText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element);
			FalconWebElement.clearOut(element); //Commented for JEOJIT boost Commented on 28-Mar-2021
			logger.info("--------	ClearText wrapper method inside FalconActions class is executed successfully	--------");
		}
		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to enter text into the input field "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	
	public static void EnterText_CGT(WebElement element, String textToType, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			//System.out.println("Enter text 1");
			waitForElementToBeVisible(element);
			//System.out.println("Enter text 2");
			element.click(); //Commented for JEOJIT boost Commented on 28-Mar-2021
			//System.out.println("Enter text 3");
			FalconWebElement.clearOut(element); //Commented for JEOJIT boost Commented on 28-Mar-2021
			//System.out.println("Enter text 4");
			FalconWebElement.typeTextIn(element, textToType);
			//System.out.println("Enter text 5");
			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("User Entered text into the "+"<b>"+elementDesc +"</b>"+" field", "pass"); //Commented on on 20-JUNE-2019 4DOC
			//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to enter text into the input field "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	public static void EnterTextWithoutWaitForTesting(WebElement element, String textToType, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			//System.out.println("Enter text 1");
			//waitForElementToBeClickable(element);
			//System.out.println("Enter text 2");
			element.click();
			//System.out.println("Enter text 3");
			FalconWebElement.clearOut(element);
			//System.out.println("Enter text 4");
			FalconWebElement.typeTextIn(element, textToType);
			//System.out.println("Enter text 5");
			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("User Entered text into the "+"<b>"+elementDesc +"</b>"+" field", "pass"); //Commented on on 20-JUNE-2019 4DOC
			//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to enter text into the input field "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	
	public static void SwitchToAlert_Accept() throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{		
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("Accepted the Alert", "pass"); //Commented on on 20-JUNE-2019 4DOC
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to accept alert "+ "due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	public static void SwitchToAlert_Dismiss() throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{		
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.dismiss();;
			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("Accepted the Alert", "pass"); //Commented on on 20-JUNE-2019 4DOC
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to accept alert "+ "due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	
	
	

	public static void VerifyText(WebElement element, String ExpectedText, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element);
			String Actual = element.getText();
			Actual  = Actual.trim().toLowerCase();
			ExpectedText = ExpectedText.trim().toLowerCase();
			assertEquals(Actual, ExpectedText);
			logger.info("Actual and Expected texts are equal: Actual is: "+"<b>"+Actual +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>");
			testlogINFO("Actual is "+Actual+" and Expected is "+Actual+" , Hence Pass");
			logEvent("Text matched for "+"<b>"+elementDesc+"</b>", "pass"); 
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Actual and expected texts are not matching for: "+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	
	public static void VerifyText_CGT(WebElement element, String ExpectedText, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element);
			String Actual = element.getText();
			Actual  = Actual.trim().toLowerCase().substring(0, 2);
			ExpectedText = ExpectedText.trim().toLowerCase();
			assertEquals(Actual, ExpectedText);
			logger.info("Actual and Expected texts are equal: Actual is: "+"<b>"+Actual +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>");
			testlogINFO("Actual is "+Actual+" and Expected is "+Actual+" , Hence Pass");
			logEvent("Text matched for "+"<b>"+elementDesc+"</b>", "pass"); 
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Actual and expected texts are not matching for: "+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	
	public static void VerifyTextCustomized(WebElement element, String ExpectedText, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element);
			String Actual = element.getText();
			Actual  = Actual.trim().toLowerCase();
			
			int indx;
			indx = Actual.indexOf(".");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(Actual.substring(indx+1));
			System.out.println(Actual.substring(indx+2));
			System.out.println(Actual.substring(indx+3));
			System.out.println(Actual.substring(indx+4));
			System.out.println(Actual.substring(indx+5));
			System.out.println(Actual.substring(indx+6));
			
			ExpectedText = ExpectedText.trim().toLowerCase();
			
			//Actual = "This person is considered a United States Resident and cannot be added as a Joint Owner to the account in which the Tax Reported For Owner is a Foreign National.";
			
			//assertEquals(Actual.substring(indx+1), ExpectedText);
			//assertEquals(Actual.trim().toLowerCase(), ExpectedText);
			assertEquals(Actual.substring(indx+2).toLowerCase(), ExpectedText);
			logger.info("Actual and Expected texts are equal: Actual is: "+"<b>"+Actual +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>");
			testlogINFO("Actual is "+Actual+" and Expected is "+Actual+" , Hence Pass");
			logEvent("Text matched for "+"<b>"+elementDesc+"</b>", "pass"); 
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Actual and expected texts are not matching for: "+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	//CUstomized for ICICI project
	public static void VerifPlainText(String ExpectedText, String ActualText, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			String Actual = ActualText;
			Actual  = Actual.trim().toLowerCase();
			ExpectedText = ExpectedText.trim().toLowerCase();
			assertEquals(Actual, ExpectedText);
			logger.info("Actual and Expected texts are equal: Actual is: "+"<b>"+Actual +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>");
			testlogINFO("Actual is "+Actual+" and Expected is "+Actual+" , Hence Pass");
			logEvent("Text matched for "+"<b>"+elementDesc+"</b>", "pass"); 
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Actual and expected texts are not matching for: "+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	//Added to verify page title as verification point
	public static void VerifyPageTitle(String ExpectedText, String elementDesc) throws IOException
	{
		logger.info("--------	VerifyPageTitle wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			String Actual = getDriver().getTitle();
			Actual  = Actual.trim().toLowerCase();
			ExpectedText = ExpectedText.trim().toLowerCase();
			assertEquals(Actual, ExpectedText);
			logger.info("Actual and Expected texts are equal: Actual is: "+"<b>"+Actual +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>");
			testlogINFO("Actual is "+Actual+" and Expected is "+Actual+" , Hence Pass");
			logEvent("Text matched for "+"<b>"+elementDesc+"</b>", "pass"); 
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Actual and expected texts are not matching for: "+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	
	public static void SwitchToWindow_PDFCompare(WebElement table, String PDFPath) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			String parentwindow=driver.getWindowHandle();
			for(String childwindow:driver.getWindowHandles())
			{
				if(!parentwindow.equals(childwindow))
				{
					driver.switchTo().window(childwindow);
					Thread.sleep(2000);
					ElementExist(table, "Table");
				}
			}
			System.out.println("PDF path is "+PDFPath);
			//count rows
			List<WebElement> Rows = driver.findElements(By.xpath("//table[@id='myTable']/tbody/tr"));
			int totalRows = Rows.size();
			System.out.println(" Total rows : "+totalRows);
			//count columns
			List<WebElement> Columns = driver.findElements(By.xpath("//table[@id='myTable']/tbody/tr[1]/td"));
			int totalColumns = Columns.size();
			System.out.println(" Total Columns : "+totalColumns);
			String PaymentSchedules = null;	  
			String firstpart="//table[@id='myTable']/tbody/tr[";
			String secondpart="]/td[";
			String thirdpart="]";
			for( int i=1;i<=totalRows;i++)
			{
				for(int j=1;j<=totalColumns;j++)
				{
					String finalxpath=firstpart+i+secondpart+j+thirdpart;
					PaymentSchedules = driver.findElement(By.xpath(finalxpath)).getText();
					System.out.println(PaymentSchedules);
				}		  
			}
			String LastPaymentAmount=driver.findElement(By.xpath("//*[@id='myTable']/tbody/tr[24]/td[4]")).getText();
						
			//Reading data from PDF file
			File file = new File(PDFPath);
			PDDocument document = PDDocument.load(file);
			//Instantiate PDFTextStripper class
			PDFTextStripper pdfStripper = new PDFTextStripper();
			//Retrieving text from PDF document
			String PDFContent = pdfStripper.getText(document);
			System.out.println(PDFContent);
			//Closing the document
			document.close();
				      
			//Check Last payment matching with PDF values
			if(PDFContent.contains(LastPaymentAmount))
			{
				System.out.println("Last Payment Amount displaying PDF matched with Last Payment in Installment plan");
			}
				     
			if(PDFContent.contains(PaymentSchedules))
			{
				System.out.println("Data dsplaying in PDF and table matched");
			}
			driver.close();
			driver.switchTo().window(parentwindow);
		
			logger.info("PDF comparision is pass");
			logEvent("PDF data is matched with Web table", "pass");

		}

		catch(Exception error)
		{
			logger.error("PDF comparision is fail"+error.getMessage());
			logEvent("PDF data is not matched with Web table"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO COMPARE PDF AND WEBTABLE : "+error.getMessage());
		}
	}

	public static void SwitchChildWindSelectOption(WebElement element, String textToSelect, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			String mainWindow=driver.getWindowHandle();
			Set<String> set =driver.getWindowHandles();
			Iterator<String> itr= set.iterator();
			while(itr.hasNext()){
				String childWindow=itr.next();
				// Compare whether the main windows is not equal to child window. If not equal, we will close.
				if(!mainWindow.equals(childWindow)){
					driver.switchTo().window(childWindow);
					System.out.println("Child window title is: "+driver.switchTo().window(childWindow).getTitle());
					new WebDriverWait(driver, 60).until(
							webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
					System.out.println("^&**&&^%$^&*(      Page loaded completely #######################");
					System.out.println("********* Wait 25 sec completed ****************");
					System.out.println("Child window title is111: "+driver.switchTo().window(childWindow).getTitle());	

					waitForElementToBeVisible(element);
					Thread.sleep(3000);
					//count rows
					List<WebElement> Rows = driver.findElements(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr"));
					int totalRows = Rows.size();
					System.out.println(" Total rows : "+totalRows);


					//count columns
					List<WebElement> Columns = driver.findElements(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr[1]/td"));
					int totalColumns = Columns.size();
					System.out.println(" Total Columns : "+totalColumns);

					//Extract data
					int m=0;
					for( int i=2;i<=totalRows;i++)
					{
						for(int j=2;j<=totalColumns;j++)
						{
							WebElement dataCell = driver.findElement(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr["+i+"]/td["+j+"]"));
							System.out.println(dataCell.getText());
							if(dataCell.getText().trim().equalsIgnoreCase(textToSelect))
							{
								driver.findElement(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr["+i+"]/td[1]")).click();
								m=5;
								break;
							}
						}
						if(m==5)
						{
							break;
						}
					}

				}
			}
			driver.switchTo().window(mainWindow);
			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("User switched to child window and Selected "+"<b>"+elementDesc +"</b>"+" from options", "pass"); //Commented on on 20-JUNE-2019 4DOC
			//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to Select "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	public static void Switch_Child_Click_SubChild_SelectOption(WebElement Child_element, WebElement SubChild_element, String textToSelect, String elementDesc, WebElement abcd) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{		
			String winHandleBefore = driver.getWindowHandle();
			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);
			}
			click(Child_element,"icon");
			for (String handle1 : driver.getWindowHandles())
			{ 
				driver.switchTo().window(handle1);
			}
			System.out.println("Sub child window");

			//Select option from table
			SelectOptionFromTable(SubChild_element, textToSelect, elementDesc);
			driver.switchTo().window(winHandleBefore);
			click(abcd,"Allocate button");
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : -----"+error.getMessage());
			logEvent("User unable to Select "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	public static void SwitchChildWindClick(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{String mainWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			// Compare whether the main windows is not equal to child window. If not equal, we will close.
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println("Child window title is: "+driver.switchTo().window(childWindow).getTitle());
				new WebDriverWait(driver, 60).until(
						webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
				System.out.println("----------      Page loaded completely -------");
				System.out.println(" -------- Wait 25 sec completed ----------------------------");
				System.out.println("Child window title is111: "+driver.switchTo().window(childWindow).getTitle());	

				waitForElementToBeVisible(element);
				Thread.sleep(3000);
				click(element,elementDesc);
			}
		}
		//driver.switchTo().window(mainWindow);
		logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
		logEvent("User switched to child window and Selected "+"<b>"+elementDesc +"</b>"+" from options", "pass"); //Commented on on 20-JUNE-2019 4DOC
		//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to Select "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	public static void SwitchChildWindClick_InsideChildWin_Allocate(WebElement Win1_icon_element,String Win1_icon_elementDesc,WebElement Win1_element1,String Win1_element1Desc, WebElement Win2_inside_element_table, String Win2_inside_textToSelectOption, String Win2_inside_elementDesc) throws IOException
	{
		//SwitchChildWindClick_InsideChildWin_Allocate(icon_conv_UserAlloc, "Users allocation icon", btn_Allocate, "Allocate Button", table, getMapData.get("User"), "User to allocate");
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{String mainWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			// Compare whether the main windows is not equal to child window. If not equal, we will close.
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println("Child window title is: "+driver.switchTo().window(childWindow).getTitle());
				new WebDriverWait(driver, 60).until(
						webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
				System.out.println("Satyanarayana ***************************");
				System.out.println("********* Wait 25 sec completed ****************");
				System.out.println("Child window title is111: "+driver.switchTo().window(childWindow).getTitle());	
				waitForElementToBeVisible(Win1_icon_element);
				click(Win1_icon_element,Win1_icon_elementDesc);
				SwitchChildWindSelectOption(Win2_inside_element_table, Win2_inside_textToSelectOption, Win2_inside_elementDesc);
				waitForElementToBeVisible(Win1_element1);
				click_Alert(Win1_element1,Win1_element1Desc);
				SwitchToAlert_Accept();					
			}
		}
		driver.switchTo().window(mainWindow);
		logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
		logEvent("User Allocated to user", "pass"); //Commented on on 20-JUNE-2019 4DOC
		//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to allocate to user"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	public static void SwitchChildWindVeriftText(WebElement element, String textToSelect, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{String mainWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			// Compare whether the main windows is not equal to child window. If not equal, we will close.
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println("Child window title is: "+driver.switchTo().window(childWindow).getTitle());
				new WebDriverWait(driver, 60).until(
						webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
				System.out.println("^&**&&^%$^&*(      Page loaded completely #######################");
				System.out.println("********* Wait 25 sec completed ****************");
				System.out.println("Child window title is111: "+driver.switchTo().window(childWindow).getTitle());	

				waitForElementToBeVisible(element);
				VerifyText(element, textToSelect, elementDesc);
				driver.close();

			}
		}
		driver.switchTo().window(mainWindow);
		logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
		logEvent("User switched to child window and verified "+"<b>"+elementDesc +"</b>"+" text", "pass"); //Commented on on 20-JUNE-2019 4DOC
		//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to Select "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	public static void SwitchChildWindVeriftMultipleTexts(WebElement element1, String textToSelect1, WebElement element2, String textToSelect2, WebElement element3, String textToSelect3, WebElement element4, String textToSelect4, String elementDesc ) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{String mainWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
			// Compare whether the main windows is not equal to child window. If not equal, we will close.
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);
				System.out.println("Child window title is: "+driver.switchTo().window(childWindow).getTitle());
				new WebDriverWait(driver, 60).until(
						webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
				System.out.println("^&**&&^%$^&*(      Page loaded completely #######################");
				System.out.println("********* Wait 25 sec completed ****************");
				System.out.println("Child window title is111: "+driver.switchTo().window(childWindow).getTitle());	

				waitForElementToBeVisible(element1);
				VerifyText(element1, textToSelect1, elementDesc);
				VerifyText(element2, textToSelect2, elementDesc);
				VerifyText(element3, textToSelect3, elementDesc);
				VerifyText(element4, textToSelect4, elementDesc);
				driver.close();

			}
		}
		driver.switchTo().window(mainWindow);
		logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
		logEvent("User switched to child window and verified "+"<b>"+elementDesc +"</b>"+" text", "pass"); //Commented on on 20-JUNE-2019 4DOC
		//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to Select "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	/*To verify the particular WebElement is displayed or not*/
	public static void ElementExist(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element); //Added on 29-MAY-2019
			if(element.isDisplayed()){
			}
			logger.info("--------	EnterText wrapper method inside CustomMethod class is executed successfully	--------");
			logEvent("<b>"+elementDesc+"</b>"+" is displayed", "pass"); //Commented on 20-JUNE-2019 4DOC
			//logEvent(elementDesc+"_is_displayed", "pass"); //Added on 20-JUNE-2019 4DOC
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("<b>"+elementDesc+"</b>"+" is not displayed due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	
	public static void SelectOptionFromTable(WebElement element_table, String textToSelect, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element_table);
			Thread.sleep(3000);
			//count rows
			List<WebElement> Rows = driver.findElements(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr"));
			int totalRows = Rows.size();
			System.out.println(" Total rows : "+totalRows);
			//count columns
			List<WebElement> Columns = driver.findElements(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr[1]/td"));
			int totalColumns = Columns.size();
			System.out.println(" Total Columns : "+totalColumns);

			//Extract data

			for( int i=2;i<=totalRows;i++)
			{
				WebElement dataCell = driver.findElement(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr["+i+"]/td[3]"));
				System.out.println(dataCell.getText());
				if(dataCell.getText().trim().equalsIgnoreCase(textToSelect))
				{
					driver.findElement(By.xpath("//table[@class='container-fluid table-responsive']/tbody/tr["+i+"]/td[1]")).click();
					break;
				}
			}

			logger.info("--------	EnterText wrapper method inside FalconActions class is executed successfully	--------");
			logEvent("User selected "+"<b>"+elementDesc +"</b>"+" option", "pass"); //Commented on on 20-JUNE-2019 4DOC
			//logEvent("User_Entered_text_into_the_"+elementDesc +"_field", "pass"); //Added on on 20-JUNE-2019 4DOC

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to select "+"<b>"+elementDesc +"</b>"+" option due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	public static void TableResults(WebElement Table,List<WebElement> Tablerowss) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(Table); //Added on 29-MAY-2019
			int total = Tablerowss.size();
			int TotalRows = total-1;
			System.out.println("Total results are: "+TotalRows);

			logger.info("--------	EnterText wrapper method inside CustomMethod class is executed successfully	--------");
			logEvent("Total number of results are "+"<b>"+TotalRows+"</b>", "pass"); //Commented on 20-JUNE-2019 4DOC
			//logEvent(elementDesc+"_is_displayed", "pass"); //Added on 20-JUNE-2019 4DOC
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Unable to get the results"+" is due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}



	public static Boolean ElementPresent_return(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully	--------");
		Boolean ElementPre = element.isDisplayed();
		
		return ElementPre;
	}
	/*Scroll to any particular element*/
	public static void ScrollToElement(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{
			//WebElement ele = driver.findElement(By.id("id_of_element"));
			((JavascriptExecutor) TestBase.driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000); 
			/*JavascriptExecutor exe = (JavascriptExecutor) TestBase.driver;
			exe.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);*/
			logger.info("--------	EnterText wrapper method inside CustomMethod class is executed successfully	--------");
			logEvent("Focus moved to "+"<b>"+elementDesc+"</b>", "pass");
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("<b>"+elementDesc+"</b>"+" is not displayed due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	/*To select value from dropdown using visible text*/
	public static void SelectVisibleText(WebElement element, String ddValue, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully!!	--------");
		try
		{
			//waitForElementToBeVisible(element); // Added on 29-MAY-2019 Commented on 03-June-2021 for AVOKA
			System.out.println("#$%^&*((*&^%$#$%^&*()(*&^%$");
			//if(element.isDisplayed()){ Commented on 03-June-2021 for AVOKA
				System.out.println("%^&*IUFTY^YHGUFRT^&YUYTRT");
				Select sel = new Select(element);
				System.out.println("$%ERYTT^&YT^&GYYY");
				sel.selectByVisibleText(ddValue);
				System.out.println("BVFGGGGGGGGGGGGGGGGGYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
				//String k = sel.getFirstSelectedOption().getText();
				logger.info("---------------s- desired dropdown value is selected -------------------");
				logEvent("<b>"+elementDesc+"</b>"+" is selected from the dropdown", "pass");
			//} Commented on 03-June-2021 for AVOKA
		}

		catch(Exception error)
		{
			logger.error("@ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to select "+"<b>"+elementDesc+"</b>"+" from the dropdown value due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}

	/*To select the dropdown value from the list which is not from select class*/
	public static void DropDownList(List<WebElement> element, String listValues, String elementDesc) throws IOException
	{
		System.out.println("Drop down started 00000000000000000");
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{

			for (WebElement listValue : element) {
				if(listValues.equals(listValue.getText().trim()))
					listValue.click(); 
			}
			logger.info("--------	EnterText wrapper method inside CustomMethod class is executed successfully	--------");
			logEvent("User selected "+"<b>"+elementDesc+"</b>"+" from the drop down list", "pass"); //Commented on 24-JUNE-2019 4DOC
			//logEvent("User_selected_"+elementDesc+"_from_the_drop_down_list", "pass"); //Added on 24-JUNE-2019 4DOC
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to select "+"<b>"+elementDesc+"</b>"+" from the drop down list due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}


	/*
		public void select_combo_by_id(WebElement element,String select_text)
		{ 

			ComboBox debs_combo = new ComboBox(element); 
			debs_combo.expand();


			driver.findElement(By.name(select_text)).click(); 
		}
	 */

	/* To click a specific option from a list
	@Parameters : web element, tag locator and text */
	public static void ClickSpecificOptionFromAList(WebElement parent, By listTag, String text) throws IOException
	{
		logger.info("--------	ClickSpecificOptionFromAList wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{
			List<WebElement> List =  FalconWebElement.getChildren(parent, listTag);
			
			System.out.println("PIYUSHHERE"+List.size());
			
			Iterator<WebElement> i = List.iterator();
			while(i.hasNext())
			{
				WebElement e = i.next();
				
				System.out.println("PIYUSH");
				System.out.println(e);
				
				if(e.getText().contains(text))
				{
					e.click();
				}
			}
			logger.info("--------	ClickSpecificOptionFromAList wrapper method inside CustomMethod class is executed successfully	--------");
			logEvent("User able to select the required option from the drop down list", "pass");
		}
		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON SPECIFIC OPTION FROM A LIST : "+error.getMessage());
			logEvent("User unable to select the required option from the drop down list"+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO CLICK ON SPECIFIC OPTION FROM A LIST : "+error.getMessage());
		}
	}
	
	//This method is customized for Geojit
	public static void ClickSpecificOptionFromAListCustomized(List<WebElement> parentList, String parentListForCustomize, String text, String elementDesc) throws IOException
	{
		logger.info("--------	ClickSpecificOptionFromAListCustomized wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{
			System.out.println("PIYUSHHERE"+parentList.size());
			Iterator<WebElement> i = parentList.iterator();
			int j = 1;
			while(i.hasNext())
			{
				WebElement e = i.next();
				System.out.println("PIYUSH");
				System.out.println(e);
				System.out.println(e.getText());
				System.out.println(text);
				if(e.getText().contains(text))
				{
					//e.click();
					parentListForCustomize = parentListForCustomize+"["+j+"]";
					driver.findElement(By.xpath(parentListForCustomize)).click();
					break;
				}
				j++;
			}
			logger.info("--------	ClickSpecificOptionFromAList wrapper method inside CustomMethod class is executed successfully	--------");
			logEvent("User able to select "+"<b>"+elementDesc+"</b>"+" option from the drop down list", "pass");
		}
		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON SPECIFIC OPTION FROM A LIST : "+error.getMessage());
			logEvent("User unable to sselect "+"<b>"+elementDesc+"</b>"+" option from the drop down list"+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO CLICK ON SPECIFIC OPTION FROM A LIST : "+error.getMessage());
		}
	}
	
	public static void HighlightElement(WebElement element) {
		executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	
	       
    public static Alert alert ;	//Added on 28-Mar-2021
	
	/* To click on specified web element
	@Parameter : Browser and web element */
    
   public static void click(WebElement element, String elementDesc) throws IOException, InterruptedException
	{
		logger.info("--------	click wrapper method inside MyAction class is invoked successfully	--------");
		try
		{
			//System.out.println("Click event: element visible check started");
			waitForElementToBeVisible(element);
			//System.out.println("Click event: element visible check success");
			//System.out.println("Click event: started");
			element.click();
			//System.out.println("Click event: End");
			//logger.info("--------	click wrapper method inside MyAction class is executed successfully	--------");
			//logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Commented on 24-JUNE-2019 4DOC
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Added on 24-JUNE-2019 4DOC
		}
		catch(NoSuchElementException exception)
		{
			logger.error("NoSuchElementException ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
			throw(exception); //Added on 28-Mar-2021
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("StaleElementReferenceException ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
			throw(exception); //Added on 28-Mar-2021
		}
		catch(WebDriverException exception)
		{
			logger.error("WebDriverException ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
			throw(exception); //Added on 28-Mar-2021
		}
		catch(Exception exception)
		{
			logger.error("Exception ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
			throw(exception); //Added on 28-Mar-2021
		}	
	}
	
	/* To click on specified web element
	@Parameter : Browser and web element */
	public static void submit(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	click wrapper method inside MyAction class is invoked successfully	--------");
		try
		{
			//System.out.println("Click event: element visible check started");
			waitForElementToBeVisible(element);
			//System.out.println("Click event: element visible check success");
			//System.out.println("Click event: started");
			element.submit();
			//System.out.println("Click event: End");
			//logger.info("--------	click wrapper method inside MyAction class is executed successfully	--------");
			//logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Commented on 24-JUNE-2019 4DOC
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Added on 24-JUNE-2019 4DOC
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	
	
	//This method added specifically for CAS project to click on caption based on web elements list
	public void clickOnSpecificCaptionInListOfWebElements(List<WebElement> webElementsList, String captionToClick, String elementDesc) throws InterruptedException, IOException
	{
		try
		{
			System.out.println(webElementsList.size());
			for(int i=0;i<webElementsList.size();i++)
			{
				System.out.println(webElementsList.get(i));
				System.out.println(webElementsList.get(i).getText());
				if(webElementsList.get(i).getText().equalsIgnoreCase(captionToClick))
				{
					//webElementsList.get(i).click(); Commented on 16-Dec-2020
					click(webElementsList.get(i), elementDesc);
					
					logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); 
					Thread.sleep(1000);
					break;
				}
			}
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}

	public static void clickForDynamicXpathgeneratedElements(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	click wrapper method inside MyAction class is invoked successfully	--------");
		try
		{
			//System.out.println("Click event: element visible check started");
			waitForElementToBeVisible(element);
			//System.out.println("Click event: element visible check success");
			//System.out.println("Click event: started");
			element.click();
			//System.out.println("Click event: End");
			//logger.info("--------	click wrapper method inside MyAction class is executed successfully	--------");
			//logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Commented on 24-JUNE-2019 4DOC
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Added on 24-JUNE-2019 4DOC
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	
	
	public static void SimpleClick(WebElement element, String elementDesc) throws IOException
	{
		System.out.println("Drop down started 00000000000000000");
		logger.info("--------	EnterText wrapper method inside CustomMethod class is invoked successfully	--------");
		try
		{
			Thread.sleep(2500);
			element.click();
			Thread.sleep(2500);
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass");
			//logEvent("User_selected_"+elementDesc+"_from_the_drop_down_list", "pass"); //Added on 24-JUNE-2019 4DOC
		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("User unable to click"+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}


	public static void click_Alert(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	click wrapper method inside MyAction class is invoked successfully	--------");
		try
		{
			waitForElementToBeClickable(element);
			element.click();
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b> Alert", "pass");
		}

		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_\"+\"<b>\"+elementDesc+\"</b>\"+\"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}

	public static void click_Wait(WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	click wrapper method inside MyAction class is invoked successfully	--------");
		try
		{
			//System.out.println("Click event: element visible check started");
			waitForElementToBeVisible(element);
			//System.out.println("Click event: element visible check success");
			//System.out.println("Click event: started");
			element.click();
			Thread.sleep(3500);
			//System.out.println("Click event: End");
			//logger.info("--------	click wrapper method inside MyAction class is executed successfully	--------");
			//logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Commented on 24-JUNE-2019 4DOC
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Added on 24-JUNE-2019 4DOC
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_\"+\"<b>\"+elementDesc+\"</b>\"+\"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_\"+\"<b>\"+elementDesc+\"</b>\"+\"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_\"+\"<b>\"+elementDesc+\"</b>\"+\"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}

	/* To double click on specified web element
	@Parameter : Browser and web element */
	public static void doubleClick(WebDriver driver, WebElement element)
	{
		logger.info("--------	doubleClick wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.doubleClick(element).build();
			action.perform();
			logger.info("--------	doubleClick wrapper method inside MyAction class is executed successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	/* To click and hold on the specified web element
	@Parameter : Browser and web element */
	public static void clickAndHold(WebDriver driver, WebElement element)
	{
		logger.info("--------	clickAndHold wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.clickAndHold(element).build();
			action.perform();
			logger.info("--------	clickAndHold wrapper method inside MyAction class is executed successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO CLICK AND HOLD ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	/* To right click on the specified web element
	@Parameter : Browser and web element */
	public static void contextClick(WebDriver driver, WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	contextClick wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.contextClick(element).build();
			action.perform();
			logger.info("--------	contextClick wrapper method inside MyAction class is executed successfully	--------");
			logEvent("User right clicked for "+"<b>"+elementDesc +"</b>"+" operations", "pass"); //Commented on on 20-JUNE-2019 4DOC
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User unable to right click for "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User unable to right click for "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User unable to right click for "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User unable to right click for "+"<b>"+elementDesc +"</b>"+" due to the Exception: "+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	/* To drag and drop specified web element
	@Parameter : Browser, source web element and destination web element */
	public static void dragAndDrop(WebDriver driver, WebElement source, WebElement distination)
	{
		logger.info("--------	dragAndDrop wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.dragAndDrop(source, distination).build();
			action.perform();
			logger.info("--------	dragAndDrop wrapper method inside MyAction class is executed successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}	
	}
	/* To drag and drop specified web element
	@Parameter : Browser, source web element and x-y axis */
	public static void dragAndDrop(WebDriver driver, WebElement element, int xOffset, int yOffset)
	{
		logger.info("--------	dragAndDrop wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.dragAndDropBy(element, xOffset, yOffset).build();
			action.perform();
			logger.info("--------	dragAndDrop wrapper method inside MyAction class is executed successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO DRAG AND DROP THE SPECIFIED WEB ELEMENT");
		}	
	}
	/* To press a particular Key on the specified element
	@Parameter : Browser, web element and key */
	public static void keyPress(WebDriver driver, WebElement element, Keys key)
	{
		logger.info("--------	keyPress wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.keyDown(element, key).build();
			action.perform();
			logger.info("--------	keyPress wrapper method inside MyAction class is executed successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO PRESS A KEY ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	/* To release a particular Key on the specified element
	@Parameter : Browser, web element and key */
	public static void keyRelease(WebDriver driver, WebElement element, Keys key)
	{
		logger.info("--------	keyRelease wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.keyUp(element, key).build();
			action.perform();
			logger.info("--------	keyRelease wrapper method inside MyAction class is executed successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO RELEASE A KEY ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	public static void mouseOver(WebDriver driver, WebElement element)
	{
		logger.info("--------	mouseOver wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.moveToElement(element).build();
			action.perform();
			logger.info("--------	mouseOver wrapper method inside MyAction class is invoked successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER SPECIFIED WEB ELEMENT");
		}	
	}
	/* To mouse over and click on the specified web element
	@Parameter : Browser and web element */
	public static void mouseOverAndClick(WebDriver driver, WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	mouseOverAndClick wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.moveToElement(element).click().build();
			action.perform();
			logger.info("--------	mouseOverAndClick wrapper method inside MyAction class is invoked successfully	--------");
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Added on 24-JUNE-2019 4DOC

		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}
	
	public static void mouseOverAndClickPPTEST(WebDriver driver, WebElement element, String elementDesc) throws IOException
	{
		logger.info("--------	mouseOverAndClick wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			waitForElementToBeVisible(element);
			actionObj = new Actions(driver);
			action = actionObj.moveToElement(element).click().build();
			action.perform();
			logger.info("--------	mouseOverAndClick wrapper method inside MyAction class is invoked successfully	--------");
			logEvent("User clicked on the "+"<b>"+elementDesc+"</b>", "pass"); //Added on 24-JUNE-2019 4DOC

		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			logEvent("User_unable_to_click_on_the_"+"<b>"+elementDesc+"</b>"+"_due_to_the_Exception:-"+exception.getMessage(), "fail");
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}

	/* To mouse over and double click on the specified web element
	@Parameter : Browser and web element */
	public static void mouseOverAndDoubleClick(WebDriver driver, WebElement element)
	{
		logger.info("--------	mouseOverAndDoubleClick wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.moveToElement(element).doubleClick().build();
			action.perform();
			logger.info("--------	mouseOverAndDoubleClick wrapper method inside MyAction class is invoked successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND DOUBLE CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}

	/* To mouse over and right click on the specified web element
	@Parameter : Browser and web element */
	public static void mouseOverAndContextClick(WebDriver driver, WebElement element)
	{
		logger.info("--------	mouseOverAndContextClick wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.moveToElement(element).contextClick().build();
			action.perform();
			logger.info("--------	mouseOverAndContextClick wrapper method inside MyAction class is invoked successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND RIGHT CLICK ON THE SPECIFIED WEB ELEMENT");
		}	
	}

	/* To mouse over and click&hold on the specified web element
	@Parameter : Browser and web element */
	public static void mouseOverAndClickAndHold(WebDriver driver, WebElement element)
	{
		logger.info("--------	mouseOverAndClickAndHold wrapper method inside MyAction class is invoked successfully	--------");
		Actions actionObj;
		Action action;
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.moveToElement(element).clickAndHold().build();
			action.perform();
			logger.info("--------	mouseOverAndClickAndHold wrapper method inside MyAction class is invoked successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT");
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT");
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT");
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO MOUSE OVER AND CLICK&HOLD ON THE SPECIFIED WEB ELEMENT");
		}	
	}


	// Wait Methods Added on 28-MAY-2019 for Sync Web and Desktop START
	public static void applyImplicitWait()
	{
		driver.manage().timeouts().implicitlyWait(Long.valueOf(getPropertyValue("implecitWait")), TimeUnit.SECONDS);
		logger.info("implecit wait as "+getPropertyValue("implecitWait")+" SECONDS applied");
	}

	@SuppressWarnings("deprecation")
	public static WebDriverWait applyWebDriverWait()
	{

		if(runningDriver ==  "Web")
		{
			logger.debug("");
			//WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), Long.valueOf(getPropertyValue("explicitWait")));
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), 120);
			wait.pollingEvery(2000, TimeUnit.MILLISECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(ElementNotVisibleException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.ignoring(NoSuchFrameException.class);
			return wait;

		}
		else
		{

			logger.debug("");
			//WebDriverWait wait = new WebDriverWait(TestBase.getDriver1(), Long.valueOf(getPropertyValue("explicitWait")));
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), 90);
			wait.pollingEvery(2000, TimeUnit.MILLISECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(ElementNotVisibleException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.ignoring(NoSuchFrameException.class);
			return wait;
		}

	}

	public static void waitForElementToBeVisible(WebElement locator)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info(locator);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		WebDriverWait wait = applyWebDriverWait();
		System.out.println("##############################");
		wait.until(ExpectedConditions.visibilityOf(locator));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%");
	}
	
		@SuppressWarnings("deprecation")
	public static void fluentWait(WebElement locator)
	{
		Wait wait = new FluentWait<WebDriver>(TestBase.getDriver())
				.withTimeout(Long.valueOf(getPropertyValue("explicitWait")), TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);



	}
	public static void waitForElementToBeClickable(WebElement locator)
	{
		logger.info(locator);
		WebDriverWait wait = applyWebDriverWait();
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}

	public static void waitForAlertToBePresent()
	{
		logger.info("Alert present wait");
		WebDriverWait wait = applyWebDriverWait();
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	public static void handleAlert(){
	    if(isAlertPresent()){
	        System.out.println("ALERT PRESENT");
	    	Alert alert = driver.switchTo().alert();
	        System.out.println(alert.getText());
	        alert.accept();
	    }
	    else
	    {
	    	System.out.println("ALERT NOT PRESENT");
	    }
	    
	}
	
	public static void handleAlert_Accept(){
	    if(isAlertPresent()){
	        System.out.println("ALERT PRESENT");
	    	Alert alert = driver.switchTo().alert();
	        System.out.println(alert.getText());
	        alert.accept();
	    }
	    else
	    {
	    	System.out.println("ALERT NOT PRESENT");
	    }
	    
	}
	
	public static void handleAlert_Dissmiss(){
	    if(isAlertPresent()){
	        System.out.println("ALERT PRESENT");
	    	Alert alert = driver.switchTo().alert();
	        System.out.println(alert.getText());
	        alert.dismiss();
	    }
	    else
	    {
	    	System.out.println("ALERT NOT PRESENT");
	    }
	    
	}

	public static boolean isAlertPresent(){
	      try{
	        
	    	  WebDriverWait wait = new WebDriverWait(driver,60);
	          wait.until(ExpectedConditions.alertIsPresent());
	    	  driver.switchTo().alert();
	          return true;
	      }catch(NoAlertPresentException ex){
	          return false;
	      }
	}
	
	public static Object executeScript(String script)
	{
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		logger.info(script);
		return exe.executeScript(script);
	}

	public static Object executeScript(String script, Object...args)
	{
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		logger.info(script);
		return exe.executeScript(script, args);
	}


	public static void scrollIntoView(WebElement element)
	{
		executeScript("arguments[0].scrollIntoView()",element);
		logger.info(element);
	}
	
	public void scrollToElement(WebElement element) 
	{
		executeScript("window.scrollTo(arguments[0],arguments[1])",element.getLocation().x,element.getLocation().y);
		logger.info(element);
	}
	
	public static void javaScriptExecutorImageClick(WebElement element)
	{
		executeScript("arguments[0].click()",element);
		logger.info(element);
	}
	
	public static void javaScriptExecutortoEnterText(WebElement element, String Value)
	{
		executeScript("arguments[0].value=Value;",element);
		logger.info(element);
	}
	
	public static void scrollDownByPixel()
	{
		executeScript("window.scrollBy(-1000,0)");
	}
	
	public static void scrollToMiddle()
	{
		executeScript("window.scrollBy(0,-1500)");
	}
	
	public static void scrollDownVertically()
	{
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static void scrollUpVertically()
	{
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	
	public static void SoftVerifyText(WebElement element, String ExpectedText, String elementDesc) throws IOException
	{
		logger.info("--------	EnterText wrapper method inside FalconActions class is invoked successfully	--------");
		try
		{
			waitForElementToBeVisible(element);

			String ActualText  = element.getText();
			String ActualText1 = ActualText.trim().toLowerCase();
			String ExpectedText1 =  ExpectedText.trim().toLowerCase();
			if(ActualText1.equalsIgnoreCase(ExpectedText1))
			{
				logger.info("Actual and Expected texts are equal: Actual is: "+"<b>"+ActualText +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>");
				logEvent("Actual and Expected texts are Equal for "+"<u>"+elementDesc+"</u>"+ " : Actual is: "+"<b>"+ActualText +"</b>"+" and Expected is: "+"<b>"+ExpectedText +"</b>","pass");
			}
			else
			{
				logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT :");
				logEvent("<p style=\"color:red\">"+"Actual and expected texts are not matching for: "+"<u>"+elementDesc+"</u>"+ " Actual Text: "+"<b>"+ActualText +"</b>"+ " Expected Text: "+"<b>"+ExpectedText+"</b>"+"</p>", "fail");
			}

		}

		catch(Exception error)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
			logEvent("Actual and expected texts are not matching for: "+"<b>"+elementDesc+"</b>"+" due to the Exception: "+error.getMessage(), "fail");
			Assert.fail("Exception : WHILE TRYING TO TYPE SPECIFIED TEXT INSIDE A WEB ELEMENT : "+error.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	// Wait Methods Added on 28-MAY-2019 for Sync Web and Desktop END


	/*	public void typeText(WebElement element, String textToType)
	{
		logger.info("--------	typeText wrapper method inside MyAction class is invoked successfully	--------");
		try
		{
			actionObj = new Actions(driver);
			action = actionObj.sendKeys(element, textToType).build();
			action.perform();
			logger.info("--------	typeText wrapper method inside MyAction class is invoked successfully	--------");
		}
		catch(NoSuchElementException exception)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
		}
		catch(StaleElementReferenceException exception)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
		}
		catch(WebDriverException exception)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
		}
		catch(Exception exception)
		{
			logger.error("ERROR WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
			Assert.fail("WebDriverException : WHILE TRYING TO TYPE SPECIFIED TEXT : "+exception.getMessage());
		}	
	}*/

}
