package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.UserRegisterModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.UserApiHelper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutUserTest extends BaseTest {
    private final UserApiHelper apiHelper   = new UserApiHelper();
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private String email;
    private String name;
    private String password;

    @Before
    public void setUpPage() {
        Faker faker = new Faker();
        email    = faker.internet().emailAddress();
        name     = faker.name().firstName();
        password = faker.internet().password(6, 12, true, true, true);

        createUserWithRetry(3);

        // Инициализируем страницы
        mainPage    = new MainPage(driver);
        loginPage   = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        // Логинимся через UI
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
    }

    private void createUserWithRetry(int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                UserRegisterModel user = new UserRegisterModel(email, password, name);
                apiHelper.createUser(user.getEmail(), user.getPassword(), user.getName());
                return; // успешно, выходим
            } catch (Exception e) {
                attempts++;
                // просто повторяем запрос без sleep()
            }
        }
        throw new RuntimeException("Не удалось создать пользователя после " + maxAttempts + " попыток!");
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("Пользователь может успешно выйти из личного кабинета")
    @Description("Проверка выхода из аккаунта через Личный кабинет")
    public void userCanLogoutSuccessfullyTest() {
        mainPage.clickPersonalAccount();
        profilePage.clickLogoutButton();

        // Явное ожидание появления заголовка "Вход"
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginHeader()));

        Assert.assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }
}