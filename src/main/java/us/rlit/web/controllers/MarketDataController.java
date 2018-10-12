package us.rlit.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import us.rlit.api.models.itbits.ItBitOrderBook;
import us.rlit.api.models.itbits.ItBitTicker;
import us.rlit.api.models.itbits.OrderBook;
import us.rlit.api.services.markets.MarketDataService;

import java.util.List;

/**
 * Created by rob on 2/22/17.
 */
@Controller
public class MarketDataController implements ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MarketDataService marketDataService;

    @Autowired
    public void setWalletService(MarketDataService tickerService) {
        this.marketDataService = tickerService;
    }

    @RequestMapping(value = "/views/tickers", method = RequestMethod.GET)
    public String tickers( Model model) {
        logger.info("/views/tickers");
        List<ItBitTicker> itBitTickers = marketDataService.getTickers();
        if (itBitTickers != null) {
            model.addAttribute("tickers", itBitTickers);
            return "views/tickers";
        }
        return showError(model, "Error getting tickets", marketDataService);
    }

    @RequestMapping(value = "/views/ticker/{ticker}", method = RequestMethod.GET)
    public String ticker(@PathVariable String ticker, Model model) {
        logger.info("/views/ticker/{{}}", ticker);
        ItBitTicker itBitTicker = marketDataService.getTicker(ticker);
        if (itBitTicker != null) {
            model.addAttribute("ticker", itBitTicker);
            return "views/ticker";
        }
        return showError(model, "Error getting ticker for " + ticker, marketDataService);
    }

    @RequestMapping(value = "/views/orderbook/{ticker}", method = RequestMethod.GET)
    public String orderBook(@PathVariable String ticker, Model model) {
        logger.info("/views/orderbook/{{}}", ticker);
        // XBTUSD XBTSGD XBTEUR
        ItBitOrderBook orderBook = marketDataService.getOrderBook(ticker);
        if (orderBook != null ) {
            orderBook.setTicker(ticker);
            model.addAttribute("orderBook", orderBook);
            return "views/orderbook";
        }
        return showError(model, "Error getting orderbook for " + ticker, marketDataService);

    }

    @RequestMapping(value = "/views/test/{ticker}", method = RequestMethod.GET)
    public String testOrderBook(@PathVariable String ticker, Model model) {
        logger.info("/views/test/ticker/{{}}", ticker);
        OrderBook orderBook = marketDataService.testRestObjectForOrderBook(ticker);
        if (orderBook != null) {
            orderBook.setTicker(ticker);
            model.addAttribute("orderBook", orderBook);
            return "views/orderbook";
        }
        return showError(model, "Error getting test ticker for + " + ticker, marketDataService);
    }

}
