package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

/* Переход из личного кабинета в конструктор.
Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.*/
public class ConstructorNavigationTest extends BaseTest {

    int random = ThreadLocalRandom.current().nextInt(100,100_000);
    private final String email = "sveta" + random + "@yandex.ru";
    private final String name = "Sveta";
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

        homePage.open();
        homePage.clickLoginButton();
        loginPage.login(email, password);

        homePage.clickPersonalAccount(); // переходим в профиль
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("User can return to constructor with 'Constructor' button")
    @Description("Проверка перехода в конструктор по кнопке 'Конструктор' в личном кабинете")
    public void userCanNavigateToConstructorViaButtonTest() {
        profilePage.clickConstructorButton();
        assertTrue("Не отобразилась кнопка 'Оформить заказ'",
                homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("User can return to constructor with logo click")
    @Description("Проверка перехода в конструктор по клику на логотип 'Stellar Burgers' в личном кабинете")
    public void userCanNavigateToConstructorViaLogoTest() {
        profilePage.clickLogo();
        assertTrue("Не отобразилась кнопка 'Оформить заказ'",
                homePage.isOrderButtonVisible());
    }
}