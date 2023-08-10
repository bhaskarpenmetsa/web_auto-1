package com.falcon.excelutils;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.falcon.driver.TestBase;


public class Xls_Reader {

	public static final Logger logger = Logger.getLogger(Xls_Reader.class.getName()); //PPAdded on 02-Apr-2019
	public static String filename = "D:/3i-SwiftMW_FalconFramework/ExcelData/TestData.xlsx";
    public static  String path; // Changed Static on 08-MAY-2019
    public  static FileInputStream fis = null;
    public  FileOutputStream fileOut =null;
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;
    private static XSSFRow row   =null;
    private static XSSFCell cell = null;
    
    //Added for Test Data workbook on 03-MAY-2019 START
    public  static FileInputStream fis4TestData = null;
    private static XSSFWorkbook workbook4TestData = null;
    private static XSSFSheet sheet4TestData = null;
    private static XSSFRow row4TestData   =null;
    private static XSSFCell cell4TestData = null;
//Added for Test Data workbook on 03-MAY-2019 END
    private static Row headerRow, rowhead; //Added rowhead on 04-Dec-2020
    
    private static String TempRow;
    
    public static String dataSets[][] = null; // PPAdded  on 02-Apr-2019

    public Xls_Reader(String path, String path4TestData) { //Updated on 03-MAY-2019

        this.path=path;
        try {
            fis = new FileInputStream(path);
            ZipSecureFile.setMinInflateRatio(-1.0d); // Added on 13-June-2019
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
            //Added for Test Data workbook on 03-MAY-2019 START
            fis4TestData = new FileInputStream(path4TestData);
            workbook4TestData = new XSSFWorkbook(fis4TestData);
            sheet4TestData = workbook4TestData.getSheetAt(0);
            fis4TestData.close();
            //Added for Test Data workbook on 03-MAY-2019 END
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public static TreeMap<String, String> getCellDataBasedOnRowValue(String sheetName, String senarioName) throws Exception
    {
    	//Declaring TreeMap to Retrieve Scenario wise Row Wise Data at Once
		TreeMap<String, String> rowdata = new TreeMap<String, String>();
		
		//private variable declarations
    	int firstColumnNumber, lastColumnNumber, scenarioDataToRetrieve = -1, noofrows = 0,ColumntoGet = -1;
    	
    	//Get sheet index
    	int index = workbook.getSheetIndex(sheetName);
		//System.out.println("INDEX :"+index);
		
		//If sheet is not available with name than return Blank
		if(index==-1)
		{
			System.out.println("Sheetname is not exists in Workbook");
			//return 	rowdata;
	    }
		
		//get sheet name by index
		sheet = workbook.getSheetAt(index);
    	
    	//return first columns number - 0 - Always 0
    	firstColumnNumber = sheet.getRow(0).getFirstCellNum();
    	System.out.println("firstColumnNumber : "+firstColumnNumber);

    	//returns last column number
    	lastColumnNumber = sheet.getRow(0).getLastCellNum();
    	System.out.println("lastColumnNumber : "+lastColumnNumber);

    	//return number of rows
    	noofrows=sheet.getLastRowNum();
    	System.out.println("noofrows : "+noofrows);
    	
    	//get 0th row - Header Row
    	headerRow = sheet.getRow(0);
    	try
    	{
    		// Get column number of scenarioName
	    	for (int column = (firstColumnNumber + 1); column <= lastColumnNumber-1; column++)
	    	{
		    	 System.out.println("column : "+column);
		    	 //Check input scenarioName in headerRow
		       	 if (headerRow!=null && headerRow.getCell(column).toString().equalsIgnoreCase(senarioName))
		       	{
		       		//If scenarioName found than get its Column Number
		       		senarioName = headerRow.getCell(column).toString();
		     	    System.out.println("senarioName found : "+senarioName);
		     	    //Assigning column number to private variable scenarioDataToRetrieve
		     	    scenarioDataToRetrieve = column;
		     	    //If scenario name found than Break the loop
		     	    break;
		     	}
		       	 else
		       	 {
		       		 //Till scenarioName not found continue next column till end
		       		 continue;
		       	 }
	    	}
	    	
	    		//If no scenarioName found than return Blank
	    		if(scenarioDataToRetrieve <=0)
	    		{
	    			System.out.println("senarioName is not exists in Workbook");
	    			//return rowdata;
	    		}
	    		
	    		//Navigate to 0th row in sheet for Heading
	        	row = sheet.getRow(0);
	        	
	        	//Look for RowName in each row and get its correspondence cell value
	        	for(int p=0;p<noofrows+1;p++)
	        	{
	        		System.out.println("P :"+p);
	        		row = sheet.getRow(p);
	        		
	        		 //System.out.println(row.getCell(0).getStringCellValue().trim());
            		 //System.out.println(rowName.trim());
	        		
	        		TempRow = row.getCell(0).getStringCellValue().trim();
	        		ColumntoGet = scenarioDataToRetrieve;
	        		cell = row.getCell(ColumntoGet);
	        		
	        		  //To get Cell Types and return respective cell value
		        	  if(cell.getCellType()==CellType.STRING)
		        	  {
		        		  //return cell.getStringCellValue();
		        		  //System.out.println("ITS STRING");
		        		  rowdata.put(TempRow, cell.getStringCellValue());
		        	  }
		        	  else if(cell.getCellType()==CellType.NUMERIC|| cell.getCellType()==CellType.FORMULA)
		        	  {
		                  String cellText  = String.valueOf(cell.getNumericCellValue());
		                  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		                      // format in form of M/D/YY
		                      double d = cell.getNumericCellValue();
		                      Calendar cal =Calendar.getInstance();
		                      cal.setTime(HSSFDateUtil.getJavaDate(d));
		                      cellText =
		                              (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		                      cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                              cal.get(Calendar.MONTH)+1 + "/" +
		                              cellText;
		                      //System.out.println(cellText);
		                  }
		                  //return cellText;
		                  //rowdata.put(TempRow, cellText);
		        	  }else if(cell.getCellType()==CellType.BLANK)
		                  //return "";
		        		  rowdata.put(TempRow, "");
		              else
		              {
		            	rowdata.put(TempRow, String.valueOf(cell.getBooleanCellValue()));
		                //return String.valueOf(cell.getBooleanCellValue());
		              }
	             }
	        	
	       	  fis.close(); 	  
    		}  
    	    	catch(NullPointerException ne)
			{
				System.out.println("Exception while reading test data from sheet "+sheetName);
				throw ne;
			}
			catch(Exception e)
			{
				throw e;
			}
	    	return rowdata;
    }
    
    
    public static int getNoOfDataSetTobeExecutedForTestCase(String sheetName) throws Exception
    {
		
    	int noOfTestSet = 0, lastColumnNumber;
    	
    	//private variable declarations
    	int noofrows = 0;
    	
    	//Get sheet index
    	int index = workbook4TestData.getSheetIndex(sheetName);
		//System.out.println("INDEX :"+index);
		
		//If sheet is not available with name than return Blank
		if(index==-1)
		{
			System.out.println("Sheetname is not exists in Workbook");
			//return 	rowdata;
	    }
		
		//get sheet name by index
		sheet4TestData = workbook4TestData.getSheetAt(index);
    	
    	
    	//return number of rows
    	noofrows=sheet4TestData.getLastRowNum();
    	System.out.println("noofrows : "+noofrows);
    	noOfTestSet = noofrows;
    	
    	//returns last column number
    	lastColumnNumber = sheet4TestData.getRow(0).getLastCellNum();
    	System.out.println("lastColumnNumber : "+lastColumnNumber);
    	
    	return noOfTestSet;
    	
    }
    
    public static TreeMap<String, String> getCellDataBasedOnColumnValue(String sheetName, String senarioName) throws Exception
    {
    	//Declaring TreeMap to Retrieve Scenario wise Row Wise Data at Once
		TreeMap<String, String> rowdata = new TreeMap<String, String>();
		
		//private variable declarations
    	int firstColumnNumber, lastColumnNumber, scenarioDataToRetrieve = -1, noofrows = 0,ColumntoGet = -1;
    	int firstRowNumber, lastRowNumber;
    	//Get sheet index
    	int index = workbook4TestData.getSheetIndex(sheetName);
		//System.out.println("INDEX :"+index);
		
		//If sheet is not available with name than return Blank
		if(index==-1)
		{
			System.out.println("Sheetname is not exists in Workbook");
			//return 	rowdata;
	    }
		
		//get sheet name by index
		sheet4TestData = workbook4TestData.getSheetAt(index);
    	
    	//return first columns number - 0 - Always 0
    	firstColumnNumber = sheet4TestData.getRow(0).getFirstCellNum();
    	System.out.println("firstColumnNumber : "+firstColumnNumber);

    	//returns last column number
    	lastColumnNumber = sheet4TestData.getRow(0).getLastCellNum();
    	System.out.println("lastColumnNumber : "+lastColumnNumber);

    	//return number of rows
    	noofrows=sheet4TestData.getLastRowNum();
    	System.out.println("noofrows : "+noofrows);
    	//first row number
    	firstRowNumber = sheet4TestData.getFirstRowNum();
    	System.out.println("firstRowNumber : "+firstRowNumber);
    	//last row number
    	lastRowNumber = sheet4TestData.getLastRowNum();
    	System.out.println("lastRowNumber : "+lastRowNumber);
    	
    	//get 0th row - Header Row
    	//headerRow = sh.getRow(0);
    	try
    	{
    		// Get column number of scenarioName
	    	for (int row = (firstRowNumber); row <= lastRowNumber; row++)
	    	{
	    		
	    		headerRow = sheet4TestData.getRow(row);
		    	 System.out.println("row : "+row);
		    	 System.out.println("header row : "+headerRow.getFirstCellNum());
		    	 System.out.println("Please check : "+headerRow.getCell(0).toString());
		    	 //Check input scenarioName in headerRow
		       	 if (headerRow!=null && headerRow.getCell(0).toString().equalsIgnoreCase(senarioName))
		       	{
		       		//If scenarioName found than get its Column Number
		       		senarioName = headerRow.getCell(0).toString();
		     	    System.out.println("senarioName found : "+senarioName);
		     	    //Assigning column number to private variable scenarioDataToRetrieve
		     	    scenarioDataToRetrieve = row;
		     	    
		     	    System.out.println("scenarioDataToRetrieve : "+scenarioDataToRetrieve);
		     	    //If scenario name found than Break the loop
		     	    break;
		     	}
		       	 else
		       	 {
		       		 //Till scenarioName not found continue next column till end
		       		 continue;
		       	 }
	    	}
	    		//If no scenarioName found than return Blank
	    		if(scenarioDataToRetrieve <=0)
	    		{
	    			System.out.println("senarioName is not exists in Workbook");
	    			//return rowdata;
	    		}
	    		
	    		//Navigate to 0th row in sheet for Heading
	        	rowhead = sheet4TestData.getRow(0);
	        	
	        	for(int p=firstColumnNumber;p<=lastColumnNumber-1;p++)
	        	{
	        		System.out.println("P :"+p);
	        		row = sheet4TestData.getRow(scenarioDataToRetrieve);
	        		
	        		System.out.println("======================================");
	        		System.out.println("ROW : "+rowhead.getCell(p).getStringCellValue().trim());
	        		System.out.println("ROW : "+row.getCell(p).getStringCellValue().trim());

	        		TempRow = rowhead.getCell(p).getStringCellValue().trim();
	        		cell = row.getCell(p);
	        		
	        		if(cell.getCellType()==CellType.STRING)
		        	  {
		        		  //return cell.getStringCellValue();
		        		  //System.out.println("ITS STRING");
		        		  rowdata.put(TempRow, cell.getStringCellValue());
		        		  System.out.println("STRING ADDED........");
		        	  }
	        		// TempRow = row.getCell(1).getStringCellValue().trim();
	        		// System.out.println("Temp Row : "+TempRow);
	        		// ColumntoGet = scenarioDataToRetrieve;
	        		// cell = row.getCell(ColumntoGet);
	        		// System.out.println("Cell : "+cell);
	        	}
	        	fis4TestData.close(); 	  
    		}  
    	    	catch(NullPointerException ne)
			{
				System.out.println("Exception while reading test data from sheet "+sheetName);
				//throw ne;
			}
			catch(Exception e)
			{
				//throw e;
			}
	    	return rowdata;
    }
    
    
    
 //Added on 06-MAY-2019 Start : for getting test data from separate Workbook-> Work sheet  
    public static TreeMap<String, String> getCellDataBasedOnRowValuefromTestData(String sheetName, String senarioName) throws Exception
    {
    	
    	senarioName = senarioName.trim(); //Added for ICICIMWCustom 27-Nov-2020
    	
    	//Declaring TreeMap to Retrieve Scenario wise Row Wise Data at Once
		TreeMap<String, String> rowdata = new TreeMap<String, String>();
		
		//private variable declarations
    	int firstColumnNumber, lastColumnNumber, scenarioDataToRetrieve = -1, noofrows = 0,ColumntoGet = -1;
    	
    	//Get sheet index
    	int index = workbook4TestData.getSheetIndex(sheetName);
		System.out.println("INDEX :"+index);
		
		//If sheet is not available with name than return Blank
		if(index==-1)
		{
			System.out.println("Sheetname is not exists in Workbook");
			//return 	rowdata;
	    }
		
		//get sheet name by index
		sheet4TestData = workbook4TestData.getSheetAt(index);
    	
    	//return first columns number - 0 - Always 0
    	firstColumnNumber = sheet4TestData.getRow(0).getFirstCellNum();
    	System.out.println("firstColumnNumber : "+firstColumnNumber);

    	//returns last column number
    	lastColumnNumber = sheet4TestData.getRow(0).getLastCellNum();
    	System.out.println("lastColumnNumber : "+lastColumnNumber);

    	//return number of rows
    	noofrows=sheet4TestData.getLastRowNum();
    	System.out.println("noofrows : "+noofrows);
    	
    	//get 0th row - Header Row
    	headerRow = sheet4TestData.getRow(0);
    	try
    	{
    		// Get column number of scenarioName
	    	for (int column = (firstColumnNumber + 1); column <= lastColumnNumber-1; column++)
	    	{
		    	 System.out.println("column : "+column);
		    	 //Check input scenarioName in headerRow
		       	 if (headerRow!=null && headerRow.getCell(column).toString().equalsIgnoreCase(senarioName))
		       	{
		       		//If scenarioName found than get its Column Number
		       		senarioName = headerRow.getCell(column).toString();
		     	    System.out.println("senarioName found : "+senarioName);
		     	    //Assigning column number to private variable scenarioDataToRetrieve
		     	    scenarioDataToRetrieve = column;
		     	    //If scenario name found than Break the loop
		     	    break;
		     	}
		       	 else
		       	 {
		       		 //Till scenarioName not found continue next column till end
		       		 continue;
		       	 }
	    	}
	    	
	    		//If no scenarioName found than return Blank
	    		if(scenarioDataToRetrieve <=0)
	    		{
	    			System.out.println("senarioName is not exists in Workbook");
	    			//return rowdata;
	    		}
	    		
	    		//Navigate to 0th row in sheet for Heading
	        	row4TestData = sheet4TestData.getRow(0);
	        	
	        	//Look for RowName in each row and get its correspondence cell value
	        	for(int p=0;p<noofrows+1;p++)
	        	{
	        		System.out.println("P :"+p);
	        		row4TestData = sheet4TestData.getRow(p);
	        		
	        		 //System.out.println(row.getCell(0).getStringCellValue().trim());
            		 //System.out.println(rowName.trim());
	        		
	        		TempRow = row4TestData.getCell(0).getStringCellValue().trim();
	        		ColumntoGet = scenarioDataToRetrieve;
	        		cell4TestData = row4TestData.getCell(ColumntoGet);
	        		
	        		  //To get Cell Types and return respective cell value
		        	  if(cell4TestData.getCellType()==CellType.STRING)
		        	  {
		        		  //return cell.getStringCellValue();
		        		  //System.out.println("ITS STRING");
		        		  rowdata.put(TempRow, cell4TestData.getStringCellValue());
		        	  }
		        	  else if(cell4TestData.getCellType()==CellType.NUMERIC|| cell4TestData.getCellType()==CellType.FORMULA)
		        	  {
		                  String cellText  = String.valueOf(cell4TestData.getNumericCellValue());
		                  if (HSSFDateUtil.isCellDateFormatted(cell4TestData)) {
		                      // format in form of M/D/YY
		                      double d = cell4TestData.getNumericCellValue();
		                      Calendar cal =Calendar.getInstance();
		                      cal.setTime(HSSFDateUtil.getJavaDate(d));
		                      cellText =
		                              (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		                      cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                              cal.get(Calendar.MONTH)+1 + "/" +
		                              cellText;
		                      //System.out.println(cellText);
		                  }
		                  //return cellText;
		                  //rowdata.put(TempRow, cellText);
		        	  }else if(cell4TestData.getCellType()==CellType.BLANK)
		                  //return "";
		        		  rowdata.put(TempRow, "");
		              else
		              {
		            	rowdata.put(TempRow, String.valueOf(cell4TestData.getBooleanCellValue()));
		                //return String.valueOf(cell.getBooleanCellValue());
		              }
	             }
	        	
	       	  fis4TestData.close(); 	  
    		}  
    	    	catch(NullPointerException ne)
			{
				System.out.println("Exception while reading test data from sheet "+sheetName);
				throw ne;
			}
			catch(Exception e)
			{
				throw e;
			}
	    	return rowdata;
    }
  //Added on 06-MAY-2019 End : for getting test data from separate Workbook-> Work sheet
    
    // returns the row count in a sheet
    public int getRowCount(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1)
            return 0;
        else{
            sheet = workbook.getSheetAt(index);
            int number=sheet.getLastRowNum()+1;
            return number;
        }

    }

    // returns the data from a cell
    @SuppressWarnings("deprecation")
	public String getCellData(String sheetName,String colName,int rowNum){
        try{
            if(rowNum <=0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num=-1;
            if(index==-1)
                return "";

            sheet = workbook.getSheetAt(index);
            row=sheet.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num=i;
            }
            if(col_Num==-1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum-1);
            if(row==null)
                return "";
            cell = row.getCell(col_Num);

            if(cell==null)
                return "";
            //System.out.println(cell.getCellType());
            if(cell.getCellType()==CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){

                String cellText  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal =Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH)+1 + "/" +
                            cellText;

                    //System.out.println(cellText);

                }



                return cellText;
            }else if(cell.getCellType()==CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        }
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+colName +" does not exist in xls";
        }
    }

