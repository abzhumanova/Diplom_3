package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private final String url = "https://stellarburgers.nomoreparties.site/";

    private final By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главный экран")
    public MainPage open() {
        driver.get(url);
        return this;
    }

    @Step("Нажать 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать 'Личный Кабинет' (unauthorized)")
    public LoginPage clickProfileButtonWhenUnauthorized() {
        driver.findElement(profileButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать 'Конструктор'")
    public BurgerConstructorPage clickConstructor() {
        driver.findElement(constructorButton).click();
        return new BurgerConstructorPage(driver);
    }

    @Step("Нажать на логотип")
    public BurgerConstructorPage clickLogo() {
        driver.findElement(logo).click();
        return new BurgerConstructorPage(driver);
    }

    @Step("Проверить, что пользователь залогинен (видна кнопка 'Личный Кабинет')")
    public boolean isLoggedIn() {
        return driver.findElements(profileButton).size() > 0;
    }
}