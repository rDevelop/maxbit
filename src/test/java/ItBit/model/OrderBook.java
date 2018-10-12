package ItBit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by rob on 4/1/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderBook {
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

    @Override
    public String toString(){
        StringBuilder buff = new StringBuilder("OrderBook: [");
        buff.append(asks);
        buff.append(bids);
        buff.append("]");
        return buff.toString();
    }
}
