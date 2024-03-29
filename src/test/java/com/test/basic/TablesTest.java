package com.test.basic;

import com.configuration.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.basic.TablesPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TablesTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(TablesTest.class);

    @Test
    @DisplayName("Table Test")
    public void shouldPrintSpecificTableValue() {
        TablesPage tablesPage = new TablesPage(driver);

        driver.get("https://seleniumui.moderntester.pl/table.php");
        tablesPage
                .setAllPeaksList()
                .setMountainByFilter("Switzerland", 4000)
                .printMountain();
        assertThat("Wrong filtering", tablesPage.getFilteredListSize(), equalTo(6));
        log.info(passed, passedMessage);
    }
}