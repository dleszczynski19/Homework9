package pl.moderntester.pages.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

import java.util.List;
import java.util.Locale;

public class IframePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(IframePage.class);

    private String iframe = "iframe";

    @FindBy(css = "#inputFirstName3")
    private WebElement firstNameInput;

    @FindBy(css = "#inputSurname3")
    private WebElement surnameInput;

    @FindBy(css = "#inputLogin")
    private WebElement loginInput;

    @FindBy(css = "#inputPassword")
    private WebElement passInput;

    @FindBy(css = "#inlineFormCustomSelectPref")
    private WebElement continentsSelect;

    @FindBy(css = "[name=\"gridRadios\"]")
    private List<WebElement> experienceListRadio;

    @FindBy(css = "[type=\"submit\"]")
    private WebElement signInButton;

    @FindBy(css = ".nav-ite.dropdown")
    private WebElement basicButton;

    public IframePage(WebDriver driver) {
        super(driver);
    }

    public IframePage switchToIframe(int index) {
        driver.switchTo().frame(iframe + index);
        log.info("Iframe switched to one with index: " + index);
        return this;
    }

    public IframePage switchToDefaultIframe() {
        driver.switchTo().defaultContent();
        log.info("Iframe switched to default");
        return this;
    }

    public IframePage fillFirstName(String name) {
        firstNameInput.sendKeys(name);
        log.info("First name input filled");
        return this;
    }

    public IframePage fillSurname(String surname) {
        surnameInput.sendKeys(surname);
        log.info("Surname input filled");
        return this;
    }

    public IframePage fillLogin(String login) {
        loginInput.sendKeys(login);
        log.info("Login input filled");
        return this;
    }

    public IframePage fillPassword(String pass) {
        passInput.sendKeys(pass);
        log.info("Password input filled");
        return this;
    }

    public IframePage chooseContinent(BasePage.Continents continent) {
        String text = continent.name().toLowerCase(Locale.ROOT).replaceAll("_", "-");
        Select continents = new Select(continentsSelect);
        continents.selectByValue(text);
        log.info("Continent chosen");
        return this;
    }

    public IframePage chooseRandomExperience() {
        clickRandomElement(experienceListRadio);
        log.info("Random experience value chosen");
        return this;
    }

    public IframePage navigateToBasic() {
        basicButton.click();
        return this;
    }

    public String getFirstNameValue() {
        return firstNameInput.getAttribute("value");
    }

    public IframePage signIn() {
        signInButton.click();
        return this;
    }
}
