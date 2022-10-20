package AutomationTests.scenarios;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.*;

public class FirstTest {
    private static WebDriver driver;

    public FirstTest(){
        System.setProperty("webdriver.chrome.driver", "C:/DRIVERS/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setHeadless(false);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100L));

    }
    @Test
    public void shouldSearch_byID(){
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement ElementEmailbyID = driver.findElement(id("exampleInputEmail1"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(300L));

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exampleInputEmail1")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1")));
       // Assertions.assertTrue(ElementEmailbyID.isEnabled());
    }
    @Test
    public void listShouldSearch_byID(){
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> ElementEmailbyID = driver.findElements(id("exampleInputEmail1"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        //THEN
       // Assertions.assertEquals(1,ElementEmailbyID.size());
        wait.until(ExpectedConditions.numberOfElementsToBe(id("exampleInputEmail1"),1));
    }

    @Test
    public void shouldSearchByName(){
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement driverElementByName = driver.findElement(name("viewport"));
        //THEN
        Assertions.assertTrue(driverElementByName.isEnabled());

    }

    @Test
    public void shouldSearchByClassName_MultipleElements(){
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> webElementList = driver.findElements(By.className("form-control"));
        //THEN
        Assertions.assertEquals(2, webElementList.size());

    }

    @Test
    public void shouldSearchByTagName(){
        //GIVEN
        int expectedDivTags = 4;
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> webElementList = driver.findElements(By.tagName("div"));
        //THEN
        Assertions.assertEquals(expectedDivTags, webElementList.size());

    }

    @Test
    public void shouldSearchByLinkText(){
        //GIVEN

        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/");
        List<WebElement> contribution_guidelines = driver.findElements(By.linkText("contribution guidelines"));
        //THEN
        Assertions.assertTrue(contribution_guidelines.isEmpty());

    }

    @Test
    public void shouldSearchByCCs(){
        //GIVEN

        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/");
        List<WebElement> contribution_guidelines = driver.findElements(By.cssSelector("div.td-content div.lead header.article-met"));
        //THEN
        Assertions.assertTrue(contribution_guidelines.isEmpty());

    }
    @Test
    public void shouldSearch_byCssSelector_complexStructure() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements = driver.findElements(By.cssSelector("#tabs-10 > li:nth-child(1) > a"));
        //THEN
       // Assertions.assertEquals(1, elements.size());
        boolean isElementActive = elements.get(0).getAttribute("class").contains("active");
        Assertions.assertTrue(isElementActive);
    }

    @Test
    public void clickCheckbox(){
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        WebElement CheckboxElement = driver.findElement(xpath("//input[@id='flexCheckDefault']"));
        //THEN
        Assertions.assertFalse(CheckboxElement.isSelected());
        //WHEN
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",CheckboxElement);

        //THEN
        Assertions.assertTrue(CheckboxElement.isSelected());


    }

    @Test
    public  void checkRadioButton(){
        //Give
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        WebElement radioButtonElement = driver.findElement(xpath("//input[@id='flexRadioDefault1']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",radioButtonElement);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //THEN
        Assertions.assertTrue(radioButtonElement.isSelected());

        //WHEN
        WebElement radioButtonElementUnChecked = driver.findElement(xpath("//input[@id='flexRadioDefault2']"));

        //THEN
        Assertions.assertFalse(radioButtonElementUnChecked.isEnabled());
    }
    @Test
    public void clickRadiobuttonWithIframe() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/");

        driver.switchTo().frame(0);
        WebElement radiobuttonElementInput = driver.findElement(By.xpath("//input[@id='xs']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", radiobuttonElementInput);

        //THEN
        Assertions.assertFalse(radiobuttonElementInput.isSelected());

        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiobuttonElementInput);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000L));
        //THEN
        Assertions.assertTrue(radiobuttonElementInput.isSelected());
        driver.switchTo().defaultContent();
    }
    @Test
    public void openTwoWindows() {
        //GIVEN
        String expectedUrl = "https://bootstrap-slack.herokuapp.com/";
        //WHEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        String mainWindowHandle = driver.getWindowHandle();
        WebElement Linkelement = driver.findElement(xpath("//a[@href='https://bootstrap-slack.herokuapp.com/']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Linkelement);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //THEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!mainWindowHandle.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }

        }
        String actualCurrentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualCurrentUrl);
        //WHEN
        driver.switchTo().window(mainWindowHandle);
    }




     @Test
    public void byRelativeLocatorTest() throws InterruptedException {
        //GIVEN
         //WHEN
         driver.get("http://online-sh.herokuapp.com/login");
         WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(500L));
         By emailLocator = RelativeLocator.with(By.xpath("//input[@name='email']"));
         WebElement emailInput = driver.findElement(emailLocator);

         emailInput.sendKeys("test@test.com");

         String currentTab = driver.getWindowHandle();
         driver.switchTo().newWindow(WindowType.TAB);

         Thread.sleep(300L);
         driver.switchTo().window(currentTab);

        //THEN
        wait.until(ExpectedConditions.urlToBe("http://online-sh.herokuapp.com/login"));
        driver.close();
        driver.quit();
    }
        @Test
        public void testNavigate(){
        //GIVEN
            String testURl = "https://www.google.com/";
            String expectedTestTitle = "Google";
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        //WHEN
            driver.navigate().to(testURl);
        //THEN
        wait.until(ExpectedConditions.titleIs(expectedTestTitle));
        }

    @Test
    public void testSelectMultiple() {
        //GIVEN
        //WHEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/select/");
        WebElement selectElement = driver.findElement(By.xpath("(//select[@class='form-select'])[2]"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("One");
        select.selectByIndex(2);
        select.selectByValue("3");

        //THEN
        Assertions.assertTrue(select.isMultiple());
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
        Assertions.assertEquals(4, options.size());


        List<WebElement> allSelectedOptions = select.getAllSelectedOptions();
        for (WebElement allSelectedOption : allSelectedOptions) {
            System.out.println(allSelectedOption.getText());
        }
        List<String> collect = allSelectedOptions.stream().map(e -> e.getText()).collect(Collectors.toList());
        Assertions.assertEquals(4, allSelectedOptions.size());


        WebElement firstSelectedOption = select.getFirstSelectedOption();
        System.out.println(firstSelectedOption.getText());
        org.assertj.core.api.Assertions.assertThat(collect).contains("Open this select menu", "One", "Two", "Three");
    }



        @Test
        public void testSelect(){
        //GIVEN
        // WHEN
            driver.get("https://getbootstrap.com/docs/5.0/forms/select/");
            WebElement testElementSelect = driver.findElement(xpath("(//select[@class='form-select'])[1]"));
            Select select = new Select(testElementSelect);
            select.selectByVisibleText("Two");


            //THEN
            WebElement firstSelectedOption = select.getFirstSelectedOption();
            Assertions.assertEquals("Two",firstSelectedOption.getText());
            Assertions.assertTrue(firstSelectedOption.isSelected());
            Assertions.assertFalse(select.isMultiple());
            //WHEN
            List<WebElement> options = select.getAllSelectedOptions();
            for (WebElement option : options){
                System.out.println(option.getText());
            }

            //THEN
           // Assertions.assertEquals("Open this select menu, One, Two, Three",options.());
            Assertions.assertEquals(4,options.size());


        }
    @Test
    public void testActions() throws InterruptedException {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(500L));
        WebElement input = driver.findElement(xpath("//input[@name='q']"));
        Actions actions = new Actions(driver);
        WebElement gmailLink = driver.findElement(By.xpath("(//a[@class='gb_d'])[1]"));

        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(300L));

        //WHEN
            //input.sendKeys("Java");
            //input.sendKeys(Keys.ENTER);
            //actions.sendKeys(input,"Java").sendKeys(Keys.ENTER).perform();
            //JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
            //jsExecutor.executeScript("arguments[0].value='QA', input");

        //THEN
       // wait.until(ExpectedConditions.presenceOfElementLocated(xpath(".//ul")));

        //WHEN
        actions.moveToElement(gmailLink).perform();
        Thread.sleep(300L);
        //Then
        driverFluentWait.until(e -> e.findElement(By.xpath("(//a[@class='gb_d'])[1]")));
    }

    @Test
    public void cookieTest() throws IOException {
        //GIVEN
        //WHEN
        driver.get("https://github.com/kolodchenko-lab/LuxSoft");
        Cookie getCoockie = new Cookie("user_session","fjujTO-iFGvf2rMwc3JFGxW-sLwEdzvB6-Tl6aMoRq-Qk8-L");
        driver.manage().addCookie(getCoockie);
        driver.navigate().refresh();

        //THEN
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("coockie"  +cookies);
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshotAsCockies = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAsCockies, new File("C:/Users/Igor/Desktop/Testscreen" + screenshotAsCockies.getName() + ".png" ));
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }

}
