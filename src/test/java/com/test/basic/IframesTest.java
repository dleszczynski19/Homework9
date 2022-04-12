package com.test.basic;

import com.configuration.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.basic.IframePage;
import pl.moderntester.pages.configuration.BasePage;

import static org.hamcrest.MatcherAssert.assertThat;

public class IframesTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(IframesTest.class);

    @ParameterizedTest()
    @CsvFileSource(resources = "/data/iframe.csv")
    @DisplayName("Iframe Test")
    public void shouldManageIframe(String name, String surname, String login, String pass, BasePage.Continents continent) {
        IframePage iframePage = new IframePage(driver);

        driver.get("https://seleniumui.moderntester.pl/iframes.php");
        iframePage
                .switchToFirstIframe()
                .fillFirstName(name)
                .fillSurname(surname)
                .switchToDefaultIframe();
        iframePage
                .switchToSecondIframe()
                .fillLogin(login)
                .fillPassword(pass)
                .chooseContinent(continent)
                .chooseRandomExperience()
                .switchToDefaultIframe();
        iframePage
                .navigateToBasic()
                .switchToFirstIframe()
                .signIn();
        assertThat("Input should be empty", iframePage.getFirstNameValue().isEmpty());
        log.info(passed, passedMessage);
    }
}