package us.rlit.api.services.markets;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import us.rlit.api.models.MaxHttpRequests;
import us.rlit.api.models.MaxResponseErrorManager;
import us.rlit.api.models.itbits.ItBitOrderBook;
import us.rlit.api.models.itbits.ItBitTicker;
import us.rlit.api.models.itbits.OrderBook;

import java.util.ArrayList;
import java.util.List;

import static us.rlit.api.properties.PropertyManager.props;

/**
 * Created by rob on 2/21/17.
 */
//extends MaxResponseErrorManager
public class MarketData extends MaxResponseErrorManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String URL_PATH = "/markets";

    public String buildUrl(String request){
        String apiUrl = props().getUrl() + URL_PATH;
        if(request.length() > 0) {
            apiUrl += "/" + request;
        }
        return apiUrl ;
    }

    public ItBitTicker getTicker(String ticker) {
        String tickerUrl = ticker + "/ticker";
        try {
            logger.info(buildUrl(tickerUrl));
            return new MaxHttpRequests().getExchangeResponse(buildUrl(tickerUrl), ItBitTicker.class);
        } catch ( HttpStatusCodeException e) {
            logger.warn("Exception in getTicker: " + e.getResponseBodyAsString());
            setMaxResponseError(e);
            return null;
        }
    }

    public List<ItBitTicker> getTickers() {
        List<ItBitTicker> tickers = new ArrayList<>(3);
        tickers.add(0, getTicker("XBTUSD"));
        tickers.add(1, getTicker("XBTSGD"));
        tickers.add(2, getTicker("XBTEUR"));
        return tickers;
    }


    public ItBitOrderBook getOrderBook(String ticker) {
        String url = ticker + "/order_book";
        MaxHttpRequests maxHttpRequests = new MaxHttpRequests();

        String orderBook = null;
        try {
            orderBook =  maxHttpRequests.getExchangeResponse(buildUrl(url), String.class);
        } catch ( HttpStatusCodeException e) {
            logger.warn("Exception in getOrderBook: " + e.getResponseBodyAsString());
            setMaxResponseError(e);
            return null;
        }
        ItBitOrderBook itBitOrderBook = new Gson().fromJson(orderBook, ItBitOrderBook.class);
        itBitOrderBook.setTicker(ticker);
        itBitOrderBook.setBidAsks();
        logger.info(itBitOrderBook.toString());
        return itBitOrderBook;

    }


    public OrderBook testRestObjectForOrderBook(String ticker) {
        String url = ticker + "/order_book";
        try {
            RestTemplate restTemplate = new RestTemplate();
            //String result = restTemplate.getForObject(buildUrl(url), String.class);

            OrderBook orderBook = restTemplate.getForObject(buildUrl(url), OrderBook.class);
            return orderBook;

        } catch (HttpStatusCodeException e) {
            logger.warn("Exception in testRestObjectForOrderBook: " + e.getResponseBodyAsString());
            setMaxResponseError(e);
        }
        return null;
    }
//
//    public List<ItBitWallet> getAllWallets() {
//
//        MaxHttpRequests maxHttpRequests = new MaxHttpRequests();
//        List<String> itBitWallets = null;
//        try {
//            itBitWallets = maxHttpRequests.getExchangeListResponse(buildUrl(""), String.class);
//        } catch ( HttpStatusCodeException e) {
//            setMaxResponseError(e);
//            return null;
//        }
//        ItBitWallet[] jsonToObject = new Gson().fromJson(itBitWallets.toString(), ItBitWallet[].class);
//        return Arrays.asList(jsonToObject);
//    }



}
//        MaxHttpResponse httpResult = new MaxHttpRequests().getHttpResult(apiUrl);
//        return genericMapper(httpResult.getCode(), httpResult.getResponse().toString(), ItBitTicker.class);



