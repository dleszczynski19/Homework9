package pl.moderntester.pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class FormPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(FormPage.class);
    private String firstNameInput = "#inputFirstName3";
    private String lastNameInput = "#inputLastName3";
    private String emailInput = "#inputEmail3";
    private String ageInput = "#inputAge3";
    private String additionalInput = "#additionalInformations";
    private String sexListRadio = "[name=\"gridRadiosSex\"]";
    private String maleRadio = "#gridRadiosMale";
    private String femaleRadio = "gridRadiosFemale";
    private String otherRadio = "gridRadiosOther";
    private String experienceListRadio = "[name=\"gridRadiosExperience\"]";
    private String experienceDefaultRadio = "#gridRadios"; // + id
    private String professionListCheckbox = "[name=\"gridCheckboxProfession\"]";
    private String manualCheckbox = "#gridCheckManulTester";
    private String automationCheckbox = "#gridCheckAutomationTester";
    private String otherCheckbox = "#gridCheckOther";
    private String continentsSelect = "#selectContinents";
    private String commandsSelect = "#selectSeleniumCommands";
    private String chooseFileInput = "#chooseFile";
    private String validatorMessage = "#validator-message";
    private String signInButton = "[type=\"submit\"]";

    public enum Sex {
        MALE, FEMALE, OTHER
    }

    public enum Profession {
        MANUAL, AUTOMATION, OTHER
    }

    public enum Commands {
        BROWSER, NAVIGATION, SWITCH, WAIT, WEB_ELEMENT
    }

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage fillFirstName(String name) {
        driver.findElement(By.cssSelector(firstNameInput)).sendKeys(name);
        log.info("First name input filled");
        return this;
    }

    public FormPage fillLastName(String lastName) {
        driver.findElement(By.cssSelector(lastNameInput)).sendKeys(lastName);
        log.info("Last name input filled");
        return this;
    }

    public FormPage fillEmail(String email) {
        driver.findElement(By.cssSelector(emailInput)).sendKeys(email);
        log.info("Email input filled");
        return this;
    }

    public FormPage chooseSex(Sex sex) {
        switch (sex) {
            case MALE:
                driver.findElement(By.cssSelector(maleRadio)).click();
                break;
            case FEMALE:
                driver.findElement(By.cssSelector(femaleRadio)).click();
                break;
            case OTHER:
                driver.findElement(By.cssSelector(otherRadio)).click();
                break;
        }
        log.info("Sex chosen");
        return this;
    }

    public FormPage chooseRandomSex() {
        List<WebElement> list = driver.findElements(By.cssSelector(sexListRadio));
        clickRandomElement(list).click();
        log.info("Random sex chosen");
        return this;
    }

    public FormPage fillAge(int age) {
        driver.findElement(By.cssSelector(ageInput)).clear();
        driver.findElement(By.cssSelector(ageInput)).sendKeys(String.valueOf(age));
        log.info("Age input filled");
        return this;
    }

    public FormPage chooseRandomExperience() {
        List<WebElement> list = driver.findElements(By.cssSelector(experienceListRadio));
        clickRandomElement(list).click();
        log.info("Random experience chosen");
        return this;
    }

    public FormPage chooseRandomContinent() {
        Select continents = new Select(driver.findElement(By.cssSelector(continentsSelect)));
        selectRandomElement(continents);
        log.info("Random continent chosen");
        return this;
    }

    public FormPage chooseContinent(BasePage.Continents continent) {
        String text = continent.name().toLowerCase(Locale.ROOT).replaceAll("_", "-");
        Select continents = new Select(driver.findElement(By.cssSelector(continentsSelect)));
        continents.selectByValue(text);
        log.info("Continent chosen");
        return this;
    }

    public FormPage chooseProfession(Profession profession) {
        switch (profession) {
            case MANUAL:
                driver.findElement(By.cssSelector(manualCheckbox)).click();
                break;
            case AUTOMATION:
                driver.findElement(By.cssSelector(automationCheckbox)).click();
                break;
            case OTHER:
                driver.findElement(By.cssSelector(otherCheckbox)).click();
                break;
        }
        log.info("Profession selected");
        return this;
    }

    public FormPage sendFile(String filePath) {
        File file = new File(filePath);
        driver.findElement(By.cssSelector(chooseFileInput)).sendKeys(file.getAbsolutePath());
        log.info("File send");
        return this;
    }

    public FormPage signIn() {
        driver.findElement(By.cssSelector(signInButton)).click();
        return this;
    }

    public FormPage selectCommands(Commands[] commands) {
        Actions actions = new Actions(driver);
        Select seleniumCommands = new Select(driver.findElement(By.cssSelector(commandsSelect)));
        for (int i = 0; i < commands.length; i++) {
            seleniumCommands.selectByValue(commands[i].name().toLowerCase(Locale.ROOT)
                    .replaceAll("_", "") + "-commands");
            actions.keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
        }
        log.info("Commands selected");
        return this;
    }

    public String getValidatorMsg() {
        return driver.findElement(By.cssSelector(validatorMessage)).getText();
    }
}