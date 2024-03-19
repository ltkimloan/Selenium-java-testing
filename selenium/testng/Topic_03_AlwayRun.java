package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_03_AlwayRun {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass(alwaysRun = true) // Du before class fail nhung after class van chay nho alwaysRun
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.print("Run beforeClass");
		Assert.assertTrue(false); // loi se xuat hien o day lam cho fail beforeclass

	}

	@Test
	public void TC_01_() {
		System.out.print("Run testcase 01");


	}

	@Test
	public void TC_02_() {
		System.out.print("Run testcase 02");

	}

	@Test
	public void TC_03_() {
		System.out.print("Run testcase 03");

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

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.print("Run after class");
		driver.quit();
	}
}