package domain.page_object;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.driver.BrowserInit.getDriver;


@Slf4j
public class HomePage {

    private By signInButton = By.className("login");
    private WebDriverWait webDriverWait;

    public HomePage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step("Click Sign in button")
    public void goToAuthenticationPage() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void openAndValidate() {
        String url = "http://automationpractice.com/index.php";
        log.info("Loading {}", url);
        getDriver().get(url);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }
}
