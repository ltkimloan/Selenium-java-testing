package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_22_Wait_I_ElementStatus {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By confirmEmail= By.cssSelector("input[name='reg_email_confirmation__']");
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.facebook.com/");

	}

	@Test
	public void TC_01_Visible() {
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("automation@gmail.com");
		sleepInSecond(2);

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmail));
		Assert.assertTrue(driver.findElement(confirmEmail).isDisplayed());

	// Tai thoi diem nay thi confirm - email dang visible / displayed
	}

	@Test
	public void TC_02_Invisible_In_Dom() {
		driver.findElement(By.xpath("//input[@name='reg_email__']")).clear();
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmail));

		Assert.assertFalse(driver.findElement(confirmEmail).isDisplayed());
	}
	@Test
	public void TC_03_Invisible_Not_In_Dom() {
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmail));
	}

	@Test
	public void TC_04_Presence_() {
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("automation@gmail.com");
		sleepInSecond(2);
		// Dieu kien 1, element co trong ui & html
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmail));
		driver.findElement(By.xpath("//input[@name='reg_email__']")).clear();
		sleepInSecond(2);

		// Dieu kien 2, element khong co trong UI nhung co trong html
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmail));

	}
	@Test
	public void TC_05_Staleness() {
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		// tai thoi diem nay element co xuat hien va ton tai trong html
		WebElement reconfirmEmail = driver.findElement(confirmEmail);

		// click close popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);

		// Dieu kien 3: element khong xuat hien tren UI  va khong co trong html
		explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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