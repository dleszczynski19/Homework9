package pl.moderntester.pages.others;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.WebElementHelper;

public class HighSitePage extends WebElementHelper {
    private static Logger log = LoggerFactory.getLogger(HighSitePage.class);

    @FindBy(css = "#scroll-button")
    private WebElement submitButton;

    public HighSitePage(WebDriver driver) {
        super(driver);
    }

    public HighSitePage scrollToButton() {
        scrollToElement(submitButton);
        return this;
    }

    public boolean isButtonVisible() {
        return isElementVisible(submitButton);
    }
}