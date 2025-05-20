import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import com.github.javafaker.Faker;

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
        email = faker.internet().emailAddress();
        name = faker.name().firstName();
        password = generateValidPassword();

        // Добавляем эту строку для явного указания пути к ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\projects\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        registerPage.open();
    }

    @After
    public void deleteUser() {
        if (userWasCreated) {
            apiHelper.deleteUser(email, password);
        }
    }

    @Test
    @DisplayName("Successful user registration")
    @Description("Проверка успешной регистрации пользователя с валидными данными")
    public void userCanRegisterSuccessfullyTest() {
        registerPage.register(name, email, password);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginHeader()));

        userWasCreated = true;

        // проверяем наличие текста заголовка 'Вход'
        assertTrue(driver.findElement(loginPage.getLoginHeader()).isDisplayed());
    }

    @Test
    @DisplayName("Registration fails with short password")
    @Description("Проверка ошибки при регистрации с паролем короче 6 символов")
    public void userCannotRegisterWithShortPasswordTest() {
        registerPage.register(name, email, "12345");
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }

    private String generateValidPassword() {
        // длина от 6 до 12, с верхним/нижним регистром, цифрами и спецсимволами
        return new Faker().internet().password(6, 12, true, true, true);
    }
}
