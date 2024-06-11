package online.shopping.system.cart_service.service;

import online.shopping.system.cart_service.dto.CartDto;

public interface CartService {

    CartDto createCart(String customerId);

    CartDto getCart(String customerId);
}
