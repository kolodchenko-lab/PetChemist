package AutomationTests.scenarios;

import AutomationTests.ScreenshotOnFailure.BaseSetUp;
import AutomationTests.ScreenshotOnFailure.screenshotOnFailure;
import AutomationTests.pages.LoginPage;
import AutomationTests.pages.StepsAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;


@Feature("Login functionality")
public class LoginTest {

    static LoginPage loginPage;
    static StepsAssertions stepsAssertions;

    @BeforeAll
    static void init(){
        BaseSetUp baseSetUp = new BaseSetUp();
        loginPage = PageFactory.initElements(BaseSetUp.driver,LoginPage.class);
        stepsAssertions = PageFactory.initElements(BaseSetUp.driver,StepsAssertions.class);

    }
    @RegisterExtension

    screenshotOnFailure watch = new screenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");
    @Story("Authorization successful")
    @Description("Check authorization  is successful after entering valid credentials  ")
    @Test
    public void checkSuccessfulAuthorization(){
        //GIVEN
        String existingUserEmail = "test@test.com";
        String existingUserPassword = "test";
        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail(existingUserEmail);
        loginPage.setPassword(existingUserPassword);
        loginPage.submit();
        //THEN
        stepsAssertions.checkUserIsRedirectedToProducts();


    }

    @Test
    @Story("Check help email label 'We'll never share your email with anyone else'")
    public void labelEmailHelp(){
        //GIVEN
        //WHEN
        loginPage.openLoginPage();
        //THEN
        loginPage.findLabelEmailHelp();

    }

    @Test
    @Story("Check validation massage with screenshot")
    @Description
    public void checkValidationMassageWithScreenshot(){
        //GIVEN
        String notValidationEmail = "testTest";
        //WHEN
        loginPage.openLoginPage();
        loginPage.notCorrectEmailAddress(notValidationEmail);
        loginPage.submit();
        //THEN
        loginPage.checkValidationMassage();


    }

    @Test
    @Story("Check label Email address")
    public void labelEmailAddress(){
        //GIVEN
        //WHEN
        loginPage.openLoginPage();
        loginPage.findLabelEmailAddress();
    }

    @Test
    @Story("Check label password")

    public void labelPassword(){
        //GIVEN
        //WHEN
        loginPage.openLoginPage();
        loginPage.findLabelPassword();
    }
    @Test
    @Story("Relative locator test")
    public void byRelativeLocatorTest()  {
        //GIVEN
        String existingUserEmail = "test@test.com";
        String existingUserPassword = "Test";
        //WHEN
        loginPage.openLoginPage();
        loginPage.setEmail(existingUserEmail);
        loginPage.setPassword(existingUserPassword);

        loginPage.submit();

        //THEN
        stepsAssertions.checkUserIsRedirectedToProducts();

    }


    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        BaseSetUp.driver.quit();
    }

}
