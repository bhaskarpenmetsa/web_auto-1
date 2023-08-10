package com.falcon.excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.falcon.driver.TestBase;

public class Excel_Reader {
	
		public String dataSets[][] = null;
		
		public ArrayList<String> readTestResultIntoList() throws IOException
		{
			ArrayList<String> summaryList = new ArrayList<String>();
			FileInputStream excelFile = null;
			 try {
				 	
		            excelFile = new FileInputStream(new File(TestBase.rootPath+TestBase.getPropertyValue("xlsPath")));
		            Workbook workbook = new XSSFWorkbook(excelFile);
		            //Sheet datatypeSheet = workbook.getSheetAt(0);
		            Sheet datatypeSheet = workbook.getSheet("TestReport");
		            Iterator<Row> iterator = datatypeSheet.iterator();
		            while (iterator.hasNext()) {
		                Row currentRow = iterator.next();
		                Iterator<Cell> cellIterator = currentRow.iterator();
		                System.out.println("=============");
		                String s = "";
		                while (cellIterator.hasNext()) {
		                    Cell currentCell = cellIterator.next();
		                    //getCellTypeEnum shown as deprecated for version 3.15
		                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
		                    
		                    if (currentCell.getCellType() == CellType.STRING) {
		                        System.out.println(currentCell.getStringCellValue() + "000000");
		                        s = s+currentCell.getStringCellValue() + "000000";
		                        //summaryList.add(currentCell.getStringCellValue() + "|");
		                        
		                        
		                        
		                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
		                        System.out.println(currentCell.getNumericCellValue() + "000000");
		                       //summaryList.add(currentCell.getStringCellValue() + "|");
		                        s = s+String.valueOf(currentCell.getNumericCellValue()) + "000000";
		                    }
		                }
		                summaryList.add(s);
		                s = "";
		                System.out.println();
		            }
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			 finally
			 {
				 excelFile.close();
			 }
			return summaryList;
		}
		
		public void pieChart(int a, int b, int c, int d, int e, int f, String title) throws FileNotFoundException, IOException 
		{
			System.out.println("Start");
			FileInputStream file = null;
			String excellocation = TestBase.rootPath+TestBase.getPropertyValue("xlsPath");
			try
			{
				file = new FileInputStream(new File(excellocation));
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheet("TestSummary");
				XSSFDrawing drawing = sheet.createDrawingPatriarch();
				XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, c, d, e, f);
				XSSFChart chart = drawing.createChart(anchor);
				chart.setTitleText(title);
				chart.setTitleOverlay(false);
				XDDFChartLegend legend = chart.getOrAddLegend();
				legend.setPosition(LegendPosition.TOP_RIGHT);
				XDDFDataSource<String> testSummary = XDDFDataSourcesFactory.fromStringCellRange(sheet,
						new CellRangeAddress(0, 0, a, b));
				XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
						new CellRangeAddress(1, 1, a, b));
				XDDFChartData data = chart.createData(ChartTypes.PIE3D, null, null);// chart.createData(ChartTypes.PIE,
				data.setVaryColors(true);
				data.addSeries(testSummary, values);
				chart.plot(data);
				// Write output to an excel file
				FileOutputStream fileOut = new FileOutputStream(excellocation);
				workbook.write(fileOut);
				System.out.println("DONE");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				file.close();
			}
		}
		 
		public static int chromeCount;
		public static int fireFoxCount;
		public static int IECount;
		public static int desktopCount;
		public static int mobileCount;
		public static int passCount;
		public static int failCount;
		public static int totalTestCaseCount;
		
		public void getExecutionResultSummaryforChart(ArrayList<String> str , String chromeKey, String FFKey, String IEKey, String desktopKey, String mobileKey, String passKey, String failKey){
			 	
			
					totalTestCaseCount = str.size()-1;
			
			 		for (int i = 0; i<str.size();i++)
			 		{
			 			
			 			System.out.println("Hey "+str.get(i));
			 			System.out.println("Check "+chromeKey);
			 			System.out.println("Check "+FFKey);
			 			System.out.println("Check "+IEKey);
			 			System.out.println("Check "+IEKey);
			 			System.out.println("Check "+desktopKey);
			 			System.out.println("Check "+passKey);
			 			System.out.println("Check "+failKey);
			 			
			 			if(str.get(i).contains(chromeKey)) 
			 			{
			 				chromeCount++;
			 			}
			 			if(str.get(i).contains(FFKey))
			 			{
			 				fireFoxCount++;
			 			}
			 			if(str.get(i).contains(IEKey))
			 			{
			 				IECount++;
			 			}
			 			if(str.get(i).contains(desktopKey))
			 			{
			 				desktopCount++;
			 			}
			 			if(str.get(i).contains(mobileKey))
			 			{
			 				mobileCount++;
			 			}
			 			if(str.get(i).contains(passKey))
			 			{
			 				passCount++;
			 			}
			 			if(str.get(i).contains(failKey))
			 			{
			 				failCount++;
			 			}
			 		}
			 		System.out.println("TEST SUMMARYYYYYYYYYYYYYY");
			 		System.out.println("chromeCount : "+ chromeCount);
			 		System.out.println("fireFoxCount : "+ fireFoxCount);
			 		System.out.println("IECount : "+ IECount);
			 		System.out.println("desktopCount : "+ desktopCount);
			 		System.out.println("mobileCount : "+ mobileCount);
			 		System.out.println("passCount : "+ passCount);
			 		System.out.println("failCount : "+ failCount);
			 		System.out.println("totalTestCaseCount : "+ totalTestCaseCount);
			 		System.out.println("TEST SUMMARYYYYYYYYYYYYYY");
			 		
		 }
		
		public String[][] getExcelData(String excellocation, String sheetName)
		{
		try	  
		{
		
			FileInputStream file = new FileInputStream(new File(excellocation));
			//Create workbook instance holding reference of .xlsx
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//Get the first / desired sheet from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			//Count number of active rows
			int totalRows = sheet.getLastRowNum()+1;
			//Count number of active columns 
			int totalCoulmns = sheet.getRow(0).getLastCellNum();
			//Create arrey of rows and columns
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
			file.close();
			//return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSets;		
	}
		
		public static void main(String[] args) throws Exception {
			
			//SEt 1
			
			String typeOfDevices[][] = null;
			
			//String excellocation = "D:/Selenium/WS/USFDADataScience_rnd/ExcelData/AppiumReportFormat.xlsx";
			String excellocation = "D:/Selenium/WS/Falcon/ExcelData/TestCases.xlsx";
			String sheetName = "TypeofDevice";
			Excel_Reader exlr = new Excel_Reader();
			typeOfDevices = exlr.getExcelData(excellocation, sheetName);
			
			
			System.out.println(typeOfDevices.length);
			
			for(int i=0;i<typeOfDevices.length;i++)
			{
				System.out.println(typeOfDevices[i][0]);
				System.out.println(typeOfDevices[i][1]);
				System.out.println(typeOfDevices[i][2]);
				System.out.println(typeOfDevices[i][3]);
				System.out.println(typeOfDevices[i][4]);	
			}
			
			/*
			//Set 2
			ExcelUtilForRowWiseReadMap_bkp euMap = new ExcelUtilForRowWiseReadMap_bkp();
			
			List<String> getListOfScenarios = new ArrayList<String>();
			try {
				euMap.setExcelFile("D:\\Selenium\\WS\\USFDADataScience_rnd\\ExcelData\\AppiumReportFormat.xlsx");
				
				getListOfScenarios = euMap.getListofScenariostobeExecuted("TestCases");
				System.out.println(getListOfScenarios);
				
				for(int i=0;i<getListOfScenarios.size();i++)
				{
					System.out.println("Value is : "+getListOfScenarios.get(i));
				}
			
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
			*/
	
			
			
			
}
}