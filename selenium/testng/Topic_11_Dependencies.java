package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
@Listeners(listeners.ExtentReport.class)
public class Topic_11_Dependencies {


	@BeforeClass
	public void beforeClass() {

	}

	@Test
	public void TC_01_CreateNewUser() {
		System.out.println("CreateNewUser");
		Assert.assertTrue(true);


	}

	@Test(dependsOnMethods = "TC_01_CreateNewUser")
	public void TC_02_ViewandSearchUser() {
		Assert.assertTrue(false);

	}

	@Test(dependsOnMethods = "TC_02_ViewandSearchUser")
	public void TC_03_MoveExistingUserToOtherRole() {
		Assert.assertTrue(true);

	}


	@AfterClass
	public void afterClass() {

	}
}