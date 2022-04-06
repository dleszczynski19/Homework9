package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

import java.time.Duration;
import java.util.List;

public class MenuPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(MenuPage.class);

    @FindBy(css = ".ui-menu-item:not(.ui-state-disabled)")
    private List<WebElement> optionsList;

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public MenuPage selectOption(String optionName, boolean isWaitNeeded) {
        WebElement option = findOptionByText(optionsList, optionName);
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
