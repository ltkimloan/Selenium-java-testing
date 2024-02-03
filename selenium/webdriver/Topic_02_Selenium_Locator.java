package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Mo trang register ra
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {

		// thao tac tren element nao thi dau tien phai xac dinh duoc vi tri cua element
		// do: findElement
		// Find theo cai gi (theo locator nao): id; class, name, css, xpath...
		// Find thay element roi thi action len element do: click/ sendKeys/...

		driver.findElement(By.id("FirstName")).sendKeys("kimloan");
	}

	@Test
	public void TC_02_Class() {

		// B1: mo trang web can thao tac

		driver.get("https://demo.nopcommerce.com/search");
		// b2: Find element- nhap text vao
		driver.findElement(By.className("search-text")).sendKeys("macbook");

	}

	@Test
	public void TC_03_Name() {

		driver.findElement(By.name("advs")).click();
	}

	@Test
	public void TC_04_TagName() {

		System.out.println(driver.findElement(By.tagName("input")).getSize());
	}

	@Test
	public void TC_05_LinkText() {

		driver.findElement(By.linkText("Addresses")).click();

	}

	@Test
	public void TC_06_partialLinkText() {
		driver.findElement(By.partialLinkText("vendor account")).click();
	}

	@Test
	public void TC_07_Css() {

		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");

		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("locator");

		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("ABC@gmail.com");

	}

	@Test
	public void TC_08_Xpath() {

		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("locator");

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}