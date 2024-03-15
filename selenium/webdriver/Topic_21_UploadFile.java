package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_21_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_SingleFile() {
		System.out.println(meoconPath);
		System.out.println(hoaquynhPath) ;
		System.out.println(opencartPath);
		By uploadFile = By.xpath("//input[@type='file']");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(uploadFile).sendKeys(meoconPath);
		sleepInSecond(2);

		driver.findElement(uploadFile).sendKeys(hoaquynhPath);
		sleepInSecond(2);

		driver.findElement(uploadFile).sendKeys(opencartPath);
		sleepInSecond(2);

		// verify file loaded thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + meoconName + "']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoaquynhName + "']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + opencartName + "']"))
				.isDisplayed());


		List<WebElement> startButton =driver.findElements(By.cssSelector("td>button.start"));
		//For each
		for (WebElement button : startButton) {
			button.click();
			sleepInSecond(2);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + meoconName+"']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hoaquynhName+"']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + opencartName+"']"))
				.isDisplayed());


	}

	@Test
	public void TC_02_Multiple_File() {
		By uploadFile = By.xpath("//input[@type='file']");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(uploadFile).sendKeys(meoconPath + "\n"+ hoaquynhPath + "\n"+ opencartPath);
		sleepInSecond(2);

		// verify file loaded thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + meoconName + "']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoaquynhName + "']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + opencartName + "']"))
				.isDisplayed());


		List<WebElement> startButton =driver.findElements(By.cssSelector("td>button.start"));
		//For each
		for (WebElement button : startButton) {
			button.click();
			sleepInSecond(2);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + meoconName+"']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hoaquynhName+"']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + opencartName+"']"))
				.isDisplayed());
	}

	@Test
	public void TC_03_() {

	}

	public void sleepInSecond(long timeInSecond) {

		try {
			Thread.sleep(timeInSecond * 1000);
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