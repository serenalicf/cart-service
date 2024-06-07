package online.shopping.system.cart.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDto {

    private int productId;
    private String productCode;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private String availableItemCount;
    private String categoryId;

}
