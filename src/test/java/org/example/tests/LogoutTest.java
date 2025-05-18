package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.ProfilePage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@DisplayName("Тест выхода из аккаунта")
public class LogoutTest extends TestBase {
    private String email, pwd = "password1";

    @Before
    public void createAndLogin() {
        email = "user" + System.currentTimeMillis() + "@mail.com";
        accessToken = userClient.create(new org.example.api.models.User("LogUser", email, pwd));
        mainPage.clickLoginButton()
                .enterEmail(email)
                .enterPassword(pwd)
                .clickLoginButton();
    }

    @Test
    @DisplayName("Выход по кнопке 'Выход'")
    public void logout() {
        ProfilePage pp = new ProfilePage(driver);
        mainPage = pp.clickLogout();
        assertFalse(mainPage.isLoggedIn());
    }
}