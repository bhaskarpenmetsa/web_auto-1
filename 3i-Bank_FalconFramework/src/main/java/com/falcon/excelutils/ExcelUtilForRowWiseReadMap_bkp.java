package com.falcon.excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilForRowWiseReadMap_bkp 
{
	public Workbook wb;
	public Sheet sh;
	public Row row;
	public Row headerRow;
	public Cell cell;
	public String TempRow;
	
	public File f;
	public FileInputStream is;
	
	public void setExcelFile(String filepath) throws IOException{
		
		System.out.println(filepath);
		
		f = new File(filepath);
		is = new FileInputStream(f);
		if(filepath.endsWith(".xlsx"))
		{
			wb = new XSSFWorkbook(is);
		}
		else if(filepath.endsWith(".xls"))
		{
			wb = new HSSFWorkbook(is);
		}
	}
	
	public  List<String> getListofScenariostobeExecuted(String sheetName) throws Exception 
	{
		//Declaration of List Array - to get list of Scenarios in the sheet 
		List<String> Scenarios = new ArrayList<String>();
		
		//private variable declarations
    	int firstColumnNumber, lastColumnNumber;
    	String senarioName;

		//Get sheet index
    	int index = wb.getSheetIndex(sheetName);
		
		//If sheet is not available with name than return Blank
		if(index==-1)
		{
			System.out.println("Sheetname is not exists in Workbook");
		}
		
		//get sheet name by index
		sh = wb.getSheetAt(index);
    	
    	//return first columns number - 0 - Always 0
    	firstColumnNumber = sh.getRow(0).getFirstCellNum();
    	System.out.println("firstColumnNumber : "+firstColumnNumber);

    	//returns last column number
    	lastColumnNumber = sh.getRow(0).getLastCellNum();
    	System.out.println("lastColumnNumber : "+lastColumnNumber);

    	//get 0th row - Header Row
    	headerRow = sh.getRow(0);
    	
    	try
    	{
    		// To get list of scenarioName in List Array
	    	for (int column = (firstColumnNumber + 1); column <= lastColumnNumber-1; column++)
	    	{
		    		senarioName = headerRow.getCell(column).toString();
		       		Scenarios.add(senarioName);
	    	}
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
		return Scenarios;
	}
	
	
	
	
	//Declaring variable for row iteration 
	public static int currentIterationRow = 0;
	
	public static void ReadScenarioDataIterative(String scenarioName)
	{
		
	}
	
	
	public TreeMap<String, String> getCellDataBasedOnRowValue(String sheetName, String senarioName) throws Exception
    {
    	//Declaring TreeMap to Retrieve Scenario wise Row Wise Data at Once
		TreeMap<String, String> rowdata = new TreeMap<String, String>();
		
		//private variable declarations
    	int firstColumnNumber, lastColumnNumber, scenarioDataToRetrieve = -1, noofrows = 0,ColumntoGet = -1;
    	
    	//Get sheet index
    	int index = wb.getSheetIndex(sheetName);
		//System.out.println("INDEX :"+index);
		
		//If sheet is not available with name than return Blank
		if(index==-1)
		{
			System.out.println("Sheetname is not exists in Workbook");
			//return 	rowdata;
	    }
		
		//get sheet name by index
		sh = wb.getSheetAt(index);
    	
    	//return first columns number - 0 - Always 0
    	firstColumnNumber = sh.getRow(0).getFirstCellNum();
    	System.out.println("firstColumnNumber : "+firstColumnNumber);

    	//returns last column number
    	lastColumnNumber = sh.getRow(0).getLastCellNum();
    	System.out.println("lastColumnNumber : "+lastColumnNumber);

    	//return number of rows
    	noofrows=sh.getLastRowNum();
    	System.out.println("noofrows : "+noofrows);
    	
    	//get 0th row - Header Row
    	headerRow = sh.getRow(0);
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
	        	row = sh.getRow(0);
	        	
	        	//Look for RowName in each row and get its correspondence cell value
	        	for(int p=0;p<noofrows+1;p++)
	        	{
	        		System.out.println("P :"+p);
	        		row = sh.getRow(p);
	        		
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
	        	
	       	  is.close(); 	  
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
}