package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By profileLink = By.xpath("//a[@href='/account/profile']");
    private final By constructorLink = By.xpath("//p[text()='Конструктор']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Выход из аккаунта")
    public void logout() {
        driver.findElement(logoutButton).click();
    }

    @Step("Переход в конструктор")
    public void navigateToConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("Проверка видимости кнопки выхода")
    public boolean isLogoutButtonVisible() {
        return driver.findElement(logoutButton).isDisplayed();
    }
}