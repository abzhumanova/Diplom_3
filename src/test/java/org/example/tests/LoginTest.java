package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.api.models.User;
import org.example.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@DisplayName("Тесты авторизации")
public class LoginTest extends TestBase {
    private String email;
    private String pwd = "password1";

    @Before
    public void createUser() {
        email = "user" + System.currentTimeMillis() + "@mail.com";
        // создаём пользователя через API
        accessToken = userClient.create(new User("UIUser", email, pwd));
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void loginViaMainPage() {
        LoginPage lp = mainPage.clickLoginButton();
        lp.enterEmail(email).enterPassword(pwd);
        mainPage = lp.clickLoginButton();
        assertTrue(mainPage.isLoggedIn());
    }

    @Test
    @DisplayName("Вход через 'Личный Кабинет'")
    public void loginViaProfileButton() {
        LoginPage lp = mainPage.clickProfileButtonWhenUnauthorized();
        lp.enterEmail(email).enterPassword(pwd);
        mainPage = lp.clickLoginButton();
        assertTrue(mainPage.isLoggedIn());
    }
}