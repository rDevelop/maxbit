package us.rlit.api.models.itbits;

/**
 * Created by rob on 10/17/16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

//@Entity
public class ItBitAccountBalance {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;

    private final double availableBalance;
    private final double totalBalance;
    private final String currency;

    public ItBitAccountBalance(@JsonProperty("availableBalance") double availableBalance,
                               @JsonProperty("totalBalance") double totalBalance,
                               @JsonProperty("currency") String currency) {

        this.availableBalance = availableBalance;
        this.totalBalance = totalBalance;
        this.currency = currency;
    }

//    public BigDecimal getAvailableBalance() {
//
//        return availableBalance.setScale(6, RoundingMode.HALF_UP);
//    }

    public double getAvailableBalance() {

        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public String getCurrency() {

        return currency;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("ItBitAccountBalance [availableBalance=");
        builder.append(availableBalance);
        builder.append(", totalBalance=");
        builder.append(totalBalance);
        builder.append(", currency=");
        builder.append(currency);
        builder.append("]");
        return builder.toString();
    }

}
