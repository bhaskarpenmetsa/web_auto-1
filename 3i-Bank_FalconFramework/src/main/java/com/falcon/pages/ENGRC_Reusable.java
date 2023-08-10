package com.falcon.pages;

import java.util.Random;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import com.falcon.actions.FalconActions;
import com.falcon.driver.TestBase;
import com.falcon.excelutils.Xls_Reader;

public class ENGRC_Reusable extends FalconActions {

	public static String RiskName;
	public static String Riskcat="CreditRisk";
	
	public ENGRC_Reusable(WebDriver driver) {
		super(driver);
	}
	
	public void Avoka_LoadExcelData() throws Exception 
	{
		getMapData = new TreeMap<String, String>();
		System.out.println(TestBase.testCaseName);
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
		System.out.println(TestBase.gettestCaseName());
		System.out.println(TestBase.gettestCaseName()+TestBase.getEnvironment()+TestBase.getDevice()+TestBase.getOS1()+TestBase.getOSVersion()+TestBase.getBrowser());
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
		//getMapData  = Xls_Reader.getCellDataBasedOnRowValuefromTestData(TestBase.gettestCaseName(), TestBase.gettestCaseName()+TestBase.getEnvironment()+TestBase.getDevice()+TestBase.getOS1()+TestBase.getOSVersion()+TestBase.getBrowser()); //Added for ICICIMWCustom 27-Nov-2020
		//getMapData  = Xls_Reader.getCellDataBasedOnRowValuefromTestData(TestBase.gettestCaseName(), TestBase.getEnvironment()); //Added for ICICIMWCustom 27-Nov-2020 Commented on 04-Dec-2020
		getMapData  = Xls_Reader.getCellDataBasedOnColumnValue(TestBase.gettestCaseName(), TestBase.getEnvironment()); //Added for ICICIMWCustom 27-Nov-2020 Commented on 04-Dec-2020
		//================================================
		System.out.println("EXCEL DATA READING ENEDED");
	}
	
	
	public static String GeneratingRandomString() {
		 
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 8;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();

	    System.out.println(generatedString);
		return generatedString;
	}

	public static String genRName() 
	{
		   int length = 4;
		    boolean useLetters = false;
		    boolean useNumbers = true;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	        RiskName=Riskcat+"_"+generatedString;
		    System.out.println(RiskName);
			return RiskName;
	}
}
