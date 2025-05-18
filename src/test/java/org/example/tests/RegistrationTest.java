package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.api.models.User;
import org.example.pages.LoginPage;
import org.example.pages.RegisterPage;
import org.junit.Test;

import static org.junit.Assert.*;

@DisplayName("Тесты регистрации")
public class RegistrationTest extends TestBase {

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        LoginPage lp = mainPage.clickLoginButton();
        RegisterPage rp = lp.clickRegisterLink();
        String email = "user" + System.currentTimeMillis() + "@mail.com";
        String pwd = "password1";
        rp.enterName("TestUser")
                .enterEmail(email)
                .enterPassword(pwd);
        // создаём через UI, но сохраняем token через API для удаления
        rp.clickRegisterButton();
        // после успешной регистрации на главной
        assertTrue(mainPage.isLoggedIn());
        // получаем токен для удаления
        accessToken = userClient.create(new User("TestUser", email, pwd));
        assertNotNull(accessToken);
    }

    @Test
    @DisplayName("Ошибка регистрации: короткий пароль")
    public void registrationWithShortPassword() {
        LoginPage lp = mainPage.clickLoginButton();
        RegisterPage rp = lp.clickRegisterLink();
        rp.enterName(" short")
                .enterEmail("short@mail.com")
                .enterPassword("123");
        rp.clickRegisterButton();
        assertEquals("Некорректный пароль", rp.getPasswordError());
    }
}