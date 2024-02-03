package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// 1- click vào 1 thẻ để cho nó xổ hết các items ra
		driver.findElement(By.id("number-button")).click();
		sleepInSecond(2);

		// 2 - Chờ cho tất cả items trong dropdown xổ ra hết
		// Xuất hiện đầy đủ trong html
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		// allItems đang chứ 19 items của dropdown

		// 3 - nếu item hiển thị thì click vào / chưa thì scroll xuống để tìm
		// 4 - tìm item chứ text mong muốn
		// Cần duyệt từ item trong các item của dropdown và xét điều kiện từng item
		// dùng vòng lặp để giải quyết [loop]

		for (WebElement item : allItems) {
			String textItem = item.getText();
			System.out.println("Text item = " + textItem);

			if (textItem.equals("10")) {
				item.click();
				break;
			}
		}

		driver.navigate().refresh();
		sleepInSecond(5);

	}

	// @Test
	public void TC_02_() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Faster");
		sleepInSecond(2);

		selectItemInDropdown("span#number-button", "ul#number-menu div", "9");
		sleepInSecond(2);

		// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Faster");

	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		sleepInSecond(2);
		selectItemInDropdown("i.dropdown.icon", "div.item span.text", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		sleepInSecond(2);

	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(2);

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		sleepInSecond(2);

	}

	@Test
	public void TC_05_Editable_dropDown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown("input.search", "div.item span.text", "Algeria");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");

	}

	// Những dữ liệu được truyền vào được xem là tham số

	public void selectItemInDropdown(String parentCss, String childItemsCss, String itemExpected) {
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(1);
		// rút gọn lại List<WebElement> allItems =
		// driver.findElements(By.cssSelector(childItemsCss));

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemsCss)));

		for (WebElement item : allItems) {
			String textItem = item.getText();
			System.out.println("Text item = " + textItem);

			if (textItem.equals(itemExpected)) {
				item.click();
				break;
			}
		}
	}

	public void selectItemInEditableDropdown(String parentCss, String childItemsCss, String itemExpected) {
		driver.findElement(By.cssSelector(parentCss)).clear();
		driver.findElement(By.cssSelector(parentCss)).sendKeys(itemExpected);
		sleepInSecond(1);
		// rút gọn lại List<WebElement> allItems =
		// driver.findElements(By.cssSelector(childItemsCss));

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemsCss)));

		for (WebElement item : allItems) {
			String textItem = item.getText();
			System.out.println("Text item = " + textItem);

			if (textItem.equals(itemExpected)) {
				item.click();
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