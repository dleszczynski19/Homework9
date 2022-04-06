package com.test.basic;

import com.configuration.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.basic.AlertsPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AlertTests extends TestBase {
    private static Logger log = LoggerFactory.getLogger(AlertTests.class);
    private String url = "https://seleniumui.moderntester.pl/alerts.php";
    String expectedLabel = "OK button pressed";


    @Test
    @DisplayName("Simple Alert Popup Test")
    public void shouldHandleSimpleAlertPopup() {
        AlertsPage alertsPage = new AlertsPage(driver);

        driver.get(url);
        assertThat("Wrong alert label", expectedLabel,
                equalTo(alertsPage
                        .handleSimpleAlert()
                        .getAlertLabel()));
        log.info(passed, passedMessage);
    }

    @Test
    @DisplayName("Prompt Alert Box Test")
    public void shouldHandlePromptAlertBox() {
        AlertsPage alertsPage = new AlertsPage(driver);

        String name = "Lord Vader";
        expectedLabel = "Hello " + name + "! How are you today?";
        driver.get(url);
        assertThat("Wrong alert label", expectedLabel,
                equalTo(alertsPage
                        .handlePromptPopup(name)
                        .getPromptLabel()));
        log.info(passed, passedMessage);
    }

    @Test
    @DisplayName("Confirm Alert Box Test")
    public void shouldHandleConfirmAlertBox() {
        AlertsPage alertsPage = new AlertsPage(driver);

        driver.get(url);
        expectedLabel = "You pressed OK!";
        assertThat("Wrong alert label", expectedLabel,
                equalTo(alertsPage
                        .handleAcceptConfirmPopup()
                        .getConfirmLabel()));
        log.info(passed, "Accept confirm pop up passed");
        expectedLabel = "You pressed Cancel!";
        assertThat("Wrong alert label", expectedLabel,
                equalTo(alertsPage
                        .handleCancelConfirmPopup()
                        .getConfirmLabel()));
        log.info(passed, "Cancel confirm pop up passed");
        log.info(passed, passedMessage);
    }

    @Test
    @DisplayName("Delayed Alert Test")
    public void shouldHandleDelayedAlert() {
        AlertsPage alertsPage = new AlertsPage(driver);

        driver.get(url);
        assertThat("Wrong alert label", expectedLabel,
                equalTo(alertsPage
                        .handleDelayedAlert()
                        .getDelayedLabel()));
        log.info(passed, passedMessage);
    }
}