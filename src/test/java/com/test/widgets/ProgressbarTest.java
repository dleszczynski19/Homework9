package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.widgets.ProgressbarPage;

public class ProgressbarTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(ProgressbarPage.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void shouldManageProgressbar() {
        ProgressbarPage progressbarPage = new ProgressbarPage(driver);

        driver.get("https://seleniumui.moderntester.pl/progressbar.php");
        progressbarPage.waitToCompleted();
        log.info(passed, passedMessage);
    }
}
