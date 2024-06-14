package online.shopping.system.cart_service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestHelperImpl implements RestHelper {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public <T> T getObject(String url, Class<T> response) {
       return restTemplate.getForObject(url, response);
    }

    @Override
    public <T> T postObject(String url, String request, Class<T> response) {
       return restTemplate.postForObject(url, request, response);
    }
}
