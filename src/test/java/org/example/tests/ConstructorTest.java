package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.example.driver.DriverFactory;
import org.example.pages.BurgerConstructorPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@DisplayName("Тесты конструктора бургеров")
public class ConstructorTest {
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testNavigateToBunsSection() {
        BurgerConstructorPage constructorPage = new BurgerConstructorPage(DriverFactory.getDriver());
        constructorPage.clickBunsSection();
        assertEquals("Булки", constructorPage.getActiveSection());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        BurgerConstructorPage constructorPage = new BurgerConstructorPage(DriverFactory.getDriver());
        constructorPage.clickSaucesSection();
        assertEquals("Соусы", constructorPage.getActiveSection());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        BurgerConstructorPage constructorPage = new BurgerConstructorPage(DriverFactory.getDriver());
        constructorPage.clickFillingsSection();
        assertEquals("Начинки", constructorPage.getActiveSection());
    }
}