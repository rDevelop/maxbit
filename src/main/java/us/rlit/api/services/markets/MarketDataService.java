package us.rlit.api.services.markets;

import us.rlit.api.models.MaxResponseErrorHandler;
import us.rlit.api.models.itbits.ItBitOrderBook;
import us.rlit.api.models.itbits.ItBitTicker;
import us.rlit.api.models.itbits.OrderBook;
import us.rlit.api.services.BaseService;

import java.util.List;

/**
 * Created by rob on 2/22/17.
 */
public interface MarketDataService extends BaseService {
    ItBitTicker getTicker(String symbol);
    List<ItBitTicker> getTickers();
    ItBitOrderBook getOrderBook(String symbol);
    OrderBook testRestObjectForOrderBook(String ticker);
    MaxResponseErrorHandler getItBitResponseError();
}
