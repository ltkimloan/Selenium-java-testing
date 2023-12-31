package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PII {
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
	public void TC_01_VerifyUrl() {
		
		// Truy cập vào trang
		driver.get("http://live.techpanda.org/");
		
		// Click My Account  link tại footer
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// cần thêm 1 bước để chờ trang load xong =>> dùng sleeep cứng
		
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		// Click vào create account
				
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		
	
	}

	@Test
	public void TC_02_Title() {
		
		driver.get("http://live.techpanda.org/");
		
		// Click My Account  link tại footer
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// cần thêm 1 bước để chờ trang load xong =>> dùng sleeep cứng
		
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		
		// Click vào create account
				
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
	}

	@Test
	public void TC_03_Navigate() {
		// truy cap vao web 
		driver.get("http://live.techpanda.org/");
		
		// my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		// click vào create
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		
		//back lại
		driver.navigate().back();
		sleepInSecond(2);
		
		// verify trang back lại có đúng không? =>> login
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		// forward regis
		driver.navigate().forward();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	
	}
	
	@Test
	public void TC_04_SourceCode() {
		// truy cap website
		driver.get("http://live.techpanda.org/");
		
		// my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		// verify login or create
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// click create
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		
		// verify create an account
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	
	}
	
	public void sleepInSecond(long timeInSecond) {
		
		// sleep chay theo kiểu dữ liệu long, đơn vị sleep là milisecond
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// khi dùng sẽ dùng biến sleepInSecond(3); tương ứng cới 3s
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}