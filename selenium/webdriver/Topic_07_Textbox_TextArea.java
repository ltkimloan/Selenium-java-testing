package webdriver;

import java.awt.RenderingHints.Key;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	Random rand= new  Random();
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "49073-685-99-9710";
	String comment = "A strong password should be unique to each account to \nreduce vulnerability in the event of a hack.";
	
	

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
	public void TC_01_Create_New_Employee() {
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		
		// Mot trang luot trong bao lau
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[(text()='PIM')]")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[(text()='Add Employee')]")).click();
		sleepInSecond(2);
		
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("Fc");
		
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		driver.findElement(By.xpath("//label[contains(.,'Username')]/parent::div/following-sibling::div/input")).sendKeys("Afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		sleepInSecond(9);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Fc");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div/button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		sleepInSecond(9);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"),comment);
	
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("username")).sendKeys("Afc" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
				
		
	
	}

	@Test
	public void TC_02_Verify_Employee() {
		
	}

	@Test
	public void TC_03_Form() {
	
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