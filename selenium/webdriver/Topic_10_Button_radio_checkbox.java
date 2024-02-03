package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_radio_checkbox {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01_botton() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		By buttonLogin = By.cssSelector("button.fhs-btn-login");

		// verify login button is disabled
		Assert.assertFalse(driver.findElement(buttonLogin).isEnabled());
		String colorButtonLogin = driver.findElement(buttonLogin).getCssValue("background-image");
		System.out.println(colorButtonLogin);
		Assert.assertTrue(colorButtonLogin.contains("rgb(224, 224, 224)"));

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0822770789");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

		sleepInSecond(2);
		// verify login button is enabled
		Assert.assertTrue(driver.findElement(buttonLogin).isEnabled());

		String loginButtonBackground = driver.findElement(By.cssSelector("button.fhs-btn-login"))
				.getCssValue("background");
		Color loginButtonBackgrounfColor = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgrounfColor.asHex().toUpperCase(), "#C92127");

	}

	@Test
	public void TC_02_Default_checkbox_Radio_single() {

		// verify 1 checkbox/radio thi trong selenium dung ham isSelect()
		// Ham nay chi verify cho HTML the input
		driver.get("https://automationfc.github.io/multiple-fields/");

		// click chon 1 check box
		driver.findElement(By.xpath("//label[contains(text(), 'Anemia ')]/preceding-sibling::input")).click();

		// click chonj 1 radio
		driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).click();

		// verify cac checknbox/ radio da chon chua
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Anemia ')]/preceding-sibling::input"))
				.isSelected());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input"))
						.isSelected());

		// Checkbox co the tu bo chon duoc
		driver.findElement(By.xpath("//label[contains(text(), 'Anemia ')]/preceding-sibling::input")).click();

		// verify checkbox co the tu bo chon
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(), 'Anemia ')]/preceding-sibling::input"))
				.isSelected());

		// Radio khong the tu bo chon duoc
		driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).click();

		// Radio van duoc chon
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input"))
						.isSelected());

	}

	@Test
	public void TC_03_Default_checkbox_Multiple() {
		// check chon tat ca cac checkbox
		List<WebElement> listCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));

		// dong vong lap de duyet qua va click vao tat ca cac checkbox

		for (WebElement checkbox : listCheckboxes) {
			checkbox.click();
			sleepInSecond(2);

		}

		// verify tat ca cac checkbox da chon thanh cong

		for (WebElement checkbox : listCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());

		}

		// verify checkbox co ten la "x" thi moi click
		for (WebElement checkbox : listCheckboxes) {
			if (checkbox.getAttribute("value").equals("Anemia")) {
				checkbox.click();
			}
		}
	}

	@Test
	public void TC_04_Default_checkbox_radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		// chon check box
		if (!driver
				.findElement(
						By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"))
				.isSelected())
			;
		driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"))
				.click();
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"))
				.isSelected());

		// bo chon checkbox - cach lam nay se gon hon so voi cach tren
		unchecktoCheckbox(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		Assert.assertFalse(driver
				.findElement(
						By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"))
				.isSelected());
	}

	public void unchecktoCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
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
		// driver.quit();
	}
}