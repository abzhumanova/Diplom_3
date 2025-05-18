package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

/* Выход из аккаунта.
Проверь выход по кнопке «Выйти» в личном кабинете.*/
public class LogoutUserTest extends BaseTest {

    int random = ThreadLocalRandom.current().nextInt(100,100_000);
    private final String email = "abzhumanova" + random + "@yandex.ru";
    private final String name = "Ayslu";
    private final String password = "123456";

    private final UserApiHelper apiHelper = new UserApiHelper();

    private HomePage homePage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void setUpPage() {
        apiHelper.createUser(email, password, name);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        // Вход
        homePage.open();
        homePage.clickLoginButton();
        loginPage.login(email, password);
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("User can log out from personal account")
    @Description("Проверка выхода из аккаунта через Личный кабинет")
    public void userCanLogoutSuccessfullyTest() {
        homePage.clickPersonalAccount();
        profilePage.clickLogoutButton();

        // Проверяем, что мы снова на странице логина
        assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }
}