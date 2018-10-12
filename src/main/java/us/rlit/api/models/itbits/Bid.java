package us.rlit.api.models.itbits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by rob on 4/1/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bid {
    String price;
    String quantity;

    public Bid(String price, String quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
