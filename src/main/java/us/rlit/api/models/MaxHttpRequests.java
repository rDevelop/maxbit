package us.rlit.api.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by rob on 2/11/17.
 */
public class MaxHttpRequests extends MaxResponseErrorManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public <T> T getExchangeResponse(String url, Class<T> genericType) throws HttpStatusCodeException {
        MaxHttpRequest itBitEntity = new MaxHttpRequest(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = restTemplate.getForEntity(url, genericType, itBitEntity.getHttpEntity(), itBitEntity.getUriVariables());
        return response.getBody();

    }

    public final <T> List<T> getExchangeListResponse(String url, final Class<T> generic) throws HttpStatusCodeException {
        MaxHttpRequest itBitEntity = new MaxHttpRequest(url);
        ParameterizedTypeReference<List<T>> typeRef = new ParameterizedTypeReference<List<T>>() {
        };
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<T>> response = restTemplate.exchange(
                url, HttpMethod.GET, itBitEntity.getHttpEntity(), typeRef, typeRef, itBitEntity.getUriVariables());


        logger.info("Response " + response);
        logger.info("Response body " + response.getBody());
        return response.getBody();
    }

//todo remove these methods once restTemplate proves to work for the entire API.
//    public MaxHttpResponse sendHttpGet(String fullUrl, String signature, String nonce, String timeStamp) throws IOException {
//
//        //testHttp(fullUrl, signature, nonce, timeStamp);
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(fullUrl);
//
//        request.setHeader("Authorization", signature);
//        request.setHeader("x-Auth-Nonce", nonce);
//        request.setHeader("x-Auth-Timestamp", timeStamp);
//        request.setHeader("ItBitService-Type", "application/json");
//
//        logger.info("Sending request");
//        HttpResponse response = client.execute(request);
//        int statusCode = response.getStatusLine().getStatusCode();
//        logger.info("Response code: " + statusCode);
//        StringBuffer result = new StringBuffer();
//        try (
//                BufferedReader rd = new BufferedReader(
//                        new InputStreamReader(response.getEntity().getContent()))) {
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            rd.close();
//        }
//        return new MaxHttpResponse(statusCode, result);
//    }

//    public MaxHttpResponse getHttpResult(String url) {
//
//        String nonce = getNonce().toString();
//        String timeStamp = getTimeStamp();
//        MaxHttpEntity maxHttpEntity = new MaxHttpEntity(nonce, timeStamp, null, "GET", url);
//        String signature = getItBitSignature(maxHttpEntity);
//        MaxHttpResponse httpResponse = null;
//        try {
//            httpResponse = new MaxHttpRequests().sendHttpGet(url, signature, nonce, timeStamp);
//        } catch (IOException e) {
//            logger.warn(e.toString());
//        }
//        return httpResponse;
//    }


}