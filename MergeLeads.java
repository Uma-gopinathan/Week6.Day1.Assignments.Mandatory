package Week6.Day1.Assignments.Mandatory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MergeLeads extends LeafTapsBaseClass{

	@Test(dependsOnMethods="Week6.Day1.Assignments.Mandatory.CreateLead.create")
	//@Test
	
	public void Merge() throws InterruptedException {
		
		driver.findElement(By.linkText("Leads")).click(); //CLICK THE Leads tab link		
		driver.findElement(By.linkText("Merge Leads")).click(); //click the create link id

		//Click the lookup leads image
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();
		//print the current window handle
		System.out.println("The new window handle is: " + driver.getWindowHandle());

		//get all the handles that are opened in current execution - use set to avoid storing duplicates
		Set<String> allHandles = driver.getWindowHandles(); 
		System.out.println("The no.of handles opened in current execution: "+allHandles.size());

		//Create a list to access by id, cant do this in set as it doesnt have get method
		List<String> lstWindowHandles = new ArrayList<String>(allHandles);
		String strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle
		System.out.println("The 2nd window handle is: "+strSecondHandle);

		//moving control to 2nd window
		driver.switchTo().window(strSecondHandle);
		String strSecondHandleTitle=driver.getTitle();
		System.out.println("The 2nd window title is: "+strSecondHandleTitle);
		driver.findElement(By.name("firstName")).sendKeys("uma"); //enter username
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click(); //click find leads
		Thread.sleep(1000);
		WebElement linkLead = driver.findElement(By.xpath("(//td[contains(@class,'x-grid3-cell-first')]/div/a)[1]"));
		String strLinkText = linkLead.getText();	
		System.out.println(linkLead.getText());
		linkLead.click(); //clicks the first lead in the search

		//move control to first window
		driver.switchTo().window(lstWindowHandles.get(0));
		String strLinkText2 = driver.findElement(By.id("ComboBox_partyIdFrom")).getAttribute("value");

		//comparing if the link text is poulated in the field
		if(strLinkText.equals(strLinkText2)) {
			System.out.println("Passed! Lead id: "+ strLinkText2+" from the lookup window: "+strSecondHandleTitle +" has been populated in the 'form lead' field of the 1st window");
		}
		else {
			System.out.println("Failed! Lead id: "+ strLinkText2+" from the lookup window: "+strSecondHandleTitle +" has not been populated in the 'form lead' field of the 1st window");
		}
	}

}
