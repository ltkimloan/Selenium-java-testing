package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {}


	@Test
	public void TC_01_Url() {
	
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