package domain.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static core.driver.BrowserInit.getDriver;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyAccountPage {

    private By heading = By.cssSelector("h1");
    private By welcomeText = By.className("info-account");
    private By signOutButton = By.className("logout");
    private By menuCategories = By.cssSelector(".sf-menu>li");

    private WebDriverWait webDriverWait;

    public MyAccountPage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step
    public void selectProductCategory(String category) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuCategories)).stream()
                .filter(element -> element.getText().toLowerCase().contains(category.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(format("No element with locator [%s] containing text [%s] found", menuCategories, category)))
                .click();
    }

    public void assertMyAccountPage() {
        assertThat(webDriverWait.until(ExpectedConditions.elementToBeClickable(heading)).getText(), is("MY ACCOUNT"));
        assertThat(webDriverWait.until(ExpectedConditions.elementToBeClickable(welcomeText)).getText(), containsString("Welcome to your account."));
        assertThat(webDriverWait.until(ExpectedConditions.elementToBeClickable(signOutButton)).isDisplayed(), is(true));
    }
}
