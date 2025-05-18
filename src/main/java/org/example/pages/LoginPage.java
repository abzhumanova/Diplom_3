package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage {
    private WebDriver driver;

    // Локаторы для формы входа
    private By emailField = By.xpath("//input[@name='name']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверить, что кнопка 'Войти' видна")
    public boolean isLoginButtonVisible() {
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Ввести email: {0}")
    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль: {0}")
    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку 'Войти'")
    public MainPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }
}