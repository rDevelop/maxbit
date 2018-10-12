package us.rlit.api.models;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import us.rlit.api.properties.ApiUtils;

import java.util.ArrayList;

/**
 * Created by rob on 3/2/17.
 */
public class MaxHttpRequest {
    private String url;
    private String nonce;
    private String timeStamp;
    private String signature;
    private ArrayList uriVariables;
    private MaxHttpEntity maxHttpEntity;
    private HttpEntity httpEntity;

    public MaxHttpRequest(String url) {
        this.url = url;
        setProtocols();
        settUriVariables();
    }

    public ArrayList getUriVariables() {
        if(uriVariables == null){
            return null;
        }
        return uriVariables;
    }

    public HttpEntity getHttpEntity() {
        if(httpEntity == null) {
            return null;
        }
        return httpEntity;
    }

    private void setProtocols() {
        nonce = ApiUtils.getNonce().toString();
        timeStamp = ApiUtils.getTimeStamp();
        maxHttpEntity = new MaxHttpEntity(nonce, timeStamp, null, "GET", url);
        signature = ApiUtils.getItBitSignature(maxHttpEntity);
        httpEntity = getHttpEntity(url, signature, nonce, timeStamp);
    }

    private void settUriVariables() {
        uriVariables = new ArrayList();
        uriVariables.add(signature);
        uriVariables.add(nonce);
        uriVariables.add(timeStamp);
    }

    private HttpEntity getHttpEntity(String fullUrl, String signature, String nonce, String timeStamp) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", signature);
        headers.set("x-Auth-Nonce", nonce);
        headers.set("x-Auth-Timestamp", timeStamp);
        headers.set("ItBitService-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }
}
