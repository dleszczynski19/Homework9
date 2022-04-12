package pl.moderntester.pages.configuration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class WebElementHelper extends IframeHelper {
    private static Logger log = LoggerFactory.getLogger(WebElementHelper.class);

    public WebElementHelper(WebDriver driver) {
        super(driver);
    }

    public WebElement clickRandomElement(List<WebElement> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public void selectRandomElement(Select selectList) {
        selectList.selectByIndex(new Random().nextInt(selectList.getOptions().size()));
    }

    public Dimension getElementSize(WebElement element) {
        int height = Integer.parseInt(element.getCssValue("height")
                .replace("px", ""));
        int width = Integer.parseInt(element.getCssValue("width")
                .replace("px", ""));
        return new Dimension(width, height);
    }

    public WebElement findOptionByText(List<WebElement> optionsList, String optionName) {
        return optionsList.stream()
                .filter(opt -> opt.getText().equals(optionName))
                .reduce((f, s) -> s)
                .orElseThrow(() -> new RuntimeException("Can't find option"));
    }

    public WebElementHelper scrollToElement(WebElement element) {
        while (!isElementVisible(element)) {
            js.executeScript("window.scrollBy(0,250)", "");
        }
        log.info("Scrolled to element: " + element);
        return this;
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}