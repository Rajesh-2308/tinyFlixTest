package UploadDownloadTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadDownload {
	
	@Test
	public void UploadDownloadFile() {
		
		String fruit = "Apple";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		
		//Download
		driver.findElement(By.xpath("//button[@id='downloadButton']")).click();
		
		//Upload
		WebElement element = driver.findElement(By.xpath("//input[@id='fileinput']"));
		element.sendKeys("C:\\Users\\91637\\Downloads\\download.xlsx");
		
		//wait for toast message to appear and disappear
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		System.out.println(toastmsg.getText());
		wait.until(ExpectedConditions.invisibilityOf(toastmsg));
		
		//check table value with respect to excel value
		String priceColumnId = driver.findElement(By.xpath("//div[.='Price']")).getAttribute("data-column-id");
		String priceValue = driver.findElement(By.xpath("//div[text()='"+fruit+"']/parent::div/parent::div/div[@id='cell-"+priceColumnId+"-undefined']")).getText();
		Assert.assertEquals(priceValue, "345");
		
		driver.quit();
		
	}

}
