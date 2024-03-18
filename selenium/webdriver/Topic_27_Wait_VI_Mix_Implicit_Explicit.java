package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_27_Wait_VI_Mix_Implicit_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Only_Implicit_Found(){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// khi vao se tim element ngay khi tim thay
		// khong can cho het timeout

		System.out.println("Start step: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("End step: " + getDateTimeNow());

	}

	@Test
	public void TC_02_Only_Implicit_Not_Found() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// khi vao khong tim thay element
		// polling  moi nua giay  tim lai 1 lan
		// khi het timeout se danh fail testcase va throw exeption: NoSuchElementExeption
		// Unable to locate element: input#NotFound

		System.out.println("Start step: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#NotFound"));

		System.out.println("End step: " + getDateTimeNow());

	}

	@Test
	public void TC_03_Only_Explicit_Found() {

		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		System.out.println("Start step: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("End step: " + getDateTimeNow());
	}

	@Test
	public void TC_04_Only_Explicit_NotFound() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		System.out.println("Start step: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Not-Found")));
		System.out.println("End step: " + getDateTimeNow());

	}

	@Test
	public void TC_05_Mix_Explicit_Implicit_Param_Located() {
		// Exception TimeoutException: Expected condition failed: waiting for visibility of element located by
		//Time out: 8s

		driver.get("https://www.facebook.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		explicitWait =new WebDriverWait(driver,Duration.ofSeconds(5));

		System.out.println("Start step: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#not-Found")));
		} catch (Exception e) {
			System.out.println("End step: " + getDateTimeNow());

			e.printStackTrace();
		}

	}
	@Test
	public void TC_06_Mix_Explicit_Implicit_Param_WebElement() {
		// Exception NoSuchElementException: Unable to locate element: input#not-Found
		// time out 8s
		driver.get("https://www.facebook.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		explicitWait =new WebDriverWait(driver,Duration.ofSeconds(5));

		System.out.println("Start step: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#not-Found"))));
		} catch (Exception e) {
			System.out.println("End step: " + getDateTimeNow());

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