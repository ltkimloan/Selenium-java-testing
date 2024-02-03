package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Popup_Part_I {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void TC_01_fixedPopup_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.xpath("//div[@id='modal-login-v1' and @style]//div[@class='modal-content']");
				
		//Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div#modal-login-v1.modal.fade.in input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("div#modal-login-v1.modal.fade.in input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("div#modal-login-v1.modal.fade.in button.btn-login-v1")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1.modal.fade.in div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button[class='close']")).click();
		sleepInSecond(2);
				
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

	}

	@Test
	public void TC_02_fixed_Popup_In_DOM() {
		driver.get("https://skills.kynaenglish.vn/");
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.k-popup-account-mb-content")).isDisplayed());
		System.out.print("Popup hien thi: " + driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.k-popup-account-mb-content")).isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail,com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("12345678");
		
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close ")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.k-popup-account-mb-content")).isDisplayed());
		

	}

	@Test
	public void TC_03_fixed_Popup_notIn_DOM() {
		driver.get("https://tiki.vn/");
		sleepInSecond(1);
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[@class='error-mess'][1]")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span[@class='error-mess'][1]")).getText(), "Mật khẩu không được để trống");
		
		driver.findElement(By.cssSelector("button.btn-close")).click();
		sleepInSecond(2);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
		
	}
	
	@Test
	public void TC_04_fixed_Popup_notIn_DOM() {
		driver.get("https://www.facebook.com/");
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.registration_redesign div#reg_form_box")).isDisplayed());
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		Assert.assertEquals(driver.findElements(By.cssSelector("div.registration_redesign div#reg_form_box")).size(), 0);
		
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
		//driver.quit();
	}
}