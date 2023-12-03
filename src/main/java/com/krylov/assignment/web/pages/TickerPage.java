package com.krylov.assignment.web.pages;

import com.krylov.assignment.utilities.PriceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TickerPage extends AbstractPage {
    private static final String PARTIAL_URL = "https://www.google.com/finance/quote/";
    private static final Logger LOGGER = LogManager.getLogger(TickerPage.class);

    @FindBy(xpath = "//div[contains(text(), 'Prev close ')]")
    List<WebElement> closingPriceOnChartList;

    public TickerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(PARTIAL_URL);
    }

    public Float getClosingPriceFromChartAsFloat() {
        int attempt = 1;
        while (attempt <= 10) {
            try {
                for (WebElement el : closingPriceOnChartList) {
                    if (el.isDisplayed()) {
                        String closingPrice = el.getText();
                        return PriceParser.priceParser(closingPrice);
                    }
                }
            } catch (StaleElementReferenceException e) {
                LOGGER.error("Price is not retrieved on " + attempt + " attempt");
                attempt++;
            }
        }
        LOGGER.error("Price can not be retrieved");
        return null;
    }
}