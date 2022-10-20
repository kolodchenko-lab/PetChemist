package PetsShop.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrdersPage {

    private WebDriver driver;
    private WebElement shadowRoot;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "div[class='navUser-action navUser-action--quickSearch'] input[id='search_query']")
    private WebElement textInput;
    @Step("Set a product in search field")
    public OrdersPage setProductSearchField (String Text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000L));
        wait.until(ExpectedConditions.elementToBeClickable(textInput)).sendKeys(Text);
        return this;
    }
    @ Step ("Open products in the list in search field")
    public void  openProductList () {
        WebElement shadowHost = driver.findElement(By.cssSelector("div[class=\"mobile-fast-ac-container\"] + div"));
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

        SearchContext shadowRoot = (SearchContext) jsDriver.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement shadowContent = shadowRoot.findElement(By.id("fast-autocomplete-1234"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.visibilityOf(shadowContent));
        Actions actions = new Actions(driver);
        actions.moveToElement(shadowContent).pause(Duration.ofMillis(3000L)).perform();
    }

    @ Step ("Choice a product in the list in search field")
    public void  clickProduct () {
        WebElement shadowHost = driver.findElement(By.cssSelector("div[class=\"mobile-fast-ac-container\"] + div"));
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

        SearchContext shadowRoot = (SearchContext) jsDriver.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("a[data-postion=\"1\"][data-id=\"1855\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.elementToBeClickable(shadowContent)).click();
    }

}
