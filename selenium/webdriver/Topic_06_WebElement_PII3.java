package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_PII3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand;
	String emailAddress, firstName, lastName, password, fullName;

	
	
	@BeforeClass
	public void beforeClass() {
			
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		rand = new Random();
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		emailAddress = "Automationfc" + rand.nextInt(999) + "@gmail.com";
		firstName = "Automation";
		lastName = "Fc";
		fullName = firstName + " " + lastName;
		password = "Auto123";
		
		
	}

	//@Test
	public void Login_01_Emty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
				
		driver.findElement(By.xpath("//div[@class='footer']// a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		
		
	}
	
	//@Test
	public void Login_02_Password_less_than_6Chars() {
		driver.get("http://live.techpanda.org/");
			
		driver.findElement(By.xpath("//div[@class='footer']// a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
		
		
	}
	
	//@Test
	public void Login_03_invalid_email() {
		driver.get("http://live.techpanda.org/");
			
		driver.findElement(By.xpath("//div[@class='footer']// a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("abc123");
		
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
		
		
	}
	
	@Test
	public void Login_04_Create_New_Account() {
		driver.get("http://live.techpanda.org/");
			
		driver.findElement(By.xpath("//div[@class='footer']// a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(2);
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
		String ContactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(ContactInfor);
		
		Assert.assertTrue(ContactInfor.contains(fullName));
		Assert.assertTrue(ContactInfor.contains(emailAddress));
		
		// log out
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		
	
	}
	
	@Test
	public void Login_05_login_valid_infor() {
	
			
		driver.findElement(By.xpath("//div[@class='footer']// a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		
		driver.findElement(By.id("send2")).click();
		
		sleepInSecond(2);
		
		String ContactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(ContactInfor);
		
		Assert.assertTrue(ContactInfor.contains(fullName));
		Assert.assertTrue(ContactInfor.contains(emailAddress));
		
		
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