package AutomationTests.scenarios;

import AutomationTests.ScreenshotOnFailure.BaseSetUp;
import AutomationTests.ScreenshotOnFailure.screenshotOnFailure;
import AutomationTests.pages.ProductsPage;
import AutomationTests.pages.StepsAssertions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;

import static AutomationTests.ScreenshotOnFailure.BaseSetUp.driver;

@Feature("Product page functionality")
public class ProductTest {
    private static ProductsPage productsPage;
    private static StepsAssertions stepsAssertions;


        @BeforeAll
        static void init() {
            BaseSetUp baseSetUp = new BaseSetUp();
            productsPage = PageFactory.initElements(driver,ProductsPage.class);
            stepsAssertions = PageFactory.initElements(driver,StepsAssertions.class);
        }

    @RegisterExtension
    screenshotOnFailure watch = new screenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");
    @Test
    @Story("Add product successful")
       public void byAddProductTest()  {
        //GIVEN
        String existingProductName = "orange";
        String existingProductPrice = "2002";
        //WHEN
        //THEN
        stepsAssertions.StepsAssertionRegisteredUser();
        productsPage.checkAddButtonProduct();
        productsPage.setAddProductName(existingProductName);
        productsPage.setAddProductPrice(existingProductPrice);
        productsPage.checkSubmitButtonProduct();


    }
    @Test
    @Story("Check delete test product")
    @Description("Check deleting a product after it has been created in the table ")
    public void deleteTestProduct(){
        //GIVEN
        // WHEN
        stepsAssertions.StepsAssertionRegisteredUser();
        String existingProductName = "orange";
        String existingProductPrice = "2002";
        stepsAssertions.StepsAssertionRegisteredUser();
        productsPage.checkAddButtonProduct();
        productsPage.setAddProductName(existingProductName);
        productsPage.setAddProductPrice(existingProductPrice);
        productsPage.checkSubmitButtonProduct();

        //THEN
        productsPage.checkDeleteButtonProduct();
    }

    @Test
    @Story("Check LogOut button")
    @Description("Check transition to the login page through the logout button")
    public void CheckLogOutButton() {
        //GIVEN
         stepsAssertions.StepsAssertionRegisteredUser();
        //WHEN
        productsPage.checkLogOutButtonProduct();
        //THEN
        stepsAssertions.checkTheTransitionToTheLoginPage();

    }
    @Test
    @Story("Check update product")
    @Description("Check update product through the update button")
    public void updateButtonTest() throws InterruptedException {
        //GIVEN
        String productNameUpdate = "orangeTest";
        String productPriceUpdate = "202";
       stepsAssertions.StepsAssertionRegisteredUser();
       //WHEN
        productsPage.checkUpdateButtonProduct();
        productsPage.setProductNameUpdate(productNameUpdate);
        productsPage.setProductPriceUpdate(productPriceUpdate);
        //THEN
        productsPage.checkUpdateSubmitButton();
        //stepsAssertions.checkTheTransitionToTheAllProductsPage();


    }
    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        driver.quit();
    }
    }


