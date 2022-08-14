package Week6.Day1.Assignments.Mandatory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class LeafTapsBaseClass {
	ChromeDriver driver;
	WebDriverWait wait;
	@BeforeMethod
	public void beforeMethod() {
		String strUsername="demosalesmanager";
		String strPassword="crmsfa";
		//we have to call WDM for the browser driver
		WebDriverManager.chromedriver().setup(); //verify the version, download,setup		
		driver=new ChromeDriver();		//launch the browser -chrome
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Open browser and login
		driver.get("http://leaftaps.com/opentaps");		 //load the url
		driver.manage().window().maximize(); //maximize the browser		
		driver.findElement(By.id("username")).sendKeys(strUsername);	//enter the username in the username field	
		driver.findElement(By.id("password")).sendKeys(strPassword);		//enter the password in the password field
		driver.findElement(By.className("decorativeSubmit")).click();//Click the login button

		//verify if login is successful
		WebElement logout= driver.findElement(By.className("decorativeSubmit")); //Check if we are in the right page
		String attribute=logout.getAttribute("value"); //Get the attribute and print
		System.out.println(attribute);		
		if(attribute.equals("Logout")){
			System.out.println("Successfully logged in to leaftaps as: " +strUsername);
		}	

		driver.findElement(By.linkText("CRM/SFA")).click(); //click CRM/SFA button
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
