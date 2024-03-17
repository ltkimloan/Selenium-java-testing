package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_24_Wait_III_ImplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Equals5_Second() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		//Wait cho loading icon bien mat
		//Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));
		// Wait cho hello world xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");
	}

	@Test
	public void TC_02_Less_Than_5_Second() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		//Wait cho loading icon bien mat
		//Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

		// Wait cho hello world xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");
	}

	@Test
	public void TC_03_More_5_Second() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		//Wait cho loading icon bien mat
		//Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

		// Wait cho hello world xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");

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

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}