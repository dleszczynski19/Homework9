package pl.moderntester.pages.others;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

public class HighSitePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(HighSitePage.class);

    @FindBy(css = "#scroll-button")
    private WebElement submitButton;

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
            return submitButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}