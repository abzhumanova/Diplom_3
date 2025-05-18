package org.example.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.*;

@DisplayName("Тест раздела Конструктор")
public class ConstructorTest extends TestBase {

    @Test
    @DisplayName("Переход к разделам булки/соусы/начинки")
    public void testTabs() {
        boolean ok = mainPage
                .clickConstructor()
                .clickBuns()
                .clickSauces()
                .clickFillings() != null;
        assertTrue(ok);
    }
}