package Week6.Day1.Assignments.Mandatory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Attributes extends LeafTapsBaseClass{
	
	//priority of the test is 2
	@Test(priority=2)
	public void Delete() throws InterruptedException {
		String strUpdatedCompanyName, strNewCompany="NewCompany6";
		//^^^^^^^Delete Lead^^^^^^
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
	
	//this test shouldnot run
	@Test(enabled=false,priority=3)
	public void MergeContact() throws InterruptedException {
	
	//=============Merge 
	driver.findElement(By.xpath("//a[text()='Contacts']")).click();    //clicks the accounts link
	driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();  //click on Merge Contacts
	driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click(); 

	Set<String> allHandles = driver.getWindowHandles();		
	//Create a list to access by id, cant do this in set as it doesnt have get method
	List<String> lstWindowHandles = new ArrayList<String>(allHandles);
	String strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle		
	driver.switchTo().window(strSecondHandle);		//moving control to 2nd window
	driver.findElement(By.xpath("(//td[contains(@class,'x-grid3-cell-first')]/div/a)[1]")).click();  //click the first contact id link

	driver.switchTo().window(lstWindowHandles.get(0)); //Switch to 1st window		
	String strLinkText1 = driver.findElement(By.id("ComboBox_partyIdFrom")).getAttribute("value");
	driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click(); //click the lookup next to 'to contact'
	allHandles = driver.getWindowHandles();		
	//Create a list to access by id, cant do this in set as it doesnt have get method
	lstWindowHandles = new ArrayList<String>(allHandles);
	strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle
	driver.switchTo().window(strSecondHandle);	
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//td[contains(@class,'x-grid3-cell-first')]/div/a)[2]")).click();  //click the second contact id link
	driver.switchTo().window(lstWindowHandles.get(0)); //Switch to 1st window	
	String strLinkText2 = driver.findElement(By.id("ComboBox_partyIdTo")).getAttribute("value");
	driver.findElement(By.xpath("//a[text()='Merge']")).click(); //Click the merge button

	//Handle the alert
	Alert alert=driver.switchTo().alert();
	String text2 = alert.getText();
	alert.accept(); //clicks ok on the prompt box
	System.out.println("Handled the alert: " +text2);
	String title = driver.getTitle();
	System.out.println("The title after merging the contacts: "+strLinkText1 +" & " +strLinkText2 + " is: " + title);
	}
	
	//below class has 3 methods, Create whose priority(default is 1) is higher than Delete and invocation is run twice
	//time taken to execute shouldnt exceed 11 secs
	@Test(invocationCount=2,timeOut=11000)
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
