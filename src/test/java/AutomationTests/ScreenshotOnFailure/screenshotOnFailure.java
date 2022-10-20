package AutomationTests.ScreenshotOnFailure;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class screenshotOnFailure implements TestWatcher {
    private final WebDriver driver;
    private final String path;

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment("screenshot","image/png","png",
                ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {

    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            captureScreenshot(driver, context.getDisplayName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void captureScreenshot(WebDriver driver, String fileName) throws IOException{
        try {
            new File(path).mkdirs();
            try (FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot -" + fileName + ".png")){
                out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            }
            } catch (IOException | WebDriverException e){
            System.out.println("screenshot failed :" + e.getMessage());
        }
        }


    public screenshotOnFailure(WebDriver driver, String path){
        this.driver = driver;
        this.path = path;
    }
    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        BaseSetUp.driver.quit();
    }
}
