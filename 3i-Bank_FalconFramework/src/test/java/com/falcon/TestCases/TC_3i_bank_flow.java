package com.falcon.TestCases;

import org.apache.log4j.Logger;
import org.testng.ITest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.falcon.driver.TestBase;
import com.falcon.pages.ENGRC_CreditRisk;
import com.falcon.pages.Login_page;
import com.falcon.pages.Logout_page;
import com.falcon.pages.Personal_Details;
import com.falcon.reports.Reports;

@Listeners(com.falcon.driver.FalconListner.class)
public class TC_3i_bank_flow extends Reports implements ITest{
	
	public static final Logger logger = Logger.getLogger(TC_3i_bank_flow.class.getName());

	@Test(dataProvider="EnvironMentDeviceOSVersionAndBrowser", dataProviderClass=TestBase.class,description = "User is Creating Account with IDV and IDA APPROVAL")
	public void TC_3i_bank_flow(String Environment, String Device, String OS, String Version, String Browser) throws Exception
	{
		try
		{
			PreReq_Test();
			assingCategory("3i-Bank");
			printInConsole("()()()()()(()()() Object creation initiated()()()()()(()()()");
			
			Login_page obj_login =new Login_page(driver);
			Personal_Details obj_PD =new Personal_Details(driver);
			Logout_page obj_logout =new Logout_page(driver);
			
			printInConsole("::::::::::::::Object with Method Calling::::::::::::::");
			
			obj_login.login_Application();
			obj_login.Apply();
			obj_PD.PersonalDetails();
			obj_logout.logout();
			
			
		}
		catch(Exception e)
		{
			loggerERROR("Failed test case due to exception : "+e.getMessage());
			testlogERROR("Failed test case due to exception"+e.getMessage());
			throw e;
		}
	}
	@Override
	public String getTestName() {
		return testName.get();
	}
}