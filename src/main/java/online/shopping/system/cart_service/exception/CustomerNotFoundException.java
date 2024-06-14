package online.shopping.system.cart_service.exception;

import online.shopping.system.cart_service.exception.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super(ErrorCode.CUSTOMER_NOT_FOUND.getMessage().replace("{}", customerId));
    }
}
