package com.test.basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DownloadFileTest {
    public WebDriver driver;
    private static Logger log = LoggerFactory.getLogger(DownloadFileTest.class);
    public static String downloadPath = "src/main/resources/files";

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        log.info("Driver setup properly");
    }

    @BeforeEach
    void initializeDriver() {
        BasePage basePage = new BasePage();
        ChromeOptions options = basePage.setDefaultDownloadDirectory(downloadPath);
        driver = new ChromeDriver(options);
        log.info("Driver initialized properly");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info("Driver closed properly");
    }

    @Test
    public void shouldDownloadFileToSpecificDirectory() {
        BasePage basePage = new BasePage(driver);

        driver.get("https://seleniumui.moderntester.pl/form.php");
        int fileAmountBefore = basePage.getDirectoryFileAmount(downloadPath);
        basePage.downloadFile();
        assertThat("Wrong amount of files!", fileAmountBefore + 1,
                equalTo(basePage.getDirectoryFileAmount(downloadPath)));
        assertThat("File doesn't exist", basePage.isFileDownloaded(downloadPath, "test-file-to-download.xlsx"));
        log.info("Test Passed!");
    }
}
