package org.example.tests;

import org.example.pages.BurgerConstructorPage;
import org.example.pages.MainPage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends TestBase {

    @Test
    public void testSwitchBetweenConstructorTabs() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        BurgerConstructorPage constructorPage = new BurgerConstructorPage(driver);

        constructorPage.clickSauces();
        assertEquals("Соусы", constructorPage.getActiveSection());

        constructorPage.clickFillings();
        assertEquals("Начинки", constructorPage.getActiveSection());

        constructorPage.clickBuns();
        assertEquals("Булки", constructorPage.getActiveSection());
    }
}