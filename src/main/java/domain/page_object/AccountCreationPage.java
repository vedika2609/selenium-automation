package domain.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

import static core.driver.BrowserInit.getDriver;

public class AccountCreationPage {

    private By mrsRadioButton = By.id("id_gender2");
    private By firstNameField = By.id("customer_firstname");
    private By lastNameField = By.id("customer_lastname");
    private By passwordField = By.id("passwd");
    private By dayOfBirthSelector = By.id("days");
    private By monthOfBirthSelector = By.id("months");
    private By yearOfBirthSelector = By.id("years");

    private By companyField = By.id("company");
    private By address1Field = By.id("address1");
    private By address2Field = By.id("address2");
    private By cityField = By.id("city");
    private By stateField = By.id("id_state");
    private By postCodeField = By.id("postcode");
    private By additionalInfoField = By.id("other");
    private By homePhoneField = By.id("phone");
    private By mobilePhoneField = By.id("phone_mobile");
    private By aliasField = By.id("alias");

    private By registerButton = By.id("submitAccount");

    private WebDriverWait webDriverWait;

    public AccountCreationPage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step("Fill personal information")
    public void fillPersonalInformation(String firstName, String lastName, String password, String day, String month, String year) {
        selectTitle();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPassword(password);
        selectDayOfBirth(day);
        selectMonthOfBirth(month);
        selectYearOfBirth(year);
    }

    @Step("Fill address information")
    public void fillAddressInformation() {
        enterCompanyName();
        enterAddress1();
        enterAddress2();
        enterCity();
        selectState();
        enterPostCode();
        enterAdditionalInformation();
        enterHomePhone();
        enterMobilePhone();
        enterAlias();
    }

    @Step("Submit account creation")
    public void submitAccountCreation() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Select Title")
    private void selectTitle() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mrsRadioButton)).click();
    }

    @Step("Enter first name")
    private void enterFirstName(String firstName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(firstName);
    }

    @Step("Enter last name")
    private void enterLastName(String lastName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(lastNameField)).sendKeys(lastName);
    }

    @Step("Enter password")
    private void enterPassword(String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
    }

    @Step("Select day of birth")
    private void selectDayOfBirth(String dayOfBirth) {
        new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dayOfBirthSelector))).selectByValue(dayOfBirth);
    }

    @Step("Select month of birth")
    private void selectMonthOfBirth(String monthOfBirth) {
        new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(monthOfBirthSelector))).selectByValue(monthOfBirth);
    }

    @Step("Select year of birth")
    private void selectYearOfBirth(String yearOfBirth) {
        new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(yearOfBirthSelector))).selectByValue(yearOfBirth);
    }

    @Step("Enter company name")
    private void enterCompanyName() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(companyField)).sendKeys(UUID.randomUUID().toString());
    }

    @Step("Enter address")
    private void enterAddress1() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(address1Field)).sendKeys("Address");
    }

    @Step("Enter address2")
    private void enterAddress2() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(address2Field)).sendKeys(UUID.randomUUID().toString());
    }

    @Step("Enter city")
    private void enterCity() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(cityField)).sendKeys(UUID.randomUUID().toString());
    }

    @Step("Enter state")
    private void selectState() {
        new Select(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(stateField))).selectByValue("2");
    }

    @Step("Enter postCode")
    private void enterPostCode() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(postCodeField)).sendKeys("11111");
    }

    @Step("Enter additionalInfo")
    private void enterAdditionalInformation() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(additionalInfoField)).sendKeys("Additional information");
    }

    @Step("Enter homePhone")
    private void enterHomePhone() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePhoneField)).sendKeys("777777777");
    }

    @Step("Enter mobilePhone")
    private void enterMobilePhone() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mobilePhoneField)).sendKeys("777777777");
    }

    @Step("Enter alias")
    private void enterAlias() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(aliasField)).sendKeys("alias");
    }
}
