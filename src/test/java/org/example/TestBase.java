package org.example;

import org.example.driver.DriverFactory;
import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class TestBase {
    protected static WebDriver driver;
    protected static MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        mainPage = new MainPage(driver).open();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}