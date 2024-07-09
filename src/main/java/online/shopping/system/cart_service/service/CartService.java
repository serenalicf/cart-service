package online.shopping.system.cart_service.service;

import online.shopping.system.cart_service.dto.CartModificationDto;
import online.shopping.system.cart_service.dto.CreateCartItemRequestDTO;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.exception.BusinessException;

public interface CartService {

    Cart createCart(Integer customerId) throws BusinessException;

    Cart getLatestCart(Integer customerId) throws BusinessException;

    CartModificationDto addItemToCart(Integer customerId, Integer cartId, CreateCartItemRequestDTO createCartItemRequestDTO) throws BusinessException;

    CartModificationDto updateCartItemQuantity(Integer customerId, Integer cartId, Integer itemId, Long quantity);
}
