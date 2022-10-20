package PetsShop.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckOutPage {
    private WebDriver driver;

    public CheckOutPage (WebDriver driver) {
        this.driver = driver;
    }
    By radioButtonShippingMethod = By.cssSelector("ul[class='form-checklist optimizedCheckout-form-checklist'] li:nth-of-type(1)");
    By buttonContinuum = By.id("checkout-shipping-continue");
    By creditCardNumberInput = By.id("credit-card-number");
    @FindBy(id = "braintree-hosted-field-number")
    private WebElement iframeCreditCardNumber;
    @FindBy(id = "braintree-hosted-field-expirationDate")
    private WebElement iframeExpiration;
    @FindBy(id = "braintree-hosted-field-cvv")
    private WebElement iframeCVV;
    By expirationInput = By.id("expiration");
    By nameOnCardInput = By.xpath("//div[@id='braintree-ccName']/input");
    By cvvCardInput = By.id("cvv");
    By checkBoxTermAndCondition = By.id("terms");
    By buttonPlaceOrder = By.id("checkout-payment-continue");


    @Step("Choice a shipping method")
    public void  clickRadioButtonShippingMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(radioButtonShippingMethod));
        WebElement element = driver.findElement(radioButtonShippingMethod);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }
    @Step("Confirmation of order information")
    public CheckOutPage clickButtonContinuum() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(50000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonContinuum));
        WebElement element = driver.findElement(buttonContinuum);
        element.submit();
        return this;
    }
    @Step ("Set credit card number in field ‘Credit card number’")
    public CheckOutPage setCreditCardNumber(String Text){
        driver.switchTo().frame(iframeCreditCardNumber);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(50000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(creditCardNumberInput)).sendKeys(Text);
        driver.switchTo().defaultContent();
        return this;
    }

    @Step("Set expiration credit card  in the field ‘Expiration’")
    public CheckOutPage setExpiration (String Text){
        driver.switchTo().frame(iframeExpiration);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(expirationInput)).sendKeys(Text);
        driver.switchTo().defaultContent();
        return this;
    }
    @Step("Set name on card in the field ‘Name on card’")
    public CheckOutPage setNameOnCard (String Text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOnCardInput)).sendKeys(Text);
        return this;
    }
    @Step ("Set CVV card in the field ‘CVV’")
    public CheckOutPage setCvvCard (String Text){
        driver.switchTo().frame(iframeCVV);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cvvCardInput)).sendKeys(Text);
        driver.switchTo().defaultContent();
        return this;
    }
    @Step ("Choice term and condition")
    public CheckOutPage clickCheckBoxTermAndCondition (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(50000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxTermAndCondition));
        WebElement element = driver.findElement(checkBoxTermAndCondition);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        //((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;",checkBoxTermAndCondition);
        return this;
    }
    @Step ("Confirmation of an order")
    public CheckOutPage clickButtonPlaceOrder (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonPlaceOrder)).click();
        return this;
    }

}
