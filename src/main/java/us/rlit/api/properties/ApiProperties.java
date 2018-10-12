package us.rlit.api.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by rob on 11/6/16.
 */
@Component
@ConfigurationProperties
public class ApiProperties {

    @Value("${api.url}")
    private String url;

    @Value("${api.userId}")
    private String userId;

    @Value("${api.clientKey}")
    private String clientKey;

    @Value("${api.secretKey}")
    private String secretKey;

    public String getUrl() {
        return url;
    }

    public String getUserId() {
        return userId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append("'url: " + url + "', " +
                "'userId: " + userId + "', " +
                "'clientKey: " + clientKey + "', " +
                "'secretKey: " + secretKey + "']");
        return sb.toString();
    }
}
