package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_part_I {
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

	}

	@Test
	public void TC_01_clickMyAccount() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

	}

	@Test
	public void TC_02_Success_msg() {

		// Them iphone vao gio hang
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		driver.findElement(By.xpath(
				"//a[@title='IPhone']//following-sibling::div[@class='product-info']//div[@class='actions']/button"))
				.click();

		// lấy succeed messag. nhưng chạy testcase này bị lỗi rồi

		System.out.println(
				"Success message is: " + driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText());
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}
}