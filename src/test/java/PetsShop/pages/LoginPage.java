package PetsShop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage (WebDriver driver){
        this.driver = driver;
    }
    By loginEmailAddress = By.xpath("//input[@id = 'login_email']");
    By loginPassword = By.xpath("//input[@id = 'login_pass']");
    By buttonSignIn = By.xpath("//input[@class= 'button button--primary']");

    @Step("Set valid email address as '{emailAddressText}' ")
    public LoginPage  setEmailAddress (String emailAddressText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.elementToBeClickable(loginEmailAddress)).sendKeys(emailAddressText);
        return this;
    }
    @Step("Set valid password as '{passwordText}' ")

        public LoginPage setPassword (String passwordText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.elementToBeClickable(loginPassword)).sendKeys(passwordText);
        return this;
    }



    @Step("Click button 'Sing In' to confirm valid user email address and password")
        public LoginPage clickButtonSingIn(){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
            wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn)).click();
            return this;
    }

}
