package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Iframe_frame {
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

	//@Test
	public void TC_01_Form_Site() {
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
		
		driver.findElement(By.cssSelector("div.details__form-image>img")).click();
		sleepInSecond(5);
		
		WebElement iframeForm = driver.findElement(By.cssSelector("div.details__form-template>iframe"));
		
		Assert.assertTrue(iframeForm.isDisplayed());
		
		// Switch vao trong  frame/iframe truoc khi thao tac voi cac element  ben trong 
		driver.switchTo().frame(iframeForm);
		
		Select yearDropdown = new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2")));
		yearDropdown.selectByVisibleText("Sophomore");
		sleepInSecond(2);
		Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(), "Sophomore");
		
		Select residenceDropdown = new Select(driver.findElement(By.xpath("//label[contains(text(), 'Residence')]/following-sibling::select")));
		residenceDropdown.selectByVisibleText("North Dorm");
		sleepInSecond(2);
		Assert.assertEquals(residenceDropdown.getFirstSelectedOption().getText(), "North Dorm");
		
		WebElement genderRadio = driver.findElement(By.xpath("//span[contains(text(),'Gender')]/following-sibling::table//label[contains(.,'Female')]/preceding-sibling::input"));
		jsExcutor.executeScript("arguments[0].click();", genderRadio);
		
		sleepInSecond(3);
		Assert.assertTrue(genderRadio.isSelected());
		
		// switch lai ra A
		driver.switchTo().defaultContent();
		sleepInSecond(2);
		
		WebElement loginButton = driver.findElement(By.xpath("//nav[@class='header header--desktop']//a[@title='Log in']"));
		jsExcutor.executeScript("arguments[0].click();", loginButton);
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[@id='login']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
		
		
	}

	@Test
	public void TC_02_KynaEnglish() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "175K followers");
		
		driver.switchTo().defaultContent();
		sleepInSecond(2);
		
		// switch vao wechat
		driver.switchTo().frame(driver.findElement(By.cssSelector("div#cs-live-chat>iframe")));
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Dieu Nhi");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654322");
		
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		
		driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys("khong biet hoc nganh nao");
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.content>h4")).getText().toLowerCase().contains("excel"));
	}

	@Test
	public void TC_03_frame_HDFC_bank() {

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