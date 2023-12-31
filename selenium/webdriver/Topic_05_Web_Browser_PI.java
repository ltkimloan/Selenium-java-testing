package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PI {
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
	public void TC_01_() {
		
		//Đóng tab đang thao tác
		driver.close();
		
		//Không quan tâm có bao nhiêu tab, sẽ đóng tất cả
		
		driver.quit();
		
		// Có thể lưu/ gán dữ liệu vào 1 biến để thực hiện cho các step sau ( dùng lại nhiều lần trên cùng 1 element)
		WebElement emailTextBox = driver.findElement(By.xpath("[//input@id='email']")); // khúc này nhớ lưu lại sau khi import
		emailTextBox.sendKeys("abc@gmail.com");
		emailTextBox.clear();
		
		// Có thể sử dụng luôn, không cần khai biến
		driver.findElement(By.xpath("[//input@id='email']")).sendKeys("abca@gmail.com");
		driver.findElement(By.xpath("[//input@id='email']")).clear();
		
		
		// Tìm nhiều elements
		List<WebElement> checkBoxes = driver.findElements(By.xpath("[//input@id='email']"));
		
		// mở url
		driver.get("https://demo.nopcommerce.com/");
		
		
		// Trả về url của trang hiện tại
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");
		
		// Trả về page source code của page hiện tại
		driver.getPageSource();
		
		// verify tương đối
		
		Assert.assertTrue(driver.getPageSource().contains("Quà tặng cuộc Sống"));
		
		
		//trả về title hiện tại của page
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(),"facebook");
		
		// lấy id của tab đang đứng
		
		String loginWindowID = driver.getWindowHandle();
		
		// hàng loạt id của các tab hiện có
		Set<String> allIDs = driver.getWindowHandles();
		
		//login thành công => lưu cookies lại
		
		
		Options opt = driver.manage();
		
		opt.getCookies();
		
		opt.logs();
		
		opt.timeouts();
		
		Timeouts time = opt.timeouts();
		
		// Khoảng thời gian  chờ element xuất hiện
		
		time.implicitlyWait(5, TimeUnit.SECONDS);
		
		// Khoảng thời gian chờ page load xong
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		// Khoảng thời gian chờ script load xong
		
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		
		win.maximize();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		
		
		driver.switchTo();
		TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame(0);
		tar.window(loginWindowID);
		
		
		
		
		
		
		
		
		
		
	
		
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