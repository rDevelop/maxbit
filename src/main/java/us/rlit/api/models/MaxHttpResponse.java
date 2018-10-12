package us.rlit.rest;

/**
 * Created by rob on 2/22/17.
 */
public class MaxHttpResponse {
    private int code;
    private StringBuffer response;
    public MaxHttpResponse() {}
    public MaxHttpResponse(int code, StringBuffer response) {
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StringBuffer getResponse() {
        return response;
    }

    public void setResponse(StringBuffer response) {
        this.response = response;
    }
}
