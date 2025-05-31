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

public class PersonalAccountNavigationTest extends BaseTest {
    private final UserApiHelper apiHelper = new UserApiHelper();
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private String email, name, password;

    @Before
    public void setUpPage() {
        Faker faker = new Faker();
        email    = faker.internet().emailAddress();
        name     = faker.name().firstName();
        password = faker.internet().password(6, 12, true, true, true);

        createUserWithRetry(3);

        mainPage    = new MainPage(driver);
        loginPage   = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

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
                return;
            } catch (Exception e) {
                attempts++;
            }
        }
        throw new RuntimeException("Не удалось создать пользователя после " + maxAttempts + " попыток!");
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("Пользователь может открыть личный кабинет")
    @Description("Проверка перехода в личный кабинет по клику на 'Личный кабинет'")
    public void userCanOpenPersonalAccountTest() {
        mainPage.clickPersonalAccount();

        // Явное ожидание появления кнопки "Выход"
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(profilePage.getLogoutButton()));

        Assert.assertTrue("Не отображается кнопка 'Выход'", profilePage.isLogoutButtonVisible());
    }
}