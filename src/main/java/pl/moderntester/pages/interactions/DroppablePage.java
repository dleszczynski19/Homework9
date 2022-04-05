package pl.moderntester.pages.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

public class DroppablePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(DroppablePage.class);

    @FindBy(css = "#draggable")
    private WebElement elementDrag;

    @FindBy(css = "#droppable")
    private WebElement elementDrop;

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    public DroppablePage dragAndDrop() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementDrag, elementDrop)
                .perform();
        log.info("Element dragged and dropped.");
        return this;
    }

    public String getText() {
        return elementDrop.getText();
    }
}