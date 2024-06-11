package online.shopping.system.cart_service.controller;

import online.shopping.system.cart_service.dto.CartDto;
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
    public CartDto createCart(@Validated @PathVariable("customerId") String customerId) {
        return cartService.createCart(customerId);
    }

    @GetMapping("/{customerId}/latestcart")
    public CartDto getCart(@Validated @PathVariable("customerId") String customerId) {
        return cartService.getCart(customerId);
    }

}
