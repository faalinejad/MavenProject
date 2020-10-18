package QAGuru.MavenDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Class5Sept {

	// Array int[][] numbers = {{1,1},{1,2},{1,3}}

	WebDriver driver;

	@BeforeMethod
	public void browserInit() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// driver.get("https://stockx.com/reviews");
	}

	@AfterMethod
	public void closeBrowser() {
		// driver.close(); // close current window , not quitting driver session
		driver.quit(); // close entire driver session
	}

	/*
	 * 
	 * 1. Both blank 2. Wrong Email 3. Unregistered 4. Wrong 5. Valid
	 * 
	 */

	@Test(dataProvider = "loginData", dataProviderClass = TestClass.class)
	public void doLogin(String email, String password, String message) {
		System.out.println(email + "   " + password);
		System.out.println("ttttt");

		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);

		CarGurus cg = new CarGurus();
		cg.pause(2);

		driver.findElement(By.id("SubmitLogin")).click();

		// 5 == 5, 5 != 5
		// ! -> Not
		// true -> !true
		// false => !false

		if (!message.isEmpty()) {
			WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li"));
			Assert.assertEquals(errorMessage.getText(), message);
		} else {
			WebElement signOut = driver.findElement(By.xpath("//div/a[contains(text(),'Sign out')]"));
			Assert.assertTrue(signOut.isDisplayed());
		}

	}

}
