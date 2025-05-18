package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final By emailField = By.xpath("//input[@name='name']");
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email для восстановления: {email}")
    public RestorePasswordPage enterEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField))
                .sendKeys(email);
        return this;
    }

    @Step("Нажать 'Восстановить'")
    public void clickResetButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(restoreButton))
                .click();
    }

    @Step("Перейти на страницу логина")
    public LoginPage clickLoginLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
        return new LoginPage(driver);
    }
}