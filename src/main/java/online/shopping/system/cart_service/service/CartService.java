package online.shopping.system.cart_service.service;

import online.shopping.system.cart_service.dto.CartDto;
import online.shopping.system.cart_service.entity.Cart;

public interface CartService {

    Cart createCart(String customerId);

    Cart getLatestCart(String customerId) throws Exception;
}
