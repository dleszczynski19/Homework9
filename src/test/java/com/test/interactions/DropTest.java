package com.test.interactions;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.interactions.DroppablePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DropTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(DragTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void manageDrop() {
        DroppablePage interactionsPage = new DroppablePage(driver);
        String expectedText = "Dropped!";

        driver.get("https://seleniumui.moderntester.pl/droppable.php");
        assertThat("Wrong alert label", expectedText,
                equalTo(interactionsPage.dragAndDrop()
                        .getText()));
        log.info(passed, passedMessage);
    }
}
