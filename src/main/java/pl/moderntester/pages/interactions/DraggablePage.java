package pl.moderntester.pages.interactions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.WebElementHelper;

public class DraggablePage extends WebElementHelper {
    private static Logger log = LoggerFactory.getLogger(DraggablePage.class);

    @FindBy(css = "#draggable")
    private WebElement draggableElement;

    public DraggablePage(WebDriver driver) {
        super(driver);
    }

    public enum Position {
        BOTTOM_LEFT, BOTTOM_RIGHT, CENTER, UPPER_LEFT, UPPER_RIGHT,
    }

    public DraggablePage dragElementTo(Position position) {
        Point destinationPoint = getPositionToDrag(position);
        int xOffset = destinationPoint.getX() - draggableElement.getLocation().getX();
        int yOffset = destinationPoint.getY() - draggableElement.getLocation().getY();
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggableElement, xOffset, yOffset)
                .perform();
        log.info("Element dragged to " + position);
        return this;
    }

    private Point getPositionToDrag(Position position) {
        Dimension window = getWindowSize();
        Dimension element = getElementSize(draggableElement);

        switch (position) {
            case BOTTOM_LEFT:
                return new Point(0, window.height - element.height);
            case BOTTOM_RIGHT:
                return new Point(window.width - element.width, window.height - element.height);
            case CENTER:
                return new Point(Math.floorDiv(window.width, 2) - Math.floorDiv(element.width, 2),
                        Math.floorDiv(window.height, 2) - Math.floorDiv(element.height, 2));
            case UPPER_LEFT:
                return new Point(0, 0);
            case UPPER_RIGHT:
                return new Point(window.width - element.width, 0);
            default:
                throw new IllegalArgumentException("Illegal position value " + position);
        }
    }
}