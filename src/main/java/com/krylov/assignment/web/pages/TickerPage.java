package com.krylov.assignment.web.pages;

import com.krylov.assignment.utilities.PriceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class TickerPage extends AbstractPage {
    private static final String PARTIAL_URL = "https://www.google.com/finance/quote/";
    private static final Logger LOGGER = LogManager.getLogger(TickerPage.class);

    @FindBy(xpath = "//div[contains(text(), 'Prev close')]")
    List<WebElement> closingPriceOnChartList;

    @FindBy(xpath = "//*[text()='Financials']")
    WebElement financialsBlock;

    public TickerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(PARTIAL_URL);
    }

    public Float getClosingPriceFromChartAsFloat() {
        // wait ensures page elements are loaded
        int attempts = 1;
        while (attempts <= 10) {
            wait.until(ExpectedConditions.visibilityOf(financialsBlock));
            try {
                String rawPrice = closingPriceOnChartList.stream()
                        .filter(el -> el.isDisplayed()).findFirst().get().getText();
                return PriceParser.priceParser(rawPrice);
            } catch (StaleElementReferenceException e) {
                attempts++;
                LOGGER.info("Closing price is not retrieved");
            }
        }
        LOGGER.info("Closing price can not be retrieved");
        return null;
    }
}