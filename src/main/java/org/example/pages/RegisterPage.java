package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы формы регистрации
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    // Локаторы ошибок
    private final By passwordError = By.xpath("//p[contains(@class,'input__error') and text()='Некорректный пароль']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public RegisterPage enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        return this;
    }

    public RegisterPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public boolean isPasswordErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}