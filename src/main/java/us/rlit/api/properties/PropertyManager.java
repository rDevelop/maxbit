package us.rlit.api.properties;

import org.springframework.stereotype.Component;

/**
 * Created by rob on 11/3/16.
 */
@Component
public final class PropertyManager {
    private static ApiProperties apiProperties;

    PropertyManager(ApiProperties properties) {
        apiProperties = properties;
    }

    public static ApiProperties props() {
        return apiProperties;
    }
}
