package pl.moderntester.pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortablePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(SortablePage.class);

    @FindBy(css = "#sortable li")
    private List<WebElement> sortableList;

    private int[] numberArray = new int[]{1, 2, 3, 4, 5, 6, 7};

    public SortablePage(WebDriver driver) {
        super(driver);
    }

    public SortablePage moveItemsToMatchArray() {
        for (int i = 0; i < numberArray.length; i++) {
            moveItem(numberArray[i], i);
        }
        log.info("Items moved");
        return this;
    }

    public SortablePage moveItem(int targetIndex, int destIndex) {
        Actions action = new Actions(driver);
        WebElement target = driver.findElement(By.xpath("//li[text()=\"Item " + targetIndex + "\"]"));
        WebElement dest = sortableList.get(destIndex);
        action.click(target).clickAndHold()
                .moveToElement(dest)
                .release()
                .perform();
        return this;
    }

    public SortablePage shuffleArray() {
        Random rand = new Random();

        for (int i = 0; i < numberArray.length; i++) {
            int randomIndexToSwap = rand.nextInt(numberArray.length);
            int temp = numberArray[randomIndexToSwap];
            numberArray[randomIndexToSwap] = numberArray[i];
            numberArray[i] = temp;
        }
        log.info("Array shuffled");
        return this;
    }

    public boolean checkItemOrder() {
        return Arrays.equals(getSortableList(), getNumberArray());
    }

    private int[] getSortableList() {
        int[] listTOString = new int[sortableList.size()];

        for (int i = 0; i < sortableList.size(); i++) {
            listTOString[i] = Integer.parseInt(sortableList.get(i).getText().replaceAll("Item ", ""));
        }
        return listTOString;
    }

    private int[] getNumberArray() {
        return numberArray;
    }
}