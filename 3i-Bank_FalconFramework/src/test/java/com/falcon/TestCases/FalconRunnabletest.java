package com.falcon.TestCases;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.internal.PackageUtils;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlMethodSelector;
import org.testng.xml.XmlScript;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.falcon.excelutils.Excel_Reader;
import com.falcon.excelutils.Xls_Reader;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FalconRunnabletest {

    public static String rootpath = System.getProperty("user.dir");

    @Test
    public void executeTestCases() throws IOException, ClassNotFoundException {

        XmlSuite suite = new XmlSuite();
        suite.setName("Regression suite");
        suite.setParallel(XmlSuite.ParallelMode.NONE);
        suite.addListener("com.falcon.utils.RetryListenerClass");
        suite.setPreserveOrder(true);

        XmlTest testRT = new XmlTest(suite);
        testRT.setName("Falcon test");
        testRT.setParallel(XmlSuite.ParallelMode.NONE);
        testRT.setPreserveOrder(true);

        List<XmlClass> Alxmlclass = new ArrayList<>();

        String[] testClasses = PackageUtils.findClassesInPackage("com.falcon.TestCases", new ArrayList<String>(),
                new ArrayList<String>());
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        ArrayList<String> fromExcelFlags = new ArrayList<>();

        // This is to read from Excel file and generate a list
        System.out.println("%%%%%%%%% Reading TestCase Data %%%%%%%%%%%");
        String excellocation = rootpath + "\\ExcelData\\TestCases.xlsx";
        String TestDataLoc = rootpath + "\\ExcelData\\TestData.xlsx";
        String sheetName = "TestCases";
        String[][] dataSets;
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
        System.out.println("===================== TestCase Data from EXCEL with Flag ==========");
        System.out.println(fromExcelFlags);
        System.out.println("=============== Classes and Methods detail from Package ============");
        for (int i = 0; i < testClasses.length; i++) {
            @SuppressWarnings("rawtypes")
            Class currentClass = cl.loadClass(testClasses[i]);
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
        XmlScript xmlScript = new XmlScript();
        xmlScript.setLanguage("beanshell");
        xmlScript.setScript("System.setProperty(\"testng.metrics.logo\",\r\n"
                + "             \"https://www.3i-infotech.com/wp-content/uploads/2021/04/3I-infotech-logo.png\");return true;");
        methodSelector.setScript(xmlScript);

        List<XmlMethodSelector> methodSelectors = Lists.newArrayList();
        methodSelectors.add(methodSelector);
        suite.setMethodSelectors(methodSelectors);

        System.out.println("===================== Generated testNG.xml ====================");
        System.out.println(suite.toXml());
        File file = new File(rootpath + "\\TestNGDynXml\\testng.xml");
        FileWriter writer = new FileWriter(file);
        writer.write(suite.toXml());
        writer.flush();
        writer.close();
        System.out.println("testng.xml generated at: " + file.getAbsolutePath());

        // Create object of TestNG Class
        TestNG runner = new TestNG();

        // Create a list of String
        List<String> suiteFiles = new ArrayList<>();

        // Add the dynamically generated testng.xml file
        suiteFiles.add(file.getAbsolutePath());

        // Set the suite files for execution
        runner.setTestSuites(suiteFiles);

        // Execute the runner using the run method
        runner.run();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        FalconRunnabletest testRunner = new FalconRunnabletest();
        testRunner.executeTestCases();
    }
}
