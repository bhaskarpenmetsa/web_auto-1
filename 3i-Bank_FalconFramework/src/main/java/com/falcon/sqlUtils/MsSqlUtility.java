package com.falcon.sqlUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.falcon.driver.TestBase;


public class MsSqlUtility extends TestBase
{
	public static final Logger logger = Logger.getLogger(MsSqlUtility.class.getName());
	
	 // JDBC driver name and database URL
    public static Connection connObj;
    public static Statement statement;
    //public static String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=MMTestDB;integratedSecurity=true";
    public static void getDbConnection() {
	 try {
		 	 String userName = getPropertyValue("SQLUserName");
			 String password = getPropertyValue("SQLPassword");
			 String url = "jdbc:sqlserver://"+getPropertyValue("SQLHostName")+":1433;databaseName="+getPropertyValue("SQLDatabaseName");
			 
			 //String urlWinAuth = "jdbc:sqlserver://"+getPropertyValue("SQLHostName")+":1433;databasename="+getPropertyValue("SQLDatabaseNameToGetCustId")+";integratedsecurity=true";
			 
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 connObj = DriverManager.getConnection(url, userName, password);
			 /*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         connObj = DriverManager.getConnection(JDBC_URL);*/
	         if(connObj != null) 
	         {
	             DatabaseMetaData metaObj = (DatabaseMetaData) connObj.getMetaData();
	             System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());
	             System.out.println("connected"); 
	             logger.info("--------Connection to SQL Server successfully--------");
	         }
     	 }catch(Exception sqlException) 
	 	{
     	  logger.info("--------sqlException while connecting MS SQL Server--------: "+sqlException.getMessage());
     	  sqlException.printStackTrace();
	 	}
	}
    public static void getDbConnection1() {
   	 try {
   		 	 String userName = getPropertyValue("SQLUserName");
   			 String password = getPropertyValue("SQLPassword");
   			 String urlWinAuth = "jdbc:sqlserver://"+getPropertyValue("SQLHostName")+":1433;databasename="+getPropertyValue("SQLDatabaseNameToGetCustId")+";integratedsecurity=true";
   			 
   			 DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
   			 
   			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   			 connObj = DriverManager.getConnection(urlWinAuth);
   			 
   		     if(connObj != null) 
   	         {
   	             DatabaseMetaData metaObj = (DatabaseMetaData) connObj.getMetaData();
   	             System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());
   	             System.out.println("connected"); 
   	             logger.info("--------Connection to SQL Server successfully--------");
   	         }
        	 }catch(Exception sqlException) 
   	 	{
        	  logger.info("--------sqlException while connecting MS SQL Server--------: "+sqlException.getMessage());
        	  sqlException.printStackTrace();
   	 	}
   	 
   	}
    
    public static void getDbConnection2() {
      	 try {
      		 	 String userName = getPropertyValue("SQLUserName");
      			 String password = getPropertyValue("SQLPassword");
      			 String urlWinAuth = "jdbc:sqlserver://"+getPropertyValue("SQLHostName")+":1433;databasename="+getPropertyValue("SQLDatabaseNameToRunTuQuery")+";integratedsecurity=true";
      			 
      			 DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
      			 
      			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      			 connObj = DriverManager.getConnection(urlWinAuth);
      			 
      		     if(connObj != null) 
      	         {
      	             DatabaseMetaData metaObj = (DatabaseMetaData) connObj.getMetaData();
      	             System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());
      	             System.out.println("connected"); 
      	             logger.info("--------Connection to SQL Server successfully--------");
      	         }
           	 }catch(Exception sqlException) 
      	 	{
           	  logger.info("--------sqlException while connecting MS SQL Server--------: "+sqlException.getMessage());
           	  sqlException.printStackTrace();
      	 	}
      	 
      	}
    
    
    public static long getCustomerId(String firstName, String lastName, String tableName)
    {
    	long sCustomerId = 0;
    	try
    	{	 statement = connObj.createStatement();
    	     String queryString = "select CustomerId from vitacrm39.."+tableName+""
    	     		+ " where firstname = '"+firstName+"' and lastname = '"+lastName+"' order by createddate desc";
    	     
    	     System.out.println(queryString);
    	     
    	     ResultSet rs = statement.executeQuery(queryString);
    	     while (rs.next()) {
    	    	 	sCustomerId =	rs.getLong("CustomerId");
    	     }
    	        logger.info("--------Table data retrieving successfully--------");
    	        System.out.println("Table data retrieving successfully");
    	}
    	catch(Exception e)
    	{
    		System.out.println("EXCEPTION WHILE READING DATA FROM TABLE "+e.getMessage());
         	logger.info("--------EXCEPTION while retrieving data from MS SQL Server Table--------: "+e.getMessage());
    		e.printStackTrace();
    	}
    	finally 
    	{
    		try {
				connObj.close();
			} catch (SQLException e) {
				System.out.println("EXCEPTION WHILE Closing Database Connection"+e.getMessage());
	         	logger.info("--------EXCEPTION WHILE Closing Database Connection--------: "+e.getMessage());
				e.printStackTrace();
			}
    	}
		return sCustomerId;
    }
    
    public static void executeTUQuery(long biCustomerId) throws SQLException
    {
     	try
     	{
        	CallableStatement statement = null;
        	statement = connObj.prepareCall("{call dbo.stpr_TuQueryRunBasedonCustomerIdbyPP(?)}");
        	statement.setLong("biCustomerId", biCustomerId);
        	boolean TUQueryResult = statement.execute();
        	if(TUQueryResult == true)
        	{
        		System.out.println("Stored procedure executed successfully with "+biCustomerId);
        		logger.info("--------Stored procedure executed successfully with "+biCustomerId+"--------");
        	}
        	else
        	{
        		System.out.println("Stored procedure unsuccessfull with "+biCustomerId);
        		logger.info("--------Stored procedure unsuccessfull with "+biCustomerId+"--------");
        	}
     	}
     	catch(Exception e)
     	{
     		System.out.println("EXCEPTION WHILE EXECUTING TU STORED PROCEDURE "+e.getMessage());
         	logger.info("--------EXCEPTION WHILE EXECUTING TU STORED PROCEDURE--------: "+e.getMessage());
     	}
     	
     	finally 
    	{
     		if (statement != null) {
                try {
                	statement.close();
                } catch (SQLException ex) {
                	System.out.println("EXCEPTION WHILE Closing Callable Statement"+ex.getMessage());
                 	logger.info("--------EXCEPTION WHILE Closing Callable Statement--------: "+ex.getMessage());
                }
            }
     		try {
				connObj.close();
			} catch (SQLException e) {
				System.out.println("EXCEPTION WHILE Closing Database Connection"+e.getMessage());
	         	logger.info("--------EXCEPTION WHILE Closing Database Connection--------: "+e.getMessage());
				e.printStackTrace();
			}
    	}
   } 
    
    
    public static void retrieveDatafromTable(String tableName) throws SQLException
	 {
    	try
    	{
    		 statement = connObj.createStatement();
    	     String queryString = "select * from "+tableName+"";
    	     ResultSet rs = statement.executeQuery(queryString);
    	     while (rs.next()) {
    	    	 System.out.println(rs.getString("LoanId"));
    	    	 System.out.println(rs.getString("FirstName"));
    	    	 System.out.println(rs.getString("LastName"));
    	     }
    	        logger.info("--------Table data retrieving successfully--------");
    	        System.out.println("Table data retrieving successfully");
    	}
    	catch(Exception e)
    	{
    		System.out.println("EXCEPTION WHILE READING DATA FROM TABLE "+e.getMessage());
         	logger.info("--------EXCEPTION while retrieving data from MS SQL Server Table--------: "+e.getMessage());
    		e.printStackTrace();
    	}
    	finally 
    	{
    		try {
				connObj.close();
			} catch (SQLException e) {
				System.out.println("EXCEPTION WHILE Closing Database Connection"+e.getMessage());
	         	logger.info("--------EXCEPTION WHILE Closing Database Connection--------: "+e.getMessage());
				e.printStackTrace();
			}
    	}
    }
    
    public static void retrieveDatafromTableWithSpecificColumns(String ColumnsWithcomma, String tableName) throws SQLException
	 {
   	try
   	{
   		 statement = connObj.createStatement();
   	     String queryString = "select "+ColumnsWithcomma+" from "+tableName+"";
   	     ResultSet rs = statement.executeQuery(queryString);
   	     
   	     while (rs.next()) {
   	    	 //System.out.println(rs.getString("LoanId"));
   	    	 System.out.println(rs.getString("FirstName"));
   	    	 System.out.println(rs.getString("LastName"));
   	     }
   	        logger.info("--------Table data retrieving successfully--------");
   	        System.out.println("Table data retrieving successfully");
   	}
   	catch(Exception e)
   	{
   		System.out.println("EXCEPTION WHILE READING DATA FROM TABLE "+e.getMessage());
        	logger.info("--------EXCEPTION while retrieving data from MS SQL Server Table--------: "+e.getMessage());
   		e.printStackTrace();
   	}
   	finally 
	{
		try {
			connObj.close();
		} catch (SQLException e) {
			System.out.println("EXCEPTION WHILE Closing Database Connection"+e.getMessage());
         	logger.info("--------EXCEPTION WHILE Closing Database Connection--------: "+e.getMessage());
			e.printStackTrace();
		}
	}
   }
    
    public static void updateColumnDataIntoTable(String CustId) throws SQLException
    {
    	try
    	{
    		statement = connObj.createStatement();
            String sql = "UPDATE MMLoan " +
                         "SET FirstName = 'Peter' WHERE LoanId in ("+CustId+")";
            statement.executeUpdate(sql);
            
            logger.info("--------Table data updated successfully--------");
            System.out.println("Table data updated successfully");
    	}
    	catch(Exception e)
    	{
    		System.out.println("EXCEPTION WHILE UPDATING DATA TO TABLE "+e.getMessage());
         	logger.info("--------EXCEPTION while Updating data to MS SQL Server Table--------: "+e.getMessage());
    		e.printStackTrace();
    	}
    	finally 
    	{
    		try {
				connObj.close();
			} catch (SQLException e) {
				System.out.println("EXCEPTION WHILE Closing Database Connection"+e.getMessage());
	         	logger.info("--------EXCEPTION WHILE Closing Database Connection--------: "+e.getMessage());
				e.printStackTrace();
			}
    	}
    }
   
    public static void updateColumnDataIntoTableWithSpecificColumns(String FirstName, String FirstNameValue, String LastName, String LastNameValue, String Tablename, String whereCond) throws SQLException
    {
    	try
    	{
    		statement = connObj.createStatement();
            String sql = "UPDATE "+Tablename+" " +
                         "SET "+FirstName+" = '"+FirstNameValue+"', "+LastName+" = '"+LastNameValue+"' WHERE LoanId in ("+whereCond+")";
            System.out.println("============");
            System.out.println(sql);
            System.out.println("============");
            statement.executeUpdate(sql);
            
            logger.info("--------Table data updated successfully--------");
            System.out.println("Table data updated successfully");
    	}
    	catch(Exception e)
    	{
    		System.out.println("EXCEPTION WHILE UPDATING DATA TO TABLE "+e.getMessage());
         	logger.info("--------EXCEPTION while Updating data to MS SQL Server Table--------: "+e.getMessage());
    		e.printStackTrace();
    	}
    	finally 
    	{
    		try {
				connObj.close();
			} catch (SQLException e) {
				System.out.println("EXCEPTION WHILE Closing Database Connection"+e.getMessage());
	         	logger.info("--------EXCEPTION WHILE Closing Database Connection--------: "+e.getMessage());
				e.printStackTrace();
			}
    	}
    }
    
    /*
    select * from vitacrm39..customer 
    where  firstname ='javi' and lastname='khadi' 
    Order by createddate desc
    */
    
public static void main(String[] args) throws SQLException, InvalidFormatException, IOException {
   
	getDbConnection1();
	long s = getCustomerId("javi","khadi","customer");
	System.out.println(s);
	getDbConnection2();
	
	
   /* retrieveDatafromTable("MMLoan"); // retrieve All data from input table
    retrieveDatafromTableWithSpecificColumns("FirstName,LastName", "MMLoan"); // retrieve specific data from input table
    updateColumnDataIntoTable("101"); // update data based on columns defined in the method and input Customer ID
    updateColumnDataIntoTableWithSpecificColumns("FirstName","UpdNameHere","LastName","UpdNameHere","MMLoanTableHere","101CustomerIdHere"); 
    //update based on input columns and respective values, TableName and Where ConditionValue table will be updated
    */
    
	}
}
