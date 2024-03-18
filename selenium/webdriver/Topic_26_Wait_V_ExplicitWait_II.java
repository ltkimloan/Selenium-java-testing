package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_26_Wait_V_ExplicitWait_II {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System .getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String meoconName = "meocon.png", hoaquynhName = "hoaquynh.jpg", opencartName = "opencart.png";

	//	String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
	String character = File.separator;
	String meoconPath = projectPath + character + "uploadFiles"+ character + meoconName;

	String hoaquynhPath = projectPath + character +"uploadFiles" + character + hoaquynhName;
	String opencartPath = projectPath + character +"uploadFiles" + character + opencartName;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_VisibiliTy_Invisibility() {

		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='13']")).click();

		// Wait loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'RadCalendar1')]/div[@class='raDiv']")));
		// wait cho date duoc selected
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //td[@class='rcSelected']//a[text()='13']")));

		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Wednesday, March 13, 2024");

	}

	@Test
	public void TC_02_Upload_file() {
		driver.get("https://gofile.io/?t=uploadFiles");
		// Wait + verify spinner
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

		//Click uploadbutton
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

		// Wait + verify spinner
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements((By.cssSelector("div.spinner-border"))))));

		// Upload file bang sendkeys
		driver.findElement(By.cssSelector("div#filesUpload input")).sendKeys(meoconPath + "\n"+ hoaquynhPath + "\n"+ opencartPath);

		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements((By.cssSelector("div.spinner-border"))))));
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements((By.cssSelector("div.progress"))))));

		driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).click();
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements((By.cssSelector("div.spinner-border"))))));

		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='" + meoconName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='" + hoaquynhName +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='" + opencartName+  "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());


	}

	@Test
	public void TC_03_() {




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