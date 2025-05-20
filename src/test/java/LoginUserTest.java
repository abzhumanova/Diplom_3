import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.github.javafaker.Faker;

public class LoginUserTest extends BaseTest {
    private final UserApiHelper apiHelper = new UserApiHelper();
    private MainPage mainPage;
    private LoginPage loginPage;
    private String email;
    private String name;
    private String password;

    @Before
    public void setUpPage() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        name = faker.name().firstName();
        password = generateValidPassword();

        createUserWithRetry(3, 1000); // Добавлена функция с повторными попытками

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    private void createUserWithRetry(int maxAttempts, long delayMillis) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                apiHelper.createUser(email, password, name);
                Thread.sleep(delayMillis); // Даем немного времени на создание
                break; // Если успешно создано, выходим из цикла
            } catch (Exception e) {
                attempts++;
                System.err.println("Ошибка при создании пользователя (попытка " + attempts + "/" + maxAttempts + "): " + e.getMessage());
                try {
                    Thread.sleep(delayMillis); // Ждем перед следующей попыткой
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
    @DisplayName("Login from main page with button 'Войти в аккаунт'")
    @Description("Проверка входа через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);
        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Login from main page with button 'Личный кабинет'")
    @Description("Проверка входа через кнопку 'Личный кабинет' на главной странице")
    public void loginFromPersonalAccountTest() {
        mainPage.open();
        mainPage.clickPersonalAccount();
        loginPage.login(email, password);
        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Login with link from registration form")
    @Description("Проверка входа по ссылке из формы регистрации")
    public void loginFromRegisterFormTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();
        loginPage.login(email, password);
        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Login with link from forgot password form")
    @Description("Проверка входа по ссылке из формы восстановления пароля")
    public void loginFromForgotPasswordTest() {
        loginPage.openForgotPasswordPage();
        loginPage.clickLoginFromForgotPassword();
        loginPage.login(email, password);
        Assert.assertTrue(mainPage.isOrderButtonVisible());
    }

    private String generateValidPassword() {
        // длина от 6 до 12, с верхним/нижним регистром, цифрами и спецсимволами
        return new Faker().internet().password(6, 12, true, true, true);
    }
}