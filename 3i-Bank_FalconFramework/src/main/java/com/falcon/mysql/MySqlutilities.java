package com.falcon.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.falcon.driver.TestBase;
import com.falcon.response.excel.ResponseExcel;


public class MySqlutilities {
public static void writeReportToMySQL() {
		
		//String jdbcURL = "jdbc:mysql://localhost:3306/flexib_db";
		String jdbcURL = "jdbc:mysql://14.99.175.107:17633/icici_direct";
	    String username = "root";
	    String password = "root";

	  //String excelFilePath =ResponseExcel.filePath;
	   
	    String excelFilePath ="D:\\Responsetime.xlsx";
	   //System.out.println("Path"+excelFilePath);
	   

	    int batchSize = 20;

	    Connection connection = null;

	    try {
	        long start = System.currentTimeMillis();
	         
	        FileInputStream inputStream = new FileInputStream(excelFilePath);

	        Workbook workbook = new HSSFWorkbook(inputStream);

	        Sheet firstSheet = workbook.getSheetAt(0);
	        Iterator<Row> rowIterator = firstSheet.iterator();

	        connection = DriverManager.getConnection(jdbcURL, username, password);
	        connection.setAutoCommit(false);

	        String sql = "INSERT INTO icici_direct.icici_mobile_android(Page_name,Response_time,Executed_time) VALUES (?,?,CURRENT_TIME())";
	        PreparedStatement statement = connection.prepareStatement(sql);    
	         
	        int count = 0;
	         
	        rowIterator.next(); // skip the header row
	         
	        while (rowIterator.hasNext()) {
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();

	            while (cellIterator.hasNext()) {
	                Cell nextCell = cellIterator.next();

	                int columnIndex = nextCell.getColumnIndex();

	                switch (columnIndex) {
	                case 0:
	                    String Page_name = nextCell.getStringCellValue();
	                    statement.setString(1, Page_name);
	                    break;
	                case 1:
	                	String resvalue=nextCell.getStringCellValue();
	                	//float Execution_time=Float.parseFloat("23.6");  
	                    float Execution_time = Float.parseFloat(resvalue);	
	                  System.out.println(Execution_time);
	                    statement.setFloat(2, Execution_time);
	                    break;
	                }

	            }
	             
	            statement.addBatch();
	             
	            if (count % batchSize == 0) {
	                statement.executeBatch();
	            }              

	        }

	        workbook.close();
	         
	        // execute the remaining queries
	        statement.executeBatch();

	        connection.commit();
	        connection.close();
	         
	        long end = System.currentTimeMillis();
	        System.out.printf("Import done in %d ms\n", (end - start));
	         
	    } catch (IOException ex1) {
	        System.out.println("Error reading file");
	        ex1.printStackTrace();
	    } catch (SQLException ex2) {
	        System.out.println("Database error");
	        ex2.printStackTrace();
	    }

		
	}

public static void main(String[] args) {
	
	MySqlutilities bbb=new MySqlutilities();
	bbb.writeReportToMySQL();
			
	
}
}
