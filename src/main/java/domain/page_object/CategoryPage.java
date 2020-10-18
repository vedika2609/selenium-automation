package domain.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static core.driver.BrowserInit.getDriver;
import static java.lang.String.format;
import static java.util.Objects.nonNull;

public class CategoryPage {

    private By leftCategoriesBlock = By.cssSelector("[id='categories_block_left'] ul[style*='block']>li");
    private By productName = By.cssSelector(".ajax_block_product .product-name");

    private WebDriverWait webDriverWait;

    public CategoryPage() {
        webDriverWait = new WebDriverWait(getDriver(), 10, 30);
    }

    @Step
    public void openProductByName(String name) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productName)).stream()
                .filter(element -> element.getText().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(format("No element with locator [%s] containing text [%s] found", productName, name)))
                .click();
    }

    @Step
    public void selectSubSubCategory(String subSubCategory) {
        selectSubCategory(subSubCategory);
    }

    @Step
    public void selectSubCategory(String subCategory) {
        if (nonNull(subCategory)) {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(leftCategoriesBlock)).stream()
                    .filter(element -> element.getText().toLowerCase().contains(subCategory.toLowerCase()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException(format("No element with locator [%s] containing text [%s] found", leftCategoriesBlock, subCategory)))
                    .click();
        }
    }
}
