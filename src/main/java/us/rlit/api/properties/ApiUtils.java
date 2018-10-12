package us.rlit.api.properties;

import us.rlit.api.crytpo.ItBitSignature;
import us.rlit.api.models.MaxHttpEntity;

/**
 * Created by rob on 2/9/17.
 */
public final class ApiUtils {
    private static Long nonce = 0L;

    public static Long getNonce() {
        return nonce++;
    }

    public static synchronized String getItBitSignature(MaxHttpEntity httpEntity) {
        ItBitSignature itBitHmacPostBodyDigest = ItBitSignature.createInstance();
        String digest = itBitHmacPostBodyDigest.digestParams(httpEntity);
        return digest;
    }

    public static String getTimeStamp() {
        return new Long(System.currentTimeMillis()).toString();
    }
}
