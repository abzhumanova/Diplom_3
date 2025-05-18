package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    private final By constructorBtn = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By logoutBtn = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти в Конструктор из профиля")
    public BurgerConstructorPage clickConstructor() {
        driver.findElement(constructorBtn).click();
        return new BurgerConstructorPage(driver);
    }

    @Step("Перейти на главную по логотипу из профиля")
    public BurgerConstructorPage clickLogo() {
        driver.findElement(logo).click();
        return new BurgerConstructorPage(driver);
    }

    @Step("Выйти из аккаунта")
    public MainPage clickLogout() {
        driver.findElement(logoutBtn).click();
        return new MainPage(driver);
    }
}