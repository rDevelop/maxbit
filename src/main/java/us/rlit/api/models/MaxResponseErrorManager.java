package us.rlit.api.models;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Created by rob on 2/25/17.
 */
public abstract class MaxResponseErrorManager {
    private static final Logger logger = LoggerFactory.getLogger(MaxResponseErrorManager.class);

    protected static MaxResponseErrorHandler maxResponseError =
            new MaxResponseError("-999", "MaxBit Error", "0");

    public static MaxResponseErrorHandler getMaxResponseError() {
        return maxResponseError;
    }
    public static void setMaxResponseError(MaxResponseError error) {
        maxResponseError = error;
    }

    protected static void setMaxResponseError(HttpStatusCodeException e) {
        if( e.getStatusCode().is5xxServerError())
        {
            maxResponseError.setResponseError(String.valueOf(e.getRawStatusCode()), e.getStatusCode().getReasonPhrase(),"0");
            return;
        }
        logger.info(e.getResponseBodyAsString());
        maxResponseError = new Gson().fromJson(e.getResponseBodyAsString(), MaxResponseError.class);
        logger.info(maxResponseError.toString());
    }
}
