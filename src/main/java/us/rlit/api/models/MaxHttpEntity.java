package us.rlit.api.models;

/**
 * Created by rob on 7/15/15.
 */
public class MaxHttpEntity {

    private String nonce;
    private String timestamp;
    private String body;
    private String verb;
    private String url;

    public MaxHttpEntity() {
        new MaxHttpEntity();
    }

    public MaxHttpEntity(String n, String t, String b, String v, String u) {
        nonce = n;
        timestamp = t;
        body = b;
        verb = v;
        url = u;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
