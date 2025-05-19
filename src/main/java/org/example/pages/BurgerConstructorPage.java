package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerConstructorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    public BurgerConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Перейти в раздел 'Булки'")
    public BurgerConstructorPage clickBuns() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
        return this;
    }

    @Step("Перейти в раздел 'Соусы'")
    public BurgerConstructorPage clickSauces() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
        return this;
    }

    @Step("Перейти в раздел 'Начинки'")
    public BurgerConstructorPage clickFillings() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
        return this;
    }

    @Step("Проверить активный раздел")
    public String getActiveSection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText();
    }
}