package tests;

import core.utils.ExcelUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends BaseTest {

    @DataProvider(name = "loginTestData")
    protected Iterator<Object[]> testData() {
        List<Object[]> loginData = new ArrayList<>();
        new ExcelUtils().getSheetDataBySheetName(TEST_DATA_PATH, "Login").forEach(x -> loginData.add(new Object[]{x.get("Email"), x.get("Password")}));
        return loginData.iterator();
    }

    @Test(dataProvider = "loginTestData")
    @Description("Verify whether the user is able to login")
    @Story("HelloFresh_1235:User Login Flow")
    protected void logInTest(String email, String password) {
        homePage.openAndValidate();
        homePage.goToAuthenticationPage();
        authenticationPage.enterEmailForLogin(email);
        authenticationPage.enterPassword(password);
        authenticationPage.submitLoginForm();
        myAccountPage.assertMyAccountPage();
    }
}
