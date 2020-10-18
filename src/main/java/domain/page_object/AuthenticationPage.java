package domain.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.driver.BrowserInit.getDriver;

public class AuthenticationPage {

    private By createAccountEmailField = By.id("email_create");
    private By createAccountButton = By.id("SubmitCreate");

    private By emailField = By.id("email");
    private By passwordField = By.id("passwd");
    private By signInButton = By.id("SubmitLogin");

    private WebDriverWait webDriverWait;

    public AuthenticationPage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step
    public void loginAs(String email, String password) {
        enterEmailForLogin(email);
        enterPassword(password);
        submitLoginForm();
    }

    @Step("Enter email to create an account")
    public void enterEmailForAccountCreation(String email) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createAccountEmailField)).sendKeys(email);
    }

    @Step("Click Create account button")
    public void submitEmailRegistration() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
    }

    @Step("Enter email to login")
    public void enterEmailForLogin(String email) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailField)).sendKeys(email);
    }

    @Step("Enter password to login")
    public void enterPassword(String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
    }

    @Step("Submit login form")
    public void submitLoginForm() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }
}
