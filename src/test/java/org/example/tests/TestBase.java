package org.example.tests;

import org.example.BrowserUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserUtils.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}