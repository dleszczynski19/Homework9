package pl.moderntester.pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

public class DroppablePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(DroppablePage.class);
    private static String elementDrag = "#draggable";
    private static String elementDrop = "#droppable";

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    public DroppablePage dragAndDrop() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(By.cssSelector(elementDrag)), driver.findElement(By.cssSelector(elementDrop)))
                .perform();
        log.info("Element dragged and dropped.");
        return this;
    }

    public String getText() {
        return driver.findElement(By.cssSelector(elementDrop)).getText();
    }
}