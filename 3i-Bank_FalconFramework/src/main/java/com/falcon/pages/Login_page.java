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

public class Login_page extends FalconActions {

	//private List<PageResponse> pageResponseList = new ArrayList<>(1);
	
	public Login_page(WebDriver driver) {
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
	
	@FindBy (how=How.XPATH, using = "//input[@id='myCheck']")
	public static WebElement check_box;	
	
	@FindBy (how=How.XPATH, using = "//button[text()='Agree & Continue']")
	public static WebElement Agreebtn;	
			
	//LOG OUT
	@FindBy (how=How.XPATH, using = "(//ul[@class='navbar-nav']//a[@id='navbarDropdown'])[2]")
	public static WebElement btn_MouseHower_ToLogOut;
	
	@FindBy (how=How.XPATH, using = "//a/span[text()='Logout']")
	public static WebElement btn_SignOut;
	
	@FindBy (how=How.XPATH, using = "//img[@id='profileimg']")
	public static WebElement pic_profile;
	
	
//===========================================Methods==================================================//

	public void login_Application() throws IOException, InterruptedException 
	{
		waitForElementToBeVisible(txt_UserName);
		EnterText(txt_UserName, getMapData.get("UserName"), "UserName");
		EnterText(txt_Password, getMapData.get("Password"), "Password");
		EnterText(txt_Card, getMapData.get("Card"), "Card");
		click(btn_Login, "Login");
	
	}
	
	public void Apply() throws IOException, InterruptedException, AWTException
	{
		waitForElementToBeVisible(btn_Apply);
		
		click(btn_Apply, "ApplyButton");
		
		//scrollToElement(check_box);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
        WebElement Element = driver.findElement(By.xpath("//input[@id='myCheck']"));

        // Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);
        
		click(check_box, "I agree check box");
		
		click(Agreebtn, "I agree button");

	
	}
	
	
}