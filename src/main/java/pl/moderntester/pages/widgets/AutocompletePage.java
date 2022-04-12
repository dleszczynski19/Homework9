package pl.moderntester.pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

import java.util.List;
import java.util.Random;

public class AutocompletePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(AutocompletePage.class);

    @FindBy(css = "#search")
    private WebElement inputSearch;

    @FindBy(css = "#ui-id-1")
    private WebElement optionsUl;

    @FindBy(css = ".ui-menu-item-wrapper")
    private List<WebElement> optionsList;

    private String optionValue;
    private String inputValue;

    public AutocompletePage(WebDriver driver) {
        super(driver);
    }

    public AutocompletePage writeText(String text) {
        inputSearch.sendKeys(text);
        wait.until(ExpectedConditions.attributeContains(optionsUl, "style", "block"));
        log.info("Text written");
        return this;
    }

    public AutocompletePage getOptionsList() {
        for (WebElement element : optionsList) {
            log.info("Option: " + element.getText());
        }
        return this;
    }

    public AutocompletePage getOptionsText() {
        int randomIndex = new Random().nextInt(optionsList.size());
        optionValue = optionsList.get(randomIndex).getText();
        optionsList.get(randomIndex).click();
        inputValue = inputSearch.getAttribute("value");
        return this;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getInputValue() {
        return inputValue;
    }
}