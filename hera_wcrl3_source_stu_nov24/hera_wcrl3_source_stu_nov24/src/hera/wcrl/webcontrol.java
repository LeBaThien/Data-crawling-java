package hera.wcrl;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

 
public class webcontrol {
 
	public WebDriver driver;
	public String firefox_link;
	public String chrome_link;
	public String safari_link;
	public String ie_link;
	public String webhost_link;
	public String dev;
	public String runnum;

	@Parameters({"browser", "num"})
	@BeforeTest
	
	//Passing Browser parameter from TestNG xml
	public void beforeTest(String browser, String num) throws InterruptedException, IOException {
		//openconfigW("config_web.xls");
		loadCSV load_csv = new loadCSV();
		load_csv.openconfigW("config_web.xls");
		firefox_link = load_csv.firefox_link;
		safari_link = load_csv.safari_link;
		chrome_link = load_csv.chrome_link;
		ie_link = load_csv.ie_link;
		webhost_link = load_csv.webhost_link;
		dev = load_csv.dev;

		//openconfigW ("config_web.xls");
		
		//get numrun
		runnum = num;
		// If the browser is Firefox, then do this
		if(browser.equalsIgnoreCase("firefox")) {
			if(isValid(firefox_link))
				System.setProperty("webdriver.gecko.driver", firefox_link);
			//options.addArguments("--disable-notifications");
			//System.setProperty("webdriver.gecko.driver","c:/your/path/to/geckodriver.exe");
			//driver = new MarionetteDriver();
			
			FirefoxOptions ffoption = new FirefoxOptions();
			ffoption.setHeadless(true);
			//FirefoxProfile ffprofile = new FirefoxProfile();
			//ffprofile.setPreference(FirefoxProfile.PORT_PREFERENCE, 8077);
			ffoption.addPreference("dom.webnotifications.enabled", false);
			//ffoption.addPreference(ffprofile.PORT_PREFERENCE, 8077);
			
			//firefoxPreferences.put("profile.password_manager_enabled", false);
			driver = new FirefoxDriver(ffoption);  
			
			//driver = new ChromeDriver();  
		}
		// If the browser is Safari, then do this
		else if (browser.equalsIgnoreCase("safari")) {
			if(isValid(safari_link))
				System.setProperty("webdriver.safari.driver",safari_link);
			driver = new SafariDriver();
		} 
		// If the browser is Chrome, then do this
		else if (browser.equalsIgnoreCase("chrome")) {
			/*
			if(isValid(chrome_link)) sdfsf
				System.setProperty("webdriver.chrome.driver",chrome_link);
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			options.addArguments("--disable-notifications");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			*/
			if(isValid(chrome_link))
				System.setProperty("webdriver.chrome.driver",chrome_link);
			
			ChromeOptions options = new ChromeOptions();
					
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			//options.addArguments("--disable-notifications");
			//options.addArguments("--silent-launch");
			//options.addArguments("--no-startup-window");
			//options.addArguments("no-sandbox");
			//options.addArguments("headless");
			//options.addArguments("port=8077");
			//capabilities.setCapability("port",8077);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			

		} 
		// If the browser is IE, then do this
		else if (browser.equalsIgnoreCase("ie")) {
			if(isValid(ie_link))
				System.setProperty("webdriver.ie.driver",ie_link);
			driver = new InternetExplorerDriver();
		} 
		// If the browser is Undefine, then do this
		//else {
			// Doesn't the browser type, lauch the Website
			driver.get(webhost_link);
		//}
		/*
		System.out.println("service init");
        Drive service = DriveQuickstart.getDriveService();
		System.out.println("end init");
        // Print the names and IDs for up to 10 files.
        FileList result=new FileList();
		try {
			result = service.files().list()
			     //.setPageSize(10)
			     //.setFields("nextPageToken, files(id, name)")
			     .execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<File> files = result.getItems();
        if (files == null || files.size() == 0) {
            System.out.println("No files found.");
        } 
        else 
        {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getOriginalFilename(), file.getId());
            }
        }
        */
    }
	
	private boolean isValid(String input){
		return !input.equals("\"\"")&&!input.isEmpty();
	}
/*
	// Once Before method is completed, Test method will start
	@Test public void loginFailed() throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='account']/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("log")).sendKeys("testuser_1");
		driver.findElement(By.id("pwd")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//form[@id='ajax_loginform']/p"));
		String strng = element.getText();
		Assert.assertEquals("ERROR: Invalid login credentials.", strng);

	}

*/

@Test
	public void runKDD() throws InterruptedException
	{

			//Call config CSV file
			koolj_dfrs KJdriven=new koolj_dfrs();
			try {
				KJdriven.openconfig("config.xls", driver, webhost_link, runnum);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
//static volatile boolean cleanedUp = false;
//static final Object lock = new Object();

	@AfterTest public void afterTest() {
	/*
		synchronized (lock) {
	        if (cleanedUp) return;
	        // do clean up
	        cleanedUp = true; 
	    }
	*/     
		driver.quit();
	}
}
