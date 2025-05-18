package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;

    private final By nameField = By.name("name");
    private final By emailField = By.name("email");
    private final By passwordField = By.name("Пароль");
    private final By registerBtn = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод name: {name}")
    public RegisterPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввод password: {pwd}")
    public RegisterPage enterPassword(String pwd) {
        driver.findElement(passwordField).sendKeys(pwd);
        return this;
    }

    @Step("Нажать 'Зарегистрироваться'")
    public MainPage clickRegisterButton() {
        driver.findElement(registerBtn).click();
        return new MainPage(driver);
    }

    @Step("Перейти на страницу логина")
    public LoginPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Получить текст ошибки под паролем")
    public String getPasswordError() {
        return driver.findElement(By.xpath("//p[text()='Некорректный пароль']")).getText();
    }
}