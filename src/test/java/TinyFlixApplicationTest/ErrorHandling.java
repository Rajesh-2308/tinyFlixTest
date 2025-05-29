package TinyFlixApplicationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import TinyFlixApplicationAbstract.AbstractComponents;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ErrorHandling extends AbstractComponents {

	@Test
	public void TC013_Network_Error() throws InterruptedException {
		
		  //		  1. Disconnect from the internet
		WebDriver driver = launchApplication();
		//		  2. Try to play a video
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'Please check your internet connection')]"));
		//		  3. Verify error message is displayed
		boolean errormsg = elements.size()>0 ? true:false;
		
		Assert.assertTrue(errormsg);
		closeApplication();

	}
	
	@Test
	public void T014_Invalid_Input() throws InterruptedException {

		WebDriver driver = launchApplication();
		
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		//		  1. Enter an invalid comment (empty or too long)
		driver.findElement(By.xpath("//form[@class='comment-form']/textarea")).sendKeys("");
		//		  2. Click "Post Comment"
		driver.findElement(By.xpath("//form[@class='comment-form']/button")).click();
		//		  3. Verify error message is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//form[@class='comment-form']/div")).getText().equals("Comment cannot be empty"));
		
		closeApplication();

	}
	
	
	
}
