package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Innovation {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, password, fullName;
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		emailAddress = getEmailAddress();
		firstName = "Automation";
		lastName = "Fc";
		fullName = firstName + " " + lastName;
		password = "Auto123";

	}

	@Test(invocationCount = 1)
	public void TC_01_Register() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']// a[@title='My Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);

		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");
		String ContactInfor = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(ContactInfor);

		Assert.assertTrue(ContactInfor.contains(fullName));
		Assert.assertTrue(ContactInfor.contains(emailAddress));

		// log out

		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']")).click();
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		System.out.println("Email Address and Password: "+ emailAddress +"/" + password);
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