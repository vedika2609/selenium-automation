package tests;

import core.utils.ExcelUtils;
import domain.page_object.AuthenticationPage;
import domain.page_object.CategoryPage;
import domain.page_object.CheckoutPage;
import domain.page_object.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdataobjects.CheckoutTestDataObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    protected void setUp() {
        super.setUp();
        authenticationPage = new AuthenticationPage();
        categoryPage = new CategoryPage();
        checkoutPage = new CheckoutPage();
        productPage = new ProductPage();
    }

    @DataProvider(name = "checkoutTestData")
    protected Iterator<Object[]> testData() {
        List<Object[]> checkoutData = new ArrayList<>();
        new ExcelUtils().getSheetDataBySheetName(TEST_DATA_PATH, "Checkout").forEach(x -> checkoutData.add(new Object[]{new CheckoutTestDataObject(x)}));
        return checkoutData.iterator();
    }

    @Test(dataProvider = "checkoutTestData")
    @Description("Verify whether the user is able to checkout")
    @Story("HelloFresh_1236:User Checkout Flow")
    protected void checkoutTest(CheckoutTestDataObject dataObject) {
        homePage.openAndValidate();
        homePage.goToAuthenticationPage();
        authenticationPage.loginAs(dataObject.getEmail(), dataObject.getPassword());
        myAccountPage.selectProductCategory(dataObject.getProductCategory());
        categoryPage.selectSubCategory(dataObject.getSubCategory());
        categoryPage.selectSubSubCategory(dataObject.getSubSubCategory());
        categoryPage.openProductByName(dataObject.getProductName());
        productPage.selectColour(dataObject.getColour());
        productPage.selectSize(dataObject.getSize());
        productPage.fillQuantity(1 + new Random().nextInt(3));
        productPage.addToCart();
        productPage.goToCheckout();
        checkoutPage.checkOrderSummaryAndProceed();
        checkoutPage.checkAddressesAndProceed();
        checkoutPage.agreeWithShippingTerms();
        checkoutPage.checkShippingAndProceed();
        checkoutPage.choosePaymentMethod("cheque");
        checkoutPage.confirmOrder();
        checkoutPage.assertHeader();
        checkoutPage.assertClassAttributeFortLastStepLabel();
        checkoutPage.assertFourthElementIsClickable();
        checkoutPage.assertPaymentConfirmationMessage();
        checkoutPage.assertOrderConfirmationUrl();
    }
}
