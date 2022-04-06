package com.test.basic;

import com.configuration.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.basic.FormPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(FormTest.class);

    @ParameterizedTest(name = "Test for - {0} {1}")
    @CsvFileSource(resources = "/data/form.csv")
    @DisplayName("Properly Fill Form Test")
    public void shouldFillFormWithSuccess(String name, String surname, String email, int age, FormPage.Profession profession) {
        FormPage formPage = new FormPage(driver);

        driver.get("https://seleniumui.moderntester.pl/form.php");
        formPage.fillFirstName(name)
                .fillLastName(surname)
                .fillEmail(email)
                .chooseRandomSex()
                .fillAge(age)
                .chooseRandomExperience()
                .chooseProfession(profession)
                .chooseRandomContinent()
                .selectCommands(new FormPage.Commands[]{FormPage.Commands.SWITCH, FormPage.Commands.WAIT})
                .sendFile("src/main/resources/files/file.txt")
                .signIn();
        assertThat("Wrong validator message", formPage.getValidatorMsg(), equalTo("Form send with success"));
        log.info(passed, passedMessage);
    }
}