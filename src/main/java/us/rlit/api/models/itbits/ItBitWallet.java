package us.rlit.api.models.itbits;

/**
 * Created by rob on 10/17/16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

//@Entity
public class ItBitWallet {
    @Id
    private String id;

    @Version
    private Integer version;
    private String userId;
    private String name;
    private ItBitAccountBalance[] balances;

    public ItBitWallet(@JsonProperty("id") String id, @JsonProperty("userId") String userId,
                       @JsonProperty("name") String name, @JsonProperty("balances") ItBitAccountBalance[] balances) {

        this.id = id;
        this.userId = userId;
        this.name = name;
        this.balances = balances;
        this.version = 1;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;

    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;

    }

    public String getName() {

        return name;
    }


    public ItBitAccountBalance[] getBalances() {
        return balances;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("ItBitWallet [id=");
        builder.append(id);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

}
