import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    String url;

    @BeforeSuite
    public static void chromeConfigs() {
        // This is for Windows users
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
    }

    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        url = BaseUrl;
        driver.get(url);
    }

   /* @AfterMethod
    public void teadDownBrowser() {
        driver.quit();
    }*/

    public void clickSubmitBtn() {
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
    }

    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.click();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        passwordField.click();
        passwordField.sendKeys(password);

    }

    @DataProvider(name="invalidCredentials")
    public static Object[][] getCredentials() {

        return new Object[][]{
                {"invalid@class.com", "invalidPass"},
                {"d@class.com", ""},
                {"", ""}
        };
    }
    @DataProvider(name = "loginData")
    public static Object[][] getLoginCredentials() {
        return new Object[][]{
                {"Nargiza10041@gmail.com", "te$t$tudent"}
        };
    }
}