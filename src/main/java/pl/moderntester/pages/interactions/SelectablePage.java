package pl.moderntester.pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

public class SelectablePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(SelectablePage.class);
    private String itemListOl = "#selectable li";
    private String selectedItem = ".ui-widget-content.ui-selectee.ui-selected";
    private String itemXpath;
    private String feedbackLabel = "#feedback";


    public SelectablePage(WebDriver driver) {
        super(driver);
    }

    public SelectablePage selectItem(int index) {
        Actions actions = new Actions(driver);
        itemXpath = "//li [text()=\"Item " + index + "\"]";
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(driver.findElement(By.xpath(itemXpath)))
                .build()
                .perform();
        log.info("Commands selected");
        return this;
    }

    public String getFeedback() {
        return driver.findElement(By.cssSelector(feedbackLabel)).getText();
    }
}
