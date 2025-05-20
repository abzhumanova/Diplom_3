import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.github.javafaker.Faker;

public class LogoutUserTest extends BaseTest {
    private final UserApiHelper apiHelper = new UserApiHelper();
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
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
        profilePage = new ProfilePage(driver);

        // Вход
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);

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
    @DisplayName("User can log out from personal account")
    @Description("Проверка выхода из аккаунта через Личный кабинет")
    public void userCanLogoutSuccessfullyTest() {
        mainPage.clickPersonalAccount();
        profilePage.clickLogoutButton();

        // Проверяем, что мы снова на странице логина
        Assert.assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }

    private String generateValidPassword() {
        // длина от 6 до 12, с верхним/нижним регистром, цифрами и спецсимволами
        return new Faker().internet().password(6, 12, true, true, true);
    }
}