package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.LoginCredentialsModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.UserApiHelper;

import java.time.Duration;

public class LoginUserTest extends BaseTest {
    private final UserApiHelper apiHelper = new UserApiHelper();
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private String email, name, password;

    @Before
    public void setUpPage() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        name = faker.name().firstName();
        password = faker.internet().password(6, 12, true, true, true);

        createUserWithRetry();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    private void createUserWithRetry() {
        int maxAttempts = 3;
        long delayMillis = 1000;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                LoginCredentialsModel loginCredentials = new LoginCredentialsModel(email, password);
                apiHelper.createUser(loginCredentials.getEmail(), loginCredentials.getPassword(), name);
                break; // Выходим из цикла после успешного создания пользователя
            } catch (Exception e) {
                attempts++;
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (attempts == maxAttempts) {
            throw new RuntimeException("Не удалось создать пользователя после " + maxAttempts + " попыток!");
        }
    }

    @After
    public void deleteUser() {
        apiHelper.deleteUser(email, password);
    }

    @Test
    @DisplayName("Вход с главной страницы с помощью кнопки 'Войти в аккаунт'")
    @Description("Проверка входа через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Оформить заказ']")));

        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход с главной страницы с помощью кнопки 'Личный кабинет'")
    @Description("Проверка входа через кнопку 'Личный кабинет' на главной странице")
    public void loginFromPersonalAccountTest() {
        mainPage.open();
        mainPage.clickPersonalAccount();
        loginPage.login(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Оформить заказ']")));

        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход по ссылке из формы регистрации")
    @Description("Проверка входа по ссылке из формы регистрации")
    public void loginFromRegisterFormTest() {
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.login(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Оформить заказ']")));

        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход по ссылке из формы восстановления пароля")
    @Description("Проверка входа по ссылке из формы восстановления пароля")
    public void loginFromForgotPasswordTest() {
        loginPage.openForgotPasswordPage();
        loginPage.clickLoginFromForgotPassword();
        loginPage.login(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Оформить заказ']")));

        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }
}