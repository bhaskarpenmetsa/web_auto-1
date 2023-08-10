package com.falcon.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.collections.Lists;
import org.testng.internal.PackageUtils;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlMethodSelector;
import org.testng.xml.XmlScript;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite.ParallelMode;

import com.falcon.excelutils.Excel_Reader;
import com.falcon.excelutils.Xls_Reader;

public class TestDynamicXmlGeneration {
	
	public static String rootPath = System.getProperty("user.dir");
	public static void generateXMLDynamically() throws IOException, ClassNotFoundException {
		XmlSuite suite = new XmlSuite();
		suite.setName("Regression suite");
		suite.setParallel(ParallelMode.NONE);
		suite.addListener("com.falcon.utils.RetryListenerClass");
		suite.setPreserveOrder(true);

		XmlTest testRT = new XmlTest(suite);
		testRT.setName("Falcon test");
		testRT.setParallel(ParallelMode.NONE);
		testRT.setPreserveOrder(true);

		ArrayList<XmlClass> Alxmlclass = new ArrayList<XmlClass>();

		String[] testClasses = PackageUtils.findClassesInPackage("com.falcon.TestCases", new ArrayList<String>(),
				new ArrayList<String>());
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		ArrayList<String> fromExcelFlags = new ArrayList<String>();

		// This is to read from Excel file and generating list
		System.out.println("%%%%%%%%%Reading TestCase Data%%%%%%%%%%%");
		String excellocation = rootPath + "\\ExcelData\\AppiumReportFormat.xlsx";
		String TestDataLoc = rootPath + "\\ExcelData\\AppiumTestData.xlsx";
		String sheetName = "TestCases";
		String dataSets[][];
		Xls_Reader xlsR = new Xls_Reader(excellocation, TestDataLoc);
		int colCount = xlsR.getColumnCount(sheetName);
		int rowCount = xlsR.getRowCount(sheetName);
		System.out.println("colCount " + colCount);
		System.out.println("rowCount " + rowCount);
		Excel_Reader obj_Exc_Read = new Excel_Reader();
		dataSets = obj_Exc_Read.getExcelData(excellocation, sheetName);

		for (int i = 0; i < rowCount - 1; i++) {
			fromExcelFlags.add(dataSets[i][3] + "#" + dataSets[i][4]);
		}
		System.out.println("=====================TestCase Data from EXCEL with Flag==========");
		System.out.println(fromExcelFlags);
		System.out.println("===============Classes and Methods detail from Package============");
		for (int i = 0; i < testClasses.length; i++) {
			@SuppressWarnings("rawtypes")
			Class currentClass = cl.loadClass(testClasses[i]);
			// System.out.println("currentClass : "+currentClass);
			int dotLen;
			dotLen = currentClass.toString().lastIndexOf(".");
			if (fromExcelFlags.contains(currentClass.toString().substring(dotLen + 1) + "#Yes")) {
				Alxmlclass.add(new XmlClass(currentClass));
			} else {
				continue;
			}
			testRT.setClasses(Alxmlclass);
			testRT.setPreserveOrder(true);
		}
		XmlMethodSelector methodSelector = new XmlMethodSelector();
		XmlScript xmlScrpt = new XmlScript();
		xmlScrpt.setLanguage("beanshell");
		xmlScrpt.setScript("System.setProperty(\"testng.metrics.logo\",\r\n"
				+ "             \"https://www.3i-infotech.com/wp-content/uploads/2018/02/3I-infotech-logo-109-x-100.png\");return true;");
		methodSelector.setScript(xmlScrpt);

		List<XmlMethodSelector> methodSelectors = Lists.newArrayList();
		methodSelectors.add(methodSelector);
		suite.setMethodSelectors(methodSelectors);

		System.out.println("=====================generated testNG.xml====================");
		System.out.println(suite.toXml());
		FileWriter writer = new FileWriter(new File(rootPath + "//" + "SampleGenerated.xml"));
		writer.write(suite.toXml());
		writer.flush();
		writer.close();
		System.out.println("testNG.xml generated at : " + new File("SampleGenerated.xml").getAbsolutePath());
	}

	public static void main(String[] args) {
		System.out.println("Test here");
		try {
			TestDynamicXmlGeneration.generateXMLDynamically();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");

	}

}
