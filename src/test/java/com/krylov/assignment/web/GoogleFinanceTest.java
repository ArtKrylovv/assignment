package com.krylov.assignment.web;

import com.krylov.assignment.web.pages.HomePage;
import com.krylov.assignment.web.pages.TickerPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class GoogleFinanceTest extends AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(GoogleFinanceTest.class);

    public void closingPriceTest()  {
        String ticker = "GOOG";

        HomePage page = new HomePage(driver);
        LOGGER.info("Opening home page");
        page.open();
        Assert.assertTrue(page.isOpen(), "Home page is not opened");
        LOGGER.info("Inputting search query: "+ticker);
        page.inputSearchQuery(ticker);
        LOGGER.info("Selecting ticker: "+ticker+" from options list");
        TickerPage tickerPage = page.selectFromSearchOptionsList(ticker);
        Assert.assertTrue(tickerPage.isOpen(), "Ticker page is not opened");
        LOGGER.info("Getting previous closing price for: "+ticker);
        Float actClosingPrice = tickerPage.getClosingPriceFromChartAsFloat();
        LOGGER.info("Retrieved closing price: "+actClosingPrice);
        Assert.assertTrue(actClosingPrice>=0, "Invalid closing price+ "+actClosingPrice);
    }
}
