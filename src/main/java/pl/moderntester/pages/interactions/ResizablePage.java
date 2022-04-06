package pl.moderntester.pages.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

public class ResizablePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(ResizablePage.class);
    private int[] oldDimension = new int[2];
    private int[] actualDimension = new int[2];

    @FindBy(css = ".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se")
    private WebElement resizeIcon;

    public ResizablePage(WebDriver driver) {
        super(driver);
    }

    public ResizablePage resizeWindow(int pxHorizontal, int pxVertical) {
        Actions actions = new Actions(driver);
        oldDimension = new int[]{resizeIcon.getLocation().getX(), resizeIcon.getLocation().getY()};
        actions.clickAndHold(resizeIcon).moveByOffset(pxHorizontal + 18, pxVertical + 18).perform();
        actualDimension = new int[]{resizeIcon.getLocation().getX(), resizeIcon.getLocation().getY()};
        log.info("Element resized");
        return this;
    }

    public boolean resizeCheck(int subX, int subY) {
        return (actualDimension[0] - oldDimension[0] == subX) && (actualDimension[1] - oldDimension[1] == subY);
    }
}