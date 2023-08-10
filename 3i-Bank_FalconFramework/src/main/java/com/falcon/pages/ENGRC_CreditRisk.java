package com.falcon.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.falcon.actions.FalconActions;
import com.falcon.model.response.PageResponse;
import com.falcon.response.excel.ResponseExcel;
import com.falcon.utils.UtilityMethods;

public class ENGRC_CreditRisk extends FalconActions {

	
	public static String Rname;
	
	//private List<PageResponse> pageResponseList = new ArrayList<>(1);
	
	public ENGRC_CreditRisk(WebDriver driver) {
		super(driver);
	}
	
	//====================Master Home====================\\
	@FindBy (how=How.XPATH, using = "//input[@id='username']")
	public static WebElement txt_UserName;
	
	@FindBy (how=How.XPATH, using = "//input[@id='password']")
	public static WebElement txt_Password;
	
	@FindBy (how=How.XPATH, using = "//input[@id='card']")
	public static WebElement txt_Card;
	
	@FindBy (how=How.XPATH, using = "//button[@id='login_btn']")
	public static WebElement btn_Login;

	@FindBy (how=How.XPATH, using = "//button[text()='Apply Now']")
	public static WebElement btn_Apply;
	
	@FindBy (how=How.XPATH, using = "//app-sidebar//div[@class='menu-list']/ul/li[2]")
	public static WebElement btn_SideMenuItem;
	
	@FindBy (how=How.XPATH, using = "//span[text()='Risk & Control Management']")
	public static WebElement btn_SubMenu_RiskAndCtrlManagement;

	@FindBy (how=How.XPATH, using = "//span[text()='Risk List']")
	public static WebElement caption_RiskList;
	
	@FindBy (how=How.XPATH, using = "//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Risk List']")
	public static WebElement tab_RiskList;
	
	@FindBy (how=How.XPATH, using = "//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Control List']")
	public static WebElement tab_ControlList;
	
	@FindBy (how=How.XPATH, using = "//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Risk Control Matrix']")
	public static WebElement tab_ControlRiskMatrix;
	
	@FindBy (how=How.XPATH, using = "//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Control Execution Workflow']")
	public static WebElement tab_ControlExecutionWorkflow;
	
	@FindBy (how=How.XPATH, using = "//table//th[text()='Risk Area']")
	public static WebElement  heading_RiskArea;
	
	@FindBy (how=How.XPATH, using = "(//div//a//img[@alt='Add'])[1]")
	public static WebElement  btn_Add;
	
	@FindBy (how=How.XPATH, using = "(//input[@formcontrolname='riskName'])")
	public static WebElement  txt_RiskName;
	
	//PAGE 1 
	@FindBy (how=How.XPATH, using = "//select[@formcontrolname='riskResponse']")
	public static WebElement  select_RiskResponse;
	//---Mitigate
	
	@FindBy (how=How.XPATH, using = "//select[@formcontrolname='assessmentModel']")
	public static WebElement select_AssessmentModel;
	//--Quantitative
	
	@FindBy (how=How.XPATH, using = "(//tab//button[text()='Save'])[1]")
	public static WebElement btn_Save;
	
	//PAGE 2
	@FindBy (how=How.XPATH, using = "//select[@formcontrolname='company']")
	public static WebElement select_company;
	//--ABC	
	
	@FindBy (how=How.XPATH, using = "(//tab//button[text()='Save'])[2]")
	public static WebElement btn_Save2;
	
	@FindBy (how=How.XPATH, using = "(//app-confirm-popup//button[text()='Confirm'])[2]")
	public static WebElement btn_Confirm;		
	
	@FindBy (how=How.XPATH, using = "//input[@aria-controls='riskListTable']")
	public static WebElement txt_RiskTable;
	
	//@FindBy (how=How.XPATH, using = "(//table[@id='riskListTable']//tr[@class='odd']/td)[3]/a")
	@FindBy (how=How.XPATH, using = "(//table[@id='riskListTable']//tr[@class='odd']/td)[4]//span")
	public static WebElement tbl_Value3rd;
	
	//DELETE button	
	@FindBy (how=How.XPATH, using = "//a[@data-toggle='dropdown']//span[3]")
	public static WebElement btn_DeleteDropDown;
	
	@FindBy (how=How.XPATH, using = "//div[@x-placement='bottom-start']//a//span[text()='Delete']")
	public static WebElement btn_Deletebutton;			
	
	//AFTER DELETE
	//TO GET COUNT AFTER DELET AND RESEARCH		
	//----------------(//table[@id='riskListTable']//tr[@class='odd']/td)[3]/a		
			
	//LOG OUT
	@FindBy (how=How.XPATH, using = "(//ul[@class='navbar-nav']//a[@id='navbarDropdown'])[2]")
	public static WebElement btn_MouseHower_ToLogOut;
	
	@FindBy (how=How.XPATH, using = "//a/span[text()='Logout']")
	public static WebElement btn_SignOut;
	

	@FindBy (how=How.XPATH, using = "//img[@id='profileimg']")
	public static WebElement pic_profile;
	
