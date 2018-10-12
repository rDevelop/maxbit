package ItBit;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import ItBit.model.OrderBook;

/**
 * Created by rob on 4/6/17.
 */
public class RestTemplateTest {

    @Test
    public void testObjectEntity() {
        String url = "https://beta-api.itbit.com/v1/markets/XBTUSD/order_book";
        url = "https://api.itbit.com/v1/markets/XBTUSD/order_book";
        RestTemplate restTemplate = new RestTemplate();
        //String result =  restTemplate.getForObject(url, String.class);

        OrderBook orderBook = restTemplate.getForObject(url, OrderBook.class);

        System.out.println(orderBook.toString());

    }
}
