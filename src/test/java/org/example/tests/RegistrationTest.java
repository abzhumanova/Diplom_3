package org.example.tests;

import org.example.driver.DriverFactory;
import org.example.pages.RegisterPage;
import org.example.user.UserClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import org.openqa.selenium.WebDriver;

public class RegistrationTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private UserClient userClient;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        // Если регистрация располагается на отдельной странице, укажите правильный URL
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPage = new RegisterPage(driver);
        userClient = new UserClient();
    }

    @Test
    public void testSuccessfulRegistration() {
        registerPage.enterEmail("test@example.com")
                .enterName("TestUser")
                .enterPassword("password123")
                .clickRegisterButton();

        // Получаем токен через API-клиент (в реальном проекте регистрация должна вернуть корректный токен)
        String token = userClient.getToken("test@example.com", "password123");
        assertNotNull("Токен не должен быть null после успешной регистрации", token);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}