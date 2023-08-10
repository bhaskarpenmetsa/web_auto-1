package com.falcon.excelutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MapRider {

	public static void main(String[] args) throws Exception {

		//WAY 2 - Retrieve once in TREE MAP and accessing from Tree Map
		ExcelUtilForRowWiseReadMap_bkp euMap = new ExcelUtilForRowWiseReadMap_bkp();
		
		List<String> getListOfScenarios = new ArrayList<String>();
		try {
			
			euMap.setExcelFile("D:\\Selenium\\WS\\Day6\\src\\TestData\\TestData2.xlsx");
			
			getListOfScenarios = euMap.getListofScenariostobeExecuted("RowWiseData");
			System.out.println(getListOfScenarios);
			
			for(int i=0;i<getListOfScenarios.size();i++)
			{
				System.out.println("Value is : "+getListOfScenarios.get(i));
			}
			
			
			
			TreeMap<String, String> getMapData = new TreeMap<String, String>();
			getMapData = euMap.getCellDataBasedOnRowValue("RowWiseData", "TestCaseState1");
			
			System.out.println("Map Data is Here"+getMapData);
			
		    String sState = getMapData.get("State");
			String sFirstName = getMapData.get("FirstName");
			String sLastName = getMapData.get("LastName");
			String sEmail = getMapData.get("Email");
			String sPassword = getMapData.get("Password");
			String sAddress = getMapData.get("Address");
			String sAddress1 = getMapData.get("Address1");
			String sZipCode = getMapData.get("ZipCode");
			String sResidenceType = getMapData.get("ResidenceType");
			String sRentAmount = getMapData.get("RentAmount");
			String sMortgageAmount = getMapData.get("MortgageAmount");
			String sPhoneNumber = getMapData.get("PhoneNumber");
			String sLandPhone = getMapData.get("LandPhone");
			
			System.out.println(sState);
			System.out.println(sFirstName);
			System.out.println(sLastName);
			System.out.println(sEmail);
			System.out.println(sPassword);
			System.out.println(sAddress);
			System.out.println(sAddress1);
			System.out.println(sZipCode);
			System.out.println(sResidenceType);
			System.out.println(sRentAmount);
			System.out.println(sMortgageAmount);
			System.out.println(sPhoneNumber);
			System.out.println(sLandPhone);
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