	public void loginEngrcApplication() throws IOException, InterruptedException
	{
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		startTime = UtilityMethods.getDateAndTimeForConsoleAndLogs();
		System.out.println("Time and date "+startTime);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		EnterText(txt_UserName, getMapData.get("UserName"), "UserName");
		EnterText(txt_Password, getMapData.get("Password"), "Password");
		EnterText(txt_Card, getMapData.get("Card"), "Card");
		click(btn_Login, "Login");
		
		endTime = UtilityMethods.getDateAndTimeForConsoleAndLogs();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		ExecutionTimelogin = (new Date(endTime).getTime()  - new Date(startTime).getTime()) /1000f ;
		String response = String.valueOf(ExecutionTimelogin);
		System.out.println("Execution time from Login page "+ExecutionTimelogin + " seconds");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		logger.info("Total time (in Seconds) is :" + ExecutionTimelogin);
		
		PageResponse pr = new PageResponse();
		pr.setPageName("Login Page");
		pr.setResponseTime(response);
		pr.setStartTime(startTime);
		pr.setEndTime(endTime);
		
		ResponseExcel.prepareExcel(pr);
	}
	
	public void addEngrdCreditRisk() throws IOException, InterruptedException, AWTException
	{
		
		
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		startTime = UtilityMethods.getDateAndTimeForConsoleAndLogs();
		System.out.println("Time and date "+startTime);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		waitForElementToBeVisible(btn_Apply);
		//mouseOver(getDriver(), btn_SideMenuItem);
		click(btn_Apply, "ApplyButton");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		        		
        WebElement Element = driver.findElement(By.xpath("//input[@id='myCheck']"));

        // Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);
        
        driver.findElement(By.xpath("//input[@id='myCheck']")).click();
        
        driver.findElement(By.xpath("//button[text()='Agree & Continue']")).click();
        
        Thread.sleep(3000);
		
		//navigationBetweenTab();
		//click(btn_Add, "Add");
		//Rname = ENGRC_Reusable.genRName(); 
		//ClearText(txt_RiskTable, "RiskName");
		//EnterText(txt_RiskName, Rname, "RiskName");
		//SelectVisibleText(select_RiskResponse, getMapData.get("RiskResponse"), "RiskResponse");
		//SelectVisibleText(select_AssessmentModel, getMapData.get("AssessmentModel"), "AssessmentModel");
		//click(btn_Save, "Save");
		//SelectVisibleText(select_company, getMapData.get("Company"), "Company");
		//scrollIntoView(btn_Save);
		//click(btn_Save2, "Save2");
		//scrollIntoView(btn_Save);
		//click(btn_Save, "Save");
		//click(btn_Confirm, "Confirm");
		//searchCreatedCreditRisk();
        
      
        endTime = UtilityMethods.getDateAndTimeForConsoleAndLogs();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		ExecutionTimelogin = (new Date(endTime).getTime()  - new Date(startTime).getTime()) /1000f ;
		String response = String.valueOf(ExecutionTimelogin);
		System.out.println("Execution time from Login page "+ExecutionTimelogin + " seconds");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		logger.info("Total time (in Seconds) is :" + ExecutionTimelogin);
		
		
		
		PageResponse pr = new PageResponse();
		
		pr = new PageResponse();
		pr.setPageName("addEngrdCreditRisk");
		pr.setResponseTime(response);
		pr.setStartTime(startTime);
		pr.setEndTime(endTime);
		pr.setEndTime(endTime);
		
		ResponseExcel.prepareExcel(pr);
		
		// writeExcelReportResponseTimes("",response, atTime1);
        
	}
	

	public void navigationBetweenTab() throws IOException, InterruptedException, AWTException
	{
		//click(tab_RiskList, "RiskList");
		click(tab_ControlList, "ControlList");
		click(tab_ControlRiskMatrix, "ControlRiskMatrix");
		click(tab_ControlExecutionWorkflow, "ControlExecutionWorkflow");
		click(tab_RiskList, "RiskList");
	}
	
	public void searchCreatedCreditRisk() throws IOException, InterruptedException
	{
		//ClearText(txt_RiskTable, "RiskName");
		EnterText(txt_RiskTable, Rname, "RiskName");
		VerifyText(tbl_Value3rd, Rname, "RiskName");
	}
	
	public void deleteEngrcCreditRisk() throws IOException, InterruptedException
	{
		click(btn_DeleteDropDown, "DeleteDropDown");
		click(btn_Deletebutton, "Delete");
		click(btn_Confirm, "Confirm");
		//ClearText(txt_RiskTable, "RiskName");
		//EnterText(txt_RiskTable, Rname, "RiskName");
		List<WebElement> a =  getDriver().findElements(By.xpath("(//table[@id='riskListTable']//tr[@class='odd']/td)[3]/a"));
		int size = a.size();
		if(size > 0 )
		{
			VerifPlainText("1", Integer.toString(size), "RecordCount");	
		}
		else
		{
			VerifPlainText("0", Integer.toString(size), "RecordCount");
		}
	}
	
	public void logOutEngrcApplication() throws IOException, InterruptedException 
	{
		startTime = UtilityMethods.getDateAndTimeForConsoleAndLogs();

		waitForElementToBeVisible(pic_profile);
		click(pic_profile, "LogoutImage");
		click(btn_SignOut, "SignOut");

		endTime = UtilityMethods.getDateAndTimeForConsoleAndLogs();
		ExecutionTimelogin = (new Date(endTime).getTime() - new Date(startTime).getTime()) / 1000f;
		String response = String.valueOf(ExecutionTimelogin);

		PageResponse pr = new PageResponse();
		pr.setPageName("Logout page");
		pr.setResponseTime(response);
		pr.setStartTime(startTime);
		pr.setEndTime(endTime);
		pr.setEndTime(endTime);

		ResponseExcel.prepareExcel(pr);

	}

	/*
	public List<PageResponse> getPageResponseList() {
		return pageResponseList;
	}

	public void setPageResponseList(List<PageResponse> pageResponseList) {
		this.pageResponseList = pageResponseList;
	}
	*/
}