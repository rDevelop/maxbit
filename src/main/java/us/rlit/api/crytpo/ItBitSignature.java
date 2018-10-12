package us.rlit.api.crytpo; /**
 * Created by rob on 7/15/15.
 */


import net.iharder.Base64;
import us.rlit.api.models.MaxHttpEntity;

import javax.crypto.Mac;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static us.rlit.api.properties.PropertyManager.props;


public class ItBitSignature extends BaseParamsDigest {
    private static final String FIELD_SEPARATOR = "\",\"";
    private final String apiKey;

    /**
     * Constructor
     *
     * @param secretKeyBase64
     * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded key is invalid).
     */
    private ItBitSignature(String apiKey, String secretKeyBase64) {

        super(secretKeyBase64, HMAC_SHA_512);
        this.apiKey = apiKey;
    }

    public static ItBitSignature createInstance() {
        return props().getSecretKey() == null ? null : new ItBitSignature(props().getClientKey(), props().getSecretKey());
    }

    public String digestParams(MaxHttpEntity maxHttpEntity) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Illegal algorithm for post body digest. Check the implementation.");
        }

        String currentNonce = maxHttpEntity.getNonce();
        String currentTimestamp = maxHttpEntity.getTimestamp();

        // only POST requests will have a non-null request body.
        String requestBody = maxHttpEntity.getBody();
        if (requestBody == null) {
            requestBody = "";
        } else {
            requestBody = requestBody.replace("\"", "\\\"");
        }

        String verb = maxHttpEntity.getVerb().trim();
        String invocationUrl = maxHttpEntity.getUrl().trim();
        String message = new StringBuilder("[\"").
                append(verb).
                append(FIELD_SEPARATOR).
                append(invocationUrl).
                append(FIELD_SEPARATOR).
                append(requestBody).
                append(FIELD_SEPARATOR).
                append(currentNonce).
                append(FIELD_SEPARATOR).
                append(currentTimestamp).
                append("\"]").
                toString();

        md.update((currentNonce + message).getBytes());
        BigInteger hash = new BigInteger(md.digest());

        Mac mac512 = getMac();
        mac512.update(invocationUrl.getBytes());
        mac512.update(hash.toByteArray());

        return apiKey + ":" + Base64.encodeBytes((new BigInteger(mac512.doFinal())).toByteArray());
    }
}
