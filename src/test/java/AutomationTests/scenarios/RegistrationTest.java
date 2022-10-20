package AutomationTests.scenarios;

import AutomationTests.pages.RegistrationPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Feature("Registration page functionality")
public class RegistrationTest{
        private static WebDriver driver;
        private static RegistrationPage registrationPage;



        public RegistrationTest() {
                System.setProperty("webdriver.chrome.driver", "C:/DRIVERS/chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                options.setHeadless(false);
                driver =new ChromeDriver(options);
                registrationPage = new RegistrationPage(driver);


        }
        @Story("Check registration new user")
        @Test
        public void registrationPageTest(){
        //GIVEN
                String testName = "orangeTest";
                String testLastName = "orangeTest";
                String testEmailAddress = "orange@test.com";
                String testPassword = "Test";
        //WHEN
        //THEN
        registrationPage.openRegistrationPage();
        registrationPage.setRegistrationName(testName);
        registrationPage.setRegistrationLastName(testLastName);
        registrationPage.setRegistrationEmailAddress(testEmailAddress);
        registrationPage.setRegistrationPassword(testPassword);
        registrationPage.checkRegistrationButton();

        }

        @Test
        @Story("Check labels in registration page")
        public void labels(){
                //GIVEN
                registrationPage.openRegistrationPage();
                //WHEN
                //THEN
                registrationPage.findRegistrationName();
                registrationPage.findRegistrationLastName();
                registrationPage.findRegistrationEmailAddress();
                registrationPage.findRegistrationPassword();
                registrationPage.findRegistrationHelpLabel();
        }


        @AfterAll
        @Step("Quit browser")
        static void tearDown(){
                driver.quit();
        }

    }
