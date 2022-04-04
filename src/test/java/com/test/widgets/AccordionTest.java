package com.test.widgets;

import com.configuration.TestBase;
import net.joshka.junit.json.params.JsonFileSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.widgets.AccordionPage;

import javax.json.JsonObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AccordionTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(AccordionTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @ParameterizedTest(name = "Accordion Test")
    @JsonFileSource(resources = "/data/accordion.json")
    public void shouldManageAccordionSections(JsonObject json) {
        AccordionPage accordionPage = new AccordionPage(driver);

        driver.get("https://seleniumui.moderntester.pl/accordion.php");
        assertThat("Wrong label!", accordionPage.getSectionLabel(AccordionPage.Sections.FIRST),
                equalTo(json.getString("first_text")));
        log.info(passed, "First section label correct.");
        assertThat("Wrong label!", accordionPage.getSectionLabel(AccordionPage.Sections.SECOND),
                equalTo(json.getString("second_text")));
        log.info(passed, "Second section label correct.");
        assertThat("Wrong label!", accordionPage.getSectionLabel(AccordionPage.Sections.THIRD),
                equalTo(json.getString("third_text")));
        log.info(passed, "Third section label correct.");
        assertThat("Wrong label!", accordionPage.getSectionLabel(AccordionPage.Sections.FOURTH),
                equalTo(json.getString("fourth_text")));
        log.info(passed, "Fourth section label correct.");
        log.info(passed, passedMessage);
    }
}