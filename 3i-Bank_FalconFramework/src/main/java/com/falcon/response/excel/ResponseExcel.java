package com.falcon.response.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.falcon.driver.TestBase;
import com.falcon.model.response.PageResponse;
import com.falcon.utils.UtilityMethods;

public class ResponseExcel {
	public static final Logger logger = LogManager.getLogger(ResponseExcel.class);
	
	public static String filePath=null;

	public ResponseExcel() {
		super();
	}

	public static void prepareExcel(PageResponse pr) {
		logger.info("****************************************************************");
		String basePath = System.getProperty("user.dir") + "/Report/"
				+ TestBase.getRunnerPropertyValue("releaseVersion") + "_Run"
				+ TestBase.getRunnerPropertyValue("runcount") + "_" + UtilityMethods.getDate_ddMMyyyy();
		//String filePath = basePath + "/ResponseTime.xlsx";
		filePath = basePath + "/ResponseTime.xlsx";
		FileOutputStream fileOut = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		FileInputStream inputStream = null;

		try {
			File file = new File(filePath);
			if (file.exists()) {
				inputStream = new FileInputStream(new File(filePath));
				workbook = new HSSFWorkbook(inputStream);
				sheet = workbook.getSheetAt(0);
			} else {
				workbook = new HSSFWorkbook();
				sheet = workbook.createSheet("ResponseTime");
			}

			int rowCount = sheet.getPhysicalNumberOfRows();
			if (rowCount == 0) {
				row = sheet.createRow(rowCount);

				row.createCell(0).setCellValue("PageName");
				row.createCell(1).setCellValue("ResponseTime");
				row.createCell(2).setCellValue("Start Time");
				row.createCell(3).setCellValue("End Time");
				//row.createCell(4).setCellValue("Executed Time");
				
				
				rowCount++;
			}

			logger.info("================ Preparing Data ================");
			logger.info("Page Name 	===> " + pr.getPageName());
			logger.info("Start Time ===> " + pr.getStartTime());
			logger.info("End Time 	===> " + pr.getEndTime());
			logger.info("Execution Time ===> " + pr.getResponseTime());
			//logger.info("Executed Time ===> " + pr.getexcutedtime());
			logger.info("****************************************************************");

			row = sheet.createRow(rowCount);

			row.createCell(0).setCellValue(pr.getPageName());
			row.createCell(1).setCellValue(pr.getResponseTime());
			row.createCell(2).setCellValue(pr.getStartTime());
			row.createCell(3).setCellValue(pr.getEndTime());
			//row.createCell(4).setCellValue(pr.getexcutedtime());
			if (file.exists()) {
				inputStream.close();
			}

			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Writing of ResponseTimes report done");
	}

}
