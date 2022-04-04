package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import java.time.Duration;
import java.util.List;

public class ModalDialogPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(ModalDialogPage.class);
    private String createUserButton = "#create-user";
    private String nameInput = "#name";
    private String emailInput = "#email";
    private String passInput = "#password";
    private String usersList = "#users tbody tr";

    public ModalDialogPage(WebDriver driver) {
        super(driver);
    }

    public ModalDialogPage createNewUser(String name, String email, String pass) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector(createUserButton)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(nameInput))));
        typeName(name);
        typeEmail(email);
        typePassword(pass);
        driver.findElement(By.xpath("//button[text()=\"Create an account\"]")).click();
        log.info("User created");
        return this;
    }

    public String[] getUserData() {
        List<WebElement> list = driver.findElements(By.cssSelector(usersList));
        List<WebElement> userTable = list.get(list.size() - 1).findElements(By.cssSelector("td"));
        return new String[]{userTable.get(0).getText(), userTable.get(1).getText(), userTable.get(2).getText()};
    }

    private void typeName(String name) {
        driver.findElement(By.cssSelector(nameInput)).clear();
        driver.findElement(By.cssSelector(nameInput)).sendKeys(name);
        log.info("Name typed");
    }

    private void typeEmail(String email) {
        driver.findElement(By.cssSelector(emailInput)).clear();
        driver.findElement(By.cssSelector(emailInput)).sendKeys(email);
        log.info("Email typed");
    }

    private void typePassword(String pass) {
        driver.findElement(By.cssSelector(passInput)).clear();
        driver.findElement(By.cssSelector(passInput)).sendKeys(pass);
        log.info("Password typed");
    }

    private void clearFields() {
        driver.findElement(By.cssSelector(emailInput)).clear();
        driver.findElement(By.cssSelector(passInput)).clear();
        log.info("Fields cleared");
    }
}
