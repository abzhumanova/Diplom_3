package org.example.tests;

import org.example.api.UserClient;
import org.example.api.models.User;
import org.example.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class LoginTest extends TestBase {
    private UserClient userClient = new UserClient();
    private String accessToken;
    private User user;

    @Before
    public void setUp() {
        user = new User(
                "login-test-" + System.currentTimeMillis() + "@example.com",
                "loginPass123",
                "Login Test"
        );
        this.accessToken = userClient.register(user);
    }

    @Test
    public void testLoginViaAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isAuthorized());
    }

    @Test
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isAuthorized());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}