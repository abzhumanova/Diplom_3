package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegisterPage;
import utils.UserApiHelper;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegisterUserTest extends BaseTest {
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private final UserApiHelper apiHelper = new UserApiHelper();
    private String email;
    private String name;
    private String password;
    private boolean userWasCreated = false;

    @Before
    public void setUpPage() {
        Faker faker = new Faker();
        email    = faker.internet().emailAddress();
        name     = faker.name().firstName();
        password = faker.internet().password(6, 12, true, true, true);

        registerPage = new RegisterPage(driver);
        loginPage    = new LoginPage(driver);
        registerPage.open(); // открыли /register
    }

    @After
    public void deleteUser() {
        if (userWasCreated) {
            apiHelper.deleteUser(email, password);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации пользователя с валидными данными")
    public void userCanRegisterSuccessfullyTest() {
        // 1) Через UI создаём пользователя
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        // 2) Ждём, что браузер переедет на страницу логина и появится её заголовок
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginHeader()));

        userWasCreated = true; // нужно удалить пользователя в @After

        // 3) Убедимся, что заголовок реально показан
        assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }

    @Test
    @DisplayName("Регистрация не удаётся с коротким паролем")
    @Description("Проверка ошибки при регистрации с паролем короче 6 символов")
    public void userCannotRegisterWithShortPasswordTest() {
        // 1) Через UI вводим всё корректно, но короткий пароль
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword("12345");
        registerPage.clickRegisterButton();

        // 2) Ждём появления текстовой ошибки «Некорректный пароль»
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Некорректный пароль')]")
        ));

        // 3) Убеждаемся, что сообщение видно
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }
}