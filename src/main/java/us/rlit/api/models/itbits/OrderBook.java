package us.rlit.api.models.itbits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by rob on 4/1/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderBook {
    private String ticker;
    private String[][] asks;
    private String[][] bids;

    public String[][] getAsks() {
        return asks;
    }

    public void setAsks(String[][] asks) {
        this.asks = asks;
    }

    public String[][] getBids() {
        return bids;
    }

    public void setBids(String[][] bids) {
        this.bids = bids;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "ticker='" + ticker + '\'' +
                ", asks=" + Arrays.toString(asks) +
                ", bids=" + Arrays.toString(bids) +
                '}';
    }
}
