package pl.moderntester.pages.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

public class AlertsPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(AlertsPage.class);

    @FindBy(css = "#simple-alert")
    private WebElement simpleAlertButton;

    @FindBy(css = "#prompt-alert")
    private WebElement promptPopupButton;

    @FindBy(css = "#confirm-alert")
    private WebElement confirmPopupButton;

    @FindBy(css = "#delayed-alert")
    private WebElement delayedPopupButton;

    @FindBy(css = "#simple-alert-label")
    private WebElement simpleAlertLabel;

    @FindBy(css = "#prompt-label")
    private WebElement promptAlertLabel;

    @FindBy(css = "#confirm-label")
    private WebElement confirmAlertLabel;

    @FindBy(css = "#delayed-alert-label")
    private WebElement delayedAlertLabel;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public AlertsPage handleSimpleAlert() {
        simpleAlertButton.click();
        driver.switchTo().alert().accept();
        log.info("Simple alert handled");
        return this;
    }

    public AlertsPage handlePromptPopup(String value) {
        promptPopupButton.click();
        driver.switchTo().alert().sendKeys(value);
        driver.switchTo().alert().accept();
        log.info("Prompt popup handled");
        return this;
    }

    public AlertsPage handleAcceptConfirmPopup() {
        confirmPopupButton.click();
        driver.switchTo().alert().accept();
        log.info("Accept confirm alert handled");
        return this;
    }

    public AlertsPage handleCancelConfirmPopup() {
        confirmPopupButton.click();
        driver.switchTo().alert().dismiss();
        log.info("Dismiss confirm alert handled");
        return this;
    }

    public AlertsPage handleDelayedAlert() {
        delayedPopupButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        log.info("Delayed alert handled");
        return this;
    }

    public String getAlertLabel() {
        return simpleAlertLabel.getText();
    }

    public String getPromptLabel() {
        return promptAlertLabel.getText();
    }

    public String getConfirmLabel() {
        return confirmAlertLabel.getText();
    }

    public String getDelayedLabel() {
        return delayedAlertLabel.getText();
    }
}
