package com.falcon.actions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/*
 * @author - 1001261
*/


public class FalconWebElement
{
	
	static final Logger logger = Logger.getLogger(FalconWebElement.class);
	
	/* To click on any WebElement
	@Parameter : WebElement */
	public static void clickOn(WebElement element)
	{
		logger.info("--------	clickOn wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{			
			if(isDisplayedorNot(element)&&isEnabledorNot(element))
			{	
				//System.out.println("Webelement is " + element);
				//System.out.println("-----> "+ isDisplayedorNot(element));
				//System.out.println(" ---->" + isEnabledorNot(element));
				element.click();
				logger.info("--------	clickOn wrapper method inside MyWebElement class is executed successfully	--------");
			}				
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO PERFORM CLICK OPERATION ON THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO PERFORM CLICK OPERATION ON THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO PERFORM CLICK OPERATION ON THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO PERFORM CLICK OPERATION ON THE SPECIFIED WEB ELEMENT");			
		}
	}
	
	/* To clear out the contents of any WebElement
	@Parameter : WebElement */
	public static void clearOut(WebElement element)
	{
		logger.info("--------	clearOut wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{
			if(isDisplayedorNot(element)&&isEnabledorNot(element))
			{				
				element.clear();	
				logger.info("--------	clearOut wrapper method inside MyWebElement class is executed successfully	--------");
			}			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO PERFORM CLEAR OPERATION ON THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO PERFORM CLEAR OPERATION ON THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO PERFORM CLEAR OPERATION ON THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO PERFORM CLEAR OPERATION ON THE SPECIFIED WEB ELEMENT");			
		}
	}
	
	/* To get child WebElement
	@Parameters : WebElement and locator */
	public static WebElement getChild(WebElement element,By locator)
	{
		logger.info("--------	getChild wrapper method inside MyWebElement class is invoked successfully	--------");
		WebElement child = null;	
		try
		{			
			if(isDisplayedorNot(element))
			{				
				child = element.findElement(locator);
				logger.info("--------	getChild wrapper method inside MyWebElement class is executed successfully	--------");
				return child;				
			}			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE CHILD ELEMENT FROM THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO THE CHILD ELEMENT FROM THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE CHILD ELEMENT FROM THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO THE CHILD ELEMENT FROM THE SPECIFIED WEB ELEMENT");			
		}
		return child;		
	}
	
	/* To get multiple child WebElements
	@Parameters : WebElement and locator */
	public static List<WebElement> getChildren(WebElement element,By locator)
	{
		logger.info("--------	getChildren wrapper method inside MyWebElement class is invoked successfully	--------");
		List<WebElement> children = null;		
		try
		{			
			if(isDisplayedorNot(element))
			{				
				children = element.findElements(locator);
				logger.info("--------	getChildren wrapper method inside MyWebElement class is executed successfully	--------");
				return children;			
			}			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE MULTIPLE CHILD ELEMENTS FROM THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO THE MULTIPLE CHILD ELEMENTS FROM THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE MULTIPLE CHILD ELEMENTS FROM THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO THE MULTIPLE CHILD ELEMENTS FROM THE SPECIFIED WEB ELEMENT");			
		}	
		return children;		
	}
	
