package pl.moderntester.pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import java.util.List;
import java.util.Locale;

public class IframePage extends BasePage{
    private static Logger log = LoggerFactory.getLogger(IframePage.class);
    private static String iframe = "iframe";
    private static String firstNameInput = "#inputFirstName3";
    private static String surnameInput = "#inputSurname3";
    private static String loginInput = "#inputLogin";
    private static String passInput = "#inputPassword";
    private static String continentsSelect = "#inlineFormCustomSelectPref";
    private String experienceListRadio = "[name=\"gridRadios\"]";
    private static String signInButton = "[type=\"submit\"]";
    private static String basicButton = ".nav-ite.dropdown";

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
        driver.findElement(By.cssSelector(firstNameInput)).sendKeys(name);
        log.info("First name input filled");
        return this;
    }

    public IframePage fillSurname(String surname) {
        driver.findElement(By.cssSelector(surnameInput)).sendKeys(surname);
        log.info("Surname input filled");
        return this;
    }

    public IframePage fillLogin(String login) {
        driver.findElement(By.cssSelector(loginInput)).sendKeys(login);
        log.info("Login input filled");
        return this;
    }

    public IframePage fillPassword(String pass) {
        driver.findElement(By.cssSelector(passInput)).sendKeys(pass);
        log.info("Password input filled");
        return this;
    }

    public IframePage chooseContinent(BasePage.Continents continent){
        String text = continent.name().toLowerCase(Locale.ROOT).replaceAll("_", "-");
        Select continents = new Select(driver.findElement(By.cssSelector(continentsSelect)));
        continents.selectByValue(text);
        log.info("Continent chosen");
        return this;
    }

    public IframePage chooseRandomExperience(){
        List<WebElement> experience = driver.findElements(By.cssSelector(experienceListRadio));
        clickRandomElement(experience);
        log.info("Random experience value chosen");
        return this;
    }

    public IframePage navigateToBasic(){
        driver.findElement(By.cssSelector(basicButton));
        return this;
    }

    public String getFirstNameValue(){
        return driver.findElement(By.cssSelector(firstNameInput)).getAttribute("value");
    }

    public IframePage signIn() {
        driver.findElement(By.cssSelector(signInButton)).click();
        return this;
    }
}
