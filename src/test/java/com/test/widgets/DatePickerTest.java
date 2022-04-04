package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.widgets.DatePickerPage;

import java.util.Random;

public class DatePickerTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(DatePickerTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @Test
    public void test() {
        DatePickerPage datePickerPage = new DatePickerPage(driver);

        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
        datePickerPage.clickDateInput()
                .selectCurrentDate()
                .isProperlyDate();
        datePickerPage.clickDateInput()
                .selectMonth(datePickerPage.getCurrentMonth() + 1)
                .selectDay(1)
                .isProperlyDate();
        datePickerPage.clickDateInput()
                .selectYear(2023)
                .selectMonth(1)
                .selectDay(31)
                .isProperlyDate();
        datePickerPage.clickDateInput()
                .selectYear(2022)
                .selectMonth(12)
                .selectDay(new Random().nextInt(31))
                .isProperlyDate();
        datePickerPage.clickDateInput()
                .selectYear(2021)
                .selectMonth(new Random().nextInt(12))
                .selectDay(new Random().nextInt(31))
                .isProperlyDate();
    }
}