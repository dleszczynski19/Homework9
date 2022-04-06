package pl.moderntester.pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.basic.models.Mountain;
import pl.moderntester.pages.configuration.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TablesPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(TablesPage.class);
    private final List<Mountain> listMountain = new ArrayList<>();
    private List<Mountain> filteredList = new ArrayList<>();

    @FindBy(css = "tbody tr")
    private List<WebElement> tableCss;

    public TablesPage(WebDriver driver) {
        super(driver);
    }

    public Mountain addMountain(WebElement element) {
        List<WebElement> rowList = element.findElements(By.cssSelector("td"));
        log.info("Mountain with pick " + rowList.get(0).getText() + " added");
        return new Mountain(Integer.parseInt(element.findElement(By.cssSelector("th")).getText()), rowList.get(0).getText(),
                rowList.get(1).getText(), rowList.get(2).getText(), Integer.parseInt(rowList.get(3).getText()));
    }

    public TablesPage setTableList() {
        for (WebElement webElement : tableCss) {
            listMountain.add(addMountain(webElement));
        }
        log.info("Table list set");
        return this;
    }

    public TablesPage printMountain() {
        filteredList.forEach(System.out::println);
        log.info("Mountain list printed");
        return this;
    }

    public TablesPage setMountainByFilter(String country, int height) {
        filteredList = listMountain.stream().filter(mountain -> mountain.getState().contains(country))
                .filter(mountain -> mountain.getHeight() > height)
                .collect(Collectors.toList());
        log.info("Mountain list filtered");
        return this;
    }

    public int getFilteredListSize() {
        return filteredList.size();
    }
}