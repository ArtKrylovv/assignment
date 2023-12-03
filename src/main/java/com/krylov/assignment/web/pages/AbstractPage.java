package com.krylov.assignment.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbstractPage {
    private static final int TIMEOUT = 5;
    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    public AbstractPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

    }
    public abstract boolean isOpen();
}
