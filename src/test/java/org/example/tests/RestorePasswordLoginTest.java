package org.example.tests;

import org.example.api.UserClient;
import org.example.api.models.User;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RestorePasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RestorePasswordLoginTest extends TestBase {
    private UserClient userClient = new UserClient();
    private String accessToken;
    private User user;

    @Before
    public void setUp() {
        user = new User(
                "restore-test-" + System.currentTimeMillis() + "@example.com",
                "restorePass123",
                "Restore Test"
        );
        this.accessToken = userClient.register(user);
    }

    @Test
    public void testLoginViaRestorePasswordPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();

        RestorePasswordPage restorePage = new RestorePasswordPage(driver);
        restorePage.clickLoginLink();

        assertTrue(loginPage.isLoginFormDisplayed());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}