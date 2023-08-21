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

public class Logout_page extends FalconActions {

	
	//private List<PageResponse> pageResponseList = new ArrayList<>(1);
	
	public Logout_page(WebDriver driver) {
		super(driver);
	}
	
	//====================Master Home====================\\
			
	//LOG OUT
	@FindBy (how=How.XPATH, using = "(//ul[@class='navbar-nav']//a[@id='navbarDropdown'])[2]")
	public static WebElement btn_MouseHower_ToLogOut;
	
	@FindBy (how=How.XPATH, using = "//a/span[text()='Logout']")
	public static WebElement btn_SignOut;
	
	@FindBy (how=How.XPATH, using = "//img[@id='profileimg']")
	public static WebElement pic_profile;
	
	
//===========================================Methods==================================================//

	
	public void logout() throws IOException, InterruptedException, AWTException
	{
		waitForElementToBeVisible(pic_profile);
		click(pic_profile, "LogoutImage");
		click(btn_SignOut, "SignOut");
	}
	
	
}
