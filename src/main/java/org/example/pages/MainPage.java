package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы верхнего меню
    private final By constructorTab = By.xpath("//p[text()='Конструктор']");
    private final By orderFeedTab = By.xpath("//p[text()='Лента Заказов']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By logo = By.cssSelector("div.AppHeader_header__logo__2D0X2");

    // Локаторы конструктора
    private final By bunsSection = By.xpath("//span[text()='Булки']/..");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/..");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Локаторы ингредиентов
    private final By ingredientItems = By.cssSelector("div.BurgerIngredients_ingredients__menuContainer__Xu3Mo");
    private final By ingredientCards = By.cssSelector("div.IngredientCard_card__1UuT4");

    // Локаторы конструктора бургера
    private final By burgerConstructor = By.cssSelector("section.BurgerConstructor_basket__29Cd7");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    // Локатор активного раздела
    private final By activeTab = By.cssSelector("div.tab_tab_type_current__2BEPc");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    // Основные действия с элементами верхнего меню
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    public void clickConstructorTab() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorTab)).click();
    }

    public void clickOrderFeedTab() {
        wait.until(ExpectedConditions.elementToBeClickable(orderFeedTab)).click();
    }

    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    // Действия с разделами конструктора
    public void selectBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
    }

    public void selectSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }

    public void selectFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }

    // Проверки состояний
    public boolean isBunsSectionActive() {
        WebElement activeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return activeElement.getText().contains("Булки");
    }

    public boolean isSaucesSectionActive() {
        WebElement activeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return activeElement.getText().contains("Соусы");
    }

    public boolean isFillingsSectionActive() {
        WebElement activeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return activeElement.getText().contains("Начинки");
    }

    public boolean isAuthorized() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Работа с ингредиентами
    public List<WebElement> getIngredientCards() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ingredientCards));
    }

    // Дополнительные полезные методы
    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorTab)).isDisplayed() &&
                    wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}