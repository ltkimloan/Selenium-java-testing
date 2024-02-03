package webdriver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window_tab {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	//@Test
	public void TC_01_Basic_form() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String basicformID = driver.getWindowHandle();
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
		
		driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("selenium");
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
	
	}

	@Test
	public void TC_02_Basic_form_rut_gon() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String basicformID = driver.getWindowHandle();
		System.out.println(basicformID);
		
		// Switch để qua trang google 
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindowByID(basicformID);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("selenium");
		driver.findElement(By.cssSelector("span.QCzoEc")).click();
		sleepInSecond(2);
		
		String googleTabID = driver.getWindowHandle();
		switchToWindowByID(googleTabID);
				
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	
	}
		
	@Test
	public void TC_03_() {

	}
	
	public void switchToWindowByID(String currentID) {
		// lay ra het tat ca tab/window ID
		Set<String> allIDs = driver.getWindowHandles();
		
		//dung vong lap duyet qua tung ID trong Set ở trên
		
		for (String id : allIDs) {
			if(!id.equals(currentID)) {
				driver.switchTo().window(id);
				break;
			}
	
		}
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