package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.widgets.ModalDialogPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModalDialogTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(ModalDialogTest.class);

    @ParameterizedTest(name = "Test for {0}")
    @CsvFileSource(resources = "/data/modal_dialog.csv")
    public void shouldManageModalDialog(String name, String email, String pass) {
        ModalDialogPage dialogPage = new ModalDialogPage(driver);

        driver.get("https://seleniumui.moderntester.pl/modal-dialog.php");
        dialogPage.createNewUser(name, email, pass);
        String[] userData = dialogPage.getUserData();
        assertThat("Wrong value!", name, equalTo(userData[0]));
        assertThat("Wrong value!", email, equalTo(userData[1]));
        assertThat("Wrong value!", pass, equalTo(userData[2]));
        log.info(passed, passedMessage);
    }
}