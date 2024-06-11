package online.shopping.system.cart_service.service;

import online.shopping.system.cart_service.dto.CartDto;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.mapper.CartMapper;
import online.shopping.system.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public CartDto createCart(String customerId) {
        Cart cart = Cart.builder().customerId(customerId).build();
        cartRepository.save(cart);
        return CartMapper.INSTANCE.cartToCartDto(cart);
    }

    @Override
    public CartDto getCart(String customerId) {
        return null;
    }
}
