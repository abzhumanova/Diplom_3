package org.example.tests;

import org.example.api.UserClient;
import org.example.api.models.User;
import org.example.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends TestBase {
    private UserClient userClient = new UserClient();
    private String accessToken;
    private User user;

    @Before
    public void setUp() {
        user = new User(
                "logout-test-" + System.currentTimeMillis() + "@example.com",
                "logoutPass123",
                "Logout Test"
        );
        this.accessToken = userClient.register(user);
    }

    @Test
    public void testSuccessfulLogout() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        PersonalAccountPage accountPage = new PersonalAccountPage(driver);
        accountPage.clickLogoutButton();

        assertTrue(loginPage.isLoginFormDisplayed());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}