package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerPage {
    private WebDriver driver;

    private String datePickerInput = "#datepicker";
    private String datePickerMonth = ".ui-datepicker-month";
    private String datePickerYear = ".ui-datepicker-year";
    private String nextButton = ".ui-datepicker-next";
    private String previousButton = ".ui-datepicker-prev";
    private LocalDate currentDate;

    public DatePickerPage(WebDriver driver) {
        this.driver = driver;
    }

    public DatePickerPage clickDateInput() {
        driver.findElement(By.cssSelector(datePickerInput)).click();
        return this;
    }

    public DatePickerPage selectMonth(int destMonth) {
        int currentMonth = getCurrentMonth();

        if (currentMonth > destMonth) setPrevMonth(destMonth);
        else if (currentMonth < destMonth) setNextMonth(destMonth);
        return this;
    }

    public DatePickerPage selectDay(int destDay) {
        driver.findElement(By.xpath("//a [text()=\"" + destDay + "\"]")).click();
        return this;
    }

    public DatePickerPage selectYear(int destYear) {
        int currentYear = getCurrentYear();

        if (currentYear > destYear) setPrevYear(destYear);
        else if (currentYear < destYear) setNextYear(destYear);
        return this;
    }

    public DatePickerPage selectCurrentDate() {
        setCurrentDate();
        selectYear(currentDate.getYear());
        selectMonth(currentDate.getMonthValue());
        selectDay(currentDate.getDayOfMonth());
        return this;
    }

    public boolean isProperlyDate(){
        System.out.println(getInputDate());
        return true;
    }

    public void setCurrentDate() {
        currentDate = LocalDate.now();
    }

    public String getInputDate() {
        return driver.findElement(By.cssSelector(datePickerInput)).getAttribute("value");
    }

    public int getCurrentMonth() {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(driver.findElement(By.cssSelector(datePickerMonth)).getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        assert date != null;
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    private void setPrevMonth(int destMonth) {
        while (getCurrentMonth() != destMonth) {
            driver.findElement(By.cssSelector(previousButton)).click();
        }
    }

    private void setNextMonth(int destMonth) {
        while (getCurrentMonth() != destMonth) {
            driver.findElement(By.cssSelector(nextButton)).click();
        }
    }

    private void setPrevYear(int destYear) {
        while (getCurrentYear() != destYear) {
            driver.findElement(By.cssSelector(previousButton)).click();
        }
    }

    private void setNextYear(int destYear) {
        while (getCurrentYear() != destYear) {
            driver.findElement(By.cssSelector(nextButton)).click();
        }
    }

    private int getCurrentYear() {
        return Integer.parseInt(driver.findElement(By.cssSelector(datePickerYear)).getText());
    }
}
