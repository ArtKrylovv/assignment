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
        searchFieldList.stream().filter(el-> el.isDisplayed())
                .findFirst().get().sendKeys(query);
    }

    public TickerPage selectFromSearchOptionsList(String dataSymbol) {
        // wait ensures options list is loaded
        wait.until(ExpectedConditions.visibilityOf(tabAll));
        WebElement option = searchOptionsList.stream()
                    .filter(el -> el.getAttribute("data-symbol").equals(dataSymbol)).findFirst().get();
        // wait fixes intermittent missed click for Safari
        wait.until(ExpectedConditions.elementToBeClickable(option));
        option.click();
        return new TickerPage(driver);
    }
}
