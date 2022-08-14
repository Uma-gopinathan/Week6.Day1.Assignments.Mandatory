package Week6.Day1.Assignments.Mandatory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class EditLead extends LeafTapsBaseClass{
@Test(dependsOnMethods="Week6.Day1.Assignments.Mandatory.CreateLead.create")
	public void EditLeadMethod() throws InterruptedException {
		//name of the company to be updated
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

}
