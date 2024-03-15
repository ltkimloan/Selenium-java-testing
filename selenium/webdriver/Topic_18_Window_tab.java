package webdriver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window_tab {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test
	public void TC_01_Basic_form() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String basicformID = driver.getWindowHandle();
		String basicTitle = driver.getTitle();
		System.out.println(basicformID);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		Set<String> allIDS = driver.getWindowHandles();
		
		// dung vong lap  duyet qua tung IDS
		for (String id : allIDS) {
			// neu nhu 1 ID  nao ma khac voi parentID thi switch vao
			if(!id.equals(basicformID)) {
				driver.switchTo().window(id);
				break;
			}
		}
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("selenium");
		driver.findElement(By.cssSelector("span.QCzoEc")).click();
		sleepInSecond(2);
		
		String googleTabID = driver.getWindowHandle();
		
		// lay ra ID cua tat ca  cac window / tab dang co
		allIDS = driver.getWindowHandles();
		// dung vong lap  duyet qua tung IDS
				for (String id : allIDS) {
					// neu nhu 1 ID  nao ma khac voi parentID thi switch vao
					if(!id.equals(googleTabID)) {
						driver.switchTo().window(id);
						break;
					}
				}
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");

		switchToWindowByTitle(basicTitle);
	}

//	@Test
//	public void TC_02_Basic_form_rut_gon() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//
//		String basicformID = driver.getWindowHandle();
//		System.out.println(basicformID);
//
//		// Switch để qua trang google
//		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
//		sleepInSecond(2);
//
//		switchToWindowByID(basicformID);
//		System.out.println(driver.getCurrentUrl());
//		System.out.println(driver.getTitle());
//
//		driver.findElement(By.xpath("//textarea[@type='Tìm kiếm']")).sendKeys("selenium");
//		driver.findElement(By.cssSelector("span.QCzoEc")).click();
//		sleepInSecond(2);
//
//		String googleTabID = driver.getWindowHandle();
//		switchToWindowByID(googleTabID);
//
//		System.out.println(driver.getCurrentUrl());
//		System.out.println(driver.getTitle());
//
//	}
	@Test
	public void TC_03_kynaEnglish() {
		driver.get("https://skills.kynaenglish.vn/");
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);
		String parentID = driver.getWindowHandle();

		switchToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		driver.findElement(By.xpath("//form[@id='login_popup_cta_form']//input[@name='email']"))
				.sendKeys("automation@gmail.com");
		sleepInSecond(2);

		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(2);

		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#channel-header div#text-container.ytd-channel-name"))
				.getText(),"Kyna.vn");
		sleepInSecond(2);

		closeSAllWindowsWithoutParent(parentID);
	}
	@Test
	public void TC_04_techpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(),
				"The product IPhone has been added to comparison list.");

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);

		String basicID = driver.getWindowHandle();
		switchToWindowByID(basicID);

		Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
		driver.close();
		driver.switchTo().window(basicID);

		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(2);

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(),
				"The comparison list was cleared.");
	}
		
	//@Test
//	public void TC_05_selenium_version4() {
//
//		driver.findElement("");
//		System.out.println("Driver id: " + driver.toString());
//
//		WebDriver facebookDriver = driver.switchTo().newWindow(WindowType.TAB);
//		facebookDriver.get("https://www.facebook.com/");
//		System.out.println("Driver ID facebook: " + facebookDriver.toString());

	//}

	public void switchToWindowByID(String currentID) {
		// lay ra het tat ca tab/window ID
		Set<String> allIDs = driver.getWindowHandles();

		
		for (String id : allIDs) {
			if(!id.equals(currentID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
			}
		}
	}
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			sleepInSecond(2);
			String actuallTitle = driver.getTitle();
			System.out.println(actuallTitle);
			if( actuallTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	public void closeSAllWindowsWithoutParent (String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String windowID : allIDs) {
			if ( !windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
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