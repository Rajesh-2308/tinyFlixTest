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

public class Comments extends AbstractComponents{

	@Test
	public void TC009_Add_Comment() throws InterruptedException {

		WebDriver driver = launchApplication();
		
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		//		  1. Enter a comment in the comment box
		driver.findElement(By.xpath("//form[@class='comment-form']/textarea")).sendKeys("testPress");
		//		  2. Click "Post Comment"
		driver.findElement(By.xpath("//form[@class='comment-form']/button")).click();
		String commentText = driver.findElement(By.xpath("//div[@class='comment']/p")).getText();
		//		  3. Verify comment appears in the comments list
		Assert.assertTrue(commentText.equals("testPress"));
		
		closeApplication();

	}
	
	@Test
	public void TC010_Reply_to_Comment() throws InterruptedException {

		WebDriver driver = launchApplication();
		
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		driver.findElement(By.xpath("//form[@class='comment-form']/textarea")).sendKeys("testPress");
		driver.findElement(By.xpath("//form[@class='comment-form']/button")).click();

		//		  1. Enter a reply in the reply box
		driver.findElement(By.xpath("//div[@class='replies']/form/input")).sendKeys("Hello");
		//		  2. Click "Reply"
		driver.findElement(By.xpath("//div[@class='replies']/form/button")).click();
		String replyText = driver.findElement(By.xpath("//div[@class='reply']/p")).getText();
		//		  3. Verify reply appears under the comment
		Assert.assertTrue(replyText.equals("Hello"));
		
		closeApplication();

	}
	
}
