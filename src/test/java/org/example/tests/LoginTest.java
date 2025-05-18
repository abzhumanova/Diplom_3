package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.LoginPage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты авторизации")
public class LoginTest extends org.example.TestBase {
    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void testLoginViaMainPageButton() {
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.enterEmail("test@example.com");
        loginPage.enterPassword("password123");
        loginPage.clickLoginButton();

        assertTrue(mainPage.isLoggedIn());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginViaProfileButton() {
        LoginPage loginPage = mainPage.clickProfileButtonWhenUnauthorized();
        loginPage.enterEmail("test@example.com");
        loginPage.enterPassword("password123");
        loginPage.clickLoginButton();

        assertTrue(mainPage.isLoggedIn());
    }
}