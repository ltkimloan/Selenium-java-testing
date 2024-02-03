package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	By emailTextBox = By.id("mail");
	By ageUnder18 = By.id("under_18");
	By eduTextArea = By.cssSelector("#edu");
	By number5 = By.xpath("//h5[text()='Name: User5']");
	By passwordTextBox = By.id("disable_password");
	By Biography = By.xpath("//textarea[@placeholder='TextArea is disabled']");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_isDisplayed() {
		// go to the website
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// text box co hien thi thi nhap vao
		if (driver.findElement(emailTextBox).isDisplayed()) {
			driver.findElement(emailTextBox).sendKeys("rememember@qa.team");
			System.out.println("Email textbox element is displayed");
		} else {
			System.out.println("Element not displayed");
		}

		// ageUnder18 - Ratio button
		if (driver.findElement(ageUnder18).isDisplayed()) {
			driver.findElement(ageUnder18).click();
			System.out.println("AgeUnder18 is displayed");
		} else {
			System.out.println("AgeUnder18 not displayed");
		}

		// education

		if (driver.findElement(eduTextArea).isDisplayed()) {
			driver.findElement(eduTextArea).sendKeys("Automation");
			System.out.println("Education Text Area is displayed");
		} else {
			System.out.println("Education Text Area not displayed");
		}

		// number 5

		if (driver.findElement(number5).isDisplayed()) {

			System.out.println("number 5 is displayed");
		} else {
			System.out.println("number 5 not displayed");
		}

	}

	// @Test
	public void TC_02_Enabled() {
		// go to the website
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Password textbox
		if (driver.findElement(passwordTextBox).isEnabled()) {
			System.out.println("Password is enabled");
		} else {
			System.out.println("Password is disenabled ");
		}

		//
		if (driver.findElement(Biography).isEnabled()) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography is disenabled ");
		}

		//
		if (driver.findElement(emailTextBox).isEnabled()) {
			System.out.println("emailTextBox is enabled");
		} else {
			System.out.println("emailTextBox is disenabled");
		}

	}

	// @Test
	public void TC_03_isSelected() {
		// go to the website
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// verify selected cua age
		Assert.assertFalse(driver.findElement(ageUnder18).isSelected());

		// tick vao o checkbox
		driver.findElement(ageUnder18).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(ageUnder18).isSelected());

	}

	@Test
	public void TC_04_MainChimp() {
		// go to the website
		driver.get("https://login.mailchimp.com/signup/");

		// Sendkey
		driver.findElement(By.cssSelector("#email")).sendKeys("remember@qa.team");

		By PasswordTextBox = By.id("new_password");

		// Verify lowerkeys & must not contain user name
		driver.findElement(PasswordTextBox).sendKeys("abc");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

		// clear du lieu cu da nhap tai password
		driver.findElement(PasswordTextBox).clear();

		// verify uppercase

		driver.findElement(PasswordTextBox).sendKeys("ABC");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

		// clear du lieu cu da nhap tai password
		driver.findElement(PasswordTextBox).clear();

		// verify number
		driver.findElement(PasswordTextBox).sendKeys("123");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

		// clear du lieu cu da nhap tai password
		driver.findElement(PasswordTextBox).clear();

		// verify special-char
		driver.findElement(PasswordTextBox).sendKeys("!@#");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

		// clear du lieu cu da nhap tai password
		driver.findElement(PasswordTextBox).clear();

		// verify happy case
		driver.findElement(PasswordTextBox).sendKeys("Abc123!@#");
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

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
		driver.quit();
	}
}