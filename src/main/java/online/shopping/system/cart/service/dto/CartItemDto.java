package online.shopping.system.cart.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItemDto {

    private int itemId;
    private int quantity;
    private ProductDto product;

}
