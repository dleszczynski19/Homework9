package com.test.interactions;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.interactions.SelectablePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SelectTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(SelectTest.class);

    @Test
    public void manageSelect() {
        SelectablePage selectablePage = new SelectablePage(driver);

        driver.get("https://seleniumui.moderntester.pl/selectable.php");
        selectablePage
                .selectItem(1)
                .selectItem(3)
                .selectItem(4);
        assertThat(selectablePage.getFeedback(), equalTo("You've selected: #1 #3 #4."));
        log.info(passed, passedMessage);
    }
}