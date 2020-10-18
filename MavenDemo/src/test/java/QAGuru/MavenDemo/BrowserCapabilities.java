package QAGuru.MavenDemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserCapabilities {

	WebDriver driver;
	public static int step = 1;

	// Capabilities

	DesiredCapabilities capability = new DesiredCapabilities();

	@BeforeMethod
	public void browserInit() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		capability = DesiredCapabilities.chrome();
		capability.setJavascriptEnabled(true);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("incognito");
		options.addArguments("--headless");

		driver = new ChromeDriver(options);
		// driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	/*
	 * <br> - Break Statement <strong> Text </strong> <em> text </em> <h1> Heading
	 * </h1> <h1> Heading </h1>
	 * 
	 * <hr> - a Horizontal line
	 * 
	 * 
	 */

	@Test
	public void doLogin() {

		Reporter.log("<h3><strong><em>TC_001 - Verify User can login.</em></strong></h3><br><hr>");

		String email = "test1@mailinator.com";
		String password = "ben@123";

		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);

		log(step, "Entered credentials<br>");

		CarGurus cg = new CarGurus();
		cg.pause(2);

		driver.findElement(By.id("SubmitLogin")).click();
		log(step, "Sign in clicked<br>");

		try {
			WebElement signOut = driver.findElement(By.xpath("//div/a[contains(text(),'Sign out')]"));
			Assert.assertTrue(signOut.isDisplayed());
			log(step, "<p style='color:Green;'> TEST IS PASSED </p>");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File file = screenshot.getScreenshotAs(OutputType.FILE);
			String path = "C:\\Users\\admin\\Desktop\\QAGuru\\Class_04\\MavenDemo\\pass.png";
			File scrst = new File(path);
			try {
				FileUtils.copyFile(file, scrst);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			log(step, "<p style='color:Red;'> TEST IS FAILED </p>");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File file = screenshot.getScreenshotAs(OutputType.FILE);
			String path = "C:\\Users\\admin\\Desktop\\QAGuru\\Class_04\\MavenDemo\\fail.png";
			File scrst = new File(path);
			try {
				FileUtils.copyFile(file, scrst);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void log(int step, String s) {
		System.out.println(s);
		Reporter.log("Step : " + step + " - " + s);
		BrowserCapabilities.step++;
	}

}
