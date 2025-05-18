package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    // Локаторы
    private final By btnConstructor     = By.xpath("//p[text()='Конструктор']");
    private final By btnOrder           = By.xpath("//button[text()='Оформить заказ']");
    private final By btnLoginOnMain     = By.xpath("//button[text()='Войти в аккаунт']");
    private final By btnProfileWhenOut  = By.xpath("//p[text()='Личный кабинет']");
    private final By btnLogout          = By.xpath("//button[text()='Выход']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу Stellar Burgers")
    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Нажать кнопку 'Конструктор'")
    public MainPage clickConstructor() {
        driver.findElement(btnConstructor).click();
        return this;
    }

    @Step("Проверить, что кнопка 'Оформить заказ' видна")
    public boolean isOrderButtonVisible() {
        return driver.findElement(btnOrder).isDisplayed();
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной")
    public LoginPage clickLoginButton() {
        driver.findElement(btnLoginOnMain).click();
        return new LoginPage(driver);
    }

    @Step("Нажать 'Личный кабинет' когда ещё не авторизован")
    public LoginPage clickProfileButtonWhenUnauthorized() {
        driver.findElement(btnProfileWhenOut).click();
        return new LoginPage(driver);
    }

    @Step("Проверить, что пользователь залогинен (видна кнопка выхода)")
    public boolean isLoggedIn() {
        return driver.findElement(btnLogout).isDisplayed();
    }

    // В класс MainPage добавляем новый метод (совместимость со старыми тестами)
    @Step("Нажать на кнопку 'Войти в аккаунт' (алиас для clickLoginButton)")
    public LoginPage clickLogin() {
        return clickLoginButton();
    }
}