package org.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestConfig {
    private static WebDriver driver;

    public static WebDriver initializeDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        else if (browserName.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "путь/к/yandexdriver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("путь/к/yandex/браузеру");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}