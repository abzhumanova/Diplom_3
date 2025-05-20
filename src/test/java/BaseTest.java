import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    private UserApiHelper apiHelper = new UserApiHelper(); // Create an instance of UserApiHelper

    @Step("Настройка браузера")
    @Before
    public void setUp() {
        System.setProperty("wdm.prohibit-download", "true");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.createBrowser(browser);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        // --- Получение и установка токенов ---
        String accessToken = apiHelper.getAccessToken("your_email@example.com", "your_password"); // Replace with valid credentials
        String refreshToken = null; // Refresh token is not really needed for logging in, using accessToken is enough

        driver.get(UserApiHelper.BASE_URL); // Open the application's main page

        driver.manage().addCookie(new Cookie("accessToken", accessToken));

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('refreshToken', arguments[0]);",
                refreshToken // refreshToken can be null here
        );

        driver.navigate().refresh(); // Refresh the page to apply the tokens
    }

    @Step("Закрытие браузера")
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}