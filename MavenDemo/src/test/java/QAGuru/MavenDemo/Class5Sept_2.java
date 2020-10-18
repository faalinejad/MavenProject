package QAGuru.MavenDemo;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Class5Sept_2 {

	// Selenium Waits

	// Implicit Wait - Start Driver Level and default defined for all elements
	// Explicit Wait - defined for each element, different conditions
	// Fluent Wait - polling freq element wait for element in interval at element
	// level

	// POM -> Page Object Model -> Testing Deisgn

	// pom.xml -> Maven Model

	/*
	 * 1, 2, 3 -> 2.05 0.95
	 * 
	 * 0.1
	 * 
	 * 0.5 1 1.5 2 2.1 0.90
	 * 
	 * 
	 * 
	 * 
	 */

	WebDriver driver;

	@BeforeMethod
	public void browserInit() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.get("https://stockx.com/reviews");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Wait until element display but max 20 sec
		// after 20 sec -> throw NoSuchElement
	}

	@Test
	public void OneTestMethod() {
		// Register
		System.out.println("OneTestMethod");
		// Assert.assertTrue(false);
	}
	
	//Headless Mode

//	@Test
//	public void TwoTestMethod() {
//		// Login
//		System.out.println("TwoTestMethod");
//	}
//
//	@Test
//	public void ThreeTestMethod() {
//		// Add Product
//		System.out.println("ThreeTestMethod");
//	}
//
//	@Test
//	public void FourTestMethod() {
//		System.out.println("FourTestMethod");
//	}

	// tesng XML

	@Test(groups = { "smoke", "regression" }, enabled = true)
	public void viewPost() {
		WebElement frame = driver.findElement(By.xpath("//iframe[@class='repuso_grid_iframe']"));
		driver.switchTo().frame(frame);

		WebElement viewPost = driver.findElement(By.xpath("//a[contains(text(),'view post')]"));
		viewPost.click();

		driver.switchTo().parentFrame();

		WebElement close = driver.findElement(By.xpath("//button[@class='mfp-close']"));

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(close));

		close.click();
	}

	@Test(groups = "regression", dependsOnGroups = "smoke", enabled = true)
	public void viewPost2() {
		WebElement frame = driver.findElement(By.xpath("//iframe[@class='repuso_grid_iframe']"));
		driver.switchTo().frame(frame);

		// Chain Methods

		// 1 -> 1000ms 0.5

		//

		// WebElement viewPost = driver.findElement(By.xpath("//a[contains(text(),'view
		// post')]"));

		// 2.05 - 2.5

		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement viewPost = fluentWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				return webDriver.findElement(By.xpath("//a[contains(text(),'view post')]"));
			}
		});

		viewPost.click();

		driver.switchTo().parentFrame();

		WebElement close = driver.findElement(By.xpath("//button[@class='mfp-close']"));

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(close));

		close.click();
	}

	@AfterMethod
	public void closeBrowser() {
		// driver.close(); // close current window , not quitting driver session
		driver.quit(); // close entire driver session
	}

}
