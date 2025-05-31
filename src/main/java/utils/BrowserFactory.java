package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    public static WebDriver createBrowser() {
        // Смотрим system-property, затем env-переменную, иначе — chrome
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = System.getenv("BROWSER");
        }
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        browser = browser.toLowerCase();

        switch (browser) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:/Users/User/projects/chromedriver-win64/chromedriver-134/chromedriver-win64/chromedriver.exe");
                ChromeOptions yOpts = new ChromeOptions();
                yOpts.setBinary("C:/Users/User/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                yOpts.addArguments(
                        "--disable-extensions",
                        "--disable-popup-blocking",
                        "--disable-infobars",
                        "--start-maximized",
                        "--disable-notifications"
                );
                return new ChromeDriver(yOpts);

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}