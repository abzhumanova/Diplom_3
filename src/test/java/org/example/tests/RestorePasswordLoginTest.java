package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.api.models.User;
import org.example.pages.LoginPage;
import org.example.pages.RestorePasswordPage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@DisplayName("Тест входа через восстановление пароля")
public class RestorePasswordLoginTest extends TestBase {
    private String email;
    private String pwd = "password1";

    @Before
    public void createUser() {
        email = "user" + System.currentTimeMillis() + "@mail.com";
        accessToken = userClient.create(new User("RestUser", email, pwd));
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    public void loginViaRestorePassword() {
        LoginPage lp = mainPage.clickLoginButton();
        RestorePasswordPage rp = lp.clickResetLink();
        rp.clickLoginLink()
                .enterEmail(email)
                .enterPassword(pwd);
        mainPage = lp.clickLoginButton();
        assertTrue(mainPage.isLoggedIn());
    }
}