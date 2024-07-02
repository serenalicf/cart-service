package online.shopping.system.cart_service.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import online.shopping.system.cart_service.exception.ExceptionCode;
import org.apache.logging.log4j.message.ParameterizedMessage;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ExceptionCode {
    CUSTOMER_NOT_FOUND("C0001","customer with id : {} is not found"),

    SYSTEM_BUSY("C0002","System busy"),

    CART_NOT_FOUND("C0003","cart with id : {} is not found"),

    PRODUCT_NOT_FOUND("C004", "product with product code : {} is not found"),

    INSUFFICIENT_PRODUCT("C005", "product with product code : {} is not sufficient");

    private final String errorCode;
    private final String message;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage(Object... arguments) {
        if(arguments == null){
            return message;
        }
        return new ParameterizedMessage(message, arguments).getFormattedMessage();
    }
}
