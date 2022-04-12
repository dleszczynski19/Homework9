package pl.moderntester.pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.WindowHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WindowsPage extends WindowHelper {
    private static Logger log = LoggerFactory.getLogger(WindowsPage.class);

    @FindBy(css = "#newBrowserWindow")
    private WebElement browserWindowButton;

    @FindBy(css = "#newMessageWindow")
    private WebElement messageWindowButton;

    @FindBy(css = "#newBrowserTab")
    private WebElement browserTabButton;

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    public WindowsPage manageNewBrowserWindow() {
        setWinHandleBefore(driver.getWindowHandle());
        browserWindowButton.click();
        switchToOpenedWindow();
        executeTableTest();
        switchToDefaultWindow();
        log.info("New browser window managed");
        return this;
    }

    public WindowsPage manageNewMessageWindow() {
        setWinHandleBefore(driver.getWindowHandle());
        messageWindowButton.click();
        switchToOpenedWindow();
        log.info(driver.findElement(By.cssSelector("body")).getText());
        switchToDefaultWindow();
        log.info("New message window managed");
        return this;
    }

    public WindowsPage manageNewBrowserTab() {
        setWinHandleBefore(driver.getWindowHandle());
        browserTabButton.click();
        switchToOpenedWindow();
        executeTableTest();
        switchToDefaultWindow();
        log.info("New browser tab managed");
        return this;
    }

    private void executeTableTest() {
        TablesPage tablesPage = new TablesPage(driver);

        tablesPage
                .setAllPeaksList()
                .setMountainByFilter("Switzerland", 4000)
                .printMountain();
        assertThat("Wrong filtering", tablesPage.getFilteredListSize(), equalTo(6));
        log.info("Table test executed");
    }
}