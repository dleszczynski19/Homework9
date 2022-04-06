package com.test.interactions;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.interactions.SortablePage;

import static org.hamcrest.MatcherAssert.assertThat;

public class SortTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(SortTest.class);

    @Test
    public void manageSort() {
        SortablePage sortablePage = new SortablePage(driver);

        driver.get("https://seleniumui.moderntester.pl/sortable.php");
        sortablePage
                .shuffleArray()
                .moveItemsToMatchArray();
        assertThat("Wrong order!", sortablePage.checkItemOrder());
        log.info(passed, passedMessage);
    }
}