package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String firstName = "lucy", lastName = "Pham", emailAddress = getEmailAddress();
	String companyName = "Automation FC", password = "12345678";
	String day = "15", month = "February", year = "1925";

	@BeforeClass 
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		sleepInSecond(1);

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		dayDropdown.selectByVisibleText(day);
		sleepInSecond(2);

		// verify dropdown này là single ( không phải multiple)
		Assert.assertFalse(dayDropdown.isMultiple());

		// verify số lượng item tròn dropdown này là 32 items
		List<WebElement> dayOptions = dayDropdown.getOptions();
		Assert.assertEquals(dayOptions.size(), 32);

		Select monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		monthDropdown.selectByVisibleText(month);
		sleepInSecond(2);

		// Nếu element chỉ dùng 1 lần chỉ cần dùng trực tiếp và không cần khai báo biến
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		sleepInSecond(2);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		sleepInSecond(2);

		driver.findElement(By.id("register-button")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

	}

	@Test
	public void TC_02_login() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-login")).click();
		sleepInSecond(2);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);

		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("a.ico-account")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("Value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("Value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("Value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("Value"), companyName);

		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

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
		return "Lucypham" + rand.nextInt(9999) + "@gmail.net";

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}