package pl.moderntester.pages.configuration;

import org.openqa.selenium.WebDriver;

public class WindowHelper extends BasePage {
    private String winHandleBefore;

    public WindowHelper(WebDriver driver) {
        super(driver);
    }

    public void switchToOpenedWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void switchToDefaultWindow() {
        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    public void setWinHandleBefore(String winHandleBefore) {
        this.winHandleBefore = winHandleBefore;
    }
}
