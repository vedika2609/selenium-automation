package domain.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.driver.BrowserInit.getDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.matchesPattern;

public class CheckoutPage {

    private static final String ORDER_CONFIRMATION_URL_REGEX =
            "\\S*(\\?)controller=order-confirmation&id_cart=\\d*&id_module=\\d*&id_order=\\d*&key=[0-9a-z]*$";
    private By summaryProceed = By.cssSelector("[class='button btn btn-default standard-checkout button-medium']");
    private By addressesProceed = By.name("processAddress");
    private By termsOfServiceBox = By.id("uniform-cgv");
    private By shippingProceed = By.name("processCarrier");
    private By confirmOrder = By.cssSelector("[id='cart_navigation']>button");
    private By header = By.cssSelector("h1");
    private By lastStepLabel = By.id("step_end");
    private By doneFourthStepLabel = By.cssSelector("[class='step_done step_done_last four']");
    private By paymentConfirmationMessage = By.cssSelector(".cheque-indent>strong,.alert.alert-success");
    private WebDriverWait webDriverWait;

    public CheckoutPage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step
    public void checkOrderSummaryAndProceed() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(summaryProceed)).click();
    }

    @Step
    public void checkAddressesAndProceed() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addressesProceed)).click();
    }

    @Step
    public void agreeWithShippingTerms() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(termsOfServiceBox)).click();
    }

    @Step
    public void checkShippingAndProceed() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(shippingProceed)).click();
    }

    @Step
    public void choosePaymentMethod(String paymentMethod) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(paymentMethod))).click();
    }

    @Step
    public void confirmOrder() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(confirmOrder)).click();
    }

    public void assertHeader() {
        assertThat(webDriverWait.until(ExpectedConditions.elementToBeClickable(header)).getText(), containsStringIgnoringCase("ORDER CONFIRMATION"));
    }

    public void assertClassAttributeFortLastStepLabel() {
        assertThat(getAttribute(lastStepLabel, "class"), is(equalTo("step_current last")));
    }

    public void assertFourthElementIsClickable() {
        assertThat(isElementClickable(doneFourthStepLabel), is(true));
    }

    public void assertPaymentConfirmationMessage() {
        assertThat(webDriverWait.until(ExpectedConditions.elementToBeClickable(paymentConfirmationMessage)).getText(), containsStringIgnoringCase("Your order on My Store is complete."));
    }

    public void assertOrderConfirmationUrl() {
        assertThat(getDriver().getCurrentUrl(), matchesPattern(ORDER_CONFIRMATION_URL_REGEX));
    }

    private String getAttribute(By locator, String attribute) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).getAttribute(attribute);
    }

    private boolean isElementClickable(By by) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }
}
