package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToApplication(String username, String password) {
        // Inherited BasePage methods automatically handle explicit waits
        typeText(usernameField, username);
        typeText(passwordField, password);
        clickElement(loginButton);
    }
}