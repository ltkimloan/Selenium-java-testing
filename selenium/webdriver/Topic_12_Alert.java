package webdriver;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	WebDriverWait explicitWait;

	By resultText = By.cssSelector("p#result");

	@BeforeClass
	public void beforeClass() {

		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\\\browserDrivers\\\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent()); // không cần phải switch cứng

// 		Alert alert = driver.switchTo().alert();
//		void dismiss(); dùng để cancel alert dùng cho confirm alert
//
// 		void accept(); dùng để accept alert cùng cho 3 loại accept alert, confirm alert, prompt alert

//		String getText();

//		void sendKeys(String keysToSend);		

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// khi accept hoặc cancel thì alert sẽ biến mất luôn
		alert.accept();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

	}

	@Test
	public void TC_02_Confirm_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();

		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

	}

	 @Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		String text = "Automation";
		alert.sendKeys(text);
		sleepInSecond(2);

		alert.accept();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);

	}

	@Test
	public void TC_04_Authertication_ByPass_ToURL() {
		// Thư viện Alert không dùng cho Authertication được, vì tính bảo mật
		// chrom devtool protocol (CDP) - chrome / edge ( chromium)

		// Khi truy cập vào website sẽ phải nhầm username & password
		// nếu nhập rồi, cần phải xóa cache để test lại. ctrl + shift + Delete

		// Cách 1 truyền thẳng admin và password vào url
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations')]")).isDisplayed());

		// Cách truyền 1 biến vào trong 1 chuỗi
		String username = "admin";
		String password = "admin";

		driver.get("https://the-internet.herokuapp.com/");

		String webLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(webLinkUrl);

		String[] authenArray = webLinkUrl.split("//");

		System.out.println(authenArray[0]);
		System.out.println(authenArray[1]);

		driver.get(authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1]);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations')]")).isDisplayed());

		// dùng hàm để xử lý

		driver.get(getAuthenAlertByUrl("https://the-internet.herokuapp.com/", username, password));

		// Cách 2 dùng autoIT ( chri dùng được cho window, không sử dụng cho Mac và
		// LiNux)
		
		// Cách 3 sử dụng Selenium version 4.0 : chỉ dùng  cho Chrome/ Edge Chromimum vì support bởi ChromeDevTool

	}

	public String getAuthenAlertByUrl(String url, String username, String password) {
		String[] authenArray = url.split("//");
		return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];
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
		driver.quit();
	}
}