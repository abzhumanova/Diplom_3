package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.pages.ProfilePage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@DisplayName("Тесты навигации")
public class NavigationTest extends TestBase {
    private String email, pwd = "password1";

    @Before
    public void createAndLogin() {
        email = "user" + System.currentTimeMillis() + "@mail.com";
        accessToken = userClient.create(new org.example.api.models.User("NavUser", email, pwd));
        // логинимся через UI
        mainPage.clickLoginButton()
                .enterEmail(email)
                .enterPassword(pwd)
                .clickLoginButton();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToProfile() {
        ProfilePage pp = new ProfilePage(driver);
        assertNotNull(pp);
    }

    @Test
    @DisplayName("Переход из профиля в конструктор")
    public void profileToConstructor() {
        ProfilePage pp = new ProfilePage(driver);
        pp.clickConstructor();
        assertTrue(driver.getCurrentUrl().contains("/"));
        pp.clickLogo();
        assertTrue(driver.getCurrentUrl().contains("/"));
    }
}