package pl.moderntester.pages.interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

public class DraggablePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(DraggablePage.class);
    private String draggableElement = "#draggable";

    public DraggablePage(WebDriver driver) {
        super(driver);
    }

    public enum Position {
        BOTTOM_LEFT, BOTTOM_RIGHT, CENTER, UPPER_LEFT, UPPER_RIGHT,
    }

    public DraggablePage dragElementTo(Position position) {
        Point destinationPoint = getPositionToDrag(position);
        WebElement elementToDrag = driver.findElement(By.cssSelector(draggableElement));
        int xOffset = destinationPoint.getX() - elementToDrag.getLocation().getX();
        int yOffset = destinationPoint.getY() - elementToDrag.getLocation().getY();
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(elementToDrag, xOffset, yOffset)
                .perform();
        log.info("Element dragged to " + position);
        return this;
    }

    private Dimension getWindowSize() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        int width = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerWidth")));
        int height = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerHeight")));
        return new Dimension(width, height);
    }

    private Dimension getElementSize() {
        int height = Integer.parseInt(driver.findElement(By.cssSelector(draggableElement))
                .getCssValue("height").replace("px", ""));
        int width = Integer.parseInt(driver.findElement(By.cssSelector(draggableElement))
                .getCssValue("width").replace("px", ""));
        return new Dimension(width, height);
    }

    private Point getPositionToDrag(Position position) {
        Dimension window = getWindowSize();
        Dimension element = getElementSize();

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