package tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

@SuppressWarnings("unused")

public class PickUrl {

public static String A300x600(WebDriver driver) throws Exception{

System.out.println("############# Automation initiated for 300x600  #############");
	
	String sURL = ExcelUtils.getCellData(1, 2);

	driver.get(sURL);
	
	return sURL ;

}

public static String B300x250(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for 300x250  #############");

	String sURL = ExcelUtils.getCellData(2, 2);

	driver.get(sURL);
	
	return sURL ;
	
}

public static String C160x600(WebDriver driver) throws Exception{

System.out.println("############# Automation initiated for 160x600 #############");

String sURL = ExcelUtils.getCellData(3, 2);

driver.get(sURL);

return sURL ;

}

public static String D728x90(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for 728x90 #############");

String sURL = ExcelUtils.getCellData(4, 2);

driver.get(sURL);

return sURL ;

}

public static String E320x50(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for E320x50 #############");

String sURL = ExcelUtils.getCellData(5, 2);

driver.get(sURL);

return sURL ;

}

public static String F300x50(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for F300x50 #############");

String sURL = ExcelUtils.getCellData(6, 2);

driver.get(sURL);

return sURL ;

}

public static String Banner7(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for Banner7 #############");

String sURL = ExcelUtils.getCellData(7, 2);

driver.get(sURL);

return sURL ;

}

public static String Banner8(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for Banner8 #############");

String sURL = ExcelUtils.getCellData(8, 2);

driver.get(sURL);

return sURL ;

}
public static String Banner9(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for Banner9 #############");

String sURL = ExcelUtils.getCellData(9, 2);

driver.get(sURL);

return sURL ;

}

public static String Banner10(WebDriver driver) throws Exception{
	
System.out.println("############# Automation initiated for Banner10 #############");

String sURL = ExcelUtils.getCellData(10, 2);

driver.get(sURL);

return sURL ;

}

}
