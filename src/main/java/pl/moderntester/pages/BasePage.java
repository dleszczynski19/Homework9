package pl.moderntester.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.basic.FormPage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BasePage {
    public WebDriver driver;
    public String defaultDirectoryPath;
    private static Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
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

    public WebElement clickRandomElement(List<WebElement> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public void selectRandomElement(Select selectList) {
        selectList.selectByIndex(new Random().nextInt(selectList.getOptions().size()));
    }

    public ChromeOptions setDefaultDownloadDirectory(String downloadFilepath) {
        File f = new File(downloadFilepath);
        String absPath = f.getAbsolutePath();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", absPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("start-maximized");
        log.info("Default download folder set");
        return options;
    }

    public int getDirectoryFileAmount(String directoryPath) {
        defaultDirectoryPath = directoryPath;
        return Objects.requireNonNull(new File(directoryPath).list()).length;
    }

    public boolean isFileDownloaded(String directoryPath, String fileName) {
        String[] list = new File(directoryPath).list();
        return Arrays.asList(Objects.requireNonNull(list)).contains(fileName);
    }

    public void downloadFile() {
        FormPage formPage = new FormPage(driver);
        int fileAmountBefore = getDirectoryFileAmount(defaultDirectoryPath);
        formPage.downloadButton.click();
        while (fileAmountBefore + 1 != getDirectoryFileAmount(defaultDirectoryPath)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("File downloaded");
    }
}