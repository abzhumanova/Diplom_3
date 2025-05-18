package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class RegisterPage {
    private WebDriver driver;

    // Локаторы (уточните значения, если DOM отличается)
    private By emailField = By.xpath("//input[@name='name']");
    private By nameField = By.xpath("//input[@name='userName']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email: {0}")
    public RegisterPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввести имя пользователя: {0}")
    public RegisterPage enterName(String userName) {
        driver.findElement(nameField).sendKeys(userName);
        return this;
    }

    @Step("Ввести пароль: {0}")
    public RegisterPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public RegisterPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }
}