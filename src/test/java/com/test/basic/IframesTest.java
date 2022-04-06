package com.test.basic;

import com.configuration.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import pl.moderntester.pages.configuration.BasePage;
import pl.moderntester.pages.basic.IframePage;

import static org.hamcrest.MatcherAssert.assertThat;

public class IframesTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(IframesTest.class);
    private static Marker passed = MarkerFactory.getMarker("PASSED");

    @ParameterizedTest()
    @CsvFileSource(resources = "/data/iframe.csv")
    @DisplayName("Iframe Test")
    public void shouldManageIframe(String name, String surname, String login, String pass, BasePage.Continents continent) {
        IframePage iframePage = new IframePage(driver);

        driver.get("https://seleniumui.moderntester.pl/iframes.php");
        iframePage.switchToIframe(1)
                .fillFirstName(name)
                .fillSurname(surname)
                .switchToDefaultIframe()
                .switchToIframe(2)
                .fillLogin(login)
                .fillPassword(pass)
                .chooseContinent(continent)
                .chooseRandomExperience()
                .switchToDefaultIframe()
                .navigateToBasic()
                .switchToIframe(1)
                .signIn();
        assertThat("Input should be empty", iframePage.getFirstNameValue().isEmpty());
        log.info(passed, passedMessage);
    }
}
