package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Popup_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExcutor;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		jsExcutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Random_Not_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		
		By popup = By.cssSelector("div.lepopup-popup-container>div.lepopup-form:not([style^='display:none'])");
		sleepInSecond(10);
		
		if ( driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			
			System.out.println("Popup hien thi");
			driver.findElement(By.cssSelector("div.lepopup-popup-container>div.lepopup-form:not([style^='display:none']) div.lepopup-fadeIn a")).click();
			sleepInSecond(2);
						
		} else { // neu kh hien thi thi qua step tiep theo)
			System.out.println("Popup ko hien thi");
		}
		
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='post-title']/a[text()='Agile Testing Explained']")).isDisplayed());
	
	}

	@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(15);
		
		findelement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
	
	} 

	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(15);
		
		By dehieuPopup = By.cssSelector("div.popup-content");
		int screenHeight = driver.manage().window().getSize().getHeight();
		System.out.println("kich thuoc Height man hinh: " + screenHeight);
		
		if( driver.findElements(dehieuPopup).size() > 0 && driver.findElements(dehieuPopup).get(0).isDisplayed() ) {
			System.out.println("Popup hien thi");
			
			if ( screenHeight < 1920) {
				jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
			} else {
			driver.findElement(By.cssSelector("button#close-popup")).click();
			}
			sleepInSecond(3);
			
		} else {
			System.out.println("Popup ko hien thi");
		}
	}
	
	public WebElement findelement(By locator) {
		// neu popup  xuat hien thi se close di
		if ( driver.findElement(By.cssSelector("div.tve-leads-conversion-object")).isDisplayed()) {
			driver.findElement(By.cssSelector("tve_ea_thrive_leads_form_close")).click();
			System.out.println("Popup hien thi");
			sleepInSecond(3);
		} else {System.out.println("Popup ko hien thi");
		
		}
		return driver.findElement(locator);
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