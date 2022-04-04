package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.moderntester.pages.BasePage;

import java.time.Duration;

public class TooltipPage extends BasePage {
    private String inputAge = "#age";
    private String tooltipText = ".ui-tooltip-content";

    public TooltipPage(WebDriver driver) {
        super(driver);
    }

    public String getTooltipLabel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector(inputAge)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(tooltipText))));
        return driver.findElement(By.cssSelector(tooltipText)).getText();
    }
}