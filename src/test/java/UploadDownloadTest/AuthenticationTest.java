package UploadDownloadTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest {
	
	@Test
	public void authenticationPopup() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		driver.findElement(By.xpath("//*[.='Basic Auth']")).click();
		boolean successmsg = driver.findElement(By.xpath("//*[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed();
		Assert.assertTrue(successmsg);
		
	}

}
