package online.shopping.system.cart_service.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CUSTOMER_NOT_FOUND("C0001","customer with id : {} is not found"),

    SYSTEM_BUSY("C0002","System busy");

    private final String code;
    private final String message;

}
