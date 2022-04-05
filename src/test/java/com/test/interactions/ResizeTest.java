package com.test.interactions;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.interactions.ResizablePage;

import static org.hamcrest.MatcherAssert.assertThat;

public class ResizeTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(ResizeTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void manageResize() {
        ResizablePage resizablePage = new ResizablePage(driver);

        driver.get("https://seleniumui.moderntester.pl/resizable.php");
        int xSize = 10;
        int ySize = 10;
        resizablePage.resizeWindow(xSize, 0);
        assertThat("Wrong resize", resizablePage.resizeCheck(xSize, 0));
        resizablePage.resizeWindow(0, ySize);
        assertThat("Wrong resize", resizablePage.resizeCheck(0, ySize));
        resizablePage.resizeWindow(xSize, ySize);
        assertThat("Wrong resize", resizablePage.resizeCheck(xSize, ySize));
        log.info(passed, passedMessage);
    }
}