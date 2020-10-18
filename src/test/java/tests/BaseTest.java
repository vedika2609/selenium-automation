package tests;

import core.base_test.TestListener;
import core.driver.BrowserInit;
import domain.page_object.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest extends BrowserInit {

    final String TEST_DATA_PATH = "/src/test/resources/InputTestData/TestData.xlsx";
    AccountCreationPage accountCreationPage;
    AuthenticationPage authenticationPage;
    CategoryPage categoryPage;
    CheckoutPage checkoutPage;
    HomePage homePage;
    MyAccountPage myAccountPage;
    ProductPage productPage;

    @BeforeMethod()
    protected void setUp() {
        authenticationPage = new AuthenticationPage();
        homePage = new HomePage();
        myAccountPage = new MyAccountPage();
    }
}
