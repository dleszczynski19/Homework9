package com.test.interactions;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.interactions.DraggablePage;

public class DragTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(DragTest.class);

    @Test
    public void manageDrag() {
        DraggablePage draggablePage = new DraggablePage(driver);

        driver.get("https://seleniumui.moderntester.pl/draggable.php");
        draggablePage
                .dragElementTo(DraggablePage.Position.UPPER_RIGHT)
                .dragElementTo(DraggablePage.Position.BOTTOM_RIGHT)
                .dragElementTo(DraggablePage.Position.CENTER)
                .dragElementTo(DraggablePage.Position.BOTTOM_LEFT);
        log.info(passed, passedMessage);
    }
}