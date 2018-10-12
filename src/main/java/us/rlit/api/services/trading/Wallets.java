package us.rlit.api.services.trading;
/**
 * Created with IntelliJ IDEA.
 * User: rob
 * Date: 2/2/14
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;
import us.rlit.api.models.MaxHttpRequests;
import us.rlit.api.models.MaxResponseErrorManager;
import us.rlit.api.models.itbits.ItBitWallet;

import java.util.Arrays;
import java.util.List;

import static us.rlit.api.properties.PropertyManager.props;

//extends MaxResponseErrorManager
public class Wallets extends MaxResponseErrorManager {
    private static Logger logger = LoggerFactory.getLogger(Wallets.class);

    private String URL_PATH = "/wallets";

    public String buildUrl(String request){
        String apiUrl = props().getUrl() + URL_PATH;
        if(request.length() > 0) {
            apiUrl += "/" + request;
        }
        return apiUrl + "?userId=" + props().getUserId();
    }

    public List<ItBitWallet> getAllWallets() {
        List<String> itBitWallets = null;
        try {
            itBitWallets = new MaxHttpRequests().getExchangeListResponse(buildUrl(""), String.class);
        } catch ( HttpStatusCodeException e) {
            setMaxResponseError(e);
            return null;
        }
        logger.info(buildUrl(""));
        ItBitWallet[] jsonToObject = new Gson().fromJson(itBitWallets.toString(), ItBitWallet[].class);
        return Arrays.asList(jsonToObject);
    }

    public ItBitWallet getWallet(String id) {
        try {
            return new MaxHttpRequests().getExchangeResponse(buildUrl(id), ItBitWallet.class);
        } catch (HttpStatusCodeException e) {
            logger.warn("Exception in getWallet: " + e.getResponseBodyAsString());
            setMaxResponseError(e);
            return null;
        }
    }

    public ItBitWallet getBalance(String id, String currencyCode) {
        String balanceRequest = id + "/balances/" + currencyCode;
        return new MaxHttpRequests().getExchangeResponse(buildUrl(balanceRequest), ItBitWallet.class);
    }

//    public void httpMethod(){
//        MaxHttpResponse httpResult = new MaxHttpRequests().getHttpResult(buildUrl(""));
//        return genericListMapper(httpResult.getCode(), httpResult.getResponse().toString(), ItBitWallet[].class);
//    }
}
