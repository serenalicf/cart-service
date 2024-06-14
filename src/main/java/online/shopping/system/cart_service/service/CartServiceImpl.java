package online.shopping.system.cart_service.service;

import lombok.extern.slf4j.Slf4j;
import online.shopping.system.cart_service.customer.dto.CustomerDto;
import online.shopping.system.cart_service.customer.service.CustomerService;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.exception.CustomerNotFoundException;
import online.shopping.system.cart_service.exception.constant.ErrorCode;
import online.shopping.system.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    CustomerService customerService;
    
    @Override
    public Cart createCart(String customerId) {
        //get latest cart
        Cart cart = getLatestCart(customerId);

        //if latest cart is empty , create an empty cart
        if(cart == null) {
            LocalDateTime currentTime = LocalDateTime.now();
            cart = Cart.builder()
                    .customerId(Integer.parseInt(customerId))
                    .totalPrice(BigDecimal.valueOf(0.0))
                    .items(new ArrayList<>())
                    .createdOn(currentTime)
                    .lastModifiedOn(currentTime)
                    .build();
            try {
                cartRepository.save(cart);
            } catch (Exception ex){
                log.error("System is busy.", ex);
                throw new RuntimeException(ErrorCode.SYSTEM_BUSY.getMessage());
            }
        }
        return cart;
    }

    @Override
    public Cart getLatestCart(String customerId) {
        try {
            //call customer api
            CustomerDto customer = customerService.getCustomer(customerId);
            if (customer == null) {
                throw new CustomerNotFoundException(customerId);
            }

            return cartRepository.findFirstByCustomerIdOrderByLastModifiedOnDesc(Integer.parseInt(customerId)).orElse(null);

        } catch (Exception ex ){
            throw new RuntimeException(ex);
        }
    }
}
