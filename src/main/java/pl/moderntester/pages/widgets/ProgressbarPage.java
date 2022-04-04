package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.moderntester.pages.BasePage;

import java.time.Duration;

public class ProgressbarPage extends BasePage {
    private String progressbar = "#progressbar";

    public ProgressbarPage(WebDriver driver) {
        super(driver);
    }

    public ProgressbarPage waitToCompleted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector(progressbar)), "aria-valuenow", "100"));
        return this;
    }
}
