package us.rlit.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * Created by rob on 2/22/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaxResponseError implements MaxResponseErrorHandler {

    @Id
    private String code;
    private String description;
    private String requestId;
    private MaxValidationError[] validationErrors;

    @Version
    private Integer version;

    @JsonIgnore
    public MaxResponseError(@JsonProperty("code") String code,
                            @JsonProperty("description") String description,
                            @JsonProperty("requestId") String requestId,
                            @JsonProperty("validationErrors") MaxValidationError[] validationErrors)
    {
        this.code = code;
        this.description = description;
        this.requestId = requestId;
        this.validationErrors = validationErrors;
    }

    public MaxResponseError(String code,
                            String description,
                            String requestId)
    {
        this.code = code;
        this.description = description;
        this.requestId = requestId;
    }

    // Allow the error to be set manually.
    @Override
    public void setResponseError(String code, String description, String requestId) {
        this.code = code;
        this.description = description;
        this.requestId = requestId;
        this.validationErrors = new MaxValidationError[]{};
    }

    @Override
    public String getCode() {return code;}

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String getDescription() {return description;}

    @Override
    public String getRequestId() {return requestId;}

    public MaxValidationError[] getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(MaxValidationError[] validationErrors) {
        this.validationErrors = validationErrors;
    }

    @Override
    public String toString(){
        String s =  code + ": " + description + " requestId: " + requestId;
        if( validationErrors != null && validationErrors.length > 0 ) {
            s += " valErrors: ";
            for (MaxValidationError maxValidationError : validationErrors) {
                s += maxValidationError.toString();
            }
        }
        return s;
    }
}
