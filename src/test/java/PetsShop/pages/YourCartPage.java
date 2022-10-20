package PetsShop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YourCartPage {
    private WebDriver driver;

    public YourCartPage (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "a[class=\'button button--primary rca-checkout-button\']")
    private WebElement buttonCheckOut;
    By banner = By.cssSelector("p[class=\"loading-text\"]");
    @Step("Choose payment type")
    public YourCartPage clickButtonCheckOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.elementToBeClickable(buttonCheckOut)).click();
        return this;
    }
    @Step("Skip banner")
    public YourCartPage ignoreBanner(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(banner));
        WebElement element = driver.findElement(banner);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].style.display = 'none'", element );
        return this;
    }
}




