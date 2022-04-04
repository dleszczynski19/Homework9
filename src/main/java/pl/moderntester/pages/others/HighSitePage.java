package pl.moderntester.pages.others;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

public class HighSitePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(HighSitePage.class);
    private String submitButton = "#scroll-button";

    public HighSitePage(WebDriver driver) {
        super(driver);
    }

    public HighSitePage scrollToButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        while (!isButtonVisible()) {
            js.executeScript("window.scrollBy(0,250)", "");
        }
        log.info("Scrolled to button");
        return this;
    }

    public boolean isButtonVisible() {
        try {
            return driver.findElement(By.cssSelector(submitButton)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}