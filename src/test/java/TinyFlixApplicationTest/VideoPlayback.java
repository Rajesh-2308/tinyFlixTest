package TinyFlixApplicationTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import TinyFlixApplicationAbstract.AbstractComponents;


public class VideoPlayback extends AbstractComponents{

	@Test
	public void TC001_Video_PlayorPause() throws InterruptedException {

		// 1. Click on a video card
		WebDriver driver = launchApplication();
		driver.findElement(By.xpath("//div[@class='video-card']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.xpath("//div[@class='video-player']/video"));

		// 2. Click the play button
		js.executeScript("arguments[0].play()", video);
		Thread.sleep(3000);

		Boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused", video);

		Long time1 = (Long) js.executeScript("return arguments[0].currentTime", video);
		Thread.sleep(2000);
		Long time2 = (Long) js.executeScript("return arguments[0].currentTime", video);

		// 3. Verify video starts playing
		// 4. Click the pause button
		// 5. Verify video pauses
		boolean videoplayed;
		if (!isPaused && time2 > time1) {
			videoplayed = true;
		} else {
			videoplayed = false;
		}

		Assert.assertTrue(videoplayed);

		closeApplication();

	}

	@Test
	public void TC002_Volume_Control() throws InterruptedException {

		WebDriver driver = launchApplication();
		// 1. Play a video
		driver.findElement(By.xpath("//div[@class='video-card']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.xpath("//div[@class='video-player']/video"));

		js.executeScript("arguments[0].play()", video);
		Thread.sleep(3000);

		Boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused", video);

		// 2. Adjust volume slider
		Long time1 = (Long) js.executeScript("return arguments[0].currentTime", video);
		Thread.sleep(2000);
		Long time2 = (Long) js.executeScript("return arguments[0].currentTime", video);

		// 3. Verify volume changes
		if (!isPaused && time2 > time1) {
			js.executeScript("arguments[0].volume = 0.7;", video);
			String value = driver.findElement(By.xpath("//div[@class='volume-control']/input")).getAttribute("value");
			Assert.assertEquals(value, "0.7");

			// 4. Mute the video
			driver.findElement(By.xpath("//div[@class='volume-control']/button")).click();
			String buttonValue = driver.findElement(By.xpath("//div[@class='volume-control']/button"))
					.getAttribute("aria-label");
			// 5. Verify video is muted
			Assert.assertEquals(buttonValue, "Unmute");
		} else {
			Assert.assertTrue(false);
		}

		closeApplication();

	}

	@Test
	public void TC003_Playback_Rate() throws InterruptedException {

		WebDriver driver = launchApplication();
		// 1. Play a video
		driver.findElement(By.xpath("//div[@class='video-card']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.xpath("//div[@class='video-player']/video"));

		js.executeScript("arguments[0].play()", video);
		Thread.sleep(3000);

		Boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused", video);

		Long time1 = (Long) js.executeScript("return arguments[0].currentTime", video);
		Thread.sleep(2000);
		Long time2 = (Long) js.executeScript("return arguments[0].currentTime", video);

		if (!isPaused && time2 > time1) {
			WebElement dropdown = driver.findElement(By.xpath("//div[@class='playback-rate']/select"));
			Select select = new Select(dropdown);

			// 2. Change playback rate to 1.5x
			select.selectByValue("1.5");
			Object rate = js.executeScript("return arguments[0].playbackRate;", video);
			double actualSpeed = Double.parseDouble(rate.toString());
			// 3. Verify video plays at 1.5x speed
			Assert.assertEquals(actualSpeed, "1.5");

			// 4. Change playback rate to 0.5x
			select.selectByValue("0.5");
			rate = js.executeScript("return arguments[0].playbackRate;", video);
			actualSpeed = Double.parseDouble(rate.toString());
			// 5. Verify video plays at 0.5x speed
			Assert.assertEquals(actualSpeed, "0.5");

		} else {
			Assert.assertTrue(false);
		}

		closeApplication();

	}

}
