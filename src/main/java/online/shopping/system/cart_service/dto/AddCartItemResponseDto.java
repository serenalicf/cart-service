package online.shopping.system.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCartItemResponseDto {

    private Integer itemId;
    private Integer quantity;
    private Integer quantityAdded;
    private String statusCode;
    private String errorCode;

}
