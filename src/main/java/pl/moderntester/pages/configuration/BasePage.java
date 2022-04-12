package pl.moderntester.pages.configuration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    private static Logger log = LoggerFactory.getLogger(BasePage.class);
    public JavascriptExecutor js;
    public WebDriverWait wait;

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public enum Continents {
        ASIA, AFRICA, EUROPE, ANTARCTICA, NORTH_AMERICA, SOUTH_AMERICA, AUSTRALIA;
    }

    public void doScreenShot(String fileName) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("src/main/resources/files/" + fileName + ".png");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Screen shot taken");
    }

    public Dimension getWindowSize() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        int width = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerWidth")));
        int height = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerHeight")));
        return new Dimension(width, height);
    }
}