package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DatePickerPage extends BasePage {
    private WebDriverWait wait;
    private static Logger log = LoggerFactory.getLogger(DatePickerPage.class);

    @FindBy(css = "#datepicker")
    private WebElement datePickerInput;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement datePickerMonth;

    @FindBy(css = ".ui-datepicker-year")
    private WebElement datePickerYear;

    @FindBy(css = ".ui-datepicker-next")
    private WebElement nextButton;

    @FindBy(css = ".ui-datepicker-prev")
    private WebElement previousButton;

    private LocalDate currentDate;
    private int destinationDay;
    private int destinationMonth;
    private int destinationYear;

    public DatePickerPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public DatePickerPage clickDateInput() {
        datePickerInput.click();
        return this;
    }

    public DatePickerPage selectCurrentDate() {
        setCurrentDate();
        selectYear(currentDate.getYear());
        selectMonth(currentDate.getMonthValue());
        selectDay(currentDate.getDayOfMonth());
        return this;
    }

    public DatePickerPage selectFirstDayNextMonth() {
        selectMonth(currentDate.getMonthValue() + 1);
        selectDay(1);
        return this;
    }

    public DatePickerPage selectLastDayOfMonthNextYear(int destinationMonth) {
        selectYear(currentDate.getYear() + 1);
        selectMonth(destinationMonth);
        selectDay(getLastDayOfMonth(destinationMonth));
        return this;
    }

    public DatePickerPage selectInputDate() {
        selectYear(destinationYear);
        selectMonth(destinationMonth);
        selectDay(destinationDay);
        return this;
    }

    public DatePickerPage selectRandomDayFromPreviousMonth() {
        selectYear(currentDate.getYear());
        selectMonth(currentDate.getMonthValue() - 1);
        selectDay(new Random().nextInt(getLastDayOfMonth(((currentDate.getMonthValue() - 1) + 1))));
        return this;
    }

    public DatePickerPage selectRandomDateLastYear() {
        selectYear(currentDate.getYear() - 1);
        int month = new Random().nextInt(12) + 1;
        selectMonth(month);
        selectDay(new Random().nextInt((getLastDayOfMonth(month) + 1)));
        return this;
    }

    public boolean isProperlyDate() {
        String inputDate = getInputDate();
        String destinationDate = parseDate(destinationMonth) + "/" + parseDate(destinationDay) + "/" + destinationYear;
        log.info("Destination date: " + destinationDate);
        log.info("Set date: " + inputDate);
        return inputDate.equals(destinationDate);
    }

    private DatePickerPage selectMonth(int destinationMonth) {
        wait.until(ExpectedConditions.visibilityOf(datePickerMonth));
        int currentMonth = getCurrentMonth();
        if (currentMonth > destinationMonth) setPrevMonth(destinationMonth);
        else if (currentMonth < destinationMonth) setNextMonth(destinationMonth);
        this.destinationMonth = destinationMonth;
        return this;
    }

    private DatePickerPage selectDay(int destinationDay) {
        wait.until(ExpectedConditions.visibilityOf(datePickerMonth));
        driver.findElement(By.xpath("//a [text()=\"" + destinationDay + "\"]")).click();
        this.destinationDay = destinationDay;
        return this;
    }

    private DatePickerPage selectYear(int destinationYear) {
        wait.until(ExpectedConditions.visibilityOf(datePickerYear));
        int currentYear = getInputYear();
        if (currentYear > destinationYear) setPrevYear(destinationYear);
        else if (currentYear < destinationYear) setNextYear(destinationYear);
        this.destinationYear = destinationYear;
        return this;
    }

    private String parseDate(int value) {
        if (value < 9) return "0" + value;
        else return String.valueOf(value);
    }

    private int getCurrentMonth() {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(datePickerMonth.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        assert date != null;
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    private int getLastDayOfMonth(int destinationMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, destinationMonth - 1);
        cal.set(Calendar.YEAR, destinationYear);
        return cal.getActualMaximum(Calendar.DATE);
    }

    private void setPrevMonth(int destMonth) {
        while (getCurrentMonth() != destMonth) {
            previousButton.click();
        }
    }

    private void setNextMonth(int destMonth) {
        while (getCurrentMonth() != destMonth) {
            nextButton.click();
        }
    }

    private void setPrevYear(int destYear) {
        while (getInputYear() != destYear) {
            previousButton.click();
        }
    }

    private void setNextYear(int destYear) {
        while (getInputYear() != destYear) {
            nextButton.click();
        }
    }

    private void setCurrentDate() {
        currentDate = LocalDate.now();
    }

    private String getInputDate() {
        return datePickerInput.getAttribute("value");
    }

    private int getInputYear() {
        return Integer.parseInt(datePickerYear.getText());
    }
}
