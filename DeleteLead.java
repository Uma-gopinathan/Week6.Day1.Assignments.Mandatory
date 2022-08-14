package Week6.Day1.Assignments.Mandatory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DeleteLead extends LeafTapsBaseClass {
	@Test(dependsOnMethods="Week6.Day1.Assignments.Mandatory.CreateLead.create")
	public void DeleteLeadMethod() throws Exception  {
		//pre-requisite -  a lead with this phone number should exist
		String strLeadID,strPhoneAreaCode,strPhoneNumber;
		strPhoneAreaCode="222";
		strPhoneNumber="3333";
		
		//Edit the lead
		driver.findElement(By.linkText("Leads")).click();//CLICK THE Leads tab link
		driver.findElement(By.linkText("Find Leads")).click(); //click the find leads button
		driver.findElement(By.linkText("Phone")).click();   //click the Phone tab
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='phoneAreaCode']")).sendKeys(strPhoneAreaCode); //Enter area code
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(strPhoneNumber); //Enter area code
		
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click(); //click Find leads button
		//to avoid stale element exception add sleep
		Thread.sleep(2000);
		WebElement leadID = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)"));
		strLeadID=leadID.getText(); //capture the lead id from results - first link
		System.out.println(strLeadID);
		leadID.click(); //click the lead id link

		//verify if the lead is opened-page verification
		String strTitle=driver.getTitle();
		System.out.println(strTitle);
		if(strTitle.equals("View Lead | opentaps CRM")) {
			System.out.println("The lead page is opened");

			//Delete lead
			driver.findElement(By.xpath("//a[text()='Delete']")).click(); 	//click the delete link	
			driver.findElement(By.linkText("Find Leads")).click();// leads //go to find leads page
			//verify if deleted lead is not seen in search results
			driver.findElement(By.name("id")).sendKeys(strLeadID);  //search by lead id which is deleted
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();   //click the find leads button
			Thread.sleep(1000);
			WebElement strNoRecords=driver.findElement(By.xpath("//div[@class='x-paging-info']"));  //text at the end of the search results table
			String strMessage=strNoRecords.getText();
			//checking the text at the below of the table
			if(strMessage.equals("No records to display")){  //table displays no records
				System.out.println("Passed!No records are displayed for the deleted lead");
			}
			else if(strMessage.contains("displaying")==true) { //table displays one or more records
				System.out.println("Failed!lead was not deleted");
			}

		}
		else {
			System.out.println("Failed!!The lead is no opened for deleting.");  //lead page is not opened - fail message
		}
	}
}
