package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.UserApiHelper;

import static org.junit.Assert.assertTrue;

public class ConstructorNavigationTest extends BaseTest {

    private Faker faker;
    private String name;
    private String email;
    private String password;
    private UserApiHelper apiHelper;

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void setUpPage() {
        faker     = new Faker();
        name      = faker.name().firstName();
        email     = faker.internet().emailAddress();
        password  = faker.internet().password(6, 12, true, true, true);
        apiHelper = new UserApiHelper();

        // создаем пользователя через API
        apiHelper.createUser(email, password, name);

        // инициализируем страницы на единственном driver из BaseTest
        mainPage    = new MainPage(driver);
        loginPage   = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        // переходим в профиль через UI
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        mainPage.clickPersonalAccount();
    }

    @After
    public void deleteUser() {
        // удаляем пользователя через API
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("Пользователь может вернуться в конструктор с помощью кнопки 'Конструктор'")
    @Description("Проверка перехода в конструктор по кнопке 'Конструктор' в личном кабинете")
    public void userCanNavigateToConstructorViaButtonTest() {
        profilePage.clickConstructorButton();
        assertTrue(
                "Не отобразилась кнопка 'Оформить заказ'",
                mainPage.isOrderButtonVisible()
        );
    }

    @Test
    @DisplayName("Пользователь может вернуться в конструктор по клику на логотип 'Stellar Burgers'")
    @Description("Проверка перехода в конструктор по клику на логотип 'Stellar Burgers' в личном кабинете")
    public void userCanNavigateToConstructorViaLogoTest() {
        profilePage.clickLogo();
        assertTrue(
                "Не отобразилась кнопка 'Оформить заказ'",
                mainPage.isOrderButtonVisible()
        );
    }
}