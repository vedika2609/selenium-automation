package domain.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static core.driver.BrowserInit.getDriver;
import static java.lang.String.format;

public class ProductPage {

    private By colours = By.cssSelector("a[id*='color_']");
    private By sizeSelect = By.cssSelector("select[id='group_1']");
    private By quantityInput = By.id("quantity_wanted");
    private By addToCartButton = By.name("Submit");
    private By proceedToCheckout = By.cssSelector("[class='btn btn-default button button-medium']");

    private WebDriverWait webDriverWait;

    public ProductPage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step
    public void selectColour(String colour) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(colours)).stream()
                .filter(element -> element.getAttribute("name").equalsIgnoreCase(colour))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(format("No element with locator [%s] and attribute [%s = %s] found", colours, "name", colour)))
                .click();
    }

    @Step
    public void selectSize(String size) {
        new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated((sizeSelect)))).selectByVisibleText(size);
    }

    @Step
    public void fillQuantity(int quantity) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(quantityInput)).sendKeys(String.valueOf(quantity));
    }

    @Step
    public void addToCart() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    @Step
    public void goToCheckout() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(proceedToCheckout)).click();
    }
}