    // returns the data from a cell
    @SuppressWarnings("deprecation")
	public String getCellData(String sheetName,int string,int rowNum){
        try{
            if(rowNum <=0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if(index==-1)
                return "";


            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum-1);
            if(row==null)
                return "";
            cell = row.getCell(string);
            if(cell==null)
                return "";

            if(cell.getCellType()==CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){

                String cellText  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal =Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH)+1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;

                    // System.out.println(cellText);

                }



                return cellText;
            }else if(cell.getCellType()==CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+string +" does not exist  in xls";
        }
    }
    
    // returns true if data is set successfully else false
    public boolean setCellDataForInt(String sheetName,String colName,int rowNum, String data){
        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if(rowNum<=0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum=-1;
            if(index==-1)
                return false;


            sheet = workbook.getSheetAt(index);


            row=sheet.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum=i;
            }
            if(colNum==-1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum-1);
            if (row == null)
                row = sheet.createRow(rowNum-1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            // cell style
            CellStyle csPass = workbook.createCellStyle();
            csPass.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); 
            csPass.setFillPattern(FillPatternType.BIG_SPOTS);
            csPass.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
            csPass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            CellStyle csFail = workbook.createCellStyle();
            csFail.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); 
            csFail.setFillPattern(FillPatternType.BIG_SPOTS);
            csFail.setFillForegroundColor(IndexedColors.RED.getIndex());  
            csFail.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //cs.setWrapText(true);
            //cell.setCellStyle(cs);
            if(data == "PASS")
            {
            	cell.setCellStyle(csPass);
            	cell.setCellValue(data);
            }
            else if(data == "FAIL")
            {
            	cell.setCellStyle(csFail);
            	cell.setCellValue(data);
            }
            else
            {
            	cell.setCellValue(Integer.parseInt(data));
            	
            }
            
            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName,String colName,int rowNum, String data){
        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if(rowNum<=0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum=-1;
            if(index==-1)
                return false;


            sheet = workbook.getSheetAt(index);


            row=sheet.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum=i;
            }
            if(colNum==-1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum-1);
            if (row == null)
                row = sheet.createRow(rowNum-1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            // cell style
            CellStyle csPass = workbook.createCellStyle();
            csPass.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); 
            csPass.setFillPattern(FillPatternType.BIG_SPOTS);
            csPass.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
            csPass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            CellStyle csFail = workbook.createCellStyle();
            csFail.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); 
            csFail.setFillPattern(FillPatternType.BIG_SPOTS);
            csFail.setFillForegroundColor(IndexedColors.RED.getIndex());  
            csFail.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //cs.setWrapText(true);
            //cell.setCellStyle(cs);
            if(data == "PASS")
            {
            	cell.setCellStyle(csPass);
            	cell.setCellValue(data);
            }
            else if(data == "FAIL")
            {
            	cell.setCellStyle(csFail);
            	cell.setCellValue(data);
            }
            else
            {
            	cell.setCellValue(data);
            }
            
            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
    //Added on 18-Mar-2021 Start
    // returns true if data is set successfully else false
    public boolean setCellDataForTestData(String sheetName,String colName,int rowNum, String data){
        try{
            fis4TestData = new FileInputStream(TestBase.rootPath+"\\ExcelData\\TestData.xlsx");
            workbook4TestData = new XSSFWorkbook(fis4TestData);

            if(rowNum<=0)
                return false;

            int index = workbook4TestData.getSheetIndex(sheetName);
            int colNum=-1;
            if(index==-1)
                return false;


            sheet4TestData = workbook4TestData.getSheetAt(index);


            row=sheet4TestData.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum=i;
            }
            if(colNum==-1)
                return false;

            sheet4TestData.autoSizeColumn(colNum);
            row = sheet4TestData.getRow(rowNum-1);
            if (row == null)
                row = sheet4TestData.createRow(rowNum-1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            // cell style
            CellStyle csPass = workbook4TestData.createCellStyle();
            csPass.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); 
            csPass.setFillPattern(FillPatternType.BIG_SPOTS);
            csPass.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
            csPass.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            CellStyle csFail = workbook4TestData.createCellStyle();
            csFail.setFillBackgroundColor(IndexedColors.YELLOW.getIndex()); 
            csFail.setFillPattern(FillPatternType.BIG_SPOTS);
            csFail.setFillForegroundColor(IndexedColors.RED.getIndex());  
            csFail.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //cs.setWrapText(true);
            //cell.setCellStyle(cs);
            if(data == "PASS")
            {
            	cell.setCellStyle(csPass);
            	cell.setCellValue(data);
            }
            else if(data == "FAIL")
            {
            	cell.setCellStyle(csFail);
            	cell.setCellValue(data);
            }
            else
            {
            	cell.setCellValue(data);
            }
            
            fileOut = new FileOutputStream(TestBase.rootPath+"\\ExcelData\\TestData.xlsx");

            workbook4TestData.write(fileOut);

            fileOut.close();

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
  //Added on 18-Mar-2021 Start
    
    

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
        //System.out.println("setCellData setCellData******************");
        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if(rowNum<=0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum=-1;
            if(index==-1)
                return false;


            sheet = workbook.getSheetAt(index);
            //System.out.println("A");
            row=sheet.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
                    colNum=i;
            }

            if(colNum==-1)
                return false;
            sheet.autoSizeColumn(colNum); //ashish
            row = sheet.getRow(rowNum-1);
            if (row == null)
                row = sheet.createRow(rowNum-1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);
            @SuppressWarnings("unused")
			XSSFCreationHelper createHelper = workbook.getCreationHelper();

            //cell style for hyperlinks
            //by default hypelrinks are blue and underlined
            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(XSSFFont.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            //hlink_style.setWrapText(true);

            //''''''''iliyas
           /* XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style)*/;
            ///////////iliyas 
            
            
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }



    // returns true if sheet is created successfully else false
    public boolean addSheet(String  sheetname){

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is removed successfully else false if sheet does not exist
    public boolean removeSheet(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1)
            return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    // returns true if column is created successfully
    @SuppressWarnings("deprecation")
	public boolean addColumn(String sheetName,String colName){
        //System.out.println("**************addColumn*********************");

        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if(index==-1)
                return false;

            XSSFCellStyle style = workbook.createCellStyle();
            //style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index); Commented on 04Sept2020JIOGIT
            //iliyas commented style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            sheet=workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);

            //cell = row.getCell();
            //if (cell == null)
            //System.out.println(row.getLastCellNum());
            if(row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;


    }
    // removes a column and all the contents
    @SuppressWarnings("deprecation")
	public boolean removeColumn(String sheetName, int colNum) {
        try{
            if(!isSheetExist(sheetName))
                return false;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet=workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            //style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index); Commented on 04Sept2020JIOGIT
            @SuppressWarnings("unused")
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
            //iliyas commentstyle.setFillPattern(HSSFCellStyle.NO_FILL);



            for(int i =0;i<getRowCount(sheetName);i++){
                row=sheet.getRow(i);
                if(row!=null){
                    cell=row.getCell(colNum);
                    if(cell!=null){
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
    // find whether sheets exists
    public boolean isSheetExist(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1){
            index=workbook.getSheetIndex(sheetName.toUpperCase());
            if(index==-1)
                return false;
            else
                return true;
        }
        else
            return true;
    }

    // returns number of columns in a sheet
    public int getColumnCount(String sheetName){
        // check if sheet exists
        if(!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if(row==null)
            return -1;

        return row.getLastCellNum();



    }
    //String sheetName, String testCaseName,String keyword ,String URL,String message
    public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){
        //System.out.println("ADDING addHyperLink******************");

        url=url.replace('\\', '/');
        if(!isSheetExist(sheetName))
            return false;

        sheet = workbook.getSheet(sheetName);

        for(int i=2;i<=getRowCount(sheetName);i++){
            if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
                //System.out.println("**caught "+(i+index));
                setCellData(sheetName, screenShotColName, i+index, message,url);
                break;
            }
        }


        return true;
    }
    public int getCellRowNum(String sheetName,String colName,String cellValue){

        for(int i=2;i<=getRowCount(sheetName);i++){
            if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
                return i;
            }
        }
        return -1;

    }
    
    //PPAdded on 02-Apr-2019 Start
	public static String[][] getExcelDataInArrayForProcessing(String fileName, String sheetName)
	{
		logger.info("Started===getExcelDataInArrayForProcessing===");
	try	  
		{
	      fis = new FileInputStream(fileName);
          workbook = new XSSFWorkbook(fis);
          int index = workbook.getSheetIndex(sheetName);
          if(index==-1)
          logger.info(sheetName+ " : is not available in the Excel");    
          else
          sheet = workbook.getSheetAt(index);
          
			//Count number of active rows
			int totalRows = sheet.getLastRowNum()+1;
			//Count number of active columns 
			int totalCoulmns = sheet.getRow(0).getLastCellNum();
			//Create array of rows and columns
			dataSets = new String[totalRows-1][totalCoulmns];
			//Iterate through rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			int t = 0;
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if(i++ !=0) {
					int k = t;
					t++;
					//For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						//Check the cell type and format accordingly
						switch(cell.getCellType()) {
						case NUMERIC:
							System.out.print(k+",");
							System.out.print(j+",");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case STRING:
							System.out.print(k+",");
							System.out.print(j+",");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case BOOLEAN:
							System.out.print(k+",");
							System.out.print(j+",");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case FORMULA:
							System.out.print(k+",");
							System.out.print(j+",");
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						
						}
					}
					System.out.println("");
				}
			}
			fis.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
			logger.info("Error in reading excel : "+e.getMessage());
		}
	return dataSets;		
}
		//PPAdded on 02-Apr-2019 End
	
	
	public static String[][] getExcelDataInArrayForProcessingCustomizedForNoOfDataSetIteration(String fileName, String sheetName, int noOfDataSet)
	{
		logger.info("Started===getExcelDataInArrayForProcessing===");
	try	  
		{
	      fis = new FileInputStream(fileName);
          workbook = new XSSFWorkbook(fis);
          int index = workbook.getSheetIndex(sheetName);
          if(index==-1)
          logger.info(sheetName+ " : is not available in the Excel");    
          else
          sheet = workbook.getSheetAt(index);
          
			//Count number of active rows
			int totalRows = sheet.getLastRowNum()+1;
			//Count number of active columns 
			int totalCoulmns = sheet.getRow(0).getLastCellNum();
			//Create array of rows and columns
			//dataSets = new String[totalRows-1][totalCoulmns]; //Commented on 06-Dec-2020
			dataSets = new String[noOfDataSet][totalCoulmns]; //Added on 06-Dec-2020
			
			//Iterate through rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			int t = 0;
			int tempIterator = 1;
			while(rowIterator.hasNext()) {
				
				System.out.println("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY :"+tempIterator+"   "+noOfDataSet);
				
				if(tempIterator == (noOfDataSet+2))
				{
					System.out.println("IF..............................................................");
					System.out.println(tempIterator == (noOfDataSet+2));
					System.out.println("Continue");
					//return dataSets;
					break;
				}
				else
				{
					System.out.println("ELSE..............................................................");
					Row row = rowIterator.next();
					if(i++ !=0) {
						int k = t;
						t++;
						//For each row, iterate through all the columns
						Iterator<Cell> cellIterator = row.cellIterator();
						int j = 0;
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							//Check the cell type and format accordingly
							switch(cell.getCellType()) {
							case NUMERIC:
								System.out.print(k+",");
								System.out.print(j+",");
								dataSets[k][j++] = cell.getStringCellValue();
								System.out.println(cell.getStringCellValue());
								break;
							case STRING:
								System.out.print(k+",");
								System.out.print(j+",");
								dataSets[k][j++] = cell.getStringCellValue();
								System.out.println(cell.getStringCellValue());
								break;
							case BOOLEAN:
								System.out.print(k+",");
								System.out.print(j+",");
								dataSets[k][j++] = cell.getStringCellValue();
								System.out.println(cell.getStringCellValue());
								break;
							case FORMULA:
								System.out.print(k+",");
								System.out.print(j+",");
								dataSets[k][j++] = cell.getStringCellValue();
								System.out.println(cell.getStringCellValue());
								break;
							
							}
						}
						System.out.println("");
					}
				}
				
				
				tempIterator++;
			}
			fis.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
			logger.info("Error in reading excel : "+e.getMessage());
		}
	return dataSets;		
}
	
	
	
	
	//Added for 06-MAY-2019 START
	public boolean setHeader(String sheetName) 
	{
		try
		{
			logger.info("Creating empty sheet for report");
			 int index = workbook.getSheetIndex(sheetName);
			 System.out.println("sheetName "+sheetName);
			 System.out.println("index "+index);
	         sheet = workbook.getSheetAt(index);
	         Row header = sheet.createRow(0);
	         header.createCell((short) 0).setCellValue("TestCase_Id");
	         header.createCell((short) 1).setCellValue("Test case name");
	         header.createCell((short) 2).setCellValue("Test Desc");
	         header.createCell((short) 3).setCellValue("Test Script Name");
	         header.createCell((short) 4).setCellValue("Environment");
	         header.createCell((short) 5).setCellValue("Device");
	         header.createCell((short) 6).setCellValue("OS");
	         header.createCell((short) 7).setCellValue("Version");
	         header.createCell((short) 8).setCellValue("Browser");
	         header.createCell((short) 9).setCellValue("Status"); // Updated Number on 18-Mar-2021
	         header.createCell((short) 10).setCellValue("TestCaseRemarks");  // Added on 18-Mar-2021
	         header.createCell((short) 11).setCellValue("ExecutedAt");// Updated Number on 18-Mar-2021
	         fileOut = new FileOutputStream(path);
	         workbook.write(fileOut);
	         fileOut.close();
	    	return true;
		}
		catch(Exception e)
		{
			logger.info("Error in reading excel : "+e.getMessage());
			return false;
		}
	}
	//Added for 06-MAY-2019 START
	
	//Added for 08-MAY-2019 START
	public void generatePivotReport(String sheetName) throws IOException
	{
		 int index = workbook.getSheetIndex(sheetName);
		 System.out.println("sheetName "+sheetName);
		 System.out.println("index "+index);
         sheet = workbook.getSheetAt(index);
         int firstRow = sheet.getFirstRowNum();
         int lastRow = sheet.getLastRowNum();
         int firstCol = sheet.getRow(0).getFirstCellNum();
         int lastCol = sheet.getRow(0).getLastCellNum();
         CellReference topLeft = new CellReference(firstRow, firstCol);
         CellReference botRight = new CellReference(lastRow, lastCol - 1);
         //AreaReference aref = new AreaReference(topLeft, botRight); Commented on 04Sept2020JIOGIT
         CellReference pos = new CellReference(firstRow + 4, lastCol + 1);
        // XSSFPivotTable pivotTable = sheet.createPivotTable(aref, pos, sheet); Commented on 04Sept2020JIOGIT
    //pivotTable.addRowLabel(3);
        // pivotTable.addRowLabel(9); //Commented on 04Sept2020JIOGIT
         //pivotTable.addColumnLabel(DataConsolidateFunction.COUNT_NUMS, //Commented on 04Sept2020JIOGIT
                      // 9, "Count of Status"); //Commented on 04Sept2020JIOGIT
//pivotTable.addColumnLabel(DataConsolidateFunction.COUNT, 9);
         FileOutputStream fileOut = new FileOutputStream(path);
         workbook.write(fileOut);
         fileOut.close();
         System.out.println("HIHIHIHI");
	}

/*
    // to run this on stand alone
    public static void main(String arg[]) throws IOException{

        //System.out.println(filename);
        Xls_Reader datatable = null;


        //datatable = new Xls_Reader("src/Framework_XL_Files/Controller.xlsx");
        //datatable = new Xls_Reader("D:/Selenium/WS/USFDADataScience_rnd/ExcelData/TestDataWorkbook.xlsx");
        
        datatable = new Xls_Reader("D:/Selenium/WS/USFDADataScience_rnd/ExcelData/ReportFormat.xlsx");
        
        for(int col=0 ;col< datatable.getColumnCount("TestCases"); col++)
        {
            System.out.println(datatable.getCellData("TestCases", col, 1));
        }
        
        
        
        
        
        
        System.out.println("datatable.getColumnCount(TestCases) : "+datatable.getColumnCount("TestCases"));
        System.out.println("datatable.getRowCount(TestCases) : "+datatable.getRowCount("TestCases"));
       
        System.out.println("===========================For Reference=======================================");
        System.out.println("datatable.getColumnCount(TestCases) : "+datatable.getColumnCount("TestCases"));
        System.out.println("datatable.getRowCount(TestCases) : "+datatable.getRowCount("TestCases"));
        System.out.println("datatable.getCellData(TestCases, 0, 1) "+datatable.getCellData("TestCases", 0, 1));
        System.out.println("datatable.getCellData(TestCases, 1, 1) "+datatable.getCellData("TestCases", 1, 1));
        System.out.println("datatable.getCellData(TestCases, 2, 1) "+datatable.getCellData("TestCases", 2, 1));
        System.out.println("datatable.getCellData(TestCases, 3, 1) "+datatable.getCellData("TestCases", 3, 1));
        System.out.println("datatable.getCellData(TestCases, 0, 2) "+datatable.getCellData("TestCases", 0, 2));
        System.out.println("datatable.getCellData(TestCases, 1, 2) "+datatable.getCellData("TestCases", 1, 2));
        System.out.println("datatable.getCellData(TestCases, 2, 2) "+datatable.getCellData("TestCases", 2, 2));
        System.out.println("datatable.getCellData(TestCases, 3, 2) "+datatable.getCellData("TestCases", 3, 2));
        System.out.println("datatable.getCellData(TestCases, 0, 3) "+datatable.getCellData("TestCases", 0, 3));
        System.out.println("datatable.getCellData(TestCases, 1, 3) "+datatable.getCellData("TestCases", 1, 3));
        System.out.println("datatable.getCellData(TestCases, 2, 3) "+datatable.getCellData("TestCases", 2, 3));
        System.out.println("datatable.getCellData(TestCases, 3, 3) "+datatable.getCellData("TestCases", 3, 3));
        System.out.println("******************************************************************************************");
        System.out.println("datatable.getCellData(TestCases, Test Script Name, 2) "+datatable.getCellData("TestCases", "Test Script Name", 2));
        System.out.println("datatable.getCellData(TestCases, Test Script Name, 3) "+datatable.getCellData("TestCases", "Test Script Name", 3));
        System.out.println("******************************************************************************************");
        System.out.println("datatable.getCellRowNum(TestCases, Test Script Name, TS_001) "+datatable.getCellRowNum("TestCases", "Test Script Name", "TS_001"));
        System.out.println("datatable.getCellRowNum(TestCases, Test Script Name, TS_002) "+datatable.getCellRowNum("TestCases", "Test Script Name", "TS_002"));
        System.out.println("===========================For Reference=======================================");
               
         
        boolean b = datatable.setCellData("SetData","TestCase_Id",2, "TS_001");
        datatable.setCellData("SetData","Test case name",2, "Login verification");
        datatable.setCellData("SetData","Test Desc",2, "Verify the login for application with valid credentials");
        datatable.setCellData("SetData","Test Script Name",2, "TS_001");
        datatable.setCellData("SetData","Environment",2, "Desktop");
        datatable.setCellData("SetData","Device",2, "NA");
        datatable.setCellData("SetData","OS version",2, "Windows7Professional");
        datatable.setCellData("SetData","Browser",2, "Chrome");
         
        
        
        
        if(b == true)
        System.out.println("TRUE");
        else
        System.out.println("FALSE");	
    }

*/
	
	
//============================Deepthi Read Excel Data Coloumn value============================//
	
	/*public ArrayList<String> extractExcelContentByColumnIndex(int columnIndex){
        ArrayList<String> columndata = null;
        try {
            File f = new File("xlsTestDataPath");
            FileInputStream ios = new FileInputStream(f);
            XSSFWorkbook workbook = new XSSFWorkbook(ios);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            columndata = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(row.getRowNum() > 0){ //To filter column headings
                        if(cell.getColumnIndex() == columnIndex){// To match column index
                            switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                columndata.add(cell.getNumericCellValue()+"");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                columndata.add(cell.getStringCellValue());
                                break;
                            }
                        }
                    }
                }
            }
            ios.close();
            System.out.println(columndata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columndata;
    }   */
	

}