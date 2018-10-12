package us.rlit.api.models.itbits;

/**
 * Created by rob on 3/13/17.
 */
public abstract class ItBitBidAsk {

    private String price;
    private String quantity;

    public ItBitBidAsk(String price, String quantity) {
        this.price = price;
        this.quantity = quantity;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String toString() {
        return "[price: " + price + ", quantity: " + quantity + "]";
    }


}
