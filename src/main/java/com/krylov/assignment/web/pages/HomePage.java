package com.krylov.assignment.web.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class HomePage extends AbstractPage{
    private static final String URL = "https://www.google.com/finance/";
    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//*[@aria-label='Search for stocks, ETFs & more']")
    List<WebElement> searchFieldList;

    @FindBy(xpath="//*[@role='option']")
    List<WebElement> searchOptionsList;

    @FindBy(xpath = "//*[@data-tab-id='all']")
    WebElement tabAll;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().equals(URL);
    }

    public void open(){
        driver.get(URL);
    }

    public void inputSearchQuery(String query){
        for(WebElement el: searchFieldList){
            if(el.isDisplayed()){
                el.sendKeys(query);
            }
        }
    }

    public TickerPage selectFromSearchOptionsList(String dataSymbol) {
        wait.until(ExpectedConditions.visibilityOf(tabAll));
        for(WebElement el: searchOptionsList){
            if(el.getAttribute("data-symbol").equals(dataSymbol)){
                el.click();
                return new TickerPage(driver);
            }
        }
        LOGGER.error("Desired ticker not found in Search Options List");
        return null;
    }
}
