package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.widgets.SelectMenuPage;

public class SelectMenuTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(SelectMenuTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void shouldManageSelectMenu() {
        SelectMenuPage selectMenuPage = new SelectMenuPage(driver);

        driver.get("https://seleniumui.moderntester.pl/selectmenu.php");
        selectMenuPage
                .selectOption(SelectMenuPage.SelectOptions.SPEED)
                .selectOption(SelectMenuPage.SelectOptions.FILE, "Some unknown file")
                .selectOption(SelectMenuPage.SelectOptions.NUMBER, 2)
                .selectOption(SelectMenuPage.SelectOptions.TITLE);
        log.info(passed, passedMessage);
    }
}
