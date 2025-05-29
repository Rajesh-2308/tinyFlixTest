package UploadDownloadTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FileUploadAutoIT {
	
	@Test
	public void FileUploadAutoITTest() throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ilovepdf.com/pdf_to_word");
		
		driver.findElement(By.xpath("//*[@id='pickfiles']")).click();
		Thread.sleep(4000);
		
		Runtime.getRuntime().exec("C:\\Users\\91637\\OneDrive\\Documents\\FileUpload.exe");
		
	}

}
