package us.rlit.api.services.markets;

import org.springframework.stereotype.Service;
import us.rlit.api.models.MaxResponseErrorHandler;
import us.rlit.api.models.itbits.ItBitOrderBook;
import us.rlit.api.models.itbits.ItBitTicker;
import us.rlit.api.models.itbits.OrderBook;

import java.util.List;

/**
 * Created by rob on 2/22/17.
 */
@Service
public class MarketDataServiceImpl implements MarketDataService {

    private MarketData marketData = new MarketData();

    @Override
    public ItBitTicker getTicker(String symbol) {
        return marketData.getTicker(symbol);
    }

    @Override
    public List<ItBitTicker> getTickers() {
        return marketData.getTickers();
    }

    @Override
    public MaxResponseErrorHandler getItBitResponseError() {
        return marketData.getMaxResponseError();
    }

    @Override
    public MaxResponseErrorHandler getMaxResponseError() {
        return marketData.getMaxResponseError();
    }

    @Override
    public ItBitOrderBook getOrderBook(String ticker) {
        return marketData.getOrderBook(ticker);
    }

    @Override
    public OrderBook testRestObjectForOrderBook(String ticker) {
        return marketData.testRestObjectForOrderBook(ticker);
    }

}

