package com.falcon.TestCases;

import org.apache.log4j.Logger;
import org.testng.ITest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.falcon.driver.TestBase;
import com.falcon.pages.ENGRC_CreditRisk;
import com.falcon.reports.Reports;

@Listeners(com.falcon.driver.FalconListner.class)
public class TC_001_CreditRisk extends Reports implements ITest{
	
	public static final Logger logger = Logger.getLogger(TC_001_CreditRisk.class.getName());

	@Test(dataProvider="EnvironMentDeviceOSVersionAndBrowser", dataProviderClass=TestBase.class,description = "User is Creating Account with IDV and IDA APPROVAL")
	public void TC_001_CreditRiskAdd(String Environment, String Device, String OS, String Version, String Browser) throws Exception
	{
		try
		{
			PreReq_Test();
			assingCategory("CreditRisk");
			printInConsole("()()()()()(()()() Object creation initiated()()()()()(()()()");		
			ENGRC_CreditRisk obj_ENGRC_CreditRisk = new ENGRC_CreditRisk(getDriver());
			obj_ENGRC_CreditRisk.loginEngrcApplication();
			obj_ENGRC_CreditRisk.addEngrdCreditRisk();
			obj_ENGRC_CreditRisk.logOutEngrcApplication();
			//obj_ENGRC_CreditRisk.logOutEngrcApplication();
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