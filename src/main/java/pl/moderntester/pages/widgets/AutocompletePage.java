package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AutocompletePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(AutocompletePage.class);
    private String inputSearch = "#search";
    private String optionsUl = "#ui-id-1";
    private String listLi = ".ui-menu-item-wrapper";
    private List<WebElement> optionsList;
    private String optionValue;
    private String inputValue;

    public AutocompletePage(WebDriver driver) {
        super(driver);
    }

    public AutocompletePage writeText(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector(inputSearch)).sendKeys(text);
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector(optionsUl)), "style", "block"));
        log.info("Text written");
        return this;
    }

    public AutocompletePage getOptionsList() {
        optionsList = driver.findElements(By.cssSelector(listLi));
        for (WebElement element : optionsList) {
            log.info("Option: " + element.getText());
        }
        return this;
    }

    public AutocompletePage getOptionsText() {
        int randomIndex = new Random().nextInt(optionsList.size());
        optionValue = optionsList.get(randomIndex).getText();
        optionsList.get(randomIndex).click();
        inputValue = driver.findElement(By.cssSelector(inputSearch)).getAttribute("value");
        return this;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getInputValue() {
        return inputValue;
    }
}
