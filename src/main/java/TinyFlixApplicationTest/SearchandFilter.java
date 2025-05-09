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

public class SearchandFilter extends AbstractComponents{

	@Test
	public void TC004_Search_by_Title() throws InterruptedException {

		WebDriver driver = launchApplication();
		//		  1. Enter a video title in the search box
		//		  2. Press Enter
		driver.findElement(By.xpath("//div[@class='search-container']/input")).sendKeys("advanced react");
		
		String videoInfo = driver.findElement(By.xpath("//div[@class='video-info']/h3")).getText();
		
		//		  3. Verify matching videos are displayed
		Assert.assertTrue(videoInfo.equalsIgnoreCase("advanced react"));
		
		closeApplication();

	}
	
	@Test
	public void TC005_Search_by_Tags() throws InterruptedException {

		WebDriver driver = launchApplication();
		//		  1. Enter a tag in the search box
		//		  2. Press Enter
		driver.findElement(By.xpath("//div[@class='search-container']/input")).sendKeys("jest");
		
		List<WebElement> taginfo = driver.findElements(By.xpath("//div[@class='video-tags']/span"));
		//		  3. Verify videos with matching tags are displayed
		Assert.assertTrue(taginfo.stream().anyMatch(a->a.getText().contains("jest")));
		
		closeApplication();

	}
	
	@Test
	public void TC006_Filter_by_Recent() throws InterruptedException {

		WebDriver driver = launchApplication();
		//  1. Select "Recent" from the filter dropdown
		WebElement dropdown = driver.findElement(By.xpath("//select[@aria-label='Filter videos']"));
		Select select = new Select(dropdown);
		select.selectByValue("recent");
		//  2. Verify only recent videos are displayed
		List<WebElement> videoCount = driver.findElements(By.xpath("//div[@class='video-card']"));
		boolean videoPresent = videoCount.size()>0 ? true : false;
		
		Assert.assertTrue(videoPresent);
		
		closeApplication();

	}
		

}
