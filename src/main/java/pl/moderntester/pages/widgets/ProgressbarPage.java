package pl.moderntester.pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.moderntester.pages.configuration.BasePage;

import java.time.Duration;

public class ProgressbarPage extends BasePage {

    @FindBy(css = "#progressbar")
    private WebElement progressbar;

    public ProgressbarPage(WebDriver driver) {
        super(driver);
    }

    public ProgressbarPage waitToCompleted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeContains(progressbar, "aria-valuenow", "100"));
        return this;
    }
}