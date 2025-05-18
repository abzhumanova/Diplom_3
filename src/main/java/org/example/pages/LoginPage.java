package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By loginBtn = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By resetLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email: {email}")
    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввод password: {pwd}")
    public LoginPage enterPassword(String pwd) {
        driver.findElement(passwordField).sendKeys(pwd);
        return this;
    }

    @Step("Нажать кнопку 'Войти'")
    public MainPage clickLoginButton() {
        driver.findElement(loginBtn).click();
        return new MainPage(driver);
    }

    @Step("Перейти на страницу регистрации")
    public RegisterPage clickRegisterLink() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

    @Step("Перейти на страницу восстановления пароля")
    public RestorePasswordPage clickResetLink() {
        driver.findElement(resetLink).click();
        return new RestorePasswordPage(driver);
    }
}