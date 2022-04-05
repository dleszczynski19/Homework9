package pl.moderntester.pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WindowsPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(WindowsPage.class);

    @FindBy(css = "#newBrowserWindow")
    private WebElement browserWindowButton;

    @FindBy(css = "#newMessageWindow")
    private WebElement messageWindowButton;

    @FindBy(css = "#newBrowserTab")
    private WebElement browserTabButton;

    private String winHandleBefore;

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    public WindowsPage manageNewBrowserWindow() {
        winHandleBefore = driver.getWindowHandle();
        browserWindowButton.click();
        switchToOpenedWindow();
        executeTableTest();
        switchToDefaultWindow();
        log.info("New browser window managed");
        return this;
    }

    public WindowsPage manageNewMessageWindow() {
        winHandleBefore = driver.getWindowHandle();
        messageWindowButton.click();
        switchToOpenedWindow();
        log.info(driver.findElement(By.cssSelector("body")).getText());
        switchToDefaultWindow();
        log.info("New message window managed");
        return this;
    }

    public WindowsPage manageNewBrowserTab() {
        winHandleBefore = driver.getWindowHandle();
        browserTabButton.click();
        switchToOpenedWindow();
        executeTableTest();
        switchToDefaultWindow();
        log.info("New browser tab managed");
        return this;
    }

    private void executeTableTest() {
        TablesPage tablesPage = new TablesPage(driver);

        tablesPage.setTableList()
                .setMountainByFilter("Switzerland", 4000)
                .printMountain();
        assertThat("Wrong filtering", tablesPage.getFilteredListSize(), equalTo(6));
        log.info("Table test executed");
    }

    private void switchToOpenedWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    private void switchToDefaultWindow() {
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }
}