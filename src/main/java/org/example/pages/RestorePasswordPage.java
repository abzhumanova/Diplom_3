package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final By emailField = By.name("email");
    private final By resetBtn = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email для восстановления: {email}")
    public RestorePasswordPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Нажать 'Восстановить'")
    public void clickResetButton() {
        driver.findElement(resetBtn).click();
    }

    @Step("Перейти на страницу логина")
    public LoginPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}