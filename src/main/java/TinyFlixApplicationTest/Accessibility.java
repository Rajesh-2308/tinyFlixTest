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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Accessibility extends AbstractComponents{

	@Test
	public void TC011_Keyboard_Navigation() throws InterruptedException {

		WebDriver driver = launchApplication();

		//		  1. Use Tab key to navigate through the application
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
       Assert.assertTrue(driver.switchTo().activeElement().getAttribute("aria-label").equals("Search videos"));
       
       //		  2. Use Enter key to activate buttons
		action.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).perform();
		WebElement dropdown = driver.findElement(By.xpath("//select[@aria-label='Filter videos']"));
		Select select = new Select(dropdown);
		Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Popular"));
		
		//		  3. Use arrow keys to adjust sliders
		driver.findElement(By.xpath("//div[@class='video-card']")).click();
		driver.findElement(By.xpath("//div[@class='volume-control']/input")).sendKeys(Keys.ARROW_LEFT);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='volume-control']/input")).getAttribute("value").equals("0.9"));
		
		closeApplication();

	}
	
}
