package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerConstructorPage {
    private final WebDriver driver;
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    public BurgerConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти в раздел Булки")
    public BurgerConstructorPage clickBuns() {
        driver.findElement(bunsTab).click();
        return this;
    }

    @Step("Перейти в раздел Соусы")
    public BurgerConstructorPage clickSauces() {
        driver.findElement(saucesTab).click();
        return this;
    }

    @Step("Перейти в раздел Начинки")
    public BurgerConstructorPage clickFillings() {
        driver.findElement(fillingsTab).click();
        return this;
    }
}