package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerConstructorPage {
    private WebDriver driver;

    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");
    private final By activeSection = By.xpath("//div[contains(@class, 'current')]/span");

    public BurgerConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход в раздел 'Булки'")
    public void clickBunsSection() {
        driver.findElement(bunsSection).click();
    }

    @Step("Переход в раздел 'Соусы'")
    public void clickSaucesSection() {
        driver.findElement(saucesSection).click();
    }

    @Step("Переход в раздел 'Начинки'")
    public void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
    }

    @Step("Получение активного раздела")
    public String getActiveSection() {
        return driver.findElement(activeSection).getText();
    }
}