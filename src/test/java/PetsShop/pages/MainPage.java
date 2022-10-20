package PetsShop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;}
    @FindBy(how = How.XPATH,using = "//div[@class='navUser-item navUser-item--account']//a[@href='/login.php']")
    private WebElement buttonSingIn;


    @Step ("Open web site")
    public MainPage openPetChemistSite(){
        driver.get("https://petchemist.com.au/");
        return this;
    }

    @Step("Click link 'Sing in' to go Login page menu ")
    public MainPage clickButtonSingIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSingIn)).click();
        return this;
    }
}