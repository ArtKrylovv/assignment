package com.krylov.assignment.web;

import com.krylov.assignment.utilities.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        Browsers browser = Browsers.CHROME;
        LOGGER.info("Initializing local driver for: " + browser);
        initializeLocalBrowser(browser);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        LOGGER.info("Quiting the browser");
        driver.quit();
    }

    private void initializeLocalBrowser(Browsers browser) {
        switch (browser) {
            case CHROME:
                this.driver = new ChromeDriver();
                break;
            case FIREFOX:
                this.driver = new FirefoxDriver();
                break;
            case SAFARI:
                this.driver = new SafariDriver();
                break;
            default:
                LOGGER.error("Browser "+browser+" is not implemented");
                throw new IllegalArgumentException();
        }
    }
}
