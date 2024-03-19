package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_00_Sample {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void TC_01_() {
		System.out.print("");


	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	public void sleepInSecond(long timeInSecond) {

		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getEmailAddress() {
		Random rand = new Random();
		return "mikejoin" + rand.nextInt(9999) + "@gmail.net";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}