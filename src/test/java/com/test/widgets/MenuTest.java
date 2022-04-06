package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.widgets.MenuPage;

public class MenuTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(MenuTest.class);

    @Test
    public void shouldManageMenu() {
        MenuPage menuPage = new MenuPage(driver);

        driver.get("https://seleniumui.moderntester.pl/menu-item.php");
        menuPage.selectOption("Music", true)
                .selectOption("Jazz", true)
                .selectOption("Modern", false);
        log.info(passed, passedMessage);
    }
}