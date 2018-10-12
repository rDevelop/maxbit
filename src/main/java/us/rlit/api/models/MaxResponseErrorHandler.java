package us.rlit.api.models;

/**
 * Created by rob on 2/26/17.
 */
public interface MaxResponseErrorHandler {
    String getCode();
    String getDescription();
    String getRequestId();
    void setResponseError(String code, String description, String requestId);
}
