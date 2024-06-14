package online.shopping.system.cart_service.controller;

import online.shopping.system.cart_service.dto.CartDto;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.mapper.CartMapper;
import online.shopping.system.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}/carts")
    public CartDto createCart(@Validated @PathVariable("customerId") String customerId) throws Exception {
        Cart cart = cartService.createCart(customerId);
        return CartMapper.INSTANCE.cartToCartDto(cart);
    }

    @GetMapping("/{customerId}/latestcart")
    public CartDto getLatestCart(@Validated @PathVariable("customerId") String customerId) throws Exception {
        Cart cart = cartService.getLatestCart(customerId);
        return CartMapper.INSTANCE.cartToCartDto(cart);
    }

}
