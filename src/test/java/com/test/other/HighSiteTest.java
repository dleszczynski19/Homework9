package com.test.other;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.others.HighSitePage;

import static org.hamcrest.MatcherAssert.assertThat;

public class HighSiteTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(HighSiteTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void shouldScrollToButton() {
        HighSitePage highSitePage = new HighSitePage(driver);

        driver.get("https://seleniumui.moderntester.pl/high-site.php");
        highSitePage.scrollToButton()
                .doScreenShot("scroll_to");
        assertThat("Button is no visible", highSitePage.isButtonVisible());
        log.info(passed, passedMessage);
    }
}