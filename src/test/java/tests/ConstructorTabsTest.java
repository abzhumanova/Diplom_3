package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTabsTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void setUpPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Пользователь может перейти на вкладку 'Соусы'")
    @Description("Проверка, что при клике на 'Соусы' активируется соответствующая вкладка")
    public void userCanSwitchToSauceTabTest() {
        mainPage.clickSauceTab();
        assertEquals("Соусы", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Пользователь может перейти на вкладку 'Начинки'")
    @Description("Проверка, что при клике на 'Начинки' активируется соответствующая вкладка")
    public void userCanSwitchToFillingTabTest() {
        mainPage.clickFillingTab();
        assertEquals("Начинки", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Пользователь может вернуться на вкладку 'Булки'")
    @Description("Проверка, что при клике на 'Булки' активируется соответствующая вкладка")
    public void userCanSwitchToBunTabTest() {
        mainPage.clickFillingTab();
        mainPage.clickBunTab();
        assertEquals("Булки", mainPage.getActiveTabText());
    }
}