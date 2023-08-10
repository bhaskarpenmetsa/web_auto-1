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

public class Personal_Details extends FalconActions {

	
	//private List<PageResponse> pageResponseList = new ArrayList<>(1);
	
	public Personal_Details(WebDriver driver) {
		super(driver);
	}
	
	//====================Master Home====================\\

	@FindBy (how=How.XPATH, using = "//input[@id='firstname']")
	public static WebElement firstname;
	
	@FindBy (how=How.XPATH, using = "//input[@id='lastname']")
	public static WebElement lastname;
	
	@FindBy (how=How.XPATH, using = "//input[@id='dob']")
	public static WebElement dob;
	
	@FindBy (how=How.XPATH, using = "//input[@id='pancard']")
	public static WebElement pancard;
	
	@FindBy (how=How.XPATH, using = "//input[@id='mobileno']")
	public static WebElement mobileno;
	
	@FindBy (how=How.XPATH, using = "//input[@id='email']")
	public static WebElement email;
	
	@FindBy (how=How.XPATH, using = "(//input[@name='gender'])[1]")
	public static WebElement Male;
	
	@FindBy (how=How.XPATH, using = "(//input[@name='gender'])[2]")
	public static WebElement female;
	
	@FindBy (how=How.XPATH, using = "//div[@class='col-md-9']//button[@type='button'][normalize-space()='Save']")
	public static WebElement savebtn;

	@FindBy (how=How.CSS, using = "#mystatus")
	public static WebElement status;
	
	
//===========================================Methods==================================================//

	public void PersonalDetails() throws IOException, InterruptedException 
	{
		waitForElementToBeVisible(firstname);
		EnterText(firstname, getMapData.get("firstname"), "firstname");
		EnterText(lastname, getMapData.get("lastname"), "lastname");
		
		if (getMapData.get("gender").equalsIgnoreCase("Male")) 
	    {
	     click(Male, "Male");
	    } 
	    else if (getMapData.get("gender").equalsIgnoreCase("female"))
	    {
	    	 click(female, "female");
	    }
		
		EnterText(dob, getMapData.get("dob"), "dob");
		EnterText(pancard, getMapData.get("pancard"), "pancard");
		EnterText(mobileno, getMapData.get("mobileno"), "mobileno");
		EnterText(email, getMapData.get("email"), "email");
		
		waitForElementToBeVisible(savebtn);
		click(savebtn, "save button");
		
		SoftVerifyText(status, "Details Saved Successfully", "pass");
	
	}
	
	
}