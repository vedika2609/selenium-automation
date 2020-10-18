package tests;

import core.utils.ExcelUtils;
import domain.page_object.AccountCreationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdataobjects.SignUpTestDataObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class SignUpTests extends BaseTest {

    @BeforeClass
    protected void setUp() {
        super.setUp();
        accountCreationPage = new AccountCreationPage();
    }

    @DataProvider(name = "signUpTestData")
    protected Iterator<Object[]> testData() {
        List<Object[]> signUpData = new ArrayList<>();
        new ExcelUtils().getSheetDataBySheetName(TEST_DATA_PATH, "SignUp").forEach(x -> signUpData.add(new Object[]{new SignUpTestDataObject(x)}));
        return signUpData.iterator();
    }

    @Test(dataProvider = "signUpTestData")
    @Description("Verify whether a user is able to sign up")
    @Story("HelloFresh_1234:User SignOut Flow")
    protected void signUpTest(SignUpTestDataObject data) {
        homePage.openAndValidate();
        homePage.goToAuthenticationPage();
        authenticationPage.enterEmailForAccountCreation(UUID.randomUUID().toString() + "@gmail.com");
        authenticationPage.submitEmailRegistration();
        accountCreationPage.fillPersonalInformation(data.getFirstName(), data.getLastName(), data.getPassword(), data.getDay(), data.getMonth(), data.getYear());
        accountCreationPage.fillAddressInformation();
        accountCreationPage.submitAccountCreation();
        myAccountPage.assertMyAccountPage();
    }
}
