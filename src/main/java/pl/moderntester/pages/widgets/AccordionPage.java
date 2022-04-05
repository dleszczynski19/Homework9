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

public class AccordionPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(AccordionPage.class);
    private String sectionActive = ".ui-accordion-header.ui-corner-top.ui-state-default.ui-accordion-icons.ui-accordion-header-active.ui-state-active";

    public enum Sections {
        FIRST("#ui-id-1", "#ui-id-2"), SECOND("#ui-id-3", "#ui-id-4"),
        THIRD("#ui-id-5", "#ui-id-6"), FOURTH("#ui-id-7", "#ui-id-8");

        private String sectionCss;
        private String textCss;

        Sections(String sectionCss, String textCss) {
            this.sectionCss = sectionCss;
            this.textCss = textCss;
        }

        public String getSectionCss() {
            return sectionCss;
        }

        public String getTextCss() {
            return textCss;
        }
    }

    public AccordionPage(WebDriver driver) {
        super(driver);
    }

    public String getSectionLabel(Sections section) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textCss = driver.findElement(By.cssSelector(section.getTextCss()));
        if (section.equals(Sections.FIRST)) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(section.getSectionCss() + sectionActive)));
                return textCss.getText();
            } catch (Exception e) {
                log.warn("Can't find text for section one");
            }
        }
        driver.findElement(By.cssSelector(section.getSectionCss())).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(textCss.findElements(By.xpath(".//*"))));
        return driver.findElement(By.cssSelector(section.getTextCss())).getText();
    }
}
