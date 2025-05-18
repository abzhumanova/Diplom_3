package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/* Раздел «Конструктор»
Проверь, что работают переходы к разделам:
«Булки»,
«Соусы»,
«Начинки».*/
public class ConstructorTabsTest extends BaseTest {

    private HomePage homePage;

    @Before
    public void setUpPage() {
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test
    @DisplayName("User can switch to 'Sauces' tab")
    @Description("Проверка, что при клике на 'Соусы' активируется соответствующая вкладка")
    public void userCanSwitchToSauceTabTest() {
        homePage.clickSauceTab();
        assertEquals("Соусы", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("User can switch to 'Fillings' tab")
    @Description("Проверка, что при клике на 'Начинки' активируется соответствующая вкладка")
    public void userCanSwitchToFillingTabTest() {
        homePage.clickFillingTab();
        assertEquals("Начинки", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("User can switch back to 'Buns' tab")
    @Description("Проверка, что при клике на 'Булки' активируется соответствующая вкладка")
    public void userCanSwitchToBunTabTest() {
        homePage.clickFillingTab();
        homePage.clickBunTab();
        assertEquals("Булки", homePage.getActiveTabText());
    }
}