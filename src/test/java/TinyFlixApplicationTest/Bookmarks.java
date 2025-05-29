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

public class Bookmarks extends AbstractComponents{

	@Test
	public void TC007_Add_Bookmark() throws InterruptedException {

		WebDriver driver = launchApplication();
		//		  1. Play a video
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.xpath("//div[@class='video-player']/video"));

		js.executeScript("arguments[0].play()", video);
		Thread.sleep(3000);

		Boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused", video);

		if (!isPaused) {
			//		  2. Click the bookmark button at a specific timestamp
			driver.findElement(By.xpath("//button[@aria-label='Add bookmark']")).click();
			String videoTime = driver.findElement(By.xpath("//div[@class='video-controls']/div/span")).getText();
			//		  3. Verify bookmark is added to the bookmark list
			String bookmarkTime = driver.findElement(By.xpath("//li[@class='bookmark-item']/button/span[@class='bookmark-time']")).getText();
			Assert.assertEquals(bookmarkTime, videoTime);
		} else {
			Assert.assertTrue(false);
		}
		
		closeApplication();

	}
	
	@Test
	public void TC008_Navigate_to_Bookmark() throws InterruptedException {

		WebDriver driver = launchApplication();
		
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.xpath("//div[@class='video-player']/video"));

		js.executeScript("arguments[0].play()", video);
		Thread.sleep(3000);

		Boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused", video);

		if (!isPaused) {
			//  1. Click on a bookmark in the bookmark list
			driver.findElement(By.xpath("//button[@aria-label='Add bookmark']")).click();
			String videoTime = driver.findElement(By.xpath("//div[@class='video-controls']/div/span")).getText();
			String bookmarkTime = driver.findElement(By.xpath("//li[@class='bookmark-item']/button/span[@class='bookmark-time']")).getText();
			//  2. Verify video jumps to the correct timestamp
			Assert.assertEquals(bookmarkTime, videoTime);
			
			driver.findElement(By.xpath("//li[@class='bookmark-item']/button/span[@class='bookmark-time']")).click();
			String videoTime2 = driver.findElement(By.xpath("//div[@class='video-controls']/div/span")).getText();
			Assert.assertEquals(bookmarkTime, videoTime2);
			
		} else {
			Assert.assertTrue(false);
		}
		closeApplication();

	}
	
}
