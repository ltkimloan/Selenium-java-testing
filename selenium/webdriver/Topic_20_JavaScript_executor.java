package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_20_JavaScript_executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
	@Test
	public void TC_01_javaScript_Executor() {
	navigateToUrlByJS("http://live.techpanda.org/");
	String verifyDomain = (String) executeForBrowser("return document.domain;");
	Assert.assertEquals(verifyDomain,"live.techpanda.org");

	String verifyUrl = (String) executeForBrowser("return document.URL;");
	Assert.assertEquals(verifyUrl,"http://live.techpanda.org/");

	clickToElementByJS("//a[text()='Mobile']");
	sleepInSecond(2);

	clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
	sleepInSecond(2);

	Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

	clickToElementByJS("//a[text()='Customer Service']");
	sleepInSecond(2);

	String customerTitle = (String) executeForBrowser("return document.title;");
	Assert.assertEquals(customerTitle,"Customer Service");

	scrollToBottomPage();
	sendkeyToElementByJS("//input[@id='newsletter']",getEmailAddress());

	clickToElementByJS("//button[@title='Subscribe']");
	sleepInSecond(2);

	Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
	}

	@Test
	public void TC_02_html5_messages() {
	driver.get("https://sieuthimaymocthietbi.com/account/register");
	sleepInSecond(2);

	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

	Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");

	driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Auto");
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
	sleepInSecond(2);
	Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"),"Please fill out this field.");

	driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Testing");
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
	sleepInSecond(2);
	Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please fill out this field.");

	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("au@ee@ee");
	driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
	sleepInSecond(5);
	Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please enter an email address.");

	}

	@Test
	public void TC_03_Create_An_Account() {
	navigateToUrlByJS("http://live.techpanda.org/");
	clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
	clickToElementByJS("//a[@title='Create an Account']");
	String firstName = "Auto", lastName = "testing", password = "123456";

	sendkeyToElementByJS("//input[@id='firstname']",firstName);
	sendkeyToElementByJS("//input[@id='lastname']",lastName);
	sendkeyToElementByJS("//form[@id='form-validate']//input[@type='email']",getEmailAddress());
	sendkeyToElementByJS("//form[@id='form-validate']//input[@id='password']",password);
	sendkeyToElementByJS("//form[@id='form-validate']//input[@id='confirmation']",password);

	clickToElementByJS("//button[@title='Register']");
	sleepInSecond(5);

	Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));
	clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
	sleepInSecond(6);

	Assert.assertTrue(isImageLoaded("//div[@class='page-title']//img"));
	}

	public void sleepInSecond(long timeInSecond) {

		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getEmailAddress() {
		Random rand = new Random();
		return "mikejoin" + rand.nextInt(9999) + "@gmail.net";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}