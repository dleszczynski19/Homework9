package com.test.basic;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.basic.WindowsPage;

public class WindowsTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(WindowsTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void shouldSwitchWindowsAndTab() {
        WindowsPage windowsPage = new WindowsPage(driver);

        log.info("Start  Test");
        driver.get("https://seleniumui.moderntester.pl/windows-tabs.php");
        windowsPage.manageNewBrowserWindow()
                .manageNewMessageWindow()
                .manageNewBrowserTab();
        log.info(passed, passedMessage);
    }
}