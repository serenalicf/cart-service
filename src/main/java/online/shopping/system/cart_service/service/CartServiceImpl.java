package online.shopping.system.cart_service.service;

import online.shopping.system.cart_service.dto.CartDto;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.mapper.CartMapper;
import online.shopping.system.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart createCart(String customerId) {
        //create an empty cart
        Cart cart = Cart.builder().customerId(customerId).totalPrice(BigDecimal.valueOf(0.0)).items(new ArrayList<>()).build();
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public CartDto getLatestCart(String customerId) {
        return null;
    }
}
