package QAGuru.MavenDemo;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

public class TestClass {

	/*
	 * 
	 * Verify user can submit form successfully.
	 * 
	 * TestNG
	 * 
	 */

	public static void main(String[] args) {

		String firstName = "John";
		
		String lastName = "Doe";
		String email = "johndoe@mail.com";
		String mobile = "1212111212";
		String address = "4900 BELT LINE ROAD, SUITE 150, ADDISON, TX";

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://demoqa.com/automation-practice-form/");

		WebElement textFirstName = driver.findElement(By.id("firstName"));
		WebElement textLastName = driver.findElement(By.id("lastName"));
		WebElement textEmail = driver.findElement(By.id("userEmail"));

		textFirstName.sendKeys(firstName);
		System.out.println("Enter first name : " + firstName);
		textLastName.sendKeys(lastName);
		System.out.println("Enter last name : " + lastName);
		textEmail.sendKeys(email);

		CarGurus cg = new CarGurus();
		cg.pause(3);

		// findElements

//		List<String> list = new ArrayList<E>();
//		["Male", "Female", "Other"]

		List<WebElement> gender = driver.findElements(By.xpath("//label[contains(@for,'gender-radio')]"));

		// List has index - make index random
		System.out.println(gender);
		DemoQA qa = new DemoQA();
		int index = qa.randomNumber(gender);

		// method (random index)

		gender.get(index).click();
		cg.pause(3);

		List<WebElement> hobbies = driver.findElements(By.xpath("//label[contains(@for,'hobbies-checkbox')]"));
		int ind1 = qa.randomNumber(hobbies); // 2
		int ind2 = qa.randomNumber(hobbies); // 2

		hobbies.get(ind1).click();
		hobbies.get(ind2).click();
		cg.pause(2);

		WebElement textMobileNumber = driver.findElement(By.id("userNumber"));
		textMobileNumber.sendKeys(mobile);

		WebElement textSubject = driver.findElement(By.id("subjectsInput"));
		textSubject.sendKeys("Comp");
		cg.pause(3);

		// System.out.println(driver.getPageSource());

		List<WebElement> subjects = driver.findElements(By.xpath("//div[contains(@id,'react-select-2-option')]"));
		subjects.get(0).click();
		cg.pause(3);

		WebElement textAddress = driver.findElement(By.id("currentAddress"));
		textAddress.sendKeys(address);
		cg.pause(3);

		// List<WebElement> listOfFiles =
		// driver.findElements(By.xpath("//select[@id='files']//option"));

		// Driver as JSE - Class Cast

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,1000)");

		WebElement cityInput = driver.findElement(By.id("react-select-4-input"));
		System.out.println(cityInput.getAttribute("disabled"));

		if (cityInput.getAttribute("disabled").equalsIgnoreCase("true")) {
			System.out.println("Dropdown is disabled");
		} else {
			System.err.println("Dropdown is not disabled");
			System.out.println("*******************");
		}

		WebElement selectStateDropdown = driver.findElement(By.xpath("//div[text()='Select State']"));
		selectStateDropdown.click();
		cg.pause(3);

		List<WebElement> states = driver.findElements(By.xpath("//div[contains(@id,'react-select-3-option')]"));

		index = qa.randomNumber(states);
		states.get(index).click();
		cg.pause(3);

		try {
			cityInput.getAttribute("disabled");
		} catch (NullPointerException e) {
			System.out.println("Dropdown is not disabled");
		}

		WebElement selectCityDropdown = driver.findElement(By.xpath("//div[text()='Select City']"));
		selectCityDropdown.click();
		cg.pause(3);

		List<WebElement> cities = driver.findElements(By.xpath("//div[contains(@id,'react-select-4-option')]"));

		index = qa.randomNumber(cities);
		cities.get(index).click();
		cg.pause(3);

		// executor.executeScript("window.scrollBy(0,300)");

		WebElement uploadPicture = driver.findElement(By.id("uploadPicture"));
		uploadPicture.sendKeys("C:\\Users\\admin\\Desktop\\Capture.PNG");
		cg.pause(3);

		WebElement buttonSubmit = driver.findElement(By.id("submit"));
		buttonSubmit.click();

		cg.pause(5);

		WebElement textSuccess = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

		try {
			textSuccess.isDisplayed();
			System.out.println(textSuccess.getText());
		} catch (Exception e) {
			System.err.println("Form submission failed.");
		}

		// Page Source

		driver.quit();

	}

	public int randomNumber(List<WebElement> list) {

		Random r = new Random();
		int start = 0;
		int end = list.size();

		return r.nextInt(end - start) + start;
		

	}

	// DataProvider
	@DataProvider
	public Object[][] loginData() {
		return new Object[][] { { "", "", "An email address required." },
				{ "test", "test", "An email address required." }, { "test2", "test2", "An email address required." },
				{ "", "", "An email address required." }, { "tests", "testst", "An email address required." } };

	}

}
