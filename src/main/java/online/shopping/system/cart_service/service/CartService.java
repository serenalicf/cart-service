package online.shopping.system.cart_service.service;

import online.shopping.system.cart_service.dto.AddCartItemResponseDto;
import online.shopping.system.cart_service.dto.CreateCartItemRequestDTO;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.exception.BusinessException;

public interface CartService {

    Cart createCart(String customerId) throws BusinessException;

    Cart getLatestCart(String customerId) throws BusinessException;

    AddCartItemResponseDto addItemToCart(String customerId, String cartId, CreateCartItemRequestDTO createCartItemRequestDTO) throws BusinessException;
}
