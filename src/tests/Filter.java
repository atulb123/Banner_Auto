package tests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tests.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

@SuppressWarnings("unused")
public class Filter extends JComponent {
	public static int flag=0;
	
	@Parameters({ "browser" })

public static int Search(WebDriver driver, int startPosition, String browser, String bannerSize){
		System.out.println("browser name :"+browser);
		 System.out.println("Initiating..."); 
		 String finalURL;
		 String res="FAIL";
try {
	int colStartPosition = 0;
	//If startposition equal to zero, then search for the column header 'Locators Expected' 
	//to get the start cell data for executing test script
	if(startPosition == 0){		
		for(int i=0; i< 20; i++){
			String str = ExcelUtils.getCellData(i, 0);
			if("Locators Expected".equals(str)){
				colStartPosition = i;
				break;
			}
		}
	}
	else
	{
		colStartPosition = startPosition;
		
	}
	ExcelUtils.setCellData(bannerSize, colStartPosition+1, 16);
			for (int i = colStartPosition + 1; i < i+2; i++) {
				String id = ExcelUtils.getCellData(i, 2);
				if ("#".equals(id)) {
					System.out.println("returning cell location for next banner: "+ colStartPosition);
					return i;  

				} else {
					for (String ParentWindow : driver.getWindowHandles()) {
						
						if (!"N".equals(ExcelUtils.getCellData(i, 0))) {
							if (driver.findElements(By.id(id)).size() > 0) {
								//
								try{
								//						
								//WebElement element = driver.findElement(By.id(id));
								ExcelUtils.setCellData("Y", i, 1);
								String excelURL = ExcelUtils.getCellData(i, 4);
								// To scroll till the particular element is
								// visible
								/*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);* Atul Commenting as of now*/
								File src1= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
								
								FileUtils.copyFile(src1, new File("C:/Banner_Auto_Old/Test_Screenshot/"+ browser + "_" + bannerSize + "_" + System.currentTimeMillis()+ "_Banner.png"));
								
								//Wait for element
								WebDriverWait wait = new WebDriverWait(driver,15);//Atul changed to 20 sec
								//wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
								// Click event passing id. Get the url from
								// click event. Store it in finalURL.
								//
								//System.out.println(driver.getCurrentUrl());								
								//
								//element.click();
								//
								//Thread.sleep(7000);//Atul commenting it
								//
								String jScript="document.getElementById('"+id+"').click()";
								//driver.manage().window().setPosition(new Point(-2000, 0));
			            		System.out.println(jScript);
			            		JavascriptExecutor js=(JavascriptExecutor)driver;
			            		js.executeScript(jScript);
								System.out.println(driver.getWindowHandles());
								System.out.println(driver.getCurrentUrl());
								// Switch to new window								
								for (String winHandle : driver.getWindowHandles()) {
									if(!winHandle.equals(ParentWindow)){
										driver.switchTo().window(winHandle);
										System.out.println(driver.getCurrentUrl());
										break;
									}
									//driver.switchTo().window(winHandle);	
								}
								//								
								//System.out.println(driver.getCurrentUrl());
								//
								// Perform the actions on new
								// window--------------
								// Below wait time is given to load newly opened page after a click in banner.
								// To get screenshot & URL after redirection, 7 seconds delay is kept
								Thread.sleep(1000);								
								driver.manage().window().maximize();								
								File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
								FileUtils.copyFile(src, new File("C:/Banner_Auto_Old/Test_Screenshot/"+ browser + "_" + bannerSize + "_" + System.currentTimeMillis()+"_PopUp.png"));
								try{
								//wait.until(ExpectedConditions.urlContains(excelURL));
								wait.until(ExpectedConditions.urlMatches(excelURL));
								 finalURL = driver.getCurrentUrl();
								//driver.close();
								}
								catch(Exception e)
								{
									finalURL = driver.getCurrentUrl();	
									//driver.close();
								}
								//ExcelUtils.setCellData(finalURL, i, 5);
								if ("Firefox".equalsIgnoreCase(browser)) {
									Thread.sleep(500);
									ExcelUtils.setCellData(finalURL, i, 7);
									
								} else if ("chrome".equalsIgnoreCase(browser)) {
									Thread.sleep(500);
									ExcelUtils.setCellData(finalURL, i, 10);
								} else if ("IE".equalsIgnoreCase(browser)) {
									Thread.sleep(500);
									ExcelUtils.setCellData(finalURL, i, 13);
								}
								
								System.out.println(" Expected excelsheet URL: "+ excelURL + "->" +browser  );
								System.out.println(" Actual landing page URL: "+ finalURL + "->" +browser );
								System.out.println("-----------------------------------"); 
								if (excelURL.equals(finalURL)) {
									
								
									
									
									if("Firefox".equalsIgnoreCase(browser)){
										Thread.sleep(1000);
											ExcelUtils.setCellData("PASS", i, 8,HSSFColor.GREEN.index);
											ExcelUtils.setCellData("Outer layer link is actionable and displayed in a new tab", i, 9);
									}else if("chrome".equalsIgnoreCase(browser)){
										Thread.sleep(500);
										ExcelUtils.setCellData("PASS", i, 11,HSSFColor.GREEN.index);
										ExcelUtils.setCellData("Outer layer link is actionable and displayed in a new tab", i, 12);
									}else if("IE".equalsIgnoreCase(browser)){
										Thread.sleep(500);
										ExcelUtils.setCellData("PASS", i, 14,HSSFColor.GREEN.index);
										ExcelUtils.setCellData("Outer layer link is actionable and displayed in a new tab", i, 15);
									}
																}
								else  {
									flag=1;
									/* when expected and actual Url is different
									 * this block will execute
									 *  */
									if ("Firefox".equalsIgnoreCase(browser)) {
										Thread.sleep(500);
										
										ExcelUtils.setCellData("FAIL", i, 8,HSSFColor.RED.index);
										ExcelUtils.setCellData("EXPECTED URL is not matching with ACTUAL URL", i, 9);
									} else if ("chrome".equalsIgnoreCase(browser)) {
										Thread.sleep(500);
										ExcelUtils.setCellData("FAIL", i, 11,HSSFColor.RED.index);
										ExcelUtils.setCellData("EXPECTED URL is not matching with ACTUAL URL", i, 12);
									} else if ("IE".equalsIgnoreCase(browser)) {
										Thread.sleep(500);
										ExcelUtils.setCellData("FAIL", i, 14,HSSFColor.RED.index);
										ExcelUtils.setCellData("EXPECTED URL is not matching with ACTUAL URL", i, 15);
									}	
							}
					
								driver.close();
								try{
								driver.switchTo().window(ParentWindow);
								}
								catch(Exception e)
								{
									driver.quit();
								}
							}
								//
								catch (Exception e){
									e.printStackTrace();									
								}
								//
							} 
							else  {
								flag=1;
									ExcelUtils.setCellData("N", i, 1);
									if ("Firefox".equalsIgnoreCase(browser)) {
										Thread.sleep(500);
										ExcelUtils.setCellData("FAIL", i, 8,HSSFColor.RED.index);
										ExcelUtils.setCellData("Expected button/link/locator is NOT AVAILABLE in banner", i, 9);
										ExcelUtils.setCellData("----NOT AVAILABLE----", i, 7);
									} else if ("chrome".equalsIgnoreCase(browser)) {
										Thread.sleep(500);
										ExcelUtils.setCellData("FAIL", i, 11,HSSFColor.RED.index);
										ExcelUtils.setCellData("Expected button/link/locator is NOT AVAILABLE in banner", i, 12);
										ExcelUtils.setCellData("----NOT AVAILABLE----", i, 10);
									} else if ("IE".equalsIgnoreCase(browser)) {
										Thread.sleep(500);
										ExcelUtils.setCellData("FAIL", i, 14,HSSFColor.RED.index);
										ExcelUtils.setCellData("Expected button/link/locator is NOT AVAILABLE in banner", i, 15);
										ExcelUtils.setCellData("----NOT AVAILABLE----", i, 13);
									}	
							}
						} 
						else {
							if (driver.findElements(By.id(id)).size() > 0) {
								/* 
								 * Condition for un-necessary link, when an unexpected locator is present.
								 * Locators expected is N, Locator in build is Y -> This condition will execute 
								 *  */
								ExcelUtils.setCellData("Y", i, 1);
								//ExcelUtils.setCellData("FAIL", i, 6);
								if("Firefox".equalsIgnoreCase(browser)){
									Thread.sleep(500);
									ExcelUtils.setCellData("N.A", i, 8);
									ExcelUtils.setCellData("Un-necessary link found!", i, 9);
									ExcelUtils.setCellData("----NOT APPLICABLE----", i, 7);
								}else if("chrome".equalsIgnoreCase(browser)){
									Thread.sleep(500);
									ExcelUtils.setCellData("N.A", i, 11);
									ExcelUtils.setCellData("Un-necessary link found!", i, 12);
									ExcelUtils.setCellData("----NOT APPLICABLE----", i, 10);
								}else if("IE".equalsIgnoreCase(browser)){
									Thread.sleep(500);
									ExcelUtils.setCellData("N.A", i, 14);
									ExcelUtils.setCellData("Un-necessary link found!", i, 15);
									ExcelUtils.setCellData("----NOT APPLICABLE----", i, 13);
								}
							} else {
								/* 
								 Condition when the build is not equipped with a locator.(Requirement is achieved)
								 Locators expected is N, Locator in build is N -> This condition will execute 
								 */
								ExcelUtils.setCellData("N", i, 1);
								//ExcelUtils.setCellData("PASS", i, 6);
								if("Firefox".equalsIgnoreCase(browser)){
									Thread.sleep(500);
									ExcelUtils.setCellData("N.A", i, 8);
									ExcelUtils.setCellData("This link/button is not required for this banner", i, 9);
									ExcelUtils.setCellData("----NOT APPLICABLE----", i, 7);
								}else if("chrome".equalsIgnoreCase(browser)){
									Thread.sleep(500);
									ExcelUtils.setCellData("N.A", i, 11);
									ExcelUtils.setCellData("This link/button is not required for this banner", i, 12);
									ExcelUtils.setCellData("----NOT APPLICABLE----", i, 10);
								}else if("IE".equalsIgnoreCase(browser)){
									Thread.sleep(500);
									ExcelUtils.setCellData("N.A", i, 14);
									ExcelUtils.setCellData("This link/button is not required for this banner", i, 15);
									ExcelUtils.setCellData("----NOT APPLICABLE----", i, 13);
								}
							}
							
						}
						
					}
				}

			}
			
		} catch (Exception e) {			
				e.printStackTrace();
		}
	return 0;
	}


}