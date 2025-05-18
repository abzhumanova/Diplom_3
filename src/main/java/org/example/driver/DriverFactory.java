package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;

    /** Возвращает готовый или создаёт новый драйвер */
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /** Инициализация с учётом версии ChromeDriver */
    public static WebDriver initDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        // Явно задаём версию ChromeDriver для Chrome 136
        WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "--start-maximized",
                "--disable-notifications"
        );

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver(options);
                break;
            case "yandex":
                String yandexPath = System.getProperty("yandex.browser.path");
                if (yandexPath == null || !new File(yandexPath).exists()) {
                    throw new IllegalArgumentException(
                            "Укажите путь к Yandex Browser. Пример:\n" +
                                    "mvn test -Dbrowser=yandex -Dyandex.browser.path=\"C:\\путь\\к\\browser.exe\""
                    );
                }
                options.setBinary(yandexPath);
                driver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /** Корректное завершение работы драйвера */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}