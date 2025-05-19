package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    // Локаторы
    private final By logoutButton = By.xpath("//button[contains(text(),'Выход')]");
    private final By constructorLink = By.xpath("//a[contains(text(),'Конструктор')]");
    private final By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    public void clickLogo() {
        driver.findElement(logo).click();
    }
}