package Week6.Day1.Assignments.Mandatory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MergeContact extends LeafTapsBaseClass {
	@Test(timeOut=12000)
	public void MergeContactMethod() throws InterruptedException {

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
}