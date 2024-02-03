package webdriver;

import static org.testng.Assert.assertFalse;

import java.time.Duration;
import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Shadow_DOM {
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

//	@Test
	public void TC_01_shadow_DOM() {
		driver.get("https://automationfc.github.io/shadow-dom/");
		sleepInSecond(2);
		
		// shadowContext dai dien cho shadow DOM 1 
		WebElement shadowHostElement =  driver.findElement(By.cssSelector("div#shadow_host"));
		SearchContext shadowContext = shadowHostElement.getShadowRoot();
		
		WebElement spanID = shadowContext.findElement(By.id("shadow_content"));
		
		String someText = shadowContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
		System.out.println(someText);
		Assert.assertEquals(someText, "some text");
		
		WebElement checkboxShadow = shadowContext.findElement(By.cssSelector("input[type='checkbox']"));
		Assert.assertFalse(checkboxShadow.isSelected());
		
		List<WebElement> allItemsInput = shadowContext.findElements(By.cssSelector("input"));
		System.out.println(allItemsInput.size());
		
		// nestedShadowContext dai dien cho nested trong shadow DOM 2	( shadow dom 2 nam trong shadow dom 1)
		WebElement nestedShadowElement = shadowContext.findElement(By.cssSelector("div#nested_shadow_host"));
		SearchContext nestedShadowContext = nestedShadowElement.getShadowRoot();
		
		String nestedText = nestedShadowContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
		System.out.println(nestedText);
		Assert.assertEquals(nestedText, "nested text");
	
	}

	@Test
	public void TC_02_Shadow_DOM_Popup() {
		driver.get("https://shopee.vn/");
		
		WebElement shadowHost = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
		SearchContext shadowContext = shadowHost.getShadowRoot();
		
		//verify popup hien thi
		if (shadowContext.findElements(By.cssSelector("div.home-popup__content")).size() > 0 
				&& shadowContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()); {
			// click close popup
					shadowContext.findElement(By.cssSelector("svg.home-popup__close-button")).click();
		sleepInSecond(2);	
		}
				
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("phone case");
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
		sleepInSecond(2);
		
		
		
		
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}