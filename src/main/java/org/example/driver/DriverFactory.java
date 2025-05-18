package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverFactory {
    private static WebDriver driver;

    /** Возвращает уже инициализированный или создаёт новый драйвер */
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /** Инициализация в зависимости от -Dbrowser */
    public static WebDriver initDriver() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(opts);
                break;
            case "yandex":
                WebDriverManager.chromedriver().setup();
                String yPath = System.getProperty("yandex.browser.path");
                if (yPath == null || !new File(yPath).exists()) {
                    throw new IllegalArgumentException(
                            "Укажите корректный путь к Yandex Browser. Пример команды для запуска:\n" +
                                    "mvn test -Dbrowser=yandex -Dyandex.browser.path=\"C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe\""
                    );
                }
                ChromeOptions yOpts = new ChromeOptions();
                yOpts.setBinary(yPath);
                driver = new ChromeDriver(yOpts);
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    /** Завершить сессию */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}