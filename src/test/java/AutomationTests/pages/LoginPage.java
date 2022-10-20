package AutomationTests.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Collections;
import java.util.List;

public class LoginPage {

    private WebDriver driver;
    @FindBy(id ="exampleInputEmail1")
    private WebElement emailInput;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//label[text()='Email address']")
    private WebElement labelEmailAddress;
    By labelPassword = By.xpath("//label[text()='Password']");
    By emailHelp = By.xpath("//div[@id='emailHelp']");


    @Step("Check label 'We'll never share your email with anyone else.'")

    public void findLabelEmailHelp() {
        List<WebElement> elements = driver.findElements(emailHelp);
        boolean elementTru = elements.get(0).getText().contains("We'll never");
        Assertions.assertTrue(elementTru);
    }
    @Step("Check label 'Email address'")

    public void findLabelEmailAddress() {
            List<WebElement> elements = Collections.singletonList(labelEmailAddress);
            boolean elementTru = elements.get(0).getText().contains("Email address");
            Assertions.assertTrue(elementTru);
            //WebElement elementLabel = driver.findElement(labelEmailAddress);
            //org.assertj.core.api.Assertions.assertThat(elementLabel).isNotNull();
        }
    @Step("Check label 'Password'")

        public void findLabelPassword() {
            driver.findElement(labelPassword);
            List<WebElement> elements = driver.findElements(labelPassword);
            boolean elementTru = elements.get(0).getText().contains("Password");
            Assertions.assertTrue(elementTru);
            // WebElement elementLabel = driver.findElement(labelPassword);
            // org.assertj.core.api.Assertions.assertThat(elementLabel).isNotNull();
        }
    @Step("Set not correct email address as {emailText}")
        public void notCorrectEmailAddress(String emailText){
        emailInput.sendKeys(emailText);
        }



        @Step("Check validation massage 'Адрес электронной почты должен содержать символ \"@\".'")
        public void checkValidationMassage(){
            String validationMessage = emailInput.getAttribute("validationMessage");
            org.assertj.core.api.Assertions.assertThat(validationMessage).contains("Адрес электронной почты должен содержать символ \"@\".");
            System.out.println(validationMessage);


        }



    public LoginPage(WebDriver driver) {
            this.driver = driver;
        }
    @Step("Set email as {emailText}")
    public LoginPage setEmail (String emailText){
            emailInput.sendKeys(emailText);
            return this;
        }
        @Step("Set password as {passwordText}")
        public LoginPage setPassword (String passwordText){
            passwordInput.sendKeys(passwordText);
            return this;
        }
        @Step("Click submit button")
        public LoginPage submit () {
            submitButton.click();
            return this;
        }
        @Step("Open login page ")
        public LoginPage openLoginPage () {
            driver.get("http://online-sh.herokuapp.com/login");
            return this;

        }
    }

