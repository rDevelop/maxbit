package us.rlit.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * Created by rob on 2/22/17.
 */
//@Entity
public class MaxValidationError {

    @Id
    private String code;
    private String description;
    private String field;

    @Version
    private Integer version;


    public MaxValidationError(
            @JsonProperty("code") String code,
            @JsonProperty("description") String description,
            @JsonProperty("field") String field
    ){
        this.code = code;
        this.description = description;
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString(){
        return code + ": " + description  + ", " + "field: " + field;
    }
}
