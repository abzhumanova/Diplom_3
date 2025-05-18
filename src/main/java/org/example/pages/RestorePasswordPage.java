package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private WebDriver driver;

    private final By emailField = By.xpath("//input[@name='name']");
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Нажатие кнопки восстановления")
    public void clickRestoreButton() {
        driver.findElement(restoreButton).click();
    }

    @Step("Переход на страницу входа")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}