package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы
    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    private final By registerLink = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private final By forgotPasswordLink = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    private final By loginForm = By.xpath("//h2[contains(text(),'Вход')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    public boolean isLoginFormDisplayed() {
        return driver.findElement(loginForm).isDisplayed();
    }
}