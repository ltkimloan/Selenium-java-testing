package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Topic_23_Wait_II_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_FindElement() {
		//*Case 1 - element được tìm thấy chỉ có 1 element

		System.out.println("Start step: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='email']"));
		System.out.println("End step: " + getDateTimeNow());

		//*Case 2 - element được tìm thấy có nhiều hơn 1 element
		System.out.println("Start step: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("auto@gmail.com");
		System.out.println("End step: " + getDateTimeNow());

		//*Case 3 - element không được tìm thấy
		System.out.println("Start step: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='not-found']"));
		System.out.println("End step: " + getDateTimeNow());
	}

	@Test
	public void TC_02_FindElements  () {
		//*Case 1 - element được tìm thấy chỉ có 1 element
		System.out.println("Start step: " + getDateTimeNow());
		List<WebElement> elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("List element have: " + elements.size());
		System.out.println("End step: " + getDateTimeNow());

		//*Case 2 - element được tìm thấy có nhiều hơn 1 element
		System.out.println("Start step: " + getDateTimeNow());
		elements = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
		System.out.println("List element have: " + elements.size());
		System.out.println("Start step: " + getDateTimeNow());

		//*Case 3 - element không được tìm thấy
		System.out.println("Start step: " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@name='reg_email__']"));
		System.out.println("List element have: " + elements.size());
		System.out.println("Start step: " + getDateTimeNow());
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

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}