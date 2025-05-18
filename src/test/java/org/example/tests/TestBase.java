package org.example.tests;

import org.example.api.UserClient;
import org.example.driver.DriverFactory;
import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class TestBase {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected UserClient userClient;
    protected String accessToken; // токен для удаления пользователя

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        mainPage = new MainPage(driver).open();
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        // удаляем по токену, если создан
        if (accessToken != null) {
            userClient.delete(accessToken);
            accessToken = null;
        }
        DriverFactory.quitDriver();
    }
}