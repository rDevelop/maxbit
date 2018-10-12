package us.rlit.api.models.itbits;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.ArrayList;

/**
 * Created by rob on 3/12/17.
 */
public class ItBitOrderBook {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String ticker;

    private ArrayList<ArrayList<String>> bids;
    private ArrayList<ArrayList<String>> asks;
    private ArrayList<ItBitBid> itBitBids;
    private ArrayList<ItBitAsk> itBitAsks;


    public ItBitOrderBook(@JsonProperty("bids") ArrayList<ArrayList<String>> bids,
                               @JsonProperty("asks") ArrayList<ArrayList<String>> asks)
    {
        this.bids = bids;
        this.asks = asks;
    }

    public void setBidAsks() {
        itBitBids = new ArrayList<>();
        itBitAsks = new ArrayList<>();
        bids.forEach(b -> itBitBids.add(new ItBitBid(b.get(0), b.get(1))));
        asks.forEach(a -> itBitAsks.add(new ItBitAsk(a.get(0), a.get(1))));
    }

    public ArrayList<ItBitBid> getItBitBids() {
        return itBitBids;
    }

    public void setItBitBids(ArrayList<ItBitBid> itBitBids) {
        this.itBitBids = itBitBids;
    }

    public ArrayList<ItBitAsk> getItBitAsks() {
        return itBitAsks;
    }

    public void setItBitAsks(ArrayList<ItBitAsk> itBitAsks) {
        this.itBitAsks = itBitAsks;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("Ticker: " + ticker +" ");
        buffer.append("[bids: ");
        itBitBids.forEach(b -> buffer.append(b.toString() + ","));
        buffer.deleteCharAt(buffer.length() -1);
        buffer.append("], [asks: ");
        itBitAsks.forEach(a -> buffer.append(a.toString() + ","));
        buffer.deleteCharAt(buffer.length() -1);
        buffer.append("]");
        return buffer.toString();

    }
}
