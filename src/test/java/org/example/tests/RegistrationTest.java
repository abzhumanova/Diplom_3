package org.example.tests;

import org.example.api.UserClient;
import org.example.api.models.User;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends TestBase {
    private final UserClient userClient = new UserClient();
    private String accessToken;
    private User user;

    @Before
    public void setUp() {
        user = new User(
                "testuser-" + System.currentTimeMillis() + "@example.com",
                "validPass123",
                "Test User"
        );
    }

    @Test
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName(user.getName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickRegisterButton();

        this.accessToken = userClient.register(user);
        assertTrue(new LoginPage(driver).isLoginFormDisplayed());
    }

    @Test
    public void testRegistrationWithInvalidPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName("Invalid User")
                .enterEmail("invalid@example.com")
                .enterPassword("short")
                .clickRegisterButton();

        assertTrue(registerPage.isPasswordErrorDisplayed());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}