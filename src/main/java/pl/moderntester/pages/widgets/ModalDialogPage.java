package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.configuration.BasePage;

import java.time.Duration;
import java.util.List;

public class ModalDialogPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(ModalDialogPage.class);

    @FindBy(css = "#create-user")
    private WebElement createUserButton;

    @FindBy(css = "#name")
    private WebElement nameInput;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#password")
    private WebElement passInput;

    @FindBy(css = "#users tbody tr")
    private List<WebElement> usersList;

    public ModalDialogPage(WebDriver driver) {
        super(driver);
    }

    public ModalDialogPage createNewUser(String name, String email, String pass) {
        createUserButton.click();
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        typeName(name);
        typeEmail(email);
        typePassword(pass);
        driver.findElement(By.xpath("//button[text()=\"Create an account\"]")).click();
        log.info("User created");
        return this;
    }

    public String[] getUserData() {
        List<WebElement> userTable = usersList.get(usersList.size() - 1).findElements(By.cssSelector("td"));
        return new String[]{userTable.get(0).getText(), userTable.get(1).getText(), userTable.get(2).getText()};
    }

    private void typeName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        log.info("Name typed");
    }

    private void typeEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        log.info("Email typed");
    }

    private void typePassword(String pass) {
        passInput.clear();
        passInput.sendKeys(pass);
        log.info("Password typed");
    }
}