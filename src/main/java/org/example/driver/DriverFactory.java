package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static WebDriver driver;

    /**
     * Получение драйвера (с инициализацией при первом вызове)
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /**
     * Инициализация драйвера. Читает параметр -Dbrowser (по умолчанию chrome).
     */
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
                ChromeOptions yOpts = new ChromeOptions();
                yOpts.setBinary("C:/Users/User/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                driver = new ChromeDriver(yOpts);
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}