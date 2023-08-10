package com.falcon.DocReport;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.falcon.driver.TestBase;
import com.falcon.reports.Reports;
import com.falcon.utils.UtilityMethods;

import freemarker.core.Environment;
import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading3;
import word.w2004.elements.Image;
import word.w2004.elements.Paragraph;
import word.w2004.style.HeadingStyle.Align;

public class SaveDocument_2 extends Reports{
	public static long ScrShtcount;
	static String falconrootPath = System.getProperty("user.dir");
	public static String ScrShotDescription;
	
	
	public static void createDoc(String testCaseName, String[] imgFileNames) {
		
		
		// Create a document object
		IDocument myDoc = new Document2004();
		
		myDoc.setPageOrientationLandscape();
		
		myDoc.encoding(Encoding.UTF_8);
		// Inserts one breakline
		myDoc.addEle(BreakLine.times(5).create()); 
		// Add client logo to document header
		myDoc.getHeader().addEle(Image.from_FULL_LOCAL_PATHL("/Sele/Logo.PNG").setHeight("30").setWidth("20").getContent());
		
		// Add Project name to document header
		myDoc.getHeader().addEle(Heading3.with(" ProjectName").withStyle().align(Align.RIGHT).create());
		// Specify Test case name as document heading
        myDoc.addEle(Heading1.with(testCaseName + " Test Case").withStyle().align(Align.CENTER).create());
        myDoc.addEle(BreakLine.times(1).create());
        // Add a description paragraph
        myDoc.addEle(Paragraph
                .with("Following are the related screenshots")
                .create());
        myDoc.addEle(BreakLine.times(1).create());
        // Add company name to document footer
        myDoc.getFooter().addEle(
                Paragraph.with("@CompanyName").create());
        // Loop through all the image files
        for(String file:imgFileNames){
        	// Insert each image file to the document
        	
        	//myDoc.getBody().addEle(arg0);
        	
        	myDoc.getBody().addEle(Image.from_FULL_LOCAL_PATHL("/Sele/screenshots/" + file + ".jpg").setHeight("150").setWidth("150").getContent());
        	/* InputStream pic = null;
			try {
				pic = new FileInputStream("E:/Sele/screenshots/WordDocWithScreenshot1.png");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	myDoc.addEle(Image.from_STREAM("WordDocWithScreenshot1", pic));
*/        	// Insert 2 linebreaks at the end of each inserted image
        	myDoc.addEle(BreakLine.times(2).create());
        	
        	 myDoc.addEle(Paragraph
                     .with(file)
                     .create());
             myDoc.addEle(BreakLine.times(1).create());
        	
        	System.out.println("File :"+file);
        }
        // Insert an image from web
        myDoc.addEle(Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png"));
        
        // Create the word document specifying a location
        File fileObj = new File("\\Sele\\" + testCaseName + ".doc");
        PrintWriter writer = null;
        try {
         writer = new PrintWriter(fileObj);
        } catch (FileNotFoundException e) {
         e.printStackTrace();
        }
        
        String myWord = myDoc.getContent();

        writer.println(myWord);
        writer.close();
        // Print a confirmation image to console
        System.out.println("Word document created successfully!");
       }
	
	public static void createTestCaseWiseReportDoc(String testCaseName, String Description, String[] imgFileNames) throws IOException, InvalidFormatException {
		// Create a document object
		IDocument myDoc = new Document2004();
		myDoc.encoding(Encoding.UTF_8);
		// Inserts one brea line
		myDoc.addEle(BreakLine.times(5).create()); 
		// Add client logo to document header
		myDoc.getHeader().addEle(Image.from_FULL_LOCAL_PATHL("/Sele/Logo.PNG").setHeight("30").setWidth("20").getContent());
		// Add Project name to document header
		myDoc.getHeader().addEle(Heading3.with(" ProjectName").withStyle().align(Align.RIGHT).create());
		// Specify Test case name as document heading
        myDoc.addEle(Heading1.with(testCaseName + " Test Case").withStyle().align(Align.CENTER).create());
        myDoc.addEle(BreakLine.times(1).create());
        // Add a description paragraph
        myDoc.addEle(Paragraph
                .with("Following are the related screenshots")
                .create());
        myDoc.addEle(BreakLine.times(1).create());
        // Add company name to document footer
        myDoc.getFooter().addEle(
                Paragraph.with("@CompanyName").create());
        //========================================
        // Loop through all the image files
      //========================================
       /* //File image1 = new File("E:\\Sele\\screenshots\\WordDocWithScreenshot1.jpg");
        File[] arr = new File[100];
		//arr[0] = image1;
		int iLen = arr.length;*/
		try (Stream<Path> files = Files.list(Paths.get("E:/Sele/screenshots/"))) 
		{
			ScrShtcount = files.count();
		    System.out.println("count "+ScrShtcount);
		}
		String rootPath = "E:\\Sele\\screenshots\\";
		File[] arr1 = new File[2];
		for(int i = 0; i< 2;i++)
		{
			String s1 = rootPath+imgFileNames[i]+".png";
			System.out.println("S1 "+s1);
	        File image = new File(s1);
	        arr1[i] = image;
		}
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun r = p.createRun();
		
//		 CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
//		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc, sectPr);
		
		/*//write header content
				CTP ctpHeader = CTP.Factory.newInstance();
			        CTR ctrHeader = ctpHeader.addNewR();
				CTText ctHeader = ctrHeader.addNewT();
				String headerText = "This is header";
				ctHeader.setStringValue(headerText);	
				XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, doc);
			        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
			        parsHeader[0] = headerParagraph;
			        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
			        
				//write footer content
				CTP ctpFooter = CTP.Factory.newInstance();
				CTR ctrFooter = ctpFooter.addNewR();
				CTText ctFooter = ctrFooter.addNewT();
				String footerText = "This is footer";
				ctFooter.setStringValue(footerText);	
				XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, doc);
			        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
			        parsFooter[0] = footerParagraph;
				policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
					
				//write body content
				XWPFParagraph bodyParagraph = doc.createParagraph();
				bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r1 = bodyParagraph.createRun();
				r1.setBold(true);
			        r1.setText("This is body content.");*/
		
		for(int i=0;i< 2;i++)
		{
			BufferedImage bimg = ImageIO.read(arr1[i]);
			int width1 = bimg.getWidth();
			int height1 = bimg.getHeight();
		
			String imgFile = arr1[i].getName();
			int imgFormat1 = getImageFormat(imgFile);
			
			String p1 = "Sample Paragraph Post. This is a sample Paragraph post. Sample Paragraph text is being cut and pasted again and again. This is a sample Paragraph post. peru-duellmans-poison-dart-frog.";

			r.setText(p1);
			r.addBreak();
			r.addPicture(new FileInputStream(arr1[i]), imgFormat1, imgFile, Units.toEMU(350), Units.toEMU(350));
		}
		/*FileOutputStream out = new FileOutputStream("E:\\Sele\\" + testCaseName + ".doc");
		doc.write(out);
		out.close();
		doc.close();*/
		
		
		    for(File file:arr1){
        	// Insert each image file to the document
        	
        	//myDoc.getBody().addEle(arg0);
        	
        	//myDoc.getBody().addEle(Image.from_FULL_LOCAL_PATHL("/Sele/screenshots/" + file + ".jpg").setHeight("150").setWidth("150").getContent());
        	/* InputStream pic = null;
			try {
				pic = new FileInputStream("E:/Sele/screenshots/WordDocWithScreenshot1.png");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	myDoc.addEle(Image.from_STREAM("WordDocWithScreenshot1", pic));
*/        	// Insert 2 linebreaks at the end of each inserted image
        	myDoc.addEle(BreakLine.times(2).create());
        	
        	//String s = rootPath+file+".jpg";
        	String s = "file://"+file+" ";
        	File image = new File(s);
        	
        	
        	System.out.println("HIII "+rootPath+file);
        	
        	myDoc.addEle(Paragraph
                    .with(Description)
                    .create()); 
        	 myDoc.addEle(BreakLine.times(1).create());
        	myDoc.addEle(Paragraph
                     .with(s)
                     .create());
        	 myDoc.addEle(BreakLine.times(2).create());
        	System.out.println("File : HEEEEE "+file);
        }
        // Insert an image from web
        myDoc.addEle(Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png"));
        
        // Create the word document specifying a location
        File fileObj = new File("\\Sele\\" + testCaseName + ".doc");
        PrintWriter writer = null;
        try {
         writer = new PrintWriter(fileObj);
        } catch (FileNotFoundException e) {
         e.printStackTrace();
        }
        
        String myWord = myDoc.getContent();

        writer.println(myWord);
        writer.close();
        
        FileOutputStream out = new FileOutputStream("E:\\Sele\\" + testCaseName + ".docx");
		doc.write(out);
		out.close();
		doc.close();
        
        // Print a confirmation image to console
        System.out.println("Word document created successfully!");
       }
	
	public static void createTestCaseWiseReportDoc1(String testCaseName, String[] imgFileNames, String Env, String browser) throws IOException, InvalidFormatException //Added on 04Sept2020JIOGIT
	{
		
		
		System.out.println("Test "+imgFileNames[0]);
		System.out.println("Test "+imgFileNames[1]);
		System.out.println("Test "+imgFileNames[2]);
		
		
		// Create a document object
		IDocument myDoc = new Document2004();
		myDoc.encoding(Encoding.UTF_8);
		// Inserts one brea line
		myDoc.addEle(BreakLine.times(1).create()); 
		// Add client logo to document header
		myDoc.getHeader().addEle(Image.from_FULL_LOCAL_PATHL(falconrootPath+"/Images/AltirayLogo.PNG").setHeight("40").setWidth("30").getContent());
		// Add Project name to document header
		myDoc.addEle(BreakLine.times(1).create());
		myDoc.getHeader().addEle(Image.from_FULL_LOCAL_PATHL(falconrootPath+"/Images/3iLogo.PNG").setHeight("40").setWidth("30").getContent());
		//myDoc.getHeader().addEle(Image.from_FULL_LOCAL_PATHL(falconrootPath+"\\Images\\3iLogo.PNG").setHeight("20").setWidth("30").getContent());
		myDoc.getHeader().addEle(Heading3.with("***ALTIRAY Swift Automation***").withStyle().align(Align.RIGHT).create());
		// Specify Test case name as document heading
        myDoc.addEle(Heading1.with(testCaseName + " Test Case").withStyle().align(Align.CENTER).create());
        myDoc.addEle(BreakLine.times(1).create());
        // Add a description paragraph
        myDoc.addEle(Paragraph
                .with("Following are the test case related screen shots :")
                .create());
        myDoc.addEle(BreakLine.times(1).create());
        // Add company name to document footer
        myDoc.getFooter().addEle(
                Paragraph.with("@3i-Infotech Limited").create());
        //========================================
        // Loop through all the image files
        //========================================
        try (Stream<Path> files = Files.list(Paths.get(TestBase.imageLocation))) 
		{
        	ScrShtcount = files.count();
		    System.out.println("count "+ScrShtcount);
		}
        
        Long l= new Long(ScrShtcount);
        int iLen=l.intValue();
        
		String rootPath = TestBase.imageLocation;
		
		System.out.println("IMAGE LOCATION :"+TestBase.imageLocation);
		
		File[] arr1 = new File[iLen];
		for(int i = 0; i< iLen;i++)
		{
			//String s1 = rootPath+imgFileNames[i]+".png"; Commented on 06-June-2019
			String s1 = imgFileNames[i]; // Added on 06-June-2019
			System.out.println("S1 "+s1);
	        File image = new File(s1);
	        arr1[i] = image;
		}
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun r = p.createRun();
		
		String imgFileLogo1=falconrootPath+"\\Images\\3iLogo.PNG";
		System.out.println("imgFileLogo1 : "+imgFileLogo1);
		XWPFPicture picture1 = r.addPicture(new FileInputStream(imgFileLogo1), XWPFDocument.PICTURE_TYPE_PNG, imgFileLogo1, Units.toEMU(50), Units.toEMU(50));
		//r.addBreak();
		
		 p.setAlignment(ParagraphAlignment.RIGHT); //Added on 20-JUNE-2019
		
		String imgFileLogo=falconrootPath+"\\Images\\AltirayLogo.PNG";
		System.out.println("imgFileLogo : "+imgFileLogo);
		XWPFPicture picture = r.addPicture(new FileInputStream(imgFileLogo), XWPFDocument.PICTURE_TYPE_PNG, imgFileLogo, Units.toEMU(50), Units.toEMU(50));
		System.out.println(picture); //XWPFPicture is added
		System.out.println(picture.getPictureData()); //but without access to XWPFPictureData (no blipID)
		
		r.setFontSize(13);
	   	r.setFontFamily("Verdana");
	    r.addBreak();
	    //r.setBold(true); //Added on 20-JUNE-2019
	    r.setText("***ALTIRAY Swift Automation Execution Report***");
	    r.addBreak();
	    r.setText("Test Case : "+testCaseName);
	    r.setUnderline(UnderlinePatterns.NONE);
	    r.addBreak();
	    r.setColor("808080");
	    p.setAlignment(ParagraphAlignment.CENTER);
	
	    for(int i=0;i< iLen;i++)
		{
			
	    	System.out.println("NEXT LOOP");
	    	
	    	System.out.println("NEED TO READ :"+arr1[i]);
	    	
	    	BufferedImage bimg = ImageIO.read(arr1[i]);
			int width1 = bimg.getWidth();
			int height1 = bimg.getHeight();
			String imgFile = arr1[i].getName();
			
			System.out.println("IMAGE FILE : CHECK: "+imgFile);
			
			
			int hashIndex;
			hashIndex = imgFile.indexOf("@");
			ScrShotDescription = imgFile.substring(0, hashIndex);
			
			
			int imgFormat1 = getImageFormat(imgFile);
			//r.setText("Description"+"\t"); // Here Need Update on 20-JUNE-2019
			r.setText("Screen "+(i+1)+": "+ScrShotDescription+":"+"\t"); // Here Need Update on 20-JUNE-2019
			r.setUnderline(UnderlinePatterns.NONE);
			r.setColor("808080");
			p.setAlignment(ParagraphAlignment.LEFT);
		
			//String p1 = Description+"\n";
			//r.setText(p1);
			r.addBreak();
			r.addPicture(new FileInputStream(arr1[i]), imgFormat1, imgFile, Units.toEMU(450), Units.toEMU(300));
		}
	    r.addBreak(); // Added on 20-JUNE-2019
	    r.setText("===END OF DOCUMENT==="); // Added on 20-JUNE-2019 
	    /*
	    for(File file:arr1)
	    {
	    	
	    	System.out.println("NEXT TO NEXT LOOP");
	    // Insert 2 line breaks at the end of each inserted image
    	myDoc.addEle(BreakLine.times(2).create());
    	//String s = rootPath+file+".jpg";
    	String s = "file://"+file+" ";
    	File image = new File(s);
    	System.out.println("HIII "+rootPath+file);
    	myDoc.addEle(Paragraph
                .with("Description") // Here Need Update on 20-JUNE-2019
                .create()); 
    	 myDoc.addEle(BreakLine.times(1).create());
    	myDoc.addEle(Paragraph
                 .with(s)
                 .create());
    	 myDoc.addEle(BreakLine.times(2).create());
    	System.out.println("File : HEEEEE "+file);
        }*/
	    
	    for(int i=0;i< arr1.length;i++)
	    {
	      	System.out.println("NEXT TO NEXT LOOP");
		    // Insert 2 line breaks at the end of each inserted image
	    	myDoc.addEle(BreakLine.times(1).create());
	    	//String s = rootPath+file+".jpg";
	    	String s = "file://"+arr1[i]+" ";
	    	File image = new File(s);
	    	System.out.println("HIII "+rootPath+arr1[i]);
	    	
	    	String filetoString = arr1[i].getName();
	    	
	    	System.out.println("FILE to STRING "+filetoString);
	    	
	    	int hashIndex;
			hashIndex = filetoString.indexOf("@");
			ScrShotDescription = filetoString.substring(0, hashIndex);
	    	
			myDoc.addEle(Paragraph
					//.with("Description") // Here Need Update on 20-JUNE-2019
	    			.with((i+1)+": "+ScrShotDescription+":") // Here Need Update on 20-JUNE-2019
	    			.create()); 
	    	myDoc.addEle(BreakLine.times(1).create());
	    	myDoc.addEle(Paragraph
	                 .with(s)
	                 .create());
	    	 myDoc.addEle(BreakLine.times(1).create());
	    	System.out.println("File : HEEEEE "+arr1[i]);
	    	
	    }
	    
	    myDoc.addEle(Paragraph
				.with("===END OF DOCUMENT===") 
    			.create()); 
	    
        // Insert an image from web
        //myDoc.addEle(Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png"));
        // Create the word document specifying a location
        File fileObj = new File(falconrootPath+"\\Report\\"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"\\"+ testCaseName+"_"+Env  +"_"+browser+ ".doc"); //Added on 04Sept2020JIOGIT
        PrintWriter writer = null;
        try {
         writer = new PrintWriter(fileObj);
        } catch (FileNotFoundException e) {
         e.printStackTrace();
        }
        String myWord = myDoc.getContent();
        writer.println(myWord);
        writer.close();
        
        //FileOutputStream out = new FileOutputStream(falconrootPath+"\\"+ testCaseName + ".docx");
		//rootPath
        FileOutputStream out = new FileOutputStream(falconrootPath+"\\Report\\"+getRunnerPropertyValue("releaseVersion")+"_Run"+getRunnerPropertyValue("runcount")+"_"+UtilityMethods.getDate_ddMMyyyy()+"\\"+ testCaseName+"_"+Env +"_"+browser+ ".docx"); //Added on 04Sept2020JIOGIT
        doc.write(out);
		out.close();
		doc.close();
        
		// Print a confirmation image to console
        System.out.println("Word document created successfully!");
     } // End of Method

	
	public static int getImageFormat(String imgFileName) {
		int format;
		if (imgFileName.endsWith(".emf"))
			format = XWPFDocument.PICTURE_TYPE_EMF;
		else if (imgFileName.endsWith(".wmf"))
			format = XWPFDocument.PICTURE_TYPE_WMF;
		else if (imgFileName.endsWith(".pict"))
			format = XWPFDocument.PICTURE_TYPE_PICT;
		else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg"))
			format = XWPFDocument.PICTURE_TYPE_JPEG;
		else if (imgFileName.endsWith(".png"))
			format = XWPFDocument.PICTURE_TYPE_PNG;
		else if (imgFileName.endsWith(".dib"))
			format = XWPFDocument.PICTURE_TYPE_DIB;
		else if (imgFileName.endsWith(".gif"))
			format = XWPFDocument.PICTURE_TYPE_GIF;
		else if (imgFileName.endsWith(".tiff"))
			format = XWPFDocument.PICTURE_TYPE_TIFF;
		else if (imgFileName.endsWith(".eps"))
			format = XWPFDocument.PICTURE_TYPE_EPS;
		else if (imgFileName.endsWith(".bmp"))
			format = XWPFDocument.PICTURE_TYPE_BMP;
		else if (imgFileName.endsWith(".wpg"))
			format = XWPFDocument.PICTURE_TYPE_WPG;
		else {
			return 0;
		}
		return format;
	}
	
}