package pl.moderntester.pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.moderntester.pages.BasePage;

public class TooltipPage extends BasePage {

    @FindBy(css = "#age")
    private WebElement inputAge;

    @FindBy(css = ".ui-tooltip-content")
    private WebElement tooltipText;

    public TooltipPage(WebDriver driver) {
        super(driver);
    }

    public String getTooltipLabel() {
        inputAge.click();
        return tooltipText.getText();
    }
}