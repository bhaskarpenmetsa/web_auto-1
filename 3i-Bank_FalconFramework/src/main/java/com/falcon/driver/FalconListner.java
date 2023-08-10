package com.falcon.driver;

import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openxml4j.samples.GetThumbnails;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.falcon.reports.Reports;
import com.falcon.excelutils.Excel_Reader;

public class FalconListner extends Reports implements ITestListener 
{

	public static final Logger logger = Logger.getLogger(TestBase.class.getName());

	@Override
	public void onTestStart(ITestResult result) {

		/*
		//test.log(Status.INFO, "=======onTestStart========");
		test.log(Status.INFO, result.getName()+" test is started");
		logger.info(result.getName()+" test is started");
		 */
		System.out.println("onTestStart : "+result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "=======onTestStart========");
		
		
		if(!result.getName().equalsIgnoreCase("TC_001_FrontRunner")) //Added on 01-Mar-2022
		{ //Added on 01-Mar-2022

			System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPSUCCESS"+result.getName());	
			
		if(rowcount == 0)
		{
			rowcount = rowcount + 1;
		}

		//Added on 06-MAY-2019 Start
		if(rowcountone == 0)
		{
			//rowcountone = rowcountone + 1; Commented on 22-Dec-2020
			rowcountone = datatable.getRowCount(getPropertyValue("xlsReportsConsolidatedSheetname")); //Added on 22-Dec-2020 
		}
		//Added on 06-MAY-2019 End
		logger.info("===onTestSuccess Start===");
		logger.info(result.getStatus());
		logger.info(ITestResult.SUCCESS);

		test.log(Status.PASS, result.getName()+ " test is Pass");
		logger.info(result.getName()+ " test is Pass");
		datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Status",rowcount+1, "PASS");
		datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"ExecutedAt",rowcount+1, atTime);
		datatable.setCellData(getPropertyValue("xlsReportsConsolidatedSheetname"),"Status",rowcountone+1, "PASS"); // Added on 06-MAY-2019
		datatable.setCellData(getPropertyValue("xlsReportsConsolidatedSheetname"),"ExecutedAt",rowcountone+1, atTime); // Added on 06-MAY-2019
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"StartTime",rowcount+1, Long.toString(start));
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"EndTime",rowcount+1, Long.toString(end));
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Diff",rowcount+1, String.valueOf((end - start) / 1000f));
		test.pass(MarkupHelper.createLabel(result.getName()+" test is passed", ExtentColor.GREEN));		
		System.out.println("onTestSuccess : "+result.getName());

		String screen = getScreenshot("");
		//test.log(LogStatus.PASS, test.addScreenCapture(screen));


		// Surrounded with try and catch on 28-MAY-2019 Start
		try {
			attachScreenshottoReport(screen);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Surrounded with try and catch on 28-MAY-2019 Start

		/* can be deleted as method is shifted to report class
		try {
			test.addScreenCaptureFromPath(screen, "Screen title");
			logger.info("Screen shot captured from path : "+screen);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Attaching screenshot to report failed due to : "+e.getMessage());
			Assert.fail("Attaching screenshot to report failed due to : "+e.getMessage());
		}*/

		if(TestBase.webDriverIntialized  && TestBase.getDriver() != null) // Updated on 27-MAY-2019 for sync web and desktop
		{
			try
			{
				//TestBase.getDriver().quit(); Commented on 27-MAY-2019 for sync web and desktop
				//TestBase.getDriver().close(); // Added on 27-MAY-2019 for sync web and desktop
				TestBase.getDriver().quit(); // Added on 14-OCT-2020 for SWIFTMW
				logger.info("Driver closed");
			}
			catch(Exception e)
			{
				Assert.fail("Attaching screenshot to report failed due to : "+e.getMessage());
				logger.info("Driver closing error"+e.getMessage());
			}
			//writeExtentReport();
		}
		
		} //Added on 01-Mar-2022

	}

	@Override
	public void onTestFailure(ITestResult result) {

		if(!result.getName().equalsIgnoreCase("TC_001_FrontRunner")) //Added on 01-Mar-2022
		{ //Added on 01-Mar-2022
		
			
			System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPFAIL"+result.getName());
		
		if(rowcount == 0)
		{
			rowcount = rowcount + 1;
		}

		//Added on 06-MAY-2019 Start
		if(rowcountone == 0)
		{
			//rowcountone = rowcountone + 1; Commented on 22-Dec-2020
			rowcountone = datatable.getRowCount(getPropertyValue("xlsReportsConsolidatedSheetname")); //Added on 22-Dec-2020
			
		}
		//Added on 06-MAY-2019 End
		logger.info("===onTestFailure Start===");
		logger.info(result.getStatus());
		logger.info(ITestResult.FAILURE);

		//test.log(Status.FAIL, "=======onTestFailure========");
		test.log(Status.FAIL, result.getName()+" test is failed and fail resoan is : *** "+result.getThrowable()+" ***");
		logger.info(result.getName()+" test is failed &nbsp; <br /> &nbsp; and fail resoan is :-"+result.getThrowable());
		datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Status",rowcount+1, "FAIL");
		datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"ExecutedAt",rowcount+1, atTime);
		datatable.setCellData(getPropertyValue("xlsReportsConsolidatedSheetname"),"Status",rowcountone+1, "FAIL"); // Added on 06-MAY-2019
		datatable.setCellData(getPropertyValue("xlsReportsConsolidatedSheetname"),"ExecutedAt",rowcountone+1, atTime); // Added on 06-MAY-2019
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"StartTime",rowcount+1, Long.toString(start));
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"EndTime",rowcount+1, Long.toString(end));
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Diff",rowcount+1, String.valueOf((end - start) / 1000f));
		test.fail(MarkupHelper.createLabel(result.getName()+" test is failed", ExtentColor.RED));

		String screen = getScreenshot("");
		//test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		try {
			test.addScreenCaptureFromPath(screen, "TitleHere1");
			logger.info("Screen shot captured from path : "+screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("onTestFailure : "+result.getName());
		if(TestBase.webDriverIntialized && TestBase.getDriver() != null) // Updated on 27-MAY-2019 for sync web and desktop
		{
			try
			{
				//TestBase.getDriver().quit(); // Commented on 27-MAY-2019 for sync web and desktop
				//TestBase.getDriver().close();  // Added on 27-MAY-2019 for sync web and desktop
				TestBase.getDriver().quit(); // Added on 14-OCT-2020 for SWIFTMW
				logger.info("Driver closed");
			}
			catch(Exception e)
			{
				Assert.fail("Attaching screenshot to report failed due to : "+e.getMessage());
				logger.info("Driver closing error"+e.getMessage());
			}
			//writeExtentReport();
		}
		
		} //Added on 01-Mar-2022
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		if(!result.getName().equalsIgnoreCase("TC_001_FrontRunner")) //Added on 01-Mar-2022
		{ //Added on 01-Mar-2022
			
			System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPSKIP"+result.getName());
		
		if(rowcount == 0)
		{
			rowcount = rowcount + 1;
		}

		//Added on 06-MAY-2019 Start
		if(rowcountone == 0)
		{
			rowcountone = rowcountone + 1;
		}
		//Added on 06-MAY-2019 End
		//test.log(Status.SKIP, "=======onTestSkipped========");
		test.log(Status.SKIP, result.getName()+" test is skipped and skip resoan is :-"+result.getThrowable());
		datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Status",rowcount+1, "SKIP");
		datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"ExecutedAt",rowcount+1, atTime);
		datatable.setCellData(getPropertyValue("xlsReportsConsolidatedSheetname"),"Status",rowcountone+1, "SKIP"); // Added on 06-MAY-2019
		datatable.setCellData(getPropertyValue("xlsReportsConsolidatedSheetname"),"ExecutedAt",rowcountone+1, atTime); // Added on 06-MAY-2019
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"StartTime",rowcount+1, Long.toString(start));
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"EndTime",rowcount+1, Long.toString(end));
		//datatable.setCellData(getPropertyValue("xlsReportsSheetname"),"Diff",rowcount+1, String.valueOf((end - start) / 1000f));
		logger.info(result.getName()+" test is skipped and skip resoan is :-"+result.getThrowable());
		test.skip(MarkupHelper.createLabel(result.getName()+" test is failed", ExtentColor.GREY));
		System.out.println("onTestSkipped : "+result.getName());
		if(TestBase.webDriverIntialized && TestBase.getDriver() != null) // Updated on 27-MAY-2019 for sync web and desktop
		{
			try
			{
				//TestBase.getDriver().quit(); // Commented on 27-MAY-2019 for sync web and desktop
				//TestBase.getDriver().close(); // Added on 27-MAY-2019 for sync web and desktop
				TestBase.getDriver().quit(); // Added on 14-OCT-2020 for SWIFTMW
				logger.info("Driver closed");
			}
			catch(Exception e)
			{
				Assert.fail("Attaching screenshot to report failed due to : "+e.getMessage());
				logger.info("Driver closing error"+e.getMessage());
			}
			//writeExtentReport();
		}
		} //Added on 01-Mar-2022
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithinSuccessPercentage : "+result.getName());

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart : "+context.getName());
		initializeReports();

		//Added on 06-MAY-2019 Start
		datatable.removeSheet(getPropertyValue("xlsReportsSheetname"));
		System.out.println("Removed Sheet");
		logger.info("Removed Sheet");
		datatable.addSheet(getPropertyValue("xlsReportsSheetname"));
		System.out.println("Added Sheet");
		logger.info("Added Sheet");
		datatable.setHeader(getPropertyValue("xlsReportsSheetname"));
		System.out.println("Created Sheet");
		logger.info("Created Sheet");
		//Added on 06-MAY-2019 End
	}

	Excel_Reader excelReader = new Excel_Reader(); //Added on 04Sept2020JIOGIT
	
	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("onFinish : "+context.getName()); Commented on 01-Mar-2022
		//writeExtentReport(); Commented on 06-Apr-2021
		//Added on 04Sept2020JIOGIT Start
		
		/*
		
		ArrayList<String> summaryList = new ArrayList<String>();
		try {
			summaryList = excelReader.readTestResultIntoList();
			System.out.println(summaryList);
			excelReader.getExecutionResultSummaryforChart(summaryList, "Chrome000000", "Firefox000000", "IEEdge000000", "Desktop000000", "Mobile000000", "PASS000000", "FAIL000000");
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"TotaluniqueTests",2, Integer.toString(Excel_Reader.totalTestCaseCount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"ChromeCount",2, Integer.toString(Excel_Reader.chromeCount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"FireFoxCount",2, Integer.toString(Excel_Reader.fireFoxCount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"IEEdgeCount",2, Integer.toString(Excel_Reader.IECount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"DesktopCount",2, Integer.toString(Excel_Reader.desktopCount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"MobileCount",2, Integer.toString(Excel_Reader.mobileCount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"PassCount",2, Integer.toString(Excel_Reader.passCount));
			datatable.setCellDataForInt(getPropertyValue("xlsSummarySheetname"),"FailCount",2, Integer.toString(Excel_Reader.failCount));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			excelReader.pieChart(3,5, 0, 4 ,4, 19, "Multi-Browser Statistics");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Added on 04Sept2020JIOGIT
		try {
			excelReader.pieChart(6,7, 5, 4, 10, 19, "Multi-Device Statistics");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Added on 04Sept2020JIOGIT
		try {
			excelReader.pieChart(8,9, 11, 4 ,16, 19, "Execution Statistics");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Added on 04Sept2020JIOGIT
		
		*/
	
		//Added on 04Sept2020JIOGIT End
		
		
		//Commented on 15-May-2019 (Reason: to remove pivot table in reports)
		//ReCommented on 04Sept2020JIOGIT Start
		/*try {
			datatable.generatePivotReport(getPropertyValue("xlsReportsConsolidatedSheetname"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//ReCommented on 04Sept2020JIOGIT End
	}

}
