package pl.moderntester.pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AlertsPage {
    private WebDriver driver;
    private static Logger log = LoggerFactory.getLogger(AlertsPage.class);
    private String simpleAlertButton = "#simple-alert";
    private String promptPopupButton = "#prompt-alert";
    private String confirmPopupButton = "#confirm-alert";
    private String delayedPopupButton = "#delayed-alert";
    private String simpleAlertLabel = "#simple-alert-label";
    private String promptAlertLabel = "#prompt-label";
    private String confirmAlertLabel = "#confirm-label";
    private String delayedAlertLabel = "#delayed-alert-label";

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    public AlertsPage handleSimpleAlert() {
        driver.findElement(By.cssSelector(simpleAlertButton)).click();
        driver.switchTo().alert().accept();
        log.info("Simple alert handled");
        return this;
    }

    public AlertsPage handlePromptPopup(String value) {
        driver.findElement(By.cssSelector(promptPopupButton)).click();
        driver.switchTo().alert().sendKeys(value);
        driver.switchTo().alert().accept();
        log.info("Prompt popup handled");
        return this;
    }

    public AlertsPage handleAcceptConfirmPopup() {
        driver.findElement(By.cssSelector(confirmPopupButton)).click();
        driver.switchTo().alert().accept();
        log.info("Accept confirm alert handled");
        return this;
    }

    public AlertsPage handleCancelConfirmPopup() {
        driver.findElement(By.cssSelector(confirmPopupButton)).click();
        driver.switchTo().alert().dismiss();
        log.info("Dismiss confirm alert handled");
        return this;
    }

    public AlertsPage handleDelayedAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector(delayedPopupButton)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        log.info("Delayed alert handled");
        return this;
    }

    public String getAlertLabel() {
        return driver.findElement(By.cssSelector(simpleAlertLabel)).getText();
    }

    public String getPromptLabel() {
        return driver.findElement(By.cssSelector(promptAlertLabel)).getText();
    }

    public String getConfirmLabel() {
        return driver.findElement(By.cssSelector(confirmAlertLabel)).getText();
    }

    public String getDelayedLabel() {
        return driver.findElement(By.cssSelector(delayedAlertLabel)).getText();
    }
}
