package pl.moderntester.pages.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pl.moderntester.pages.configuration.BasePage;

public class RowPage extends BasePage {

    public RowPage(WebDriver driver, WebElement row) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(row), this);
    }

    @FindBy(css = "th")
    private WebElement rank;

    @FindBy(xpath =  "td[1]")
    private WebElement peak;

    @FindBy(xpath =  "td[2]")
    private WebElement mountainRange;

    @FindBy(xpath =  "td[3]")
    private WebElement state;

    @FindBy(xpath =  "td[4]")
    private WebElement height;

    public String getName(){
        return peak.getText();
    }

    public String getState(){
        return state.getText();
    }

    public int getHeight(){
        return Integer.parseInt(height.getText());
    }

    @Override
    public String toString() {
        return "Rank: " + rank.getText() + "\nPeak: " + getName() + "\nMountain range: " + mountainRange.getText() + "\n--------------------";
    }
}