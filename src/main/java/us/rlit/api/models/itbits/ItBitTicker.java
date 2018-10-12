package us.rlit.api.models.itbits;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rob on 2/21/17.
 */
public class ItBitTicker {

    private String pair;
    private String bid;
    private String bidAmt;
    private String ask;
    private String askAmt;
    private String lastPrice;
    private String lastAmt;
    private String volume24h;
    private String volumeToday;
    private String high24h;
    private String low24h;
    private String highToday;
    private String lowToday;
    private String openToday;
    private String vwapToday;
    private String vwap24h;
    private String serverTimeUTC;

    public ItBitTicker(
            @JsonProperty("pair") String pair,
            @JsonProperty("bid") String bid,
            @JsonProperty("bidAmt") String bidAmt,
            @JsonProperty("ask") String ask,
            @JsonProperty("askAmt") String askAmt,
            @JsonProperty("lastPrice") String lastPrice,
            @JsonProperty("lastAmt") String lasAmt,
            @JsonProperty("volume24h") String volume24h,
            @JsonProperty("volumeToday") String volumeToday,
            @JsonProperty("high24h") String high24,
            @JsonProperty("low24h") String low24,
            @JsonProperty("highToday") String highToday,
            @JsonProperty("lowToday") String lowToday,
            @JsonProperty("openToday") String openToday,
            @JsonProperty("vwapToday") String vwapToday,
            @JsonProperty("vwap24h") String vwap24h,
            @JsonProperty("serverTimeUTC") String serverTimeUTC) {

        this.pair = pair;
        this.bid = bid;
        this.bidAmt = bidAmt;
        this.ask = ask;
        this.askAmt = askAmt;
        this.lastPrice = lastPrice;
        this.lastAmt = lastAmt;
        this.volume24h = volume24h;
        this.volumeToday = volumeToday;
        this.high24h = high24;
        this.low24h = low24;
        this.highToday = highToday;
        this.lowToday = lowToday;
        this.openToday = openToday;
        this.vwapToday = vwapToday;
        this.vwap24h = vwap24h;
        this.serverTimeUTC = serverTimeUTC;

    }
    
    public String getPair() {
        return pair;
    }

    public String getBid() {
        return bid;
    }

    public String getBidAmt() {
        return bidAmt;
    }

    public String getAsk() {
        return ask;
    }

    public String getAskAmt() {
        return askAmt;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public String getLastAmt() {
        return lastAmt;
    }

    public String getVolume24h() {
        return volume24h;
    }

    public String getVolumeToday() {
        return volumeToday;
    }

    public String getHigh24h() {
        return high24h;
    }

    public String getLow24h() {
        return low24h;
    }

    public String getHighToday() {
        return highToday;
    }

    public String getLowToday() {
        return lowToday;
    }

    public String getOpenToday() {
        return openToday;
    }

    public String getVwapToday() {
        return vwapToday;
    }

    public String getVwap24h() {
        return vwap24h;
    }

    public String getServerTimeUTC() {
        return serverTimeUTC;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ItBitTicker [pair=");
        builder.append(pair);
        builder.append(", bid=");
        builder.append(bid);
        builder.append("]");
        return  builder.toString();
    }
}