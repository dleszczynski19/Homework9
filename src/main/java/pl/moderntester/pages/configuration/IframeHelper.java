package pl.moderntester.pages.configuration;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.basic.IframePage;

public class IframeHelper extends BasePage {
    private static Logger log = LoggerFactory.getLogger(IframePage.class);

    public IframeHelper(WebDriver driver) {
        super(driver);
    }

    public IframeHelper switchToIframe(String frameName) {
        driver.switchTo().frame(frameName);
        log.info("Iframe switched to: " + frameName);
        return this;
    }

    public IframeHelper switchToDefaultIframe() {
        driver.switchTo().defaultContent();
        log.info("Iframe switched to default");
        return this;
    }
}
