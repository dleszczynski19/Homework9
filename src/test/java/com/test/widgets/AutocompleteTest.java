package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.widgets.AutocompletePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AutocompleteTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(AutocompleteTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    @DisplayName("Autocomplete Test")
    public void shouldManageAutocomplete(){
        AutocompletePage autocompletePage = new AutocompletePage(driver);

        driver.get("https://seleniumui.moderntester.pl/autocomplete.php");
        autocompletePage.writeText("a")
                .getOptionsList()
                .getOptionsText();
        assertThat("Wrong option value", autocompletePage.getOptionValue(), equalTo(autocompletePage.getInputValue()));
        log.info(passed, passedMessage);
    }
}
