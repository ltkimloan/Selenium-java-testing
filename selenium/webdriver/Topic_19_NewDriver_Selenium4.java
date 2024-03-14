package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_NewDriver_Selenium4 {
	WebDriver userDriver;
	WebDriver adminDriver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		userDriver = new FirefoxDriver();
		userDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("Webdriver ID cua User: "+userDriver.toString());


		adminDriver = new FirefoxDriver();
		adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("Webdriver ID cua admin: "+adminDriver.toString());
	}

	@Test
	public void TC_01_() {
		userDriver.get("https://demo.nopcommerce.com/");
		userDriver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("auto");

		adminDriver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		adminDriver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);

		Assert.assertEquals(adminDriver.findElement(By.xpath("//div[@class='card-header with-border']/parent::div//h4")).getText()
		, "Welcome to your store!");

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

	@AfterClass
	public void afterClass() {
		userDriver.quit();
		adminDriver.quit();
	}

}