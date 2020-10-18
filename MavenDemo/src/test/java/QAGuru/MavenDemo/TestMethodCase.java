package QAGuru.MavenDemo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// User should able to Login and Logout successfully in application

public class TestMethodCase {

	@Parameters({ "email", "password" })
	@Test
	public void doLogin(String e, String p) {
		// Login

	}

	@Test
	public void logout() {
		// Logout
	}

	@Test
	public void addProduct() {
		// Add Product
	}

}
