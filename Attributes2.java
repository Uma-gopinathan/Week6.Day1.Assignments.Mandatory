package Week6.Day1.Assignments.Mandatory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class Attributes2 extends LeafTapsBaseClass{
	@Test(groups="create", priority=2)
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
		driver.findElement(By.className("smallSubmit")).click();  //click the create lead button
	}

	@Test(groups="edit",priority=-1)
	public void editLead() throws InterruptedException {
		String strNewCompany="NewCompany6";
		String strUpdatedCompanyName;
	
		//Edit the lead
		driver.findElement(By.linkText("Leads")).click();//CLICK THE Leads tab link
		driver.findElement(By.linkText("Find Leads")).click(); //click the find leads button
		driver.findElement(By.xpath("(//label[text()='First name:'])[3]/following::input[@name='firstName']")).sendKeys("uma"); //enter the first name
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click(); //click Find leads button
		Thread.sleep(2000);
		WebElement table = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
		wait.until(ExpectedConditions.visibilityOf(table));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")).click();  //click the first resulting lead

		//verify is the lead is created-page verification
		String strTitle=driver.getTitle();
		System.out.println(strTitle);
		if(strTitle.equals("View Lead | opentaps CRM")) {
			System.out.println("The lead page is opened");
			driver.findElement(By.xpath("//a[text()='Edit']")).click();  //Click the edit button
			driver.findElement(By.xpath("//span[text()='Company Name']/following::input[@name='companyName']")).clear(); //clear the company name
			driver.findElement(By.xpath("//span[text()='Company Name']/following::input[@name='companyName']")).sendKeys(strNewCompany) ; //enter new company name
			driver.findElement(By.xpath("//input[@value='Update']")).click();  //click updatebutton
			//Confirm the changed name appears
			WebElement updatedCompany = driver.findElement(By.xpath("//span[contains(text(),'NewCompany')]"));
			strUpdatedCompanyName = updatedCompany.getText();
			System.out.println(strUpdatedCompanyName);
			if(strUpdatedCompanyName.contains(strNewCompany)) {
				System.out.println("Changed company name is updated in the lead");
			}
			else {
				System.out.println("Failed!!Changed company name is not updated in the lead");
			}
		}

		else{
			System.out.println("Failed!! The lead page is not opened, cannot edit the lead");			
		}
	}
	
	@Test(groups="duplicate",priority=2)
	public void edit() {
		driver.findElement(By.linkText("Leads")).click(); //CLICK THE Leads tab link		
		driver.findElement(By.linkText("Create Lead")).click(); //click the create link id            
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("SeleniumTest");//Enter company name
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Uma");//Enter the first name
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("G");//Enter the last name
		driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("uma");//Enter the first name(local)
		driver.findElement(By.id("createLeadForm_departmentName")).sendKeys("Testing");
		driver.findElement(By.id("createLeadForm_description")).sendKeys("This is a testcase to create a duplicate lead");
		//		driver.findElement(By.name("primaryEmail")).sendKeys("uma123@gmail.com");
		WebElement dropStateOrProvince = driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId"));//Webelement dropdown object
		Select objProvince=new Select(dropStateOrProvince); //creating a select class to select value from dropdown
		objProvince.selectByVisibleText("New York"); //Select the state or province from dropdown
		driver.findElement(By.className("smallSubmit")).click();  //click the create lead button

		//verify if the lead is created-page verification
		String strTitle=driver.getTitle();
		//System.out.println(strTitle);
		if(strTitle.equals("View Lead | opentaps CRM")) { //Checking title of the page
			System.out.println("Successfully created lead");
		}
		else{
			System.out.println("Couldnot create lead");
		}

		//Duplicate lead
		driver.findElement(By.linkText("Duplicate Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).clear();//Clear the field
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("NewCompany");
		driver.findElement(By.id("createLeadForm_firstName")).clear();//clear the name field
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("uma g"); //enter new value
		driver.findElement(By.className("smallSubmit")).click(); 
		if(strTitle.equals("View Lead | opentaps CRM")) { //Checking title of the page
			System.out.println("Successfully updated lead");
		}
		else{
			System.out.println("Couldnot update lead");
		}
	}
}
