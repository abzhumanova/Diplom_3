package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerConstructorPage {
    private final WebDriver driver;

    // Локаторы для разделов конструктора
    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");

    public BurgerConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти в раздел Булки")
    public BurgerConstructorPage clickBuns() {
        clickSection(bunsSection);
        return this;
    }

    @Step("Перейти в раздел Соусы")
    public BurgerConstructorPage clickSauces() {
        clickSection(saucesSection);
        return this;
    }

    @Step("Перейти в раздел Начинки")
    public BurgerConstructorPage clickFillings() {
        clickSection(fillingsSection);
        return this;
    }

    // Общий метод для клика с явным ожиданием
    private void clickSection(By sectionLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sectionLocator))
                .click();
    }
}