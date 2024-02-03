package webdriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Actions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions actions;
		
	JavascriptExecutor javascriptExecutor;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		actions = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		
	} 

	//@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
		
		actions.moveToElement(ageTextbox).perform();
		sleepInSecond(3);
		
		WebElement ageTooltip = driver.findElement(By.cssSelector("div.ui-tooltip"));
		
		Assert.assertEquals(ageTooltip.getText(), "We ask for your age only for statistical purposes.");
	}

	//@Test
	public void TC_02_Hover_To_Element() {
		driver.get("https://www.myntra.com/");
		
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
		sleepInSecond(2);
		
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).click().perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).getText(), "Kids Home Bath");
		
	

	}

	//@Test
	public void TC_03_ClickAndHold() { // chon du tu 1 den 15
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//  Tổng số chưa chọn
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
		Assert.assertEquals(allNumbers.size(), 20);
		
		//Chọn 1 - 12 theo đủ hàng / cột
		
		actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).release().perform();
		
		// Chọn từ 13 -15
		
		actions.keyDown(Keys.CONTROL).perform(); // nhan phim Ctrl xuong 
		
		actions.click(allNumbers.get(12))
				.click(allNumbers.get(13))
				.click(allNumbers.get(14))
				.keyUp(Keys.CONTROL).perform();
		
		sleepInSecond(2);
	}
	
	//@Test
	public void TC_04_ClickAndHold() { // chon theo Block - ngang / doc
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//  Tổng số chưa chọn
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
		Assert.assertEquals(allNumbers.size(), 20);
		
		//Chọn theo block - ngang / doc
		// chon 1 => 15
		
		actions.clickAndHold(allNumbers.get(0))
				.pause(2000)
				.moveToElement(allNumbers.get(14))
				.pause(2000)
				.release().perform();
		sleepInSecond(3);
		
	
		String [] allNumberTextExpectedArray = {"1","2","3","5","6","7","9","10","11","13","14","15"};
		
		//Convert tu Array qua ArrayList (List)
		List<String> allNumberTextExpected = Arrays.asList(allNumberTextExpectedArray);
		
		// verify so luong element da duoc selected
		List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(allNumbersSelected.size(), 12);
		
		// Get text cua element da duoc selected	
		List<String> allNumberTextActual = new ArrayList();
		
		for (WebElement element : allNumbersSelected) {
			allNumberTextActual.add(element.getText());
		}
			
		Assert.assertEquals(allNumberTextActual, allNumberTextActual);		
		sleepInSecond(2);
	}
	
	//@Test
	public void TC_05_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleclickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		// Can scroll toi elemnet roi moi double click 
		if (driver.toString().contains("firefox")) {
			// scrollIntoView(true) ; keo mep tren cua element len phia tren cung cua viewpoint
			// scrollIntoView(false): keo mep duoi cua elemnt xuong phia duoi cung cua view point
			
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleclickButton);
			sleepInSecond(1);
		} else {
			actions.scrollToElement(doubleclickButton).perform();
			sleepInSecond(2);
		}
		
		actions.doubleClick(doubleclickButton).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}
	
	//@Test
	public void TC_06_RightClickc() {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		
		//Kiem tra quit menu hien thi
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		
		driver.switchTo().alert().accept();
		sleepInSecond(2);
			
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		
	}
	
	@Test
	public void TC_07_DragAndDropHTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement biggerCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		actions.dragAndDrop(smallCircle, biggerCircle)
				.pause(3000).perform();
		sleepInSecond(2);
		
		
		Assert.assertEquals(biggerCircle.getText(), "You did great!");
		
		Assert.assertEquals(Color.fromString(biggerCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
		
		
		
		
		
	}
	
	@Test
	public void TC_08_DragAndDropHTML5() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
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