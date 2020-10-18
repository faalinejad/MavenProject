package QAGuru.MavenDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CarGurus {

	public static void main(String[] args) {

		// DRY - Do not repeat yourself

		String zipCode = "75254";

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.cargurus.com/");

		// text() -> For selecting element based on text
		// contains() -> check of particular string value contains in the element

		// label[@data-tab-name='Financing']

		// and or
		// and - both statements are true
		// or - either of statement true

		WebElement buttonNewCar = driver.findElement(By.xpath("//span[text()='New Car']"));
		buttonNewCar.click();

		WebElement textNewCarZip = driver.findElement(By.xpath("//form[@id='newCarSearchForm']//input[@name='zip']"));
		textNewCarZip.sendKeys(zipCode);

		WebElement buttonNewCarSearch = driver
				.findElement(By.xpath("//input[contains(@id,'newCarSearchForm') and @type='submit']"));
		buttonNewCarSearch.click();

		// Alert Window/popup
		System.out.println("Alert Display - " + driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

		textNewCarZip.clear();

		CarGurus cg = new CarGurus();
		cg.pause(3);

		textNewCarZip.sendKeys("12345");
		cg.pause(5);

		driver.quit();
	}

	public void pause(int second) {
		try {
			Thread.sleep(second * 1000); // millisecond -> sec * 1000
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
