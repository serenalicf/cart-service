package online.shopping.system.cart.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class CartDto {

    private int cartId;
    private BigDecimal totalPrice;
    private int customerId;
    private List<CartItemDto> cartItemList;

}
