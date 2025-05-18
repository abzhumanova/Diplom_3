package org.example.tests;

import org.example.driver.DriverFactory;
import org.example.pages.MainPage;
import org.example.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class NavigationTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testNavigationToOrderPage() {
        mainPage.clickConstructor();
        assertTrue("Кнопка 'Оформить заказ' должна быть видна", mainPage.isOrderButtonVisible());
    }

    @Test
    public void testNavigateToLoginPage() {
        LoginPage loginPage = mainPage.clickLogin();
        assertTrue("Кнопка 'Войти' должна быть видна на странице входа", loginPage.isLoginButtonVisible());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}