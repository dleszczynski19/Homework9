package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class MenuPage {
    private WebDriver driver;
    private static Logger log = LoggerFactory.getLogger(MenuPage.class);
    private String optionsList = ".ui-menu-item:not(.ui-state-disabled)";

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public MenuPage selectOption(String optionName, boolean isWaitNeeded) {
        List<WebElement> options = driver.findElements(By.cssSelector(optionsList));
        WebElement option = options.stream().filter(opt -> opt.getText().equals(optionName))
                .reduce((f, s) -> s)
                .orElseThrow(() -> new RuntimeException("Can't find option"));
        option.click();
        if (isWaitNeeded) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.attributeToBe(option.findElement(By.xpath(".//following-sibling::ul")),
                    "aria-expanded", "true"));
        }
        log.info("Option selected");
        return this;
    }
}
