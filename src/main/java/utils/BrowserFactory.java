package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
    public static WebDriver createBrowser(String browserName) {
        browserName = browserName.toLowerCase();  //ensure case-insensitivity

        if (browserName.equals("chrome")) {
            // Для Chrome используем версию 136
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\projects\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            return new ChromeDriver();
        } else if (browserName.equals("yandex")) {
            // Для Yandex используем версию 134
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\projects\\chromedriver-win64\\chromedriver-134\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--start-maximized");
            return new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        }
    }
}

