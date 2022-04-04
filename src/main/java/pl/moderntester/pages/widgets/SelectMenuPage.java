package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import java.util.List;
import java.util.Random;

public class SelectMenuPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(SelectMenuPage.class);
    private String openOptions = ".ui-selectmenu-open .ui-menu-item:not(.ui-state-disabled";
    private List<WebElement> optionsList;

    public enum SelectOptions {
        SPEED("#speed-button"), FILE("#files-button"), NUMBER("#number-button"),
        TITLE("#salutation-button");

        String cssSelector;

        SelectOptions(String cssSelector) {
            this.cssSelector = cssSelector;
        }

        public String getCssSelector() {
            return cssSelector;
        }
    }

    public SelectMenuPage(WebDriver driver) {
        super(driver);
    }

    public SelectMenuPage selectOption(SelectOptions option) {
        driver.findElement(By.cssSelector(option.getCssSelector())).click();
        optionsList = driver.findElements(By.cssSelector(openOptions));
        optionsList.get(new Random().nextInt(optionsList.size())).click();
        log.info("Option selected");
        return this;
    }

    public SelectMenuPage selectOption(SelectOptions option, int index) {
        driver.findElement(By.cssSelector(option.getCssSelector())).click();
        optionsList = driver.findElements(By.cssSelector(openOptions));
        optionsList.get(index).click();
        log.info("Option selected");
        return this;
    }

    public SelectMenuPage selectOption(SelectOptions option, String value) {
        driver.findElement(By.cssSelector(option.getCssSelector())).click();
        optionsList = driver.findElements(By.cssSelector(openOptions));
        optionsList.stream().filter(opt -> opt.getText().equals(value))
                .reduce((f, s) -> s)
                .orElseThrow(() -> new RuntimeException("Can't find option"))
                .click();
        log.info("Option selected");
        return this;
    }
}