package PetShopTest;

import PetsShop.pages.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

import static PetShopTest.BaseSetup.driver;

@Feature("")
public class BaseTest {

    private static OrdersPage ordersPage;
    private static ProductPage productPage;
    private static YourCartPage yourCartPage;
    private static CheckOutPage checkOutPage;
    private static MainPage mainPage;
    private static LoginPage loginPage;

    @BeforeAll
    @Step("Set up driver before tests")
    static void init() {
        BaseSetup baseSetUp = new BaseSetup();
        ordersPage = PageFactory.initElements(driver, OrdersPage.class);
        productPage = PageFactory.initElements(driver, ProductPage.class);
        yourCartPage = PageFactory.initElements(driver, YourCartPage.class);
        checkOutPage = PageFactory.initElements(driver, CheckOutPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }
    @Test
    @Story("Make an order with log in user successful")
    public void logInUserOrder() throws InterruptedException {
        //GIVEN
        String existingProduct = "Antinol Rapid For Cats";
        String existingCreditCardNumber = "235667895643";
        String existingExpiration = "549";
        String existingNameOnCard = "Ivanov Ivan";
        String existingCvvCard = "5792";
        String existingEmailAddress= "kolod4enko777@gmail.com";
        String existingPassword = "123qwer";

        //WHEN
        mainPage.openPetChemistSite();
        mainPage.clickButtonSingIn();
        loginPage.setEmailAddress(existingEmailAddress);
        loginPage.setPassword(existingPassword);
        loginPage.clickButtonSingIn();
        ordersPage.setProductSearchField(existingProduct);
        ordersPage.openProductList();
        ordersPage.clickProduct();
        productPage.clickButtonSizeProduct();
        //productPage.clickButtonPackProduct();
        productPage.clickButtonAddToCart();
        productPage.clickButtonViewOrEditYourCart();
        yourCartPage.clickButtonCheckOut();
        yourCartPage.ignoreBanner();
        checkOutPage.clickRadioButtonShippingMethod();
        checkOutPage.clickButtonContinuum();
        Thread.sleep(5000L);
        checkOutPage.setCreditCardNumber(existingCreditCardNumber);
        checkOutPage.setExpiration(existingExpiration);
        checkOutPage.setNameOnCard(existingNameOnCard);
        checkOutPage.setCvvCard(existingCvvCard);
        checkOutPage.clickCheckBoxTermAndCondition();
        checkOutPage.clickButtonPlaceOrder();
    }
    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        driver.quit();
    }
}
