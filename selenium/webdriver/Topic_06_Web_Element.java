package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
	public void TC_01_WebElement() {
		
		// tương tác với 1 element phải tương tác thông qua locator của nó
		
		// đó là Id, name, class, Xpath, Css, tagname, LinkText
		
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		WebElement emailText = driver.findElement(By.className("email"));
		
		emailText.sendKeys("remember@qa.team");
		emailText.clear();
		
		
		WebElement element = driver.findElement(By.className(""));
		
		// Dùng để xóa dữ liệu trước khi nhập vào text box
		
		element.clear();
		
		// Trả về tên của dữ liệu attribute của element đó
		
		String searchAttribute = element.getAttribute("placeholder");
		
		// verify dữ liệu đang nhập ở Text box
		
		String emailTextBox = element.getAttribute("value");
		
		
		// verify các thuộc tính thiết kế css của element như font size color position location [ ít dùng ]
				
		element.getCssValue("background-color");
		
		// vị trí của element so với trang web
		
		element.getLocation();
		
		// kích thước của element
		
		element.getRect();
		
		// location + size
		
		element.getRect();
		
		//Chụp màn hình =>> qua phần framework rồi học
		
		element.getScreenshotAs(OutputType.FILE);
		
		// lấy ra tên thẻ html của element đó để truyền cho 1 element khác
		
		driver.findElement(By.id("email")).getTagName();
		
		// lấy ra error message,  header message
		element.getText();
		
		//khi nào dùng getText và khi nào dùng attribute
		// nếu nằm bên ngoài thẻ span =>> dùng getText
		// Nếu nằm bên trong attribute =>> getAttribute
		
		
		// Dùng để verify xem 1 element hiển thị hay không?
		// phạm vi; tất cả các element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		// Dùng để verify xem 1 element có thao tác được hay không?
		// phạm vi; tất cả các element
		Assert.assertTrue(element.isEnabled());
		
		// dùng để verify xem 1 element có được chọn hay không? [ dùng cho check box và Ratio]
		// phạm vi; tất cả các element
	
		Assert.assertTrue(element.isSelected());
		
		// dùng để đăng ký form
		element.submit();
		
		
	
	}

	@Test
	public void TC_02_Logo() {
		
	}

	@Test
	public void TC_03_Form() {
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}