	/* To verify whether a WebElement is displayed or not
	@Parameter : WebElement */
	public static boolean isDisplayedorNot(WebElement element)
	{
		logger.info("--------	isDisplayedorNot wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{
			element.isDisplayed();
			System.out.println("Element Displayed " +element );
			logger.info("--------	isDisplayedorNot wrapper method inside MyWebElement class is executed successfully	--------");
			return true;				
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS DISPLAYED OR NOT (No Such Element Exception): "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS DISPLAYED OR NOT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS DISPLAYED OR NOT (StaleElementRef Exp) : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS DISPLAYED OR NOT");			
		}
		return false;		
	}
	
	/* To verify whether a WebElement is enabled or not
	@Parameter : WebElement */
	public static boolean isEnabledorNot(WebElement element)
	{
		logger.info("--------	isEnabledorNot wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{
			element.isEnabled();
			logger.info("--------	isEnabledorNot wrapper method inside MyWebElement class is executed successfully	--------");
			return true;
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS ENABLED OR NOT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS ENABLED OR NOT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS ENABLED OR NOT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS ENABLED OR NOT");			
		}
		return false;		
	}
	
	/* To verify whether a WebElement is selected or not
	@Parameter : WebElement */
	public static boolean isSelectedorNot(WebElement element)
	{
		logger.info("--------	isSelectedorNot wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{
			element.isSelected();
			logger.info("--------	isSelectedorNot wrapper method inside MyWebElement class is executed successfully	--------");
			return true;			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS SELECTED OR NOT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS SELECTED OR NOT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS SELECTED OR NOT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE CHECKING WHETHER THE SPECIFIED WEB ELEMENT IS SELECTED OR NOT");			
		}
		return false;	
	}
	
	/* To get text from any WebElement
	@Parameter : WebElement */
	public static String getTextFrom(WebElement element)
	{
		logger.info("--------	getTextFrom wrapper method inside MyWebElement class is invoked successfully	--------");
		String text="";		
		try
		{
			if(isDisplayedorNot(element))
			{				
				text = element.getText();
				System.out.println("Element Displayed " + element);
				logger.info("--------	getTextFrom wrapper method inside MyWebElement class is executed successfully	--------");
				return text;			
			}			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET TEXT FROM THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET TEXT FROM THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET TEXT FROM THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO GET TEXT FROM THE SPECIFIED WEB ELEMENT");			
		}
		return text;		
	}
	
	/* To type text inside any WebElement
	@Parameters : WebElement and Text */
	public static void typeTextIn(WebElement element, String textToType)
	{
		logger.info("--------	typeTheText wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{
			if(isDisplayedorNot(element)&&isEnabledorNot(element))
			{				
				//clickOn(element); //Commented on 28-Mar-2021
				element.sendKeys(textToType);	
				logger.info("--------	typeTheText wrapper method inside MyWebElement class is executed successfully	--------");
			}			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO TYPE TEXT INSIDE THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO TYPE TEXT INSIDE THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO TYPE TEXT INSIDE THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO TYPE TEXT INSIDE THE SPECIFIED WEB ELEMENT");			
		}
	}
	
	/* To submit any WebElement (similar to clicking on OK)
	@Parameter : WebElement */
	public static void submit(WebElement element)
	{
		logger.info("--------	submit wrapper method inside MyWebElement class is invoked successfully	--------");
		try
		{
			if(isDisplayedorNot(element)&&isEnabledorNot(element))
			{				
				element.submit();	
				logger.info("--------	submit wrapper method inside MyWebElement class is executed successfully	--------");
			}			
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO PERFORM SUBMIT OPERATION ON THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO PERFORM SUBMIT OPERATION ON THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO PERFORM SUBMIT OPERATION ON THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO PERFORM SUBMIT OPERATION ON THE SPECIFIED WEB ELEMENT");			
		}
	}
	
	/* To get the x-axis coordinate of a web element
	@Parameters : WebElement */
	public static int getXAxis(WebElement element)
	{
		logger.info("--------	getXAxis wrapper method inside MyWebElement class is invoked successfully	--------");
		int xAxis = 0;
		try
		{
			xAxis = element.getLocation().x;
			logger.info("--------	getXAxis wrapper method inside MyWebElement class is executed successfully	--------");
			return xAxis;
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET X-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET X-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET X-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET X-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT");		
		}
		return xAxis;
	}
	
	/* To get the y-axis coordinate of a web element
	@Parameters : WebElement */
	public static int getYAxis(WebElement element)
	{
		logger.info("--------	getYAxis wrapper method inside MyWebElement class is invoked successfully	--------");
		int yAxis = 0;
		try
		{
			yAxis = element.getLocation().y;
			logger.info("--------	getYAxis wrapper method inside MyWebElement class is executed successfully	--------");
			return yAxis;
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET Y-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET Y-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET Y-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET Y-AXIS CO-ORDINATE OF THE SPECIFIED WEB ELEMENT");		
		}
		return yAxis;
	}
	
	/* To get the width of a web element
	@Parameters : WebElement */
	public static int getWidth(WebElement element)
	{
		logger.info("--------	getWidth wrapper method inside MyWebElement class is invoked successfully	--------");
		int width = 0;
		try
		{
			width = element.getSize().width;
			logger.info("--------	getWidth wrapper method inside MyWebElement class is executed successfully	--------");
			return width;
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET THE WIDTH OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET THE WIDTH OF THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET THE WIDTH OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET THE WIDTH OF THE SPECIFIED WEB ELEMENT");		
		}
		return width;
	}
	
	/* To get the height of a web element
	@Parameters : WebElement */
	public static int getHeight(WebElement element)
	{
		logger.info("--------	getHeight wrapper method inside MyWebElement class is invoked successfully	--------");
		int heigth = 0;
		try
		{
			heigth = element.getSize().height;
			logger.info("--------	getHeight wrapper method inside MyWebElement class is invoked successfully	--------");
			return heigth;
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET THE HEIGHT OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET THE HEIGHT OF THE SPECIFIED WEB ELEMENT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO GET THE HEIGHT OF THE SPECIFIED WEB ELEMENT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO GET THE HEIGHT OF THE SPECIFIED WEB ELEMENT");		
		}
		return heigth;
	}
	
	/* To select an option from a DropDown box using specified value
	@Parameters : WebElement and value */
	public static void selectDropDownByValue(WebElement element, String value)
	{
		logger.info("--------	selectDropDownByValue wrapper method inside MyWebElement class is invoked successfully	--------");
		Select dropdown;		
		try
		{		
			if(FalconWebElement.isDisplayedorNot(element))
			{				
				dropdown = new Select(element);
				dropdown.selectByValue(value);	
				logger.info("--------	selectDropDownByValue wrapper method inside MyWebElement class is executed successfully	--------");
			}		
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED VALUE : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED VALUE");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED VALUE : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED VALUE");			
		}
	}
	
	/* To select an option from a DropDown box using specified text
	@Parameters : WebElement and text */
	public static void selectDropDownByVisibleText(WebElement element, String text)
	{
		logger.info("--------	selectDropDownByVisibleText wrapper method inside MyWebElement class is invoked successfully	--------");	
		Select dropdown;		
		try
		{				
			if(FalconWebElement.isDisplayedorNot(element))
			{					
				dropdown = new Select(element);
				dropdown.selectByVisibleText(text);
				logger.info("--------	selectDropDownByVisibleText wrapper method inside MyWebElement class is executed successfully	--------");
			}		
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED TEXT : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED TEXT");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED TEXT : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED TEXT");			
		}
	}
	
	/* To select an option from a DropDown box using specified index
	@Parameters : WebElement and index */	
	public static void selectDropDownByIndex(WebElement element, int index)
	{
		logger.info("--------	selectDropDownByIndex wrapper method inside MyWebElement class is invoked successfully	--------");
		Select dropdown;		
		try
		{				
			if(FalconWebElement.isDisplayedorNot(element))
			{					
				dropdown = new Select(element);
				dropdown.selectByIndex(index);
				logger.info("--------	selectDropDownByIndex wrapper method inside MyWebElement class is executed successfully	--------");
			}				
		}
		catch(NoSuchElementException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED INDEX : "+error.getMessage());
			Assert.fail("NoSuchElementException : WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED INDEX");		
		}
		catch(StaleElementReferenceException error)
		{			
			logger.error("ERROR WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED INDEX : "+error.getMessage());
			Assert.fail("StaleElementReferenceException : WHILE TRYING TO THE SELECT A DROPDOWN OPTION USING SPECIFIED INDEX");			
		}
	}
	


}