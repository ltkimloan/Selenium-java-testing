package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_part_III {
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
	public void TC_01_Empty_Data() {

		// Mở Url lên

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Invalid_Email() {

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("loan");
		driver.findElement(By.id("txtEmail")).sendKeys("123@123@456");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@123@456");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("loan");
		driver.findElement(By.id("txtEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("loan@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("loan");
		driver.findElement(By.id("txtEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("abc");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Incorrect_Password() {

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("loan");
		driver.findElement(By.id("txtEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc12357");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Invalid_Phone_number() {

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action 1
		driver.findElement(By.id("txtFirstname")).sendKeys("loan");
		driver.findElement(By.id("txtEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("loan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("abc123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("abc12356");
		driver.findElement(By.id("txtPhone")).sendKeys("09876");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Action 2

		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0987123456781");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify 2

		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Action 3

		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("12345678910");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify 3
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}