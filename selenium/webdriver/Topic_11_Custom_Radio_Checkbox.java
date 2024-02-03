package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
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

		// Luôn luôn khỏi tạo jsExcutor sau biến Driver này

		jsExcutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// cách verify element nào của check box hoặc radio có thể hiển thị trên chrome
	// //
	// là element nào khi hover chuột vào có hình RĂNG CƯA MÀU TÍM//

	// @Test
	public void TC_01_() {
		// Case 1//
		// Thẻ Input bị che nên không thao tác được
		// thẻ input lại dùng để verify được = >> vì hàm isselectect chỉ dùng với thẻ
		// input//

		driver.get("");

	}

	// @Test
	public void TC_02_() {
		// Case 2//
		// Thẻ khác với Input để clcik ( span/div/label/..)
		// thẻ này lại k dùng để verify được = >> vì hàm isselectect chỉ dùng với thẻ
		// input//

		driver.get("");
	}

	// @Test
	public void TC_03_() {

		// Case 3//
		// Thẻ khác với Input để click ( span/div/label/..)
		// thẻ này lại k dùng để verify được = >> vì hàm isselectect chỉ dùng với thẻ
		// input//
		// dùng thẻ label.span.div để thao tác và dùng thẻ input để verify

		// Nhược điểm của case này//
		// Chỉ sd cho trường hợp viết basic/demo //
		// nếu apply vào 2 framwork/ dự án thực tế thì không nên
		// vì 1 element phải define tới nhiều locator, dễ bị hiểu nhầm hoặc mất thòi
		// gian maintain

	}

	// @Test
	public void TC_04_() {

		// Case 4//
		// Thẻ input bị ẩn nhưng vẫn dùng để click
		// Hàm click () của webelement nó sẽ không thao tác vòa element bị ẩn được
		// dùng 1 hàm click() của javascript để click ( click vào element bị ẩn được)
		// Thẻ input lại dùng để verify được

		// selenium cung cấp 1 thư viện để nhúng đoạn code JavaScript =>>
		// JavaScriptExcutor

		driver.get("");
		sleepInSecond(3);

		By radioButton = By.xpath("//div[text()/input");

		// Tha tác chọn với JavaScriptExcutor
		jsExcutor.executeScript("Arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);

		// verify đã chọn thành công
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
	}

	@Test
	public void TC_05_() {

		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);

		By CanThoRadioButton = By.xpath("//div[@aria-label='Cần Thơ']");

		// verify là "cần thơ" radio button chưa được chọn' bằng hàm isDisplayed

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());

		// Hàm arguments này rất hay viết sai, cần lưu ý khi sử dụng
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(CanThoRadioButton));
		sleepInSecond(3);

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

		// cách 2 dùng hàm getattribute

		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).getAttribute("aria-checked"),
				"true");

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