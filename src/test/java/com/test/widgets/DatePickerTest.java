package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.widgets.DatePickerPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class DatePickerTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(DatePickerTest.class);

    @Test
    public void shouldManageDatePicker() {
        DatePickerPage datePickerPage = new DatePickerPage(driver);

        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
        assertThat("Wrong date", datePickerPage
                .clickDateInput()
                .selectCurrentDate()
                .isProperlyDate());
        log.info(passed, "Current date - selected");
        assertThat("Wrong date", datePickerPage
                .clickDateInput()
                .selectFirstDayNextMonth()
                .isProperlyDate());
        log.info(passed, "First day next month - selected");
        assertThat("Wrong date", datePickerPage
                .clickDateInput()
                .selectLastDayOfMonthNextYear(1)
                .isProperlyDate());
        log.info(passed, "Last day January next year - selected");
        assertThat("Wrong date", datePickerPage
                .clickDateInput()
                .selectInputDate()
                .isProperlyDate());
        log.info(passed, "Same date again - selected");
        assertThat("Wrong date", datePickerPage
                .clickDateInput()
                .selectRandomDayFromPreviousMonth()
                .isProperlyDate());
        log.info(passed, "Random day from previous month - selected");
        assertThat("Wrong date", datePickerPage
                .clickDateInput()
                .selectRandomDateLastYear()
                .isProperlyDate());
        log.info(passed, "Random date from last year - selected");
        log.info(passed, passedMessage);
    }
}