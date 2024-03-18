package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;

public class Topic_28_FluentWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	private long pollingMillis =300;
	private long timeOutSecond = 30;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		fluentDriver = new FluentWait<WebDriver>(driver);

	}

	@Test
	public void TC_01_helloworld() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// Setting cho fluetWait
//		fluentDriver.withTimeout(Duration.ofSeconds(15))
//				.pollingEvery(Duration.ofMillis(100))
//				.ignoring(NoSuchElementException.class);

		//Conditions

//		Boolean isDisplayHello = fluentDriver.until(new Function<WebDriver, Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) { // driver nay la bien local co the Dinh dang duoc
//				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
//			}
//		});
//
// 		Verify HelloWorld hien thi
//		Assert.assertTrue(isDisplayHello);

//		String helloText = fluentDriver.until(new Function<WebDriver, String>(){
//			@Override
//			public String apply(WebDriver driver) {
//				String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
//				System.out.println("Get text: " + text);
//				return text;
//			}
//		});

		String helloText= waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();

		Assert.assertEquals(helloText,"Hello World!");
	}

	@Test
	public void TC_02_countDownTime() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countDown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

		fluentElement = new FluentWait<WebElement>(countDown);

		
		fluentElement.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		// Dieu kien mong doi
		Boolean verifyCountDown = fluentElement.until(new Function<WebElement,Boolean>() {

			@Override
			public Boolean apply(WebElement webElement) {
				return webElement.getText().endsWith("00");
			}
		});

		Assert.assertTrue(verifyCountDown);

	}

	@Test
	public void TC_03_() {

	}

	public WebElement waitAndFindElement(By locator) {
		FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(timeOutSecond))
				.pollingEvery(Duration.ofMillis(pollingMillis))
				.ignoring(NoSuchElementException.class);
		return  fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}


	public String getEmailAddress() {
		Random rand = new Random();
		return "mikejoin" + rand.nextInt(9999) + "@gmail.net";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}