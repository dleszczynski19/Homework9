package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.widgets.TooltipPage;

public class TooltipTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(TooltipTest.class);

    @Test
    public void manageTooltip() {
        TooltipPage tooltipPage = new TooltipPage(driver);

        driver.get("https://seleniumui.moderntester.pl/tooltip.php");
        log.info("Tooltip text: " + tooltipPage.getTooltipLabel());
        log.info(passed, passedMessage);
    }
}