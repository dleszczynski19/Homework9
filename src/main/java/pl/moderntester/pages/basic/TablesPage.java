package pl.moderntester.pages.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TablesPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(TablesPage.class);
    private List<RowPage> allPeaksList = new ArrayList<>();
    private List<RowPage> filteredList = new ArrayList<>();

    @FindBy(css = "tbody tr")
    private List<WebElement> mountainsTable;

    public TablesPage(WebDriver driver) {
        super(driver);
    }

    public TablesPage printMountain() {
        filteredList.forEach(System.out::println);
        log.info("Mountain list printed");
        return this;
    }

    public TablesPage setMountainByFilter(String country, int height) {
        filteredList = allPeaksList.stream().filter(rowPage -> rowPage.getState().contains(country))
                .filter(rowPage -> rowPage.getHeight() > height)
                .collect(Collectors.toList());
        log.info("Mountain list filtered");
        return this;
    }

    public int getFilteredListSize() {
        return filteredList.size();
    }

    public TablesPage setAllPeaksList() {
        for (WebElement peak : mountainsTable) {
            allPeaksList.add(new RowPage(driver, peak));
        }
        return this;
    }
}