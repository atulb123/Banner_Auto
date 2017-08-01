package tests;

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import tests.PickUrl;


@SuppressWarnings("unused")
public class ParallelTest {
	public static String version2;
	public static String version3;
	public static String version1;
		int initValue = 0;
		private WebDriver driver;
		public String startTime;
		public String endTime;
		public int st,et;
		public int tt;
		//public static String browser name = "";
	    
	@Parameters({ "browser" })
	@BeforeTest
	public void openBrowser(String browser) throws Exception {
		
		// Disable below comment to start video recording, it will save in current user Videos
		// VideoReord.startRecording();
		//browsername = browser;
		try {
			FileUtils.cleanDirectory(new File("C:/Banner_Auto_Old/Output/")); 
			System.out.println("Suceesfully deleted output file");
			}
			catch (Exception e) {
				System.out.println("Failed to delete file");
				System.out.println(e.getMessage());
			}
		
		
		Constant.Copy();
		System.out.println("Creating OutputFile in Output folder...... can take up to 2 seconds... ");
		Thread.sleep(2000);
		// Below code copy & paste it in output folder - alternate to above constant_copy method
		/* try {
			File source = new File("C:\\Banner_Automation_Pre-requisite\\Input_Output.xls");
			File dest = new File("C:\\Banner_Automation_Pre-requisite\\Output\\");
			FileUtils.copyFileToDirectory(source, dest);
			Thread.sleep(5000);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		*///
			ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"MultipleBrowser");

			try {
			FileUtils.cleanDirectory(new File("C:/Banner_Auto_Old/Test_Screenshot/"));
			
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		try {
			 if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
				    version2 = cap.getBrowserName().toString();
	
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
				driver = new ChromeDriver();
			driver.manage().window().maximize();
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			    version1 = cap.getBrowserName().toString()+" ";
			   String version = cap.getVersion().toString();

			    for(int i=0;i<=3;i++)
			    {
			    	version1=version1+version.charAt(i);
			    }
			    
			    System.out.println("Running chrome "+version1);

			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver","./driver/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
				   String v = cap.getVersion().toString();
			     version3="IE "+v;
			    System.out.println("Running IE "+version3);
			} 			
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	int bannerURLLength = 0;
	@Test (priority=1)
	public void findBannerLength() throws Exception
	{
		for(int i=1; i< 20; i++){
			String ttt = ExcelUtils.getCellData(i, 0);
			if(!"BannerListEnd".equals(ttt)){
				bannerURLLength += 1;
			}else{
				break;
			}
		}
		
	}
	
	@Parameters({ "browser" })	
	@Test (priority=2)
	public void TestCase_ALL(String browser ) throws Exception {
		 try {
			 System.out.println("Banner length total: "+ bannerURLLength);
			for(int i=1; i<= bannerURLLength; i++){
				String sURL = ExcelUtils.getCellData(i, 2);
				System.out.println("I:: "+ i+ " sURL: "+ sURL);
				if(!("".equals(sURL) || sURL == null)){
					String bannerSize = ExcelUtils.getCellData(i, 1);
					driver.get(sURL);
					Thread.sleep(1000); 
					initValue = Filter.Search(driver, initValue, browser, bannerSize);	
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@AfterTest
	
	public void closeBrowser() {
		/*try {
			VideoReord.stopRecording();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		//driver.close();
		driver.quit();
	}
	
   @BeforeSuite
   public void startTime() throws Exception
   {
	    startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
	   System.out.println(startTime);
	  
   }
   @AfterSuite
   public void endTime() throws Exception
   {	
	   String machineName = InetAddress.getLocalHost().getCanonicalHostName();
	   System.out.println(machineName);
	   ExcelUtils.setCellData(machineName, 16, 2);
	   if(Filter.flag==1)
	   {
		   ExcelUtils.setCellData("FAIL", 12, 2,HSSFColor.RED.index);
	   }
	   else
	   {
		   ExcelUtils.setCellData("PASS", 12, 2,HSSFColor.GREEN.index);
	   }
	    endTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
	   System.out.println(endTime);
	   ExcelUtils.setCellData(endTime, 14,2);
	   ExcelUtils.setCellData(startTime, 13,2);
	   TimeCal();TimeCal1();
		tt=(et-st)/60;
		int tts=(et-st)%60;
//		DecimalFormat df = new DecimalFormat("###.##");
//		System.out.println(tt);
		String ttime=tt+" min "+tts+" sec";
		
		
		ExcelUtils.setCellData(ttime, 15,2);
		Constant.CopyAllFiles();
		SendEmail.PostMarkSendEmail();
   }
   public void TimeCal()
   {
	   char ch1 = endTime.charAt(0);
	   char ch2 = endTime.charAt(1);
	   String sh=""+ch1+ch2;
	  // System.out.println(sh);
	   int digit=Integer.parseInt(sh);
	   digit=digit*3600;
	   //System.out.println(digit);
	   char ch3 = endTime.charAt(3);
	   char ch4 = endTime.charAt(4);
	   String sh1=""+ch3+ch4;
	  // System.out.println(sh1);
	   int digit1=Integer.parseInt(sh1);
	   digit1=digit1*60;
	   char ch5 = endTime.charAt(6);
	   char ch6 = endTime.charAt(7);
	   String sh2=""+ch5+ch6;
	  // System.out.println(sh2);
	   int digit2=Integer.parseInt(sh2);
	    et=digit2+digit+digit1;
	   System.out.println(et);
   }
   public void TimeCal1()
   {
	   char ch1 = startTime.charAt(0);
	   char ch2 = startTime.charAt(1);
	   String sh=""+ch1+ch2;
	  // System.out.println(sh);
	   int digit=Integer.parseInt(sh);
	   digit=digit*3600;
	 //  System.out.println(digit);
	   char ch3 = startTime.charAt(3);
	   char ch4 = startTime.charAt(4);
	   String sh1=""+ch3+ch4;
	  // System.out.println(sh1);
	   int digit1=Integer.parseInt(sh1);
	   digit1=digit1*60;
	   char ch5 = startTime.charAt(6);
	   char ch6 = startTime.charAt(7);
	   String sh2=""+ch5+ch6;
	  // System.out.println(sh2);
	   int digit2=Integer.parseInt(sh2);
	    st=digit2+digit+digit1;
	   System.out.println(st);
   }
   
	}
