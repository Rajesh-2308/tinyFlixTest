package TinyFlixApplicationAbstract;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbstractComponents {

	WebDriver driver = new ChromeDriver();

	public WebDriver launchApplication() {

		driver.get(" http://localhost:5173/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;

	}

	public void closeApplication() {
		driver.quit();

	}

}
