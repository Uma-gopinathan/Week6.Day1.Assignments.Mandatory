package Week6.Day1.Assignments.Mandatory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateLead extends LeafTapsBaseClass{
	//@Test(invocationCount=2)//execute create Lead twice
	@Test(invocationCount=2)
	public void create() {
		//^^^^^^^Create Lead^^^^^^
		driver.findElement(By.linkText("Leads")).click(); //CLICK THE Leads tab link		
		driver.findElement(By.linkText("Create Lead")).click(); //click the create link id            
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("SeleniumTest");//Enter company name
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Uma");//Enter the first name
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("G");//Enter the last name
		driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("uma");//Enter the first name(local)
		driver.findElement(By.id("createLeadForm_departmentName")).sendKeys("Testing");
		driver.findElement(By.id("createLeadForm_description")).sendKeys("This is a testcase to create lead");
		//WebElement email = driver.findElement(By.name("primaryEmail"));  //element not interactable exception.so commented
		//wait.until(ExpectedConditions.visibilityOf(email));

		//driver.findElement(By.name("primaryEmail")).sendKeys("uma123@gmail.com");
		WebElement dropStateOrProvince = driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId"));//Webelement dropdown object
		Select objProvince=new Select(dropStateOrProvince); //creating a select class to select value from dropdown
		objProvince.selectByVisibleText("District of Columbia"); //Select the state or province from dropdown
		driver.findElement(By.className("smallSubmit")).click();  //click the create lead button

		//verify if the lead is created-page verification
		String strTitle=driver.getTitle();
		System.out.println(strTitle);
		if(strTitle.equals("View Lead | opentaps CRM")) {
			System.out.println("Successfully created lead");
		}
		else{
			System.out.println("Couldnot create lead");
		}

	}

}
