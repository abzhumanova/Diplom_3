package tests;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.Endpoints;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @Step("Настройка браузера")
    @Before
    public void setUp() {
        // Создаём WebDriver (Chrome или Yandex, в зависимости от -Dbrowser)
        driver = BrowserFactory.createBrowser();

        // Универсальные настройки
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Открываем основной URL
        driver.get(Endpoints.BASE_URL);
    }

    @Step("Закрытие браузера")
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // закрывает все окна и убивает процесс chromedriver
        }
    }
}