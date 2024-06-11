package online.shopping.system.cart_service.mapper;

import online.shopping.system.cart_service.dto.CartDto;
import online.shopping.system.cart_service.dto.CartItemDto;
import online.shopping.system.cart_service.entity.Cart;
import online.shopping.system.cart_service.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    CartDto cartToCartDto(Cart cart);
    CartItemDto cartItemToCartItemDto(CartItem cartItem);

}
