package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_29_PageLoading {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

	}

	@Test
	public void TC_01_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com");

		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");

		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

		driver.findElement(By.cssSelector("button.login-button")).click();

		// Cho loading page
		SleepInSecond(2);
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.xpath("//i[contains(@class,'fa-user')]/following-sibling::p")).click();

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//li[contains(@class,'menu-is-opening')]/ul[@style='display: block;']//p[contains(string(),'Customers')]"))).click();

		Assert.assertTrue(isPageLoadedSuccess());



	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	public void SleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }

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