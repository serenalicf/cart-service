package online.shopping.system.cart_service.helper;

import java.net.http.HttpHeaders;

public interface RestHelper {

    public <T>T getObject(String url, Class<T> response);

    public <T>T postObject(String url, String request, Class<T> response);

}